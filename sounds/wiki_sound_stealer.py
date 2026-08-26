# download_wiki_sounds.py
#
# Install:
#   py -m pip install requests beautifulsoup4
#
# Run:
#   py download_wiki_sounds.py
#
# Or:
#   py download_wiki_sounds.py "https://minecraft.wiki/w/Dungeons:Redstone_Monstrosity"
#
# Dry run:
#   py download_wiki_sounds.py --dry-run

from __future__ import annotations

import argparse
import os
import re
import time
from pathlib import Path
from typing import Iterable
from urllib.parse import unquote, urljoin, urlparse

import requests
from bs4 import BeautifulSoup


DEFAULT_PAGE_URL = "https://minecraft.wiki/w/Dungeons:Iceologer"
API_URL = "https://minecraft.wiki/api.php"

AUDIO_EXTENSIONS = ".ogg"

HEADERS = {
	"User-Agent": "SMCM sound downloader / personal modding script"
}


def title_from_wiki_url(value: str) -> str:
	value = value.strip().strip('"').strip("'")

	parsed = urlparse(value)
	path = unquote(parsed.path)

	if "/wiki/" in path:
		return path.split("/wiki/", 1)[1]

	if "/w/" in path:
		return path.split("/w/", 1)[1]

	# If they passed the wiki title directly instead of a URL.
	if ":" in value and not value.startswith("http"):
		return value

	# Fallback for paths like /Dungeons:Redstone_Monstrosity
	if path and ":" in path:
		return path.lstrip("/")

	raise ValueError(f"Could not infer wiki title from URL or title: {value}")


def safe_filename(name: str) -> str:
	name = unquote(name)
	name = name.replace("File:", "")
	name = name.replace(" ", "_")
	name = re.sub(r"[\\/:*?\"<>|]+", "_", name)
	name = re.sub(r"_+", "_", name)
	return name.strip("._")


def api_get(session: requests.Session, **params) -> dict:
	params.setdefault("format", "json")
	params.setdefault("formatversion", "2")

	response = session.get(API_URL, params=params, timeout=30)
	response.raise_for_status()
	return response.json()


def parse_page(session: requests.Session, title: str) -> dict:
	return api_get(
		session,
		action="parse",
		page=title,
		prop="text|images|wikitext",
		redirects=1,
	)["parse"]


def extract_direct_audio_urls_from_html(page_html: str, base_url: str) -> set[str]:
	soup = BeautifulSoup(page_html, "html.parser")
	urls: set[str] = set()

	# <audio><source src="..."></audio>
	for tag in soup.select("audio source[src], audio[src]"):
		src = tag.get("src")
		if src:
			urls.add(urljoin(base_url, src))

	# Plain links to audio files.
	for tag in soup.select("a[href]"):
		href = tag.get("href")
		if not href:
			continue

		lower = href.lower()
		if any(ext in lower for ext in AUDIO_EXTENSIONS):
			urls.add(urljoin(base_url, href))

	return urls


def extract_audio_file_titles_from_images(parse_images: Iterable[str]) -> set[str]:
	titles: set[str] = set()

	for image_name in parse_images:
		lower = image_name.lower()
		if lower.endswith(AUDIO_EXTENSIONS):
			if image_name.startswith("File:"):
				titles.add(image_name)
			else:
				titles.add(f"File:{image_name}")

	return titles


def extract_resource_locations(text: str) -> set[str]:
	"""
	Finds likely Minecraft Dungeons sound resource-location strings, such as:
	  sfx_mob_rsMonstrosityDeath
	  bgm_mob_fifoBossLoop
	  bgm_env_generic2DArenaEnd
	"""
	matches = re.findall(r"\b(?:sfx|bgm)_[A-Za-z0-9_]+\b", text)
	return set(matches)


def extract_search_terms(title: str, page_html: str, wikitext: str) -> list[str]:
	soup = BeautifulSoup(page_html, "html.parser")
	visible_text = soup.get_text("\n")

	resource_locations = extract_resource_locations(visible_text + "\n" + wikitext)

	terms: list[str] = []

	# Good broad terms.
	readable_title = title.replace("Dungeons:", "").replace("_", " ")
	terms.append(readable_title)
	terms.append("geomancer")

	# Exact resource-location terms from the sound table.
	terms.extend(sorted(resource_locations))

	# Split camel-ish resource locations into useful search terms.
	for resource in resource_locations:
		# sfx_mob_rsMonstrosityDeath -> rsMonstrosityDeath
		tail = resource.split("_")[-1]
		terms.append(tail)

		# rsMonstrosityDeath -> rs Monstrosity Death
		spaced = re.sub(r"(?<!^)([A-Z])", r" \1", tail)
		terms.append(spaced)

	# De-dupe while preserving order.
	seen = set()
	unique_terms = []
	for term in terms:
		term = term.strip()
		if term and term.lower() not in seen:
			seen.add(term.lower())
			unique_terms.append(term)

	return unique_terms


def search_file_namespace(session: requests.Session, term: str, limit: int) -> set[str]:
	"""
	Search the MediaWiki File namespace.

	Namespace 6 == File:
	"""
	found: set[str] = set()

	queries = [
		f'"{term}"',
		term,
	]

	for query in queries:
		data = api_get(
			session,
			action="query",
			list="search",
			srnamespace=6,
			srsearch=query,
			srlimit=limit,
		)

		for result in data.get("query", {}).get("search", []):
			title = result.get("title", "")
			lower = title.lower()

			if title.startswith("File:") and lower.endswith(AUDIO_EXTENSIONS):
				found.add(title)

		if found:
			break

		time.sleep(0.25)

	return found


def resolve_file_urls(session: requests.Session, file_titles: Iterable[str]) -> dict[str, str]:
	"""
	Converts File:Something.ogg titles into direct upload URLs.
	"""
	titles = sorted(set(file_titles))
	resolved: dict[str, str] = {}

	# MediaWiki title query can take pipe-separated batches.
	batch_size = 25

	for i in range(0, len(titles), batch_size):
		batch = titles[i:i + batch_size]

		data = api_get(
			session,
			action="query",
			titles="|".join(batch),
			prop="imageinfo",
			iiprop="url|mime|size",
		)

		pages = data.get("query", {}).get("pages", [])

		for page in pages:
			title = page.get("title", "")
			image_info = page.get("imageinfo", [])

			if not title or not image_info:
				continue

			info = image_info[0]
			url = info.get("url")
			mime = info.get("mime", "")

			if not url:
				continue

			lower_url = url.lower()
			if mime.startswith("audio/") or lower_url.endswith(AUDIO_EXTENSIONS):
				resolved[title] = url

		time.sleep(0.25)

	return resolved


def filename_from_url(url: str) -> str:
	path = urlparse(url).path
	return safe_filename(Path(unquote(path)).name)


def download_file(
	session: requests.Session,
	url: str,
	output_path: Path,
	overwrite: bool,
	dry_run: bool,
) -> None:
	output_path.parent.mkdir(parents=True, exist_ok=True)

	if output_path.exists() and not overwrite:
		print(f"SKIP existing: {output_path.name}")
		return

	if dry_run:
		print(f"WOULD DOWNLOAD: {url}")
		print(f"          INTO: {output_path}")
		return

	with session.get(url, stream=True, timeout=60) as response:
		response.raise_for_status()

		with output_path.open("wb") as file:
			for chunk in response.iter_content(chunk_size=1024 * 128):
				if chunk:
					file.write(chunk)

	print(f"DOWNLOADED: {output_path.name}")


def main() -> None:
	parser = argparse.ArgumentParser(
		description="Download audio files from a Minecraft Wiki page."
	)

	parser.add_argument(
		"page_url",
		nargs="?",
		default=DEFAULT_PAGE_URL,
		help="Minecraft Wiki page URL.",
	)

	parser.add_argument(
		"-o",
		"--output",
		default="wiki_sounds",
		help="Output folder.",
	)

	parser.add_argument(
		"--dry-run",
		action="store_true",
		help="Show what would be downloaded without writing files.",
	)

	parser.add_argument(
		"--overwrite",
		action="store_true",
		help="Overwrite existing files.",
	)

	parser.add_argument(
		"--no-search",
		action="store_true",
		help="Only download directly embedded/linked audio; do not search File namespace.",
	)

	parser.add_argument(
		"--search-limit",
		type=int,
		default=25,
		help="Maximum File namespace results per search term.",
	)

	args = parser.parse_args()

	page_url = args.page_url
	title = title_from_wiki_url(page_url)
	output_dir = Path(args.output)

	session = requests.Session()
	session.headers.update(HEADERS)

	print(f"Page title: {title}")
	print("Fetching page metadata...")

	parsed = parse_page(session, title)
	page_html = parsed.get("text", "")
	parse_images = parsed.get("images", [])
	wikitext = parsed.get("wikitext", "")

	if isinstance(wikitext, dict):
		wikitext = wikitext.get("*", "")

	direct_urls = extract_direct_audio_urls_from_html(page_html, page_url)
	file_titles = extract_audio_file_titles_from_images(parse_images)

	print(f"Direct audio URLs found on page: {len(direct_urls)}")
	print(f"Audio File: titles found on page: {len(file_titles)}")

	if not args.no_search:
		terms = extract_search_terms(title, page_html, wikitext)

		print("\nSearching wiki File namespace with terms:")
		for term in terms:
			print(f"  - {term}")

		for term in terms:
			found = search_file_namespace(session, term, args.search_limit)
			if found:
				print(f"Found {len(found)} audio file(s) for search term: {term}")
				file_titles.update(found)

	resolved_file_urls = resolve_file_urls(session, file_titles)

	all_downloads: dict[str, str] = {}

	for url in direct_urls:
		all_downloads[filename_from_url(url)] = url

	for file_title, url in resolved_file_urls.items():
		all_downloads[safe_filename(file_title)] = url

	print(f"\nTotal downloadable audio files found: {len(all_downloads)}")

	if not all_downloads:
		print("No downloadable audio files were found.")
		print("This can happen when the wiki page lists sound names/placeholders but has no uploaded audio files.")
		return

	for filename, url in sorted(all_downloads.items()):
		download_file(
			session=session,
			url=url,
			output_path=output_dir / filename,
			overwrite=args.overwrite,
			dry_run=args.dry_run,
		)

	print(f"\nDone. Output folder: {output_dir.resolve()}")


if __name__ == "__main__":
	main()
