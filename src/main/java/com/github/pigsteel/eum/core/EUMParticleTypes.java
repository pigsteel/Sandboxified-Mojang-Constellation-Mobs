package com.github.pigsteel.eum.core;

import com.github.pigsteel.eum.EUM;
import net.minecraft.client.particle.EndRodParticle;
import net.minecraft.core.particles.SimpleParticleType;

import java.util.function.Supplier;

//? fabric {
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
//?}

public class EUMParticleTypes {
	public static final Supplier<SimpleParticleType> NECROMANCER_MAGIC = register("necromancer_magic", false);

	private static Supplier<SimpleParticleType> register(final String name, final boolean overrideLimiter) {
		return EUM.xplat().register(name, overrideLimiter);
	}

	public static void load() {}

	public static void clientLoad() {
		//? fabric {
		ParticleProviderRegistry.getInstance().register(NECROMANCER_MAGIC.get(), EndRodParticle.Provider::new);
		//?}
	}
}
