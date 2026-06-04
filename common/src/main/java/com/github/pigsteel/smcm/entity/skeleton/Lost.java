package com.github.pigsteel.smcm.entity.skeleton;

import com.github.pigsteel.smcm.registry.smcm$SoundEvents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.skeleton.AbstractSkeleton;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.entity.projectile.arrow.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;

public class Lost extends AbstractSkeleton {
    public Lost(EntityType<? extends AbstractSkeleton> type, Level level) {
        super(type, level);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return smcm$SoundEvents.LOST_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(final DamageSource source) {
        return smcm$SoundEvents.LOST_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return smcm$SoundEvents.LOST_DEATH.get();
    }

    @Override
    public SoundEvent getStepSound() {
        return smcm$SoundEvents.LOST_STEP.get();
    }

    @Override
    protected AbstractArrow getArrow(ItemStack projectile, float power, @Nullable ItemStack firingWeapon) {
        AbstractArrow arrow = super.getArrow(projectile, power, firingWeapon);
        if (arrow instanceof Arrow) {
            ((Arrow)arrow).addEffect(new MobEffectInstance(MobEffects.INFESTED, 440));
        }

        return arrow;
    }
}
