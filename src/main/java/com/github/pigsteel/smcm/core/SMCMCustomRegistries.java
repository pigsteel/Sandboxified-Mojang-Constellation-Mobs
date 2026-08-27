package com.github.pigsteel.smcm.core;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.world.entity.monster.skeleton.SunkenVariant;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public class SMCMCustomRegistries {
    public static final ResourceKey<Registry<SunkenVariant>> SUNKEN_VARIANT;

	static {
		SUNKEN_VARIANT =
				ResourceKey.createRegistryKey(
						SMCM.id("sunken/variant")
				);
	}

    public static void load() {
    }
}
