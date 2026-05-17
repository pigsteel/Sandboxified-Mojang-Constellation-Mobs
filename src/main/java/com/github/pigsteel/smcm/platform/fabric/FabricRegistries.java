package com.github.pigsteel.smcm.platform.fabric;

//? fabric {

import com.github.pigsteel.smcm.core.smcm$Registries;
import com.github.pigsteel.smcm.world.entity.monster.skeleton.SunkenVariant;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;

public class FabricRegistries {
    public static void init() {
        DynamicRegistries.registerSynced(smcm$Registries.SUNKEN_VARIANT, SunkenVariant.DIRECT_CODEC, SunkenVariant.NETWORK_CODEC);
    }
}
//?}
