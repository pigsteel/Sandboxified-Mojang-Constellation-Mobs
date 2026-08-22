package com.github.pigsteel.smcm.core;

import com.github.pigsteel.smcm.core.particles.CustomSimpleParticleType;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;

public class smcm$ParticleTypes {
	public static final SimpleParticleType ANGRY_VILLAGER = register("necromancer_magic", false);

	private static SimpleParticleType register(final String name, final boolean overrideLimiter) {
		return Registry.register(BuiltInRegistries.PARTICLE_TYPE, name, new CustomSimpleParticleType(overrideLimiter));
	}
}
