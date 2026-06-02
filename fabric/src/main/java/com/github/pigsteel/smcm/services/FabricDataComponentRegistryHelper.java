package com.github.pigsteel.smcm.services;

import com.github.pigsteel.smcm.SMCM;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.function.Supplier;

public class FabricDataComponentRegistryHelper implements IDataComponentRegistryHelper {




    @Override
    public <T extends LootItemCondition> Supplier<MapCodec<T>> registerLootConditionType(String name, MapCodec<T> codec) {
        /*
        return Registry.register(
                Registries.LOOT_CONDITION_TYPE,
                Identifier.fromNamespaceAndPath(SMCM.MOD_ID, name)),
                new LootItemCondition()
        );

         */
        return null;
    }
}
