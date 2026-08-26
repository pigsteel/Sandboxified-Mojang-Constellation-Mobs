package com.github.pigsteel.smcm.world.entity.monster.illager;

import com.github.pigsteel.smcm.core.SMCMSoundEvents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.illager.Vindicator;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class Mountaineer extends Vindicator {
    public Mountaineer(EntityType<? extends Mountaineer> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
    }

    @Override
    public IllagerArmPose getArmPose() {
        if (this.isAggressive()) {
            return IllagerArmPose.ATTACKING;
        } else {
            return this.isCelebrating() ? IllagerArmPose.CELEBRATING : IllagerArmPose.NEUTRAL;
        }
    }

    @Override
    protected void populateDefaultEquipmentSlots(final RandomSource random, final DifficultyInstance difficulty) {
        if (this.getCurrentRaid() == null) {
            this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_PICKAXE));
        }
    }

	@Override
	public SoundEvent getCelebrateSound() {
		return SMCMSoundEvents.MOUNTAINEER_CELEBRATE.get();
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SMCMSoundEvents.MOUNTAINEER_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(final DamageSource source) {
		return SMCMSoundEvents.MOUNTAINEER_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SMCMSoundEvents.MOUNTAINEER_DEATH.get();
	}
}
