package com.github.pigsteel.smcm.entity.zombie;

import com.github.pigsteel.smcm.registry.Sounds;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.level.Level;

// Just a poison zombie lol
public class Reclaimed extends Zombie {
    public Reclaimed(EntityType<? extends Zombie> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Zombie.createAttributes();
    }

    @Override
    public boolean doHurtTarget(final ServerLevel level, final Entity target) {
        boolean result = super.doHurtTarget(level, target);
        if (result && this.getMainHandItem().isEmpty() && target instanceof LivingEntity) {
            float difficulty = level.getCurrentDifficultyAt(this.blockPosition()).getEffectiveDifficulty();
            ((LivingEntity)target).addEffect(new MobEffectInstance(MobEffects.POISON, 140 * (int)difficulty), this);
        }

        return result;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return Sounds.RECLAIMED_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(final DamageSource source) {
        return Sounds.RECLAIMED_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return Sounds.RECLAIMED_DEATH;
    }

    @Override
    protected SoundEvent getStepSound() {
        return Sounds.RECLAIMED_STEP;
    }
}
