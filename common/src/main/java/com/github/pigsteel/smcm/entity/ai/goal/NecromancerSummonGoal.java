package com.github.pigsteel.smcm.entity.ai.goal;

import com.github.pigsteel.smcm.entity.Necromancer;
import com.github.pigsteel.smcm.registry.smcm$SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.equine.ZombieHorse;
import net.minecraft.world.entity.monster.skeleton.Skeleton;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class NecromancerSummonGoal extends Goal {
    private static final int MAX_SUMMON_POINTS = 12;
    private static final int CAST_TIME = 25;
    private static final int COOLDOWN_TIME = 20 * 4;

    private static final double CAST_RANGE_SQR = 16.0D * 16.0D;
    private static final double STOP_RANGE_SQR = 10.0D * 10.0D;

    private final Necromancer necromancer;

    private int castTicks;
    private int cooldownTicks;
    private boolean casting;
    private boolean hasPlayedPrepareSound;

    public NecromancerSummonGoal(Necromancer necromancer) {
        this.necromancer = necromancer;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (this.cooldownTicks > 0) {
            --this.cooldownTicks;
            return false;
        }

        if (!(this.necromancer.level() instanceof ServerLevel serverLevel)) {
            return false;
        }

        LivingEntity target = this.necromancer.getTarget();

        if (target == null || !target.isAlive()) {
            return false;
        }

        return this.countOwnedSummonPoints(serverLevel) < MAX_SUMMON_POINTS;
    }

    @Override
    public boolean canContinueToUse() {
        if (!(this.necromancer.level() instanceof ServerLevel serverLevel)) {
            return false;
        }

        LivingEntity target = this.necromancer.getTarget();

        return target != null
                && target.isAlive()
                && this.countOwnedSummonPoints(serverLevel) < MAX_SUMMON_POINTS;
    }

    @Override
    public void start() {
        this.castTicks = 0;
        this.casting = false;
        this.hasPlayedPrepareSound = false;
    }

    @Override
    public void stop() {
        this.castTicks = 0;
        this.casting = false;
        this.hasPlayedPrepareSound = false;

        this.necromancer.getNavigation().stop();
        this.necromancer.setSummoning(false);
    }

    @Override
    public void tick() {
        LivingEntity target = this.necromancer.getTarget();

        if (target == null || !target.isAlive()) {
            return;
        }

        if (!(this.necromancer.level() instanceof ServerLevel serverLevel)) {
            return;
        }

        this.necromancer.getLookControl().setLookAt(target, 30.0F, 30.0F);

        if (this.cooldownTicks > 0) {
            int cooldownReduction = this.countOwnedSummonPoints(serverLevel) == 0 ? 3 : 1;
            this.cooldownTicks = Math.max(0, this.cooldownTicks - cooldownReduction);
        }

        double distanceSqr = this.necromancer.distanceToSqr(target);
        boolean inCastRange = distanceSqr <= CAST_RANGE_SQR;

        if (!inCastRange) {
            this.necromancer.getNavigation().moveTo(target, 1.0D);
            this.cancelCasting();
            return;
        }

        if (distanceSqr > STOP_RANGE_SQR && !this.casting) {
            this.necromancer.getNavigation().moveTo(target, 0.8D);
        } else {
            this.necromancer.getNavigation().stop();
        }

        if (this.cooldownTicks > 0) {
            this.cancelCasting();
            return;
        }

        if (!this.casting) {
            this.beginCasting();
        }

        this.necromancer.setSummoning(true);

        --this.castTicks;

        if (this.castTicks <= 0) {
            this.performSummon(serverLevel);
            this.cooldownTicks = COOLDOWN_TIME;
            this.stop();
        }
    }

    private void beginCasting() {
        this.casting = true;
        this.castTicks = CAST_TIME;

        if (!this.hasPlayedPrepareSound) {
            this.necromancer.playSound(
                    smcm$SoundEvents.NECROMANCER_PREPARE_SUMMON.get(),
                    1.0F,
                    1.0F
            );

            this.hasPlayedPrepareSound = true;
        }
    }

    private void cancelCasting() {
        this.casting = false;
        this.castTicks = 0;
        this.hasPlayedPrepareSound = false;
        this.necromancer.setSummoning(false);
    }

    private void performSummon(ServerLevel level) {
        int existingCost = this.countOwnedSummonPoints(level);
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

            SummonType type = this.pickSummonType(remainingBudget);

            if (type == null) {
                return;
            }

            if (this.trySpawnSummon(level, summonIndex, type)) {
                remainingBudget -= type.cost();
                summonIndex++;
            }
        }
    }

    @Nullable
    private SummonType pickSummonType(int remainingBudget) {
        List<SummonType> possible = new ArrayList<>();

        for (SummonType type : SummonType.values()) {
            if (type.cost() <= remainingBudget) {
                possible.add(type);
            }
        }

        if (possible.isEmpty()) {
            return null;
        }

        int roll = this.necromancer.getRandom().nextInt(100);

        if (remainingBudget >= SummonType.ZOMBIE_HORSEMAN.cost() && roll < 15) {
            return SummonType.ZOMBIE_HORSEMAN;
        }

        return this.necromancer.getRandom().nextBoolean()
                ? SummonType.ZOMBIE
                : SummonType.BOWLESS_SKELETON;
    }

    private boolean trySpawnSummon(ServerLevel level, int index, SummonType type) {
        double angle = (Math.PI * 2.0D / 3.0D) * index
                + this.necromancer.getRandom().nextDouble() * 0.75D;

        double radius = 2.0D + this.necromancer.getRandom().nextDouble() * 2.0D;

        double x = this.necromancer.getX() + Mth.cos((float) angle) * radius;
        double z = this.necromancer.getZ() + Mth.sin((float) angle) * radius;

        BlockPos pos = BlockPos.containing(x, this.necromancer.getY(), z);
        BlockPos spawnPos = this.findSpawnPos(level, pos, type == SummonType.ZOMBIE_HORSEMAN
                ? EntityType.ZOMBIE_HORSE
                : EntityType.ZOMBIE
        );

        if (spawnPos == null) {
            return false;
        }

        if (type == SummonType.ZOMBIE_HORSEMAN) {
            return this.trySpawnZombieHorseman(level, spawnPos);
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

        entity.setYRot(this.necromancer.getRandom().nextFloat() * 360.0F);
        entity.setXRot(0.0F);

        if (entity instanceof Mob mob) {
            this.finalizeSummonedMob(level, mob);
            this.ensureSunproofHelmet(level, mob);
            this.postProcessSummon(entity, type);
        }

        this.prepareSummonEntity(entity);

        level.addFreshEntityWithPassengers(entity);
        this.necromancer.getSummonedMobs().add(entity.getUUID());

        this.playSummonEffects(level, spawnPos);

        return true;
    }

    private boolean trySpawnZombieHorseman(ServerLevel level, BlockPos spawnPos) {
        ZombieHorse horse = EntityType.ZOMBIE_HORSE.create(level, EntitySpawnReason.MOB_SUMMONED);
        Zombie rider = EntityType.ZOMBIE.create(level, EntitySpawnReason.MOB_SUMMONED);

        if (horse == null || rider == null) {
            return false;
        }

        double x = spawnPos.getX() + 0.5D;
        double y = spawnPos.getY();
        double z = spawnPos.getZ() + 0.5D;
        float yRot = this.necromancer.getRandom().nextFloat() * 360.0F;

        horse.setPos(x, y, z);
        horse.setYRot(yRot);
        horse.setXRot(0.0F);

        rider.setPos(x, y, z);
        rider.setYRot(yRot);
        rider.setXRot(0.0F);

        this.finalizeSummonedMob(level, horse);
        this.finalizeSummonedMob(level, rider);

        horse.setTamed(true);
        horse.setPersistenceRequired();

        rider.setItemSlot(EquipmentSlot.MAINHAND, Items.IRON_SPEAR.getDefaultInstance());
        rider.setTarget(this.necromancer.getTarget());
        rider.setPersistenceRequired();

        /*
         * Add both entities first, then mount. This tends to sync the passenger
         * to the client immediately instead of only after a world reload.
         */
        level.addFreshEntity(horse);
        level.addFreshEntity(rider);

        rider.startRiding(horse, true, true);

        this.necromancer.getSummonedMobs().add(horse.getUUID());

        this.playSummonEffects(level, spawnPos);

        return true;
    }

    private void finalizeSummonedMob(ServerLevel level, Mob mob) {
        mob.finalizeSpawn(
                level,
                level.getCurrentDifficultyAt(mob.blockPosition()),
                EntitySpawnReason.MOB_SUMMONED,
                (SpawnGroupData) null
        );
    }

    private void prepareSummonEntity(Entity entity) {
        if (entity instanceof Mob mob) {
            mob.setTarget(this.necromancer.getTarget());
            mob.setPersistenceRequired();
        }

        for (Entity passenger : entity.getPassengers()) {
            if (passenger instanceof Mob passengerMob) {
                passengerMob.setTarget(this.necromancer.getTarget());
                passengerMob.setPersistenceRequired();
            }
        }
    }

    private void ensureSunproofHelmet(ServerLevel level, Mob mob) {
        ItemStack currentHelmet = mob.getItemBySlot(EquipmentSlot.HEAD);

        if (!currentHelmet.isEmpty()) {
            return;
        }

        float effectiveDifficulty = level.getCurrentDifficultyAt(mob.blockPosition()).getEffectiveDifficulty();
        ItemStack helmet = this.createDifficultyScaledHelmet(level, effectiveDifficulty);

        mob.setItemSlot(EquipmentSlot.HEAD, helmet);

        /*
         * Optional but recommended:
         * prevents summoned mobs from becoming free helmet farms.
         *
         * If this method name changed in your mappings, remove this line or check Mob/LivingEntity.
         */
        mob.setDropChance(EquipmentSlot.HEAD, 0.0F);
    }

    // This is kind of scuffed, ideally we use a vanilla method
    private ItemStack createDifficultyScaledHelmet(ServerLevel level, float effectiveDifficulty) {
        int roll = level.getRandom().nextInt(100);

        /*
         * effectiveDifficulty is usually roughly:
         *   low/easy area: ~0.75 - 1.5
         *   normal mature area: ~2.0 - 3.0
         *   hard/local max: up to ~6.75
         *
         * These thresholds intentionally mimic the idea of vanilla scaling
         * without needing access to vanilla's protected equipment methods.
         */
        if (effectiveDifficulty >= 5.0F) {
            if (roll < 10) {
                return Items.DIAMOND_HELMET.getDefaultInstance();
            }

            if (roll < 45) {
                return Items.IRON_HELMET.getDefaultInstance();
            }

            if (roll < 75) {
                return Items.CHAINMAIL_HELMET.getDefaultInstance();
            }

            return Items.GOLDEN_HELMET.getDefaultInstance();
        }

        if (effectiveDifficulty >= 3.0F) {
            if (roll < 30) {
                return Items.IRON_HELMET.getDefaultInstance();
            }

            if (roll < 65) {
                return Items.CHAINMAIL_HELMET.getDefaultInstance();
            }

            if (roll < 85) {
                return Items.GOLDEN_HELMET.getDefaultInstance();
            }

            return Items.LEATHER_HELMET.getDefaultInstance();
        }

        if (effectiveDifficulty >= 1.5F) {
            if (roll < 20) {
                return Items.CHAINMAIL_HELMET.getDefaultInstance();
            }

            if (roll < 45) {
                return Items.GOLDEN_HELMET.getDefaultInstance();
            }

            return Items.LEATHER_HELMET.getDefaultInstance();
        }

        return Items.LEATHER_HELMET.getDefaultInstance();
    }

    private void postProcessSummon(Entity entity, SummonType type) {
        if (type == SummonType.BOWLESS_SKELETON && entity instanceof Skeleton skeleton) {
            skeleton.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
        }
    }

    @Nullable
    private Entity createSummon(ServerLevel level, SummonType type) {
        return switch (type) {
            case ZOMBIE -> EntityType.ZOMBIE.create(level, EntitySpawnReason.MOB_SUMMONED);
            case BOWLESS_SKELETON -> EntityType.SKELETON.create(level, EntitySpawnReason.MOB_SUMMONED);
            case ZOMBIE_HORSEMAN -> null;
        };
    }

    private void playSummonEffects(ServerLevel level, BlockPos spawnPos) {
        level.playSound(
                null,
                spawnPos,
                smcm$SoundEvents.NECROMANCER_SUMMON.get(),
                SoundSource.HOSTILE,
                1.0F,
                1.0F
        );

        level.levelEvent(null, 2004, spawnPos, 0);
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

    private int countOwnedSummonPoints(ServerLevel level) {
        Set<UUID> summonedMobs = this.necromancer.getSummonedMobs();

        final int[] points = {0};

        summonedMobs.removeIf(uuid -> {
            Entity entity = level.getEntity(uuid);

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
        if (entity instanceof ZombieHorse) {
            return SummonType.ZOMBIE_HORSEMAN.cost();
        }

        if (entity instanceof Zombie || entity instanceof Skeleton) {
            return 1;
        }

        return 1;
    }

    private enum SummonType {
        ZOMBIE(1),
        BOWLESS_SKELETON(1),
        ZOMBIE_HORSEMAN(5);

        private final int cost;

        SummonType(int cost) {
            this.cost = cost;
        }

        public int cost() {
            return this.cost;
        }
    }
}