package com.github.pigsteel.smcm.core;

import com.github.pigsteel.smcm.SMCM;
import net.minecraft.client.particle.EndRodParticle;
import net.minecraft.core.particles.SimpleParticleType;

import java.util.function.Supplier;

//? fabric {
/*import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
*///?}

public class SMCMParticleTypes {
	public static final Supplier<SimpleParticleType> NECROMANCER_MAGIC = register("necromancer_magic", false);

	private static Supplier<SimpleParticleType> register(final String name, final boolean overrideLimiter) {
		return SMCM.xplat().register(name, overrideLimiter);
	}

	public static void load() {}

	public static void clientLoad() {
		//? fabric {
		/*ParticleProviderRegistry.getInstance().register(NECROMANCER_MAGIC.get(), EndRodParticle.Provider::new);
		*///?}
	}
}
