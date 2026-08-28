package com.github.pigsteel.eum.world.entity.monster.skeleton;

import com.github.pigsteel.eum.core.EUMSoundEvents;
import com.github.pigsteel.eum.world.entity.ProjectileUtil;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FleeSunGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RangedBowAttackGoal;
import net.minecraft.world.entity.ai.goal.RestrictSunGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.golem.IronGolem;
import net.minecraft.world.entity.animal.turtle.Turtle;
import net.minecraft.world.entity.animal.wolf.Wolf;
import net.minecraft.world.entity.monster.skeleton.AbstractSkeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.entity.projectile.arrow.Arrow;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;

public class Lost extends AbstractSkeleton {
    private final LostRangedBowAttackGoal bowGoal = new LostRangedBowAttackGoal(this, 1.0D, 20, 15.0F);

    public Lost(EntityType<? extends Lost> type, Level level) {
        super(type, level);
    }

    public void playAmbientSound() {
        if(!this.isCrouching()) {
            this.makeSound(this.getAmbientSound());
        }
    }

    protected void defineSynchedData(final SynchedEntityData.Builder entityData) {
        super.defineSynchedData(entityData);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return EUMSoundEvents.LOST_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(final DamageSource source) {
        return EUMSoundEvents.LOST_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return EUMSoundEvents.LOST_DEATH.get();
    }

    @Override
    public SoundEvent getStepSound() {
        return EUMSoundEvents.LOST_STEP.get();
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
            ItemStack itemstack = this.getItemInHand(ProjectileUtil.getWeaponHoldingHand(this, new Item[] { Items.BOW }));
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

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new RestrictSunGoal(this));
        this.goalSelector.addGoal(3, new FleeSunGoal(this, 1.0));
        this.goalSelector.addGoal(3, new AvoidEntityGoal(this, Wolf.class, 6.0F, 1.0, 1.2));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, IronGolem.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, Turtle.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR));
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
			if (this.lost.getPose() == Pose.STANDING) this.lost.setPose(Pose.CROUCHING);
        }

        @Override
        public void stop() {
            super.stop();
            if(this.lost.getPose() == Pose.CROUCHING) this.lost.setPose(Pose.STANDING);
        }
    }
}
