package com.github.pigsteel.smcm.world.entity.monster.necromancer;

import com.github.pigsteel.smcm.util.EntityTypesUtil;
import com.github.pigsteel.smcm.world.entity.ai.behavior.necromancer.ShootingMagic;
import com.github.pigsteel.smcm.world.entity.ai.behavior.necromancer.Summoning;
import com.github.pigsteel.smcm.world.entity.ai.memory.smcm$MemoryModuleTypes;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.ActivityData;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.BackUpIfTooClose;
import net.minecraft.world.entity.ai.behavior.BehaviorControl;
import net.minecraft.world.entity.ai.behavior.DoNothing;
import net.minecraft.world.entity.ai.behavior.EntityTracker;
import net.minecraft.world.entity.ai.behavior.GateBehavior;
import net.minecraft.world.entity.ai.behavior.LookAtTargetSink;
import net.minecraft.world.entity.ai.behavior.MoveToTargetSink;
import net.minecraft.world.entity.ai.behavior.RandomStroll;
import net.minecraft.world.entity.ai.behavior.RunOne;
import net.minecraft.world.entity.ai.behavior.SetLookAndInteract;
import net.minecraft.world.entity.ai.behavior.StartAttacking;
import net.minecraft.world.entity.ai.behavior.StopAttackingIfTargetInvalid;
import net.minecraft.world.entity.ai.behavior.declarative.BehaviorBuilder;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.memory.NearestVisibleLivingEntities;
import net.minecraft.world.entity.ai.memory.WalkTarget;
import net.minecraft.world.entity.schedule.Activity;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class NecromancerAi {
	public static List<ActivityData<Necromancer>> getActivities(Necromancer necromancer) {
		return List.of(
				initCoreActivity(),
				initIdleActivity(),
				initFightActivity(necromancer)
		);
	}

	private static ActivityData<Necromancer> initCoreActivity() {
		return ActivityData.create(
				Activity.CORE,
				0,
				ImmutableList.of(
						new LookAtTargetSink(45, 90),
						new MoveToTargetSink()
				)
		);
	}

	private static ActivityData<Necromancer> initIdleActivity() {
		return ActivityData.create(
				Activity.IDLE,
				10,
				ImmutableList.of(
						StartAttacking.create((var0, necromancer) -> necromancer.getBrain().getMemory(MemoryModuleType.NEAREST_ATTACKABLE)),
						StartAttacking.create(
								(var0, Necromancer) -> Necromancer.getBrain()
										.getMemory(MemoryModuleType.HURT_BY)
										.map(DamageSource::getEntity)
										.filter(entity -> entity instanceof LivingEntity)
										.map(entity -> (LivingEntity)entity)
						),
						createIdleMovementBehaviors(),
						SetLookAndInteract.create(
								EntityTypesUtil.PLAYER
								, 4) // this is mainly just for creative mode
				)
		);
	}

	private static ActivityData<Necromancer> initFightActivity(Necromancer body) {
		return ActivityData.create(
				Activity.FIGHT,
				ActivityData.createPriorityPairs(
						10,
						ImmutableList.of(
								StopAttackingIfTargetInvalid.create(),
								new GateBehavior<>(
										ImmutableMap.of(),
										ImmutableSet.of(),
										GateBehavior.OrderPolicy.ORDERED,
										GateBehavior.RunningPolicy.RUN_ONE,
										ImmutableList.of(
												Pair.of(BackUpIfTooClose.create(3, 2.0F), 0),
												Pair.of(new Summoning<>(), 1),
												Pair.of(new ShootingMagic<>(), 2),
												Pair.of(ApproachOrGlareIfCannotAttack.create(1.0F), 3) // prioritize staring at enemy if everything is on cooldown
										)
								)
						)
				),
				ImmutableSet.of(
						Pair.of(MemoryModuleType.ATTACK_TARGET, MemoryStatus.VALUE_PRESENT), Pair.of(MemoryModuleType.WALK_TARGET, MemoryStatus.REGISTERED)
				),
				ImmutableSet.of(
						MemoryModuleType.ATTACK_TARGET, MemoryModuleType.WALK_TARGET
				)
		);
	}

	private static RunOne<Necromancer> createIdleMovementBehaviors() {
		return new RunOne<>(
				ImmutableList.of(
						Pair.of(RandomStroll.stroll(0.6F), 2),
						Pair.of(new DoNothing(30, 60), 1)
				)
		);
	}

	public static void updateActivity(final Brain<Necromancer> brain) {
		brain.setActiveActivityToFirstValid(ImmutableList.of(Activity.FIGHT, Activity.IDLE));

		if (brain.isActive(Activity.FIGHT)) {
			var current = brain.getMemory(MemoryModuleType.ATTACK_TARGET);
			var nearest = brain.getMemory(MemoryModuleType.NEAREST_ATTACKABLE);

			if (nearest.isPresent() && current.isPresent()) {
				if (!current.get().is(nearest.get())) {
					brain.setMemory(MemoryModuleType.ATTACK_TARGET, nearest.get());
				}
			}
		}
	}

	static class ApproachOrGlareIfCannotAttack {
		public static BehaviorControl<Mob> create(final float speedModifier) {
			return create((mob) -> speedModifier);
		}

		public static BehaviorControl<Mob> create(final Function<LivingEntity, Float> speedModifier) {
			return BehaviorBuilder.create((i) -> i.group(
					i.registered(MemoryModuleType.WALK_TARGET),
					i.registered(MemoryModuleType.LOOK_TARGET),
					i.present(MemoryModuleType.ATTACK_TARGET),
					i.present(smcm$MemoryModuleTypes.SUMMONING_COOLDOWN.get()),
					i.present(smcm$MemoryModuleTypes.SHOOTING_COOLDOWN.get()),
					i.absent(smcm$MemoryModuleTypes.PENDING_SUMMON.get())
			).apply(i, (
					walkTarget,
					lookTarget,
					attackTarget,
					_,
					_,
					_
			) -> (
					_,
					body,
					_
			) -> {
				LivingEntity toAttack = i.get(attackTarget);

				lookTarget.set(new EntityTracker(toAttack, true));
				if (body.closerThan(toAttack, 15.0D)) {
					walkTarget.erase();
				} else {
					walkTarget.set(new WalkTarget(new EntityTracker(toAttack, false), speedModifier.apply(body), 15));
				}

				return true;
			}));
		}
	}
}
