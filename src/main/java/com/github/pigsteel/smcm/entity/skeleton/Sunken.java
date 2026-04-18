package com.github.pigsteel.smcm.entity.skeleton;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.CrossbowAttackMob;
import net.minecraft.world.entity.monster.skeleton.AbstractSkeleton;
import net.minecraft.world.level.Level;

public abstract class Sunken extends AbstractSkeleton implements CrossbowAttackMob {

    public Sunken(EntityType<? extends Sunken> type, final Level level) {
        super(type, level);
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.BOGGED_AMBIENT;
    }

    protected SoundEvent getHurtSound(final DamageSource source) {
        return SoundEvents.BOGGED_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.BOGGED_DEATH;
    }

    protected SoundEvent getStepSound() {
        return SoundEvents.BOGGED_STEP;
    }
}
