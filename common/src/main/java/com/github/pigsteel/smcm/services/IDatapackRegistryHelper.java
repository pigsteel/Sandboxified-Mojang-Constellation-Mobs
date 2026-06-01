package com.github.pigsteel.smcm.services;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

public interface IDatapackRegistryHelper {
    <T> void registerDatapackRegistry(
            ResourceKey<Registry<T>> key,
            Codec<T> directCodec,
            Codec<T> networkCodec
    );
}
