package com.github.pigsteel.eum.core;

import com.github.pigsteel.eum.EUM;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

public class EUMLootTables {
	private EUMLootTables() {}

    public static ResourceKey<LootTable> FROSTBITTEN = ResourceKey.create(Registries.LOOT_TABLE, EUM.id("entities/frostbitten"));
    public static ResourceKey<LootTable> RECLAIMED = ResourceKey.create(Registries.LOOT_TABLE, EUM.id("entities/reclaimed"));
    public static ResourceKey<LootTable> ENCHANTER = ResourceKey.create(Registries.LOOT_TABLE, EUM.id("entities/enchanter"));
    public static ResourceKey<LootTable> SUNKEN = ResourceKey.create(Registries.LOOT_TABLE, EUM.id("entities/sunken"));
    public static ResourceKey<LootTable> BRUISER = ResourceKey.create(Registries.LOOT_TABLE, EUM.id("entities/bruiser"));
	public static ResourceKey<LootTable> VILER_WITCH = ResourceKey.create(Registries.LOOT_TABLE, EUM.id("entities/viler_witch"));
    public static ResourceKey<LootTable> SHEAR_RECLAIMED = ResourceKey.create(Registries.LOOT_TABLE, EUM.id("shearing/reclaimed"));
    public static ResourceKey<LootTable> SHEAR_SUNKEN = ResourceKey.create(Registries.LOOT_TABLE, EUM.id("shearing/sunken"));

    public static void load() {}
}
