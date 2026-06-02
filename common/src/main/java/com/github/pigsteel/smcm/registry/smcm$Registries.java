package com.github.pigsteel.smcm.registry;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.entity.skeleton.SunkenVariant;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

public class smcm$Registries {
    public static final ResourceKey<Registry<SunkenVariant>> SUNKEN_VARIANT =
            ResourceKey.createRegistryKey(
                    Identifier.fromNamespaceAndPath(SMCM.MOD_ID, "sunken_variant")
            );


    public static void load() {}
}
