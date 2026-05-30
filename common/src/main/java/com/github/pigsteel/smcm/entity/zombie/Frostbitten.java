package com.github.pigsteel.smcm.entity.zombie;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.registry.smcm$SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.golem.IronGolem;
import net.minecraft.world.entity.animal.turtle.Turtle;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.entity.monster.zombie.ZombifiedPiglin;
import net.minecraft.world.entity.npc.villager.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.throwableitemprojectile.Snowball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.Nullable;

public class Frostbitten extends Zombie implements RangedAttackMob {
    private static final EntityDataAccessor<Boolean> AIMING_SNOWBALL = SynchedEntityData.defineId(Frostbitten.class, EntityDataSerializers.BOOLEAN);
    private static final int SNOWBALL_COOLDOWN = 40;
    private final FrostbittenThrowSnowballGoal snowballGoal = new FrostbittenThrowSnowballGoal(this, 0.1, 20, 10.0f);
    private int snowballCooldownTime;
    private int shiveringTicksLeft;

    public Frostbitten(EntityType<? extends Frostbitten> type, Level level) {
        super(type, level);
        this.reassessWeaponGoal();
    }

    public static boolean checkFrostbittenSpawnRules(
            final EntityType<Frostbitten> type, final ServerLevelAccessor level, final EntitySpawnReason spawnReason, final BlockPos pos, final RandomSource random
    ) {
        BlockPos checkSkyPos = pos;

        do {
            checkSkyPos = checkSkyPos.above();
        } while (level.getBlockState(checkSkyPos).is(Blocks.POWDER_SNOW));

        return Monster.checkMonsterSpawnRules(type, level, spawnReason, pos, random)
                && (EntitySpawnReason.isSpawner(spawnReason) || level.canSeeSky(checkSkyPos.below()));
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return smcm$SoundEvents.FROSTBITTEN_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(final DamageSource source) {
        return smcm$SoundEvents.FROSTBITTEN_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return smcm$SoundEvents.FROSTBITTEN_DEATH.get();
    }

    @Override
    protected void addBehaviourGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new SpearUseGoal<>(this, 1.0, 1.0, 10.0F, 2.0F));
        this.goalSelector.addGoal(3, new ZombieAttackGoal(this, 1.0, false));
        this.goalSelector.addGoal(6, new MoveThroughVillageGoal(this, 1.0, true, 4, this::canBreakDoors));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setAlertOthers(ZombifiedPiglin.class));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, AbstractVillager.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, IronGolem.class, true));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal(this, Turtle.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR));
    }

    public boolean isShaking() {
        return this.isInPowderSnow || shiveringTicksLeft != 0 && !this.isOnFire();
    }

    public boolean isAimingSnowball() {
        return this.entityData.get(AIMING_SNOWBALL);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(AIMING_SNOWBALL, false);
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput valueOutput) {
        super.addAdditionalSaveData(valueOutput);
        valueOutput.putInt("snowball_cooldown", snowballCooldownTime);
    }

    @Override
    protected void readAdditionalSaveData(ValueInput valueInput) {
        super.readAdditionalSaveData(valueInput);
        snowballCooldownTime = valueInput.getInt("snowball_cooldown").orElse(0);
        this.reassessWeaponGoal();
    }

    public void setAimingSnowball(boolean aimingSnowball) {
        this.entityData.set(AIMING_SNOWBALL, aimingSnowball);
    }

    @Override
    public void performRangedAttack(LivingEntity target, float power) {
        double xd = target.getX() - this.getX();
        double yd = target.getEyeY() - 1.1F;
        double zd = target.getZ() - this.getZ();
        double yo = Math.sqrt(xd * xd + zd * zd) * 0.2F;
        if (this.level() instanceof ServerLevel serverLevel) {
            ItemStack itemStack = new ItemStack(Items.SNOWBALL);
            Projectile.spawnProjectile(
                    new Snowball(serverLevel, this, itemStack), serverLevel, itemStack, projectile -> projectile.shoot(xd, yd + yo - projectile.getY(), zd, 1.6F, 2.0F)
            );
            float difficulty = ((ServerLevel)this.level()).getCurrentDifficultyAt(this.blockPosition()).getEffectiveDifficulty();
            this.snowballCooldownTime = (int)(SNOWBALL_COOLDOWN - 5.0F * difficulty);
        }

        this.playSound(smcm$SoundEvents.FROSTBITTEN_SHOOT.get(), 1.0F, 0.4F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
    }

    public void reassessWeaponGoal() {
        if (this.level() != null && !this.level().isClientSide()) {
            this.goalSelector.removeGoal(this.snowballGoal);
            if (this.getMainHandItem().isEmpty()) {
                SMCM.LOGGER.debug("Main hand is empty");
                this.goalSelector.addGoal(2, this.snowballGoal);
            }
        }
    }

    @Override
    public void onEquipItem(final EquipmentSlot slot, final ItemStack oldStack, final ItemStack stack) {
        super.onEquipItem(slot, oldStack, stack);
        if (!this.level().isClientSide()) {
            if (this.getMainHandItem().is(Items.SNOWBALL)) return;
            this.reassessWeaponGoal();
        }
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(
            final ServerLevelAccessor level, final DifficultyInstance difficulty, final EntitySpawnReason spawnReason, @Nullable SpawnGroupData groupData
    ) {
        groupData = super.finalizeSpawn(level, difficulty, spawnReason, groupData);
        float difficultyModifier = difficulty.getSpecialMultiplier();
        if (spawnReason != EntitySpawnReason.CONVERSION) {
            this.setCanPickUpLoot(random.nextFloat() < 0.55F * difficultyModifier);
        }

        if (groupData != null) {
            groupData = new FrostbittenGroupData((Zombie.ZombieGroupData)groupData);
        }

        this.reassessWeaponGoal();
        return groupData;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Zombie.createAttributes().add(Attributes.MAX_HEALTH, 16.0);
    }

    @Override
    public boolean doHurtTarget(final ServerLevel level, final Entity target) {
        boolean result = super.doHurtTarget(level, target);
        if (result && this.getMainHandItem().isEmpty() && target instanceof LivingEntity) {
            float difficulty = level.getCurrentDifficultyAt(this.blockPosition()).getEffectiveDifficulty();
            var living = (LivingEntity)target;
            living.setTicksFrozen(living.getTicksFrozen() + 140 * (int)difficulty);
        }

        return result;
    }

    public static boolean IsTargetSlowed(LivingEntity target) {
        return target != null && (target.hasEffect(MobEffects.SLOWNESS) || target.isFreezing() || target.isInPowderSnow || target.wasInPowderSnow);
    }

    @Override
    public void tick() {
        super.tick();

        if(shiveringTicksLeft != 0) {
            shiveringTicksLeft--;
        } else {
            if (this.random.nextInt() % 100 == 0) {
                shiveringTicksLeft = 20;
            }
        }

        if (!this.level().isClientSide() && this.isAlive() && !this.isNoAi()) {
            if (this.snowballCooldownTime > 0) snowballCooldownTime--;
        }
    }

    private static class FrostbittenThrowSnowballGoal extends RangedAttackGoal {
        protected final Frostbitten frostbitten;

        public FrostbittenThrowSnowballGoal(Frostbitten frostbitten, double mobSpeed, int intervalTicks, float maxShootRange) {
            super(frostbitten, mobSpeed, intervalTicks, maxShootRange);
            this.frostbitten = frostbitten;
        }

        @Override
        public void start() {
            super.start();

            this.frostbitten.setAimingSnowball(true);

            this.frostbitten.setItemSlot(
                    EquipmentSlot.MAINHAND,
                    new ItemStack(Items.SNOWBALL)
            );
        }

        @Override
        public void stop() {
            super.stop();

            this.frostbitten.setAimingSnowball(false);

            this.frostbitten.setItemSlot(
                    EquipmentSlot.MAINHAND,
                    ItemStack.EMPTY
            );
        }

        @Override public boolean canUse() {
            var target = this.frostbitten.getTarget();
            if(target == null) return false;
            var distance = this.frostbitten.distanceTo(target);

            return super.canUse() && !IsTargetSlowed(target) && distance > 3.0F && distance < 16.0F && this.frostbitten.snowballCooldownTime == 0; }
        @Override public boolean canContinueToUse() { return super.canContinueToUse() && this.canUse(); }

    }

    public static class FrostbittenGroupData extends Zombie.ZombieGroupData {
        public FrostbittenGroupData(Zombie.ZombieGroupData groupData) {
            super(groupData.isBaby, groupData.canSpawnJockey);
        }
    }
}
