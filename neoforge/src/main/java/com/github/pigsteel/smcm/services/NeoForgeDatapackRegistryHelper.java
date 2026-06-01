package com.github.pigsteel.smcm.services;

import com.github.pigsteel.smcm.SMCM;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;

public final class NeoForgeDatapackRegistryHelper implements IDatapackRegistryHelper {
    @Override
    public <T> void registerDatapackRegistry(ResourceKey<Registry<T>> key, Codec<T> directCodec, Codec<T> networkCodec) {

    }
}
