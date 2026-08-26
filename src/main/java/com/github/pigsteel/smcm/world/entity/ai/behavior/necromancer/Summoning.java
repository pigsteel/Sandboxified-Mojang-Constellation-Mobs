package com.github.pigsteel.smcm.world.entity.ai.behavior.necromancer;

import com.github.pigsteel.smcm.network.SMCMLevelEventPacketPayload;
import com.github.pigsteel.smcm.util.EntityTypesUtil;
import com.github.pigsteel.smcm.world.entity.ai.memory.SMCMMemoryModuleTypes;
import com.github.pigsteel.smcm.world.entity.monster.necromancer.Necromancer;
import com.google.common.collect.ImmutableMap;
//? fabric {
/*import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
*///?} neoforge {
import net.neoforged.neoforge.network.PacketDistributor;
//?}
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.animal.equine.ZombieHorse;
import net.minecraft.world.entity.monster.skeleton.Skeleton;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static com.github.pigsteel.smcm.client.animation.definitions.NecromancerAnimation.NECROMANCER_SUMMON_LENGTH;

public class Summoning<E extends Necromancer> extends Behavior<E> {
	public Summoning() {
		super(ImmutableMap.of(MemoryModuleType.ATTACK_TARGET, MemoryStatus.VALUE_PRESENT, SMCMMemoryModuleTypes.SUMMONING_COOLDOWN.get(), MemoryStatus.VALUE_ABSENT, MemoryModuleType.LOOK_TARGET, MemoryStatus.REGISTERED), DURATION);
	}

	private static final int MAX_SUMMON_POINTS = 12;
	private static final int DURATION = Mth.ceil(20 * NECROMANCER_SUMMON_LENGTH);
	private static final int COOLDOWN_TIME = 20 * 10;

	private static final double CAST_RANGE_SQR = 16.0D * 16.0D;
	private static final double STOP_RANGE_SQR = 10.0D * 10.0D;

	private int castTicks;
	private boolean casting;
	private boolean hasCasted;
	private boolean hasPlayedPrepareSound;

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
		return !body.isCastingSpell() && BehaviorUtils.canSee(body, attackTarget) && body.closerThan(attackTarget, 16.0D) && this.countOwnedSummonPoints(level, body) < MAX_SUMMON_POINTS;
	}

	private void resetValues() {
		this.castTicks = 0;
		this.casting = false;
		this.hasCasted = false;
		this.hasPlayedPrepareSound = false;
	}

	@Override
	public void start(final ServerLevel level, final E body, final long timestamp) {
		resetValues();
		body.getBrain().eraseMemory(MemoryModuleType.LOOK_TARGET);
		body.getBrain().eraseMemory(MemoryModuleType.WALK_TARGET);
		body.getBrain().setMemory(SMCMMemoryModuleTypes.PENDING_SUMMON.get(), Optional.empty());
		body.getLookControl().setLookAt(body.getTarget(), 30.0F, 30.0F);
	}

	@Override
	public void stop(final ServerLevel level, final E body, final long timestamp) {
		resetValues();
		body.setIsCastingSpell(Necromancer.NecromancerSpell.NONE);
		body.getBrain().eraseMemory(SMCMMemoryModuleTypes.PENDING_SUMMON.get());
	}

	@Override
	public void tick(final ServerLevel level, final E body, final long timestamp) {
		if (!(body.level() instanceof ServerLevel serverLevel) || hasCooldown(body)) {
			return;
		}

		if(this.casting && this.castTicks <= 0) {
			setCooldown(body, COOLDOWN_TIME);
		}

		var memory = body.getBrain().getMemory(MemoryModuleType.ATTACK_TARGET);

		if (memory.isEmpty()) {
			this.stop(level, body, timestamp);
			return;
		}

		LivingEntity target = memory.get();

		body.getLookControl().setLookAt(target, 30.0F, 30.0F);

		double distanceSqr = body.distanceToSqr(target);
		boolean inCastRange = distanceSqr <= CAST_RANGE_SQR;

		if (!this.casting && !inCastRange) {
			this.stop(level, body, timestamp);
			return;
		}

		if (!this.casting) {
			this.beginCasting(body);
		}

		--this.castTicks;

		if (!hasCasted && this.castTicks <= 15) {
			this.performSummon(serverLevel, body);
			this.hasCasted = true;
		}
	}

	private void beginCasting(E body) {
		this.casting = true;
		this.castTicks = DURATION;

		//body.setIsCastingSpell(Necromancer.NecromancerSpell.NONE);
		body.setIsCastingSpell(Necromancer.NecromancerSpell.SUMMON);

		if (!this.hasPlayedPrepareSound) {
			body.playSound(
					body.getPrepareSummonSound(),
					1.0F,
					1.0F
			);

			this.hasPlayedPrepareSound = true;
		}
	}

	private void performSummon(final ServerLevel level, final E body) {
		int existingCost = this.countOwnedSummonPoints(level, body);
		int remainingBudget = MAX_SUMMON_POINTS - existingCost;

		if (remainingBudget <= 0) {
			return;
		}

		remainingBudget = Math.min(remainingBudget, 5);

		int attempts = 0;
		int summonIndex = 0;
		int summonAmount = 3;

		while (remainingBudget > 0 && attempts < 12 && summonAmount > 0) {
			attempts++;
			summonAmount--;

			SummonType type = this.pickSummonType(body, remainingBudget);

			if (type == null) {
				return;
			}

			if (this.trySpawnSummon(level, body, summonIndex, type)) {
				remainingBudget -= type.cost();
				summonIndex++;
			}
		}
	}

	@Nullable
	private SummonType pickSummonType(E body, int remainingBudget) {
		List<SummonType> possible = new ArrayList<>();

		for (SummonType type : SummonType.values()) {
			if (type.cost() <= remainingBudget) {
				possible.add(type);
			}
		}

		if (possible.isEmpty()) {
			return null;
		}

		if (remainingBudget >= SummonType.ZOMBIE_HORSEMAN.cost() && body.getRandom().nextInt(100) < 2) {
			return SummonType.ZOMBIE_HORSEMAN;
		}

		return body.getRandom().nextBoolean()
				? SummonType.ZOMBIE
				: SummonType.BOWLESS_SKELETON;
	}

	private boolean trySpawnSummon(ServerLevel level, @NonNull E body, int index, SummonType type) {
		double angle = (Math.PI * 2.0D / 3.0D) * index
				+ body.getRandom().nextDouble() * 0.75D;

		double radius = 2.0D + body.getRandom().nextDouble() * 2.0D;

		double x = body.getX() + Mth.cos((float) angle) * radius;
		double z = body.getZ() + Mth.sin((float) angle) * radius;

		BlockPos pos = BlockPos.containing(x, body.getY(), z);
		BlockPos spawnPos = this.findSpawnPos(level, pos, type == SummonType.ZOMBIE_HORSEMAN
				? EntityTypesUtil.ZOMBIE_HORSE
				: EntityTypesUtil.ZOMBIE
		);

		if (spawnPos == null) {
			return false;
		}

		if (type == SummonType.ZOMBIE_HORSEMAN) {
			return this.trySpawnZombieHorseman(level, body, spawnPos);
		}

		Entity entity = this.createSummon(level, type);

		if (entity == null) {
			return false;
		}

		entity.setPos(
				spawnPos.getX() + 0.5D,
				spawnPos.getY(),
				spawnPos.getZ() + 0.5D
		);

		entity.setYRot(body.getRandom().nextFloat() * 360.0F);
		entity.setXRot(0.0F);

		if (entity instanceof Mob mob) {
			this.finalizeSummonedMob(level, mob, body.getTarget());
			this.postProcessSummon(entity, type);
		}

		this.prepareSummonEntity(body, entity);

		level.addFreshEntityWithPassengers(entity);

		body.addSummonedMob(entity.asLivingEntity());

		this.playSummonEffects(level, spawnPos);

		return true;
	}

	private boolean trySpawnZombieHorseman(ServerLevel level, E body, BlockPos spawnPos) {
		ZombieHorse horse = EntityTypesUtil.ZOMBIE_HORSE.create(level, EntitySpawnReason.MOB_SUMMONED);
		Zombie rider = EntityTypesUtil.ZOMBIE.create(level, EntitySpawnReason.MOB_SUMMONED);

		if (horse == null || rider == null) {
			return false;
		}

		double x = spawnPos.getX() + 0.5D;
		double y = spawnPos.getY();
		double z = spawnPos.getZ() + 0.5D;
		float yRot = body.getRandom().nextFloat() * 360.0F;

		horse.setPos(x, y, z);
		horse.setYRot(yRot);
		horse.setXRot(0.0F);

		rider.setPos(x, y, z);
		rider.setYRot(yRot);
		rider.setXRot(0.0F);

		this.finalizeSummonedMob(level, horse);
		this.finalizeSummonedMob(level, rider, body.getTarget());

		horse.setTamed(true);
		rider.setItemSlot(EquipmentSlot.MAINHAND, Items.IRON_SPEAR.getDefaultInstance());
		rider.setTarget(body.getTarget());

		level.addFreshEntity(horse);
		level.addFreshEntity(rider);

		rider.startRiding(horse, true, true);

		body.addSummonedMob(horse);

		this.playSummonEffects(level, spawnPos);

		return true;
	}

	private void finalizeSummonedMob(ServerLevel level, Mob mob, LivingEntity target) {
		finalizeSummonedMob(level, mob);
		mob.setTarget(target);
	}

	private void finalizeSummonedMob(ServerLevel level, Mob mob) {
		mob.finalizeSpawn(
				level,
				level.getCurrentDifficultyAt(mob.blockPosition()),
				EntitySpawnReason.MOB_SUMMONED,
				(SpawnGroupData) null
		);
	}

	private void prepareSummonEntity(E body, Entity entity) {
		if (entity instanceof Mob mob) {
			mob.setTarget(body.getTarget());
			mob.setPersistenceRequired();
		}

		for (Entity passenger : entity.getPassengers()) {
			if (passenger instanceof Mob passengerMob) {
				passengerMob.setTarget(body.getTarget());
				passengerMob.setPersistenceRequired();
			}
		}
	}

	private void postProcessSummon(Entity entity, SummonType type) {
		if (type == SummonType.BOWLESS_SKELETON && entity instanceof Skeleton skeleton) {
			skeleton.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
		}
	}

	@Nullable
	private Entity createSummon(ServerLevel level, SummonType type) {
		return switch (type) {
			case ZOMBIE -> EntityTypesUtil.ZOMBIE.create(level, EntitySpawnReason.MOB_SUMMONED);
			case BOWLESS_SKELETON -> EntityTypesUtil.SKELETON.create(level, EntitySpawnReason.MOB_SUMMONED);
			case ZOMBIE_HORSEMAN -> null;
		};
	}

	private void playSummonEffects(ServerLevel level, BlockPos spawnPos) {
		SMCMLevelEventPacketPayload payload = new SMCMLevelEventPacketPayload(1002, spawnPos);

		//? fabric {
		/*for (ServerPlayer player : PlayerLookup.level(level)) {
			ServerPlayNetworking.send(player, payload);
		}
		*///?} neoforge {
		PacketDistributor.sendToAllPlayers(payload);
		//?}
	}

	private BlockPos findSpawnPos(ServerLevel level, BlockPos start, EntityType<?> entityType) {
		for (int dy = 0; dy <= 2; dy++) {
			BlockPos pos = start.above(dy);

			if (level.noCollision(entityType.getSpawnAABB(
					pos.getX() + 0.5D,
					pos.getY(),
					pos.getZ() + 0.5D
			))) {
				return pos;
			}
		}

		return null;
	}

	private int countOwnedSummonPoints(ServerLevel level, E body) {
		Set<UUID> summonedMobs = body.getSummonedMobs();

		final int[] points = {0};

		summonedMobs.removeIf(uuid -> {
			var entity = level.getEntity(uuid);

			if (entity == null) {
				return false;
			}

			if (!entity.isAlive()) {
				return true;
			}

			points[0] += this.getSummonCost(entity);
			return false;
		});

		return points[0];
	}

	private int getSummonCost(Entity entity) {
		if(entity.is(EntityTypesUtil.ZOMBIE)) {
			return SummonType.ZOMBIE.cost();
		} else if(entity.is(EntityTypesUtil.SKELETON)) {
			return SummonType.BOWLESS_SKELETON.cost();
		} else if(entity.is(EntityTypesUtil.ZOMBIE_HORSE)) {
			return SummonType.ZOMBIE_HORSEMAN.cost();
		} else {
			return 0;
		}
	}

	public static void setCooldown(LivingEntity body, int cooldown) {
		body.getBrain().setMemoryWithExpiry(SMCMMemoryModuleTypes.SUMMONING_COOLDOWN.get(), Unit.INSTANCE, (long)cooldown);
	}

	public static boolean hasCooldown(LivingEntity body) {
		return body.getBrain().hasMemoryValue(SMCMMemoryModuleTypes.SUMMONING_COOLDOWN.get());
	}

	private enum SummonType {
		ZOMBIE(1),
		BOWLESS_SKELETON(2),
		ZOMBIE_HORSEMAN(6);

		private final int cost;

		SummonType(int cost) {
			this.cost = cost;
		}

		public int cost() {
			return this.cost;
		}
	}
}
