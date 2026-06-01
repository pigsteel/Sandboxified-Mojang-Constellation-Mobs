package com.github.pigsteel.smcm.registry;

import com.github.pigsteel.smcm.entity.skeleton.SunkenVariant;
import com.github.pigsteel.smcm.entity.skeleton.SunkenVariants;
import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.minecraft.core.Holder;

public class FabricRegistries {
    public static void init() {
        DynamicRegistries.registerSynced(smcm$Registries.SUNKEN_VARIANT, SunkenVariant.DIRECT_CODEC, SunkenVariant.NETWORK_CODEC);
    }
}
