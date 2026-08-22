package com.github.pigsteel.smcm.core;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.core.particles.CustomSimpleParticleType;
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import net.minecraft.client.particle.EndRodParticle;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;

import java.util.function.Supplier;

public class smcm$ParticleTypes {
	public static final Supplier<SimpleParticleType> NECROMANCER_MAGIC = register("necromancer_magic", false);

	private static Supplier<SimpleParticleType> register(final String name, final boolean overrideLimiter) {
		SimpleParticleType var10000 = Registry.register(BuiltInRegistries.PARTICLE_TYPE, SMCM.id(name), new CustomSimpleParticleType(overrideLimiter));
		return () -> var10000;
	}

	public static void load() {}

	public static void clientLoad() {
		//? fabric {
		ParticleProviderRegistry.getInstance().register(NECROMANCER_MAGIC.get(), EndRodParticle.Provider::new);
		//?}
	}
}
