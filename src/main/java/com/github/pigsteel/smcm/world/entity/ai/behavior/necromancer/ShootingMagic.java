package com.github.pigsteel.smcm.world.entity.ai.behavior.necromancer;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.world.entity.ai.memory.smcm$MemoryModuleTypes;
import com.github.pigsteel.smcm.world.entity.monster.necromancer.Necromancer;
import com.google.common.collect.ImmutableMap;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;

public class ShootingMagic<E extends Necromancer> extends Behavior<E> {
	public ShootingMagic() {
		super(ImmutableMap.of(MemoryModuleType.ATTACK_TARGET, MemoryStatus.VALUE_PRESENT, MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT, smcm$MemoryModuleTypes.SUMMONING_COOLDOWN.get(), MemoryStatus.VALUE_ABSENT), 10);
	}

	protected boolean checkExtraStartConditions(ServerLevel level, E body) {
		LivingEntity attackTarget = getAttackTarget(body);
		return BehaviorUtils.canSee(body, attackTarget);
	}

	private static LivingEntity getAttackTarget(LivingEntity body) {
		return body.getBrain().getMemory(MemoryModuleType.ATTACK_TARGET).get();
	}

	@Override
	public void tick(final ServerLevel level, final E body, final long timestamp) {

	}
}
