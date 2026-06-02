package com.github.pigsteel.smcm.services;

import com.github.pigsteel.smcm.SMCM;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class NeoForgeDataComponentRegistryHelper implements IDataComponentRegistryHelper {
    public static final DeferredRegister<MapCodec<? extends LootItemCondition>> LOOT_CONDITION_TYPES =
            DeferredRegister.create(Registries.LOOT_CONDITION_TYPE, SMCM.MOD_ID);

    @Override
    public <T extends LootItemCondition> Supplier<MapCodec<T>> registerLootConditionType(String name, MapCodec<T> codec) {
        return LOOT_CONDITION_TYPES.register(name, () -> codec);
    }
}
