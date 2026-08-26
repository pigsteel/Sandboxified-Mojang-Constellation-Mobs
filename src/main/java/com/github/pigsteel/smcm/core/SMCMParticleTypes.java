package com.github.pigsteel.smcm.core;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.core.particles.CustomSimpleParticleType;
import net.minecraft.client.particle.EndRodParticle;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;

import java.util.function.Supplier;

//? fabric {
/*import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
*///?} neoforge {
import static com.github.pigsteel.smcm.platform.neoforge.NeoforgeVariables.PARTICLE_TYPES;
//?}


public class SMCMParticleTypes {
	public static final Supplier<SimpleParticleType> NECROMANCER_MAGIC = register("necromancer_magic", false);

	private static Supplier<SimpleParticleType> register(final String name, final boolean overrideLimiter) {
		//? fabric {
		/*SimpleParticleType var10000 = Registry.register(BuiltInRegistries.PARTICLE_TYPE, SMCM.id(name), new CustomSimpleParticleType(overrideLimiter));
		return () -> var10000;
		*///?} neoforge {
		return PARTICLE_TYPES.register(name, () -> new CustomSimpleParticleType(overrideLimiter));
		//?}
	}

	public static void load() {}

	public static void clientLoad() {
		//? fabric {
		/*ParticleProviderRegistry.getInstance().register(NECROMANCER_MAGIC.get(), EndRodParticle.Provider::new);
		*///?}
	}
}
