package com.github.pigsteel.smcm.world.entity.monster.zombie;

import com.github.pigsteel.smcm.core.SMCMSoundEvents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.zombie.ZombifiedPiglin;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class ZombifiedPiglinBrute extends ZombifiedPiglin {
    public ZombifiedPiglinBrute(EntityType<? extends ZombifiedPiglinBrute> type, Level level) {
        super(type, level);
    }

    @Override
    public boolean isBaby() {
        return false;
    }

    @Override
    public void populateDefaultEquipmentSlots(final RandomSource random, final DifficultyInstance difficulty) {
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.GOLDEN_AXE));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return ZombifiedPiglin.createAttributes()
                .add(Attributes.MAX_HEALTH, 60.0F)
                .add(Attributes.MOVEMENT_SPEED, 0.21F)
                .add(Attributes.ATTACK_DAMAGE, 7.0F)
                .add(Attributes.FOLLOW_RANGE, 12.0F);
    }

	protected SoundEvent getAmbientSound() {
		return (this.isAngry() ? SMCMSoundEvents.ZOMBIFIED_PIGLIN_BRUTE_ANGRY : SMCMSoundEvents.ZOMBIFIED_PIGLIN_BRUTE_AMBIENT).get();
	}

	protected SoundEvent getHurtSound(final DamageSource source) {
		return SMCMSoundEvents.ZOMBIFIED_PIGLIN_BRUTE_HURT.get();
	}

	protected SoundEvent getDeathSound() {
		return SMCMSoundEvents.ZOMBIFIED_PIGLIN_BRUTE_DEATH.get();
	}

	@Override
	public void playAngerSound() {
		this.playSound(SMCMSoundEvents.ZOMBIFIED_PIGLIN_BRUTE_ANGRY.get(), this.getSoundVolume() * 2.0F, this.getVoicePitch() * 1.8F);
	}
}
