package com.github.pigsteel.smcm.entity.skeleton;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Shearable;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.golem.IronGolem;
import net.minecraft.world.entity.animal.turtle.Turtle;
import net.minecraft.world.entity.animal.wolf.Wolf;
import net.minecraft.world.entity.monster.CrossbowAttackMob;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.illager.Pillager;
import net.minecraft.world.entity.monster.skeleton.AbstractSkeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Sunken extends AbstractSkeleton implements CrossbowAttackMob, Shearable {
    private static final EntityDataAccessor<Boolean> IS_CHARGING_CROSSBOW;

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
