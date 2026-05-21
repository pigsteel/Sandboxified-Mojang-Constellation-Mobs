package com.github.pigsteel.smcm.entity.skeleton;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Shearable;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.golem.IronGolem;
import net.minecraft.world.entity.animal.turtle.Turtle;
import net.minecraft.world.entity.animal.wolf.Wolf;
import net.minecraft.world.entity.monster.CrossbowAttackMob;
import net.minecraft.world.entity.monster.skeleton.AbstractSkeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class Sunken extends AbstractSkeleton implements CrossbowAttackMob, Shearable {
    private static final EntityDataAccessor<Boolean> IS_CHARGING_CROSSBOW;
    private final RangedCrossbowAttackGoal crossbowGoal = new RangedCrossbowAttackGoal(this, 1.0, 20);

    public Sunken(EntityType<? extends Sunken> type, final Level level) {
        super(type, level);
    }

    protected void defineSynchedData(final SynchedEntityData.Builder entityData) {
        super.defineSynchedData(entityData);
        entityData.define(IS_CHARGING_CROSSBOW, false);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new RestrictSunGoal(this));
        this.goalSelector.addGoal(3, new FleeSunGoal(this, 1.0));
        this.goalSelector.addGoal(3, new AvoidEntityGoal(this, Wolf.class, 6.0F, 1.0, 1.2));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, IronGolem.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, Turtle.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR));
    }

    @Override
    public void reassessWeaponGoal() {
        if (this.level() != null && !this.level().isClientSide()) {
            this.goalSelector.removeGoal(this.meleeGoal);
            this.goalSelector.removeGoal(this.bowGoal);
            this.goalSelector.removeGoal(this.crossbowGoal);
            ItemStack usedWeapon = this.getItemInHand(smcm$ProjectileUtil.getWeaponHoldingHand(this, new net.minecraft.world.item.Item[]{Items.BOW, Items.CROSSBOW}));
            if (usedWeapon.is(Items.CROSSBOW)) {
                this.goalSelector.addGoal(4, this.crossbowGoal);
            } else if (usedWeapon.is(Items.BOW)) {
                int minAttackInterval = this.getHardAttackInterval();
                if (this.level().getDifficulty() != Difficulty.HARD) {
                    minAttackInterval = this.getAttackInterval();
                }

                this.bowGoal.setMinAttackInterval(minAttackInterval);
                this.goalSelector.addGoal(4, this.bowGoal);
            } else {
                this.goalSelector.addGoal(4, this.meleeGoal);
            }
        }
    }

    @Override
    protected void populateDefaultEquipmentSlots(final RandomSource random, final DifficultyInstance difficulty) {
        super.populateDefaultEquipmentSlots(random, difficulty);
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.CROSSBOW));
    }

    @Override
    public SoundEvent getAmbientSound() {
        return SoundEvents.BOGGED_AMBIENT;
    }

    @Override
    public SoundEvent getHurtSound(final DamageSource source) {
        return SoundEvents.BOGGED_HURT;
    }

    @Override
    public SoundEvent getDeathSound() {
        return SoundEvents.BOGGED_DEATH;
    }

    public SoundEvent getStepSound() {
        return SoundEvents.BOGGED_STEP;
    }

    @Override
    public void shear(ServerLevel level, SoundSource soundSource, ItemStack tool) {

    }

    @Override
    public void performRangedAttack(final LivingEntity target, final float power) {
        ItemStack weaponItem = this.getItemInHand(smcm$ProjectileUtil.getWeaponHoldingHand(this, new net.minecraft.world.item.Item[] { Items.BOW, Items.CROSSBOW } ));
        ItemStack projectile = this.getProjectile(weaponItem);
        AbstractArrow arrow = this.getArrow(projectile, power, weaponItem);
        double xd = target.getX() - this.getX();
        double yd = target.getY(0.3333333333333333) - arrow.getY();
        double zd = target.getZ() - this.getZ();
        double distanceToTarget = Math.sqrt(xd * xd + zd * zd);
        if (this.level() instanceof ServerLevel serverLevel) {
            Projectile.spawnProjectileUsingShoot(
                    arrow, serverLevel, projectile, xd, yd + distanceToTarget * 0.2F, zd, 1.6F, 14 - serverLevel.getDifficulty().getId() * 4
            );
        }

        this.playSound(SoundEvents.SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
    }


    @Override
    public boolean readyForShearing() {
        return false;
    }

    @Override
    public void setChargingCrossbow(boolean isCharging) {
        this.entityData.set(IS_CHARGING_CROSSBOW, isCharging);
    }

    @Override
    public void onCrossbowAttackPerformed() {
        this.noActionTime = 0;
    }

    static {
        IS_CHARGING_CROSSBOW = SynchedEntityData.defineId(Sunken.class, EntityDataSerializers.BOOLEAN);
    }
}
