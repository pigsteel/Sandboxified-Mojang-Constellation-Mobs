package com.github.pigsteel.smcm.entity.skeleton;

import com.github.pigsteel.smcm.entity.smcm$ProjectileUtil;
import com.github.pigsteel.smcm.registry.smcm$SoundEvents;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.RangedBowAttackGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.skeleton.AbstractSkeleton;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.entity.projectile.arrow.Arrow;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;

public class Lost extends AbstractSkeleton {
    private static final EntityDataAccessor<Boolean> IS_CROUCHING;
    private final LostRangedBowAttackGoal bowGoal = new LostRangedBowAttackGoal(this, 1.0D, 20, 15.0F);

    public Lost(EntityType<? extends AbstractSkeleton> type, Level level) {
        super(type, level);
    }

    public void playAmbientSound() {
        if(!this.isCrouching()) {
            this.makeSound(this.getAmbientSound());
        }
    }

    protected void defineSynchedData(final SynchedEntityData.Builder entityData) {
        super.defineSynchedData(entityData);
        entityData.define(IS_CROUCHING, false);
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

    @Override
    public void reassessWeaponGoal() {
        if (this.level() != null && !this.level().isClientSide()) {
            this.goalSelector.removeGoal(this.meleeGoal);
            this.goalSelector.removeGoal(this.bowGoal);
            ItemStack itemstack = this.getItemInHand(smcm$ProjectileUtil.getWeaponHoldingHand(this, new Item[] { Items.BOW }));
            if (itemstack.getItem() instanceof BowItem) {
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

    public boolean isCrouching() {
        return this.entityData.get(IS_CROUCHING);
    }

    public void setIsCrouching(boolean isCrouching) {
        this.entityData.set(IS_CROUCHING, isCrouching);
    }

    static {
        IS_CROUCHING = SynchedEntityData.defineId(Lost.class, EntityDataSerializers.BOOLEAN);
    }

    private static class LostRangedBowAttackGoal extends RangedBowAttackGoal<Lost> {
        protected final Lost lost;

        public LostRangedBowAttackGoal(Lost lost, double speedModifier, int attackIntervalMin, float attackRadius) {
            speedModifier *= 0.5F;
            super(lost, speedModifier, attackIntervalMin, attackRadius);
            this.lost = lost;
        }

        @Override
        public void start() {
            super.start();
            this.lost.setIsCrouching(true);
        }

        @Override
        public void stop() {
            super.stop();
            this.lost.setIsCrouching(false);
        }
    }
}
