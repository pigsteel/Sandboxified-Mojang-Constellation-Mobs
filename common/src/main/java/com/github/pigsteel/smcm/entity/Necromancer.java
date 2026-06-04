package com.github.pigsteel.smcm.entity;

import com.github.pigsteel.smcm.registry.smcm$SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.golem.IronGolem;
import net.minecraft.world.entity.animal.turtle.Turtle;
import net.minecraft.world.entity.animal.wolf.Wolf;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.skeleton.AbstractSkeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Objects;

public class Necromancer extends Monster {
    private boolean smcm$cloakInitialized;

    public double smcm$cloakX;
    public double smcm$cloakY;
    public double smcm$cloakZ;

    public double smcm$cloakXOld;
    public double smcm$cloakYOld;
    public double smcm$cloakZOld;

    public Necromancer(EntityType<? extends Monster> type, Level level) {
        super(type, level);

        this.smcm$cloakX = this.getX();
        this.smcm$cloakY = this.getY();
        this.smcm$cloakZ = this.getZ();
        this.smcm$cloakXOld = this.smcm$cloakX;
        this.smcm$cloakYOld = this.smcm$cloakY;
        this.smcm$cloakZOld = this.smcm$cloakZ;
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(2, new RestrictSunGoal(this));
        this.goalSelector.addGoal(3, new FleeSunGoal(this, (double)1.0F));
        this.goalSelector.addGoal(3, new AvoidEntityGoal(this, Wolf.class, 6.0F, (double)1.0F, 1.2));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, (double)1.0F));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this, new Class[0]));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, IronGolem.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, Turtle.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR));
        this.targetSelector.addGoal(4, new MeleeAttackGoal(this, 1.0F, false) {
            {
                Objects.requireNonNull(Necromancer.this);
                Objects.requireNonNull(Necromancer.this);
            }

            public void stop() {
                super.stop();
                Necromancer.this.setAggressive(false);
            }

            public void start() {
                super.start();
                Necromancer.this.setAggressive(true);
            }
        });
    }

    @Override
    public void tick() {
        super.tick();

        this.smcm$tickCloak();
    }

    private void smcm$tickCloak() {
        if (!this.smcm$cloakInitialized) {
            this.smcm$resetCloakPosition();
            return;
        }

        this.smcm$cloakXOld = this.smcm$cloakX;
        this.smcm$cloakYOld = this.smcm$cloakY;
        this.smcm$cloakZOld = this.smcm$cloakZ;

        double dx = this.getX() - this.smcm$cloakX;
        double dy = this.getY() - this.smcm$cloakY;
        double dz = this.getZ() - this.smcm$cloakZ;

        double maxDistance = 10.0D;

        if (dx * dx + dy * dy + dz * dz > maxDistance * maxDistance) {
            this.smcm$resetCloakPosition();
            return;
        }

        this.smcm$cloakX += dx * 0.25D;
        this.smcm$cloakY += dy * 0.25D;
        this.smcm$cloakZ += dz * 0.25D;
    }

    public void smcm$resetCloakPosition() {
        this.smcm$cloakX = this.getX();
        this.smcm$cloakY = this.getY();
        this.smcm$cloakZ = this.getZ();

        this.smcm$cloakXOld = this.smcm$cloakX;
        this.smcm$cloakYOld = this.smcm$cloakY;
        this.smcm$cloakZOld = this.smcm$cloakZ;

        this.smcm$cloakInitialized = true;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 100.0F)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.FOLLOW_RANGE, 16.0F);
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockState) {
        this.playSound(this.getStepSound(), 0.15F, 1.0F);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return this.random.nextFloat() < 0.05F
                ? smcm$SoundEvents.NECROMANCER_LAUGH.get()
                : smcm$SoundEvents.NECROMANCER_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(final DamageSource source) {
        return smcm$SoundEvents.NECROMANCER_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return smcm$SoundEvents.NECROMANCER_DEATH.get();
    }

    protected SoundEvent getStepSound() {
        return smcm$SoundEvents.NECROMANCER_STEP.get();
    }
}
