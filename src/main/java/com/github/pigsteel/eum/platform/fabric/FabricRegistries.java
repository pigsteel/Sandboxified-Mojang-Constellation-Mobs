package com.github.pigsteel.eum.platform.fabric;

//? fabric {

import com.github.pigsteel.eum.core.EUMCustomRegistries;
import com.github.pigsteel.eum.world.entity.monster.skeleton.SunkenVariant;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;

public class FabricRegistries {
    public static void init() {
        DynamicRegistries.registerSynced(EUMCustomRegistries.SUNKEN_VARIANT, SunkenVariant.DIRECT_CODEC, SunkenVariant.NETWORK_CODEC);
    }
}
//?}
