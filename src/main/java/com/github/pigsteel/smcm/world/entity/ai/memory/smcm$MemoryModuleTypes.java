package com.github.pigsteel.smcm.world.entity.ai.memory;

import com.github.pigsteel.smcm.SMCM;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

//? neoforge {
/*import static com.github.pigsteel.smcm.platform.neoforge.NeoforgeVariables.MEMORY_MODULE_TYPES;
*///?}

public class smcm$MemoryModuleTypes {
	public static final Supplier<MemoryModuleType<Unit>> SUMMONING_COOLDOWN;

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
		SUMMONING_COOLDOWN = register("summoning_cooldown");
	}

	public static void init() {}
}
