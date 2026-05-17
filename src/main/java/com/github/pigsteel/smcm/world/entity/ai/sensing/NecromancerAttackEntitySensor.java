package com.github.pigsteel.smcm.world.entity.ai.sensing;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.sensing.NearestVisibleLivingEntitySensor;

public class NecromancerAttackEntitySensor extends NearestVisibleLivingEntitySensor {
	public NecromancerAttackEntitySensor() {
		super();
	}

	@Override
	protected boolean isMatchingEntity(ServerLevel level, LivingEntity body, LivingEntity mob) {
		return body.canAttack(mob) && mob.closerThan(body, 32.0);
	}

	@Override
	protected MemoryModuleType<LivingEntity> getMemoryToSet() {
		return MemoryModuleType.NEAREST_ATTACKABLE;
	}
}
