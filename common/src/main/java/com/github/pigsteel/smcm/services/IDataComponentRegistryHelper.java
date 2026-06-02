package com.github.pigsteel.smcm.services;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.function.Supplier;

public interface IDataComponentRegistryHelper {
    <T extends LootItemCondition> Supplier<MapCodec<T>> registerLootConditionType(
            String name,
            MapCodec<T> codec
    );
}
