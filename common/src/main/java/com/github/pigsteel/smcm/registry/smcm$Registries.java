package com.github.pigsteel.smcm.registry;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.entity.skeleton.SunkenVariant;
import com.github.pigsteel.smcm.services.Services;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.variant.ModelAndTexture;
import net.minecraft.world.entity.variant.SpawnPrioritySelectors;

public class smcm$Registries {
    public static final ResourceKey<Registry<SunkenVariant>> SUNKEN_VARIANT =
            ResourceKey.createRegistryKey(
                    Identifier.fromNamespaceAndPath(SMCM.MOD_ID, "sunken_variant")
            );


    public static void init() {}
}
