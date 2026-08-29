package com.github.pigsteel.eum.core;

import com.github.pigsteel.eum.EUM;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
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

	private static <T extends Mob> SpawnEggItem registerSpawnEgg(Supplier<EntityType<T>> entity, Item.Properties properties) {
		//? >= 1.21.11 {
		return new SpawnEggItem(properties.spawnEgg(entity.get()));
		//?} < 1.21.11 {
		/*return new SpawnEggItem(entity.get(), 0, 0, properties);
		*///?}
	}

	static {
		BRUISER_SPAWN_EGG = register(
				"bruiser_spawn_egg",
				properties -> registerSpawnEgg(EUMEntityTypes.BRUISER, properties)
		);
		FROSTBITTEN_SPAWN_EGG = register(
				"frostbitten_spawn_egg",
				properties -> registerSpawnEgg(EUMEntityTypes.FROSTBITTEN, properties)
		);

		RECLAIMED_SPAWN_EGG = register(
				"reclaimed_spawn_egg",
				properties -> registerSpawnEgg(EUMEntityTypes.RECLAIMED, properties)
		);

		ENCHANTER_SPAWN_EGG = register(
				"enchanter_spawn_egg",
				properties -> registerSpawnEgg(EUMEntityTypes.ENCHANTER, properties)
		);

		SUNKEN_SPAWN_EGG = register(
				"sunken_spawn_egg",
				properties -> registerSpawnEgg(EUMEntityTypes.SUNKEN, properties)
		);

		LOST_SPAWN_EGG = register(
				"lost_spawn_egg",
				properties -> registerSpawnEgg(EUMEntityTypes.LOST, properties)
		);

		NECROMANCER_SPAWN_EGG = register(
				"necromancer_spawn_egg",
				properties -> registerSpawnEgg(EUMEntityTypes.NECROMANCER, properties)
		);

		ZOMBIFIED_PIGLIN_BRUTE_SPAWN_EGG = register(
				"zombified_piglin_brute_spawn_egg",
				properties -> registerSpawnEgg(EUMEntityTypes.ZOMBIFIED_PIGLIN_BRUTE, properties)
		);

		GEOMANCER_SPAWN_EGG = register(
				"geomancer_spawn_egg",
				properties -> registerSpawnEgg(EUMEntityTypes.GEOMANCER, properties)
		);

		ICEOLOGER_SPAWN_EGG = register(
				"iceologer_spawn_egg",
				properties -> registerSpawnEgg(EUMEntityTypes.ICEOLOGER, properties)
		);

		VILER_WITCH_SPAWN_EGG = register(
				"viler_witch_spawn_egg",
				properties -> registerSpawnEgg(EUMEntityTypes.VILER_WITCH, properties)
		);

		MOUNTAINEER_SPAWN_EGG = register(
				"mountaineer_spawn_egg",
				properties -> registerSpawnEgg(EUMEntityTypes.MOUNTAINEER, properties)
		);

		WINDCALLER_SPAWN_EGG = register(
				"windcaller_spawn_egg",
				properties -> registerSpawnEgg(EUMEntityTypes.WINDCALLER, properties)
		);

		REDSTONE_GOLEM_SPAWN_EGG = register(
				"redstone_golem_spawn_egg",
				properties -> registerSpawnEgg(EUMEntityTypes.REDSTONE_GOLEM, properties)
		);

		PIGLIN_FARMER_SPAWN_EGG = register(
				"piglin_farmer_spawn_egg",
				properties -> registerSpawnEgg(EUMEntityTypes.PIGLIN_FARMER, properties)
		);
	}

	public static void load() {}
}
