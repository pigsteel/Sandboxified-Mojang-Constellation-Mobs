package com.github.pigsteel.eum.core;

import com.github.pigsteel.eum.EUM;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

import java.util.function.Function;
import java.util.function.Supplier;

public class EUMItems {
	private EUMItems() {}

	public static final Supplier<Item> BRUISER_SPAWN_EGG;

	public static final Supplier<Item> FROSTBITTEN_SPAWN_EGG;

	public static final Supplier<Item> RECLAIMED_SPAWN_EGG;

	public static final Supplier<Item> ENCHANTER_SPAWN_EGG;

	public static final Supplier<Item> SUNKEN_SPAWN_EGG;

	public static final Supplier<Item> LOST_SPAWN_EGG;

	public static final Supplier<Item> NECROMANCER_SPAWN_EGG;

	public static final Supplier<Item> ZOMBIFIED_PIGLIN_BRUTE_SPAWN_EGG;

	public static final Supplier<Item> GEOMANCER_SPAWN_EGG;

	public static final Supplier<Item> ICEOLOGER_SPAWN_EGG;

	public static final Supplier<Item> VILER_WITCH_SPAWN_EGG;

	public static final Supplier<Item> MOUNTAINEER_SPAWN_EGG;

	public static final Supplier<Item> WINDCALLER_SPAWN_EGG;

	public static final Supplier<Item> REDSTONE_GOLEM_SPAWN_EGG;

	public static final Supplier<Item> PIGLIN_FARMER_SPAWN_EGG;

	private static <T extends Item> Supplier<T> register(String name, Function<Item.Properties, T> itemFactory) {
		return EUM.xplat().register(name, itemFactory);
	}

	static {
		BRUISER_SPAWN_EGG = register(
				"bruiser_spawn_egg",
				properties -> new SpawnEggItem(properties.spawnEgg(EUMEntityTypes.BRUISER.get()))
		);
		FROSTBITTEN_SPAWN_EGG = register(
				"frostbitten_spawn_egg",
				properties -> new SpawnEggItem(properties.spawnEgg(EUMEntityTypes.FROSTBITTEN.get()))
		);

		RECLAIMED_SPAWN_EGG = register(
				"reclaimed_spawn_egg",
				properties -> new SpawnEggItem(properties.spawnEgg(EUMEntityTypes.RECLAIMED.get()))
		);

		ENCHANTER_SPAWN_EGG = register(
				"enchanter_spawn_egg",
				properties -> new SpawnEggItem(properties.spawnEgg(EUMEntityTypes.ENCHANTER.get()))
		);

		SUNKEN_SPAWN_EGG = register(
				"sunken_spawn_egg",
				properties -> new SpawnEggItem(properties.spawnEgg(EUMEntityTypes.SUNKEN.get()))
		);

		LOST_SPAWN_EGG = register(
				"lost_spawn_egg",
				properties -> new SpawnEggItem(properties.spawnEgg(EUMEntityTypes.LOST.get()))
		);

		NECROMANCER_SPAWN_EGG = register(
				"necromancer_spawn_egg",
				properties -> new SpawnEggItem(properties.spawnEgg(EUMEntityTypes.NECROMANCER.get()))
		);

		ZOMBIFIED_PIGLIN_BRUTE_SPAWN_EGG = register(
				"zombified_piglin_brute_spawn_egg",
				properties -> new SpawnEggItem(properties.spawnEgg(EUMEntityTypes.ZOMBIFIED_PIGLIN_BRUTE.get()))
		);

		GEOMANCER_SPAWN_EGG = register(
				"geomancer_spawn_egg",
				properties -> new SpawnEggItem(properties.spawnEgg(EUMEntityTypes.GEOMANCER.get()))
		);

		ICEOLOGER_SPAWN_EGG = register(
				"iceologer_spawn_egg",
				properties -> new SpawnEggItem(properties.spawnEgg(EUMEntityTypes.ICEOLOGER.get()))
		);

		VILER_WITCH_SPAWN_EGG = register(
				"viler_witch_spawn_egg",
				properties -> new SpawnEggItem(properties.spawnEgg(EUMEntityTypes.VILER_WITCH.get()))
		);

		MOUNTAINEER_SPAWN_EGG = register(
				"mountaineer_spawn_egg",
				properties -> new SpawnEggItem(properties.spawnEgg(EUMEntityTypes.MOUNTAINEER.get()))
		);

		WINDCALLER_SPAWN_EGG = register(
				"windcaller_spawn_egg",
				properties -> new SpawnEggItem(properties.spawnEgg(EUMEntityTypes.WINDCALLER.get()))
		);

		REDSTONE_GOLEM_SPAWN_EGG = register(
				"redstone_golem_spawn_egg",
				properties -> new SpawnEggItem(properties.spawnEgg(EUMEntityTypes.REDSTONE_GOLEM.get()))
		);

		PIGLIN_FARMER_SPAWN_EGG = register(
				"piglin_farmer_spawn_egg",
				properties -> new SpawnEggItem(properties.spawnEgg(EUMEntityTypes.PIGLIN_FARMER.get()))
		);
	}

	public static void load() {}
}
