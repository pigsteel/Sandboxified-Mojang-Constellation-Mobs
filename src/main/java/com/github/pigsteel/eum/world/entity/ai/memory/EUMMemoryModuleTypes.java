package com.github.pigsteel.eum.world.entity.ai.memory;

import com.github.pigsteel.eum.EUM;
import com.mojang.serialization.Codec;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;

import java.util.Optional;
import java.util.function.Supplier;

public class EUMMemoryModuleTypes {
	public static final Supplier<MemoryModuleType<Unit>> SUMMONING_COOLDOWN;
	public static final Supplier<MemoryModuleType<Unit>> SHOOTING_COOLDOWN;
	public static final Supplier<MemoryModuleType<Unit>> SOUL_BLAST_COOLDOWN;
	public static final Supplier<MemoryModuleType<Unit>> PENDING_SUMMON;

	private static <U> Supplier<MemoryModuleType<U>> register(String name, Optional<Codec<U>> codec) {
		return EUM.xplat().registerMemoryModuleType(name, codec);
	}

	private static <U> Supplier<MemoryModuleType<U>> register(String name) {
		return register(name, Optional.empty());
	}

	static {
		SUMMONING_COOLDOWN = register("necromancer_summoning_cooldown");
		SHOOTING_COOLDOWN = register("necromancer_shooting_cooldown");
		SOUL_BLAST_COOLDOWN = register("necromancer_soulblast_cooldown");
		PENDING_SUMMON = register("necromancer_pending_summon");
	}

	public static void load() {}
}
