package com.github.pigsteel.eum.world.entity.ai.behavior.necromancer;

import com.github.pigsteel.eum.core.EUMSoundEvents;
import com.github.pigsteel.eum.world.entity.ai.memory.EUMMemoryModuleTypes;
import com.github.pigsteel.eum.world.entity.monster.necromancer.Necromancer;
import com.github.pigsteel.eum.world.entity.projectile.NecromancerBall;
import com.google.common.collect.ImmutableMap;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

public class ShootingMagic<E extends Necromancer> extends Behavior<E> {
	private State state;
	private int lifetimeInTicks;
	private final int READY_TO_FIRE = 10;
	private final int SHOOT_COOLDOWN_TICKS = 2;

	public ShootingMagic() {
		super(ImmutableMap.of(MemoryModuleType.ATTACK_TARGET, MemoryStatus.VALUE_PRESENT, EUMMemoryModuleTypes.SHOOTING_COOLDOWN.get(), MemoryStatus.VALUE_ABSENT, EUMMemoryModuleTypes.PENDING_SUMMON.get(), MemoryStatus.VALUE_ABSENT), 18);
	}

	@Override
	public boolean canStillUse(final ServerLevel level, final E body, final long timestamp) {
		Optional<LivingEntity> targetO = body.getBrain().getMemory(MemoryModuleType.ATTACK_TARGET);
		if(targetO.isEmpty()) return false;
		LivingEntity target = targetO.get();

		return target.isAlive() && body.canAttack(target) && BehaviorUtils.canSee(body, target);
	}

	protected boolean checkExtraStartConditions(ServerLevel level, E body) {
		LivingEntity attackTarget = body.getTarget();
		assert attackTarget != null;
		return !body.isCastingSpell() && BehaviorUtils.canSee(body, attackTarget) && body.closerThan(attackTarget, 16.0D);
	}

	@Override
	public void start(final ServerLevel level, final E body, final long timestamp) {
		body.setIsCastingSpell(Necromancer.NecromancerSpell.SHOOT);
		body.getBrain().eraseMemory(MemoryModuleType.LOOK_TARGET);
		body.getBrain().eraseMemory(MemoryModuleType.WALK_TARGET);
		this.state = State.CHARGING;
		this.lifetimeInTicks = 0;
	}

	@Override
	public void tick(final ServerLevel level, final E body, final long timestamp) {
		Brain<Necromancer> brain = body.getBrain();
		LivingEntity target = brain.getMemory(MemoryModuleType.ATTACK_TARGET).orElse(null);
		if (target != null) {
			body.getLookControl().setLookAt(target, 90.0F, 30.0F);
			lifetimeInTicks++;
			if(state == State.CHARGING && lifetimeInTicks >= READY_TO_FIRE) {
				double xd = target.getX() - body.getX();
				double yd = target.getEyeY() - body.getFiringYPosition();
				double zd = target.getZ() - body.getZ();

				Projectile.spawnProjectileUsingShoot(new NecromancerBall(body, level), level, ItemStack.EMPTY, xd, yd, zd, 0.5F, 0.0F);

				body.playSound(EUMSoundEvents.NECROMANCER_SPELL.get(), 1.0F, 1.0F);
				state = State.FIRED;
			}
		}
	}

	protected void stop(final ServerLevel level, final E body, final long timestamp) {
		if (body.getCurrentSpell() == Necromancer.NecromancerSpell.SHOOT) {
			body.setIsCastingSpell(Necromancer.NecromancerSpell.NONE);
		}

		body.getBrain().setMemoryWithExpiry(EUMMemoryModuleTypes.SHOOTING_COOLDOWN.get(), Unit.INSTANCE, (long)SHOOT_COOLDOWN_TICKS);
	}

	protected float getInertia() {
		return 1.0F;
	}

	protected float getLiquidInertia() {
		return this.getInertia();
	}

	private enum State {
		CHARGING,
		FIRED
	}
}
