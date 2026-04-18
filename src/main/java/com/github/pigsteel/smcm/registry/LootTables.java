package com.github.pigsteel.smcm.registry;

import com.github.pigsteel.smcm.SMCM;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

public class LootTables {
    public static ResourceKey<LootTable> FROSTBITTEN = ResourceKey.create(Registries.LOOT_TABLE, Identifier.fromNamespaceAndPath(SMCM.MOD_ID, "entities/frostbitten"));
    public static ResourceKey<LootTable> RECLAIMED = ResourceKey.create(Registries.LOOT_TABLE, Identifier.fromNamespaceAndPath(SMCM.MOD_ID, "entities/reclaimed"));

    public static void initialize() {}
}
