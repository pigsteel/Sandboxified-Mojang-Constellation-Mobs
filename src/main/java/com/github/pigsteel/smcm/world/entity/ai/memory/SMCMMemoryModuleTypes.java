package com.github.pigsteel.smcm.world.entity.ai.memory;

import com.github.pigsteel.smcm.SMCM;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;

import java.util.Optional;
import java.util.function.Supplier;

//? neoforge {
/*import static com.github.pigsteel.smcm.platform.neoforge.NeoforgeVariables.MEMORY_MODULE_TYPES;
*///?}

public class SMCMMemoryModuleTypes {
	public static final Supplier<MemoryModuleType<Unit>> SUMMONING_COOLDOWN;
	public static final Supplier<MemoryModuleType<Unit>> SHOOTING_COOLDOWN;
	public static final Supplier<MemoryModuleType<Unit>> SOUL_BLAST_COOLDOWN;
	public static final Supplier<MemoryModuleType<Unit>> PENDING_SUMMON;

	private static <U> Supplier<MemoryModuleType<U>> register(String name, Codec<U> codec) {
		//? fabric {
		var var10000 = Registry.register(BuiltInRegistries.MEMORY_MODULE_TYPE, SMCM.id(name), new MemoryModuleType<>(Optional.of(codec)));
		return () -> var10000;
		//?} neoforge {
		/*return MEMORY_MODULE_TYPES.register(name, () -> new MemoryModuleType<>(Optional.of(codec)));
		*///?}
	}

	private static <U> Supplier<MemoryModuleType<U>> register(String name) {
		//? fabric {
		var var10000 = Registry.register(BuiltInRegistries.MEMORY_MODULE_TYPE, SMCM.id(name), new MemoryModuleType<U>(Optional.empty()));
		return () -> var10000;
		//?} neoforge {
		/*return MEMORY_MODULE_TYPES.register(name, () -> new MemoryModuleType<>(Optional.empty()));
		*///?}
	}

	static {
		SUMMONING_COOLDOWN = register("necromancer_summoning_cooldown");
		SHOOTING_COOLDOWN = register("necromancer_shooting_cooldown");
		SOUL_BLAST_COOLDOWN = register("necromancer_soulblast_cooldown");
		PENDING_SUMMON = register("necromancer_pending_summon");
	}

	public static void load() {}
}
