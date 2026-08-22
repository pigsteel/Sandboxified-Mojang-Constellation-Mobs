package com.github.pigsteel.smcm.core;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.world.entity.monster.skeleton.SunkenVariant;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import org.apache.commons.lang3.NotImplementedException;
//? neoforge {
/*import net.neoforged.neoforge.registries.RegistryBuilder;
*///?}

public class SMCMCustomRegistries {
    public static final ResourceKey<Registry<SunkenVariant>> SUNKEN_VARIANT =
            ResourceKey.createRegistryKey(
                    SMCM.id("sunken/variant")
            );

	public static <T> Registry<T> registerSynced(ResourceKey<Registry<T>> key) {
		//? neoforge {
		/*return new RegistryBuilder<T>(key).sync(true).create();
		*///?} fabric {
		throw new NotImplementedException();
		//?}
	}

    public static void load() {
    }
}
