package com.github.pigsteel.eum.core;

import com.github.pigsteel.eum.EUM;
import com.github.pigsteel.eum.world.entity.monster.skeleton.SunkenVariant;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public class EUMCustomRegistries {
    public static final ResourceKey<Registry<SunkenVariant>> SUNKEN_VARIANT;

	static {
		SUNKEN_VARIANT =
				ResourceKey.createRegistryKey(
						EUM.id("sunken/variant")
				);
	}

    public static void load() {
    }
}
