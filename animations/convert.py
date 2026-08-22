import argparse
import re
from pathlib import Path


ANIMATION_RE = re.compile(
	r'public\s+static\s+final\s+Animation\s+(\w+)\s*='
	r'\s*Animation\.Builder\.create\(\s*([^)]*?)\s*\)'
)

BONE_ANIMATION_START_RE = re.compile(
	r'\.addBoneAnimation\(\s*"([^"]+)"\s*,\s*'
	r'new\s+Transformation\('
)


KEYFRAME_RE = re.compile(
	r'new\s+Keyframe\(\s*'
	r'([^,]+?)\s*,\s*'
	r'AnimationHelper\.create(Rotational|Translational)Vector\(\s*'
	r'([^,]+?)\s*,\s*'
	r'([^,]+?)\s*,\s*'
	r'([^)]+?)\s*\)\s*,\s*'
	r'Transformation\.Interpolations\.([A-Z_]+)'
	r'\s*\)'
)


def find_matching_paren(text, opening_index):
	"""
	Given the index of an opening '(',
	return the index of its matching ')'.
	"""
	depth = 0

	for i in range(opening_index, len(text)):
		char = text[i]

		if char == '(':
			depth += 1

		elif char == ')':
			depth -= 1

			if depth == 0:
				return i

	raise ValueError(
		f"No matching ')' found for '(' at index {opening_index}"
	)

def create_left_handed_variant(animation):
	"""
	Create a left-handed version of an animation.

	- Swaps right_arm <-> left_arm
	- Negates Y and Z rotation values
	- Leaves X rotation unchanged
	- Leaves translations unchanged
	- Appends _LEFT to the animation name
	"""

	left = {
		"name": animation["name"] + "_LEFT",
		"length": animation["length"],
		"animations": [],
	}

	for channel in animation["animations"]:
		bone = channel["bone"]

		if bone == "right_arm":
			bone = "left_arm"
		elif bone == "left_arm":
			bone = "right_arm"

		if bone == "right_leg":
			bone = "left_leg"
		elif bone == "left_leg":
			bone = "right_leg"

		new_channel = {
			"bone": bone,
			"target": channel["target"],
			"keyframes": [],
		}

		for keyframe in channel["keyframes"]:
			new_keyframe = keyframe.copy()

			# Only mirror rotations.
			if (
				channel["target"] == "ROTATE"
				and keyframe["vector"] == "Rotational"
			):
				new_keyframe["y"] = negate_java_float(
					keyframe["y"]
				)

				new_keyframe["z"] = negate_java_float(
					keyframe["z"]
				)

			new_channel["keyframes"].append(
				new_keyframe
			)

		left["animations"].append(new_channel)

	return left

def negate_java_float(value):
	"""
	Negate a Java floating-point literal while preserving
	its textual representation as much as possible.
	"""

	value = value.strip()

	if value.startswith("-"):
		return value[1:]

	if value.startswith("+"):
		return "-" + value[1:]

	if value == "0.0F":
		return "0.0F"

	if value == "0F":
		return "0F"

	return "-" + value

def extract_add_bone_animation(text, start):
	"""
	Extract one complete .addBoneAnimation(...) call.

	Returns:
		(end_index, bone_name, target, transformation_body)

	or None if no animation starts at `start`.
	"""

	match = BONE_ANIMATION_START_RE.match(text, start)

	if match is None:
		return None

	bone_name = match.group(1)

	# We are currently positioned immediately after
	# "new Transformation("
	transformation_opening = (
		match.end() - 1
	)

	transformation_closing = find_matching_paren(
		text,
		transformation_opening
	)

	transformation_body = text[
		transformation_opening + 1:
		transformation_closing
	]

	# Extract the target from the beginning of the Transformation.
	target_match = re.match(
		r'\s*Transformation\.Targets\.(ROTATE|TRANSLATE)\s*,',
		transformation_body
	)

	if target_match is None:
		raise ValueError(
			f"Could not find transformation target "
			f"for bone '{bone_name}'"
		)

	target = target_match.group(1)

	# Find the opening parenthesis of addBoneAnimation().
	add_opening = text.find(
		"(",
		start
	)

	add_closing = find_matching_paren(
		text,
		add_opening
	)

	return (
		add_closing + 1,
		bone_name,
		target,
		transformation_body
	)


def parse_keyframes(body):
	keyframes = []

	for match in KEYFRAME_RE.finditer(body):
		keyframes.append({
			"time": match.group(1).strip(),
			"vector": match.group(2),
			"x": match.group(3).strip(),
			"y": match.group(4).strip(),
			"z": match.group(5).strip(),
			"interpolation": match.group(6),
		})

	return keyframes


def parse_bone_animations(body):
	animations = []

	# Find every occurrence of .addBoneAnimation(
	# instead of trying to match the entire nested expression with regex.
	positions = [
		match.start()
		for match in re.finditer(
			r'\.addBoneAnimation\(',
			body
		)
	]

	for position in positions:
		result = extract_add_bone_animation(
			body,
			position
		)

		if result is None:
			continue

		(
			end,
			bone_name,
			target,
			transformation_body
		) = result

		keyframes = parse_keyframes(
			transformation_body
		)

		animations.append({
			"bone": bone_name,
			"target": target,
			"keyframes": keyframes,
		})

	return animations


def parse_animations(source):
	animations = []

	for match in ANIMATION_RE.finditer(source):
		name = match.group(1)
		length = match.group(2).strip()

		# Find the .build() belonging to this animation.
		build_index = source.find(
			".build();",
			match.end()
		)

		if build_index == -1:
			raise ValueError(
				f"Could not find .build(); for animation '{name}'"
			)

		body = source[
			match.end():
			build_index
		]

		bone_animations = parse_bone_animations(
			body
		)

		animations.append({
			"name": name,
			"length": length,
			"animations": bone_animations,
		})

	return animations


def convert_target(target):
	if target == "ROTATE":
		return "ROTATION"

	if target == "TRANSLATE":
		return "POSITION"

	raise ValueError(
		f"Unknown transformation target: {target}"
	)


def convert_vector(vector):
	if vector == "Rotational":
		return "degreeVec"

	if vector == "Translational":
		return "posVec"

	raise ValueError(
		f"Unknown vector type: {vector}"
	)


def generate_keyframe(keyframe, interpolation_override=None):
	vector = convert_vector(
		keyframe["vector"]
	)

	interpolation = (
		interpolation_override
		if interpolation_override
		else keyframe["interpolation"]
	)

	return (
		f'new Keyframe('
		f'{keyframe["time"]}, '
		f'{vector}('
		f'{keyframe["x"]}, '
		f'{keyframe["y"]}, '
		f'{keyframe["z"]}'
		f'), '
		f'Interpolations.{interpolation})'
	)


def generate_animation(
	animation,
	interpolation_override=None
):
	name = animation["name"].upper()
	length = animation["length"]

	lines = [
		f'public static final AnimationDefinition {name} = '
		f'Builder.withLength({length})'
	]

	for animation_channel in animation["animations"]:
		bone = animation_channel["bone"]
		target = convert_target(
			animation_channel["target"]
		)

		lines.append(
			f'       .addAnimation("{bone}", '
			f'new AnimationChannel(Targets.{target},'
		)

		keyframes = animation_channel["keyframes"]

		for i, keyframe in enumerate(keyframes):
			comma = "," if i < len(keyframes) - 1 else ""

			generated = generate_keyframe(
				keyframe,
				interpolation_override
			)

			lines.append(
				f"             {generated}{comma}"
			)

		lines.append("       ))")

	lines.append("       .build();")

	return "\n".join(lines)


def main():
	parser = argparse.ArgumentParser(
		description=(
			"Convert Blockbench 1.17 Java animations "
			"to the modern AnimationDefinition format."
		)
	)

	parser.add_argument(
		"input",
		type=Path
	)

	parser.add_argument(
		"-o",
		"--output",
		type=Path
	)

	parser.add_argument(
		"--interpolation",
		choices=[
			"LINEAR",
			"CATMULLROM",
			"CONSTANT",
		],
		default=None,
		help=(
			"Override interpolation for every keyframe. "
			"By default the original interpolation is preserved."
		)
	)

	args = parser.parse_args()

	source = args.input.read_text(
		encoding="utf-8"
	)

	animations = parse_animations(source)

	if not animations:
		raise SystemExit(
			"No Blockbench animations were found."
		)

	all_animations = []

	for animation in animations:
		all_animations.append(animation)
		all_animations.append(
			create_left_handed_variant(animation)
		)

	output = "\n\n\n".join(
		generate_animation(
			animation,
			args.interpolation
		)
		for animation in all_animations
	)

	if args.output:
		args.output.write_text(
			output + "\n",
			encoding="utf-8"
		)

		print(
			f"Converted {len(animations)} animation(s) "
			f"to {args.output}"
		)
	else:
		print(output)


if __name__ == "__main__":
	main()
