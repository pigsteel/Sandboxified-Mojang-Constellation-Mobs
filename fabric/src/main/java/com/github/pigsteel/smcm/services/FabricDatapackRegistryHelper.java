package com.github.pigsteel.smcm.services;

import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

public class FabricDatapackRegistryHelper implements IDatapackRegistryHelper {
    @Override
    public <T> void registerDatapackRegistry(ResourceKey<Registry<T>> key, Codec<T> directCodec, Codec<T> networkCodec) {

    }
}
