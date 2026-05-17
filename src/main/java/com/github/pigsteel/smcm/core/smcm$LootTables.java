package com.github.pigsteel.smcm.core;

import com.github.pigsteel.smcm.SMCM;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

public class smcm$LootTables {
    public static ResourceKey<LootTable> FROSTBITTEN = ResourceKey.create(Registries.LOOT_TABLE, SMCM.id("entities/frostbitten"));
    public static ResourceKey<LootTable> RECLAIMED = ResourceKey.create(Registries.LOOT_TABLE, SMCM.id("entities/reclaimed"));
    public static ResourceKey<LootTable> ENCHANTER = ResourceKey.create(Registries.LOOT_TABLE, SMCM.id("entities/enchanter"));
    public static ResourceKey<LootTable> SUNKEN = ResourceKey.create(Registries.LOOT_TABLE, SMCM.id("entities/sunken"));
    public static ResourceKey<LootTable> BRUISER = ResourceKey.create(Registries.LOOT_TABLE, SMCM.id("entities/bruiser"));
    public static ResourceKey<LootTable> SHEAR_RECLAIMED = ResourceKey.create(Registries.LOOT_TABLE, SMCM.id("shearing/reclaimed"));
    public static ResourceKey<LootTable> SHEAR_SUNKEN = ResourceKey.create(Registries.LOOT_TABLE, SMCM.id("shearing/sunken"));

    public static void load() {}
}
