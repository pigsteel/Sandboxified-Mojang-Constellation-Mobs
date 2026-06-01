package com.github.pigsteel.smcm.registry;

import com.github.pigsteel.smcm.entity.skeleton.SunkenVariants;
import net.minecraft.core.RegistrySetBuilder;

public final class smcm$RegistryData {
    private smcm$RegistryData() {
    }

    public static RegistrySetBuilder createRegistrySetBuilder() {
        return new RegistrySetBuilder()
                .add(smcm$Registries.SUNKEN_VARIANT, SunkenVariants::bootstrap);
    }
}
