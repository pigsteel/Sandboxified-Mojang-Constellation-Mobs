package com.github.pigsteel.smcm.registry;

import com.github.pigsteel.smcm.entity.skeleton.SunkenVariant;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;

public class SMCMNeoForgeRegistries {
    @SubscribeEvent
    public static void registerDatapackRegistries(DataPackRegistryEvent.NewRegistry event) {
        event.dataPackRegistry(
                smcm$Registries.SUNKEN_VARIANT,
                SunkenVariant.DIRECT_CODEC,
                SunkenVariant.NETWORK_CODEC,
                builder -> builder.maxId(256)
        );
    }
}
