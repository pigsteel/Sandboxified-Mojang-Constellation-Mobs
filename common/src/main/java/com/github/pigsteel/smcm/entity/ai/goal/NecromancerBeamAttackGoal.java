package com.github.pigsteel.smcm.entity.ai.goal;

import com.github.pigsteel.smcm.entity.Necromancer;
import com.github.pigsteel.smcm.registry.smcm$SoundEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class NecromancerBeamAttackGoal extends Goal {
    private static final int WINDUP_TIME = 20;
    private static final int COOLDOWN_TIME = 20 * 3;

    private static final double ATTACK_RANGE_SQR = 18.0D * 18.0D;
    private static final double STOP_RANGE_SQR = 12.0D * 12.0D;

    private final Necromancer necromancer;

    private int windupTicks;
    private int cooldownTicks;
    private boolean charging;

    public NecromancerBeamAttackGoal(Necromancer necromancer) {
        this.necromancer = necromancer;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        LivingEntity target = this.necromancer.getTarget();

        return target != null
                && target.isAlive();
                //&& !this.necromancer.isSummoning();
    }

    @Override
    public boolean canContinueToUse() {
        LivingEntity target = this.necromancer.getTarget();

        return target != null
                && target.isAlive();
                //&& !this.necromancer.isSummoning();
    }

    @Override
    public void start() {
        this.windupTicks = 0;
        this.charging = false;
    }

    @Override
    public void stop() {
        this.abortCharge();
        this.necromancer.getNavigation().stop();
    }

    @Override
    public void tick() {
        LivingEntity target = this.necromancer.getTarget();

        if (target == null || !target.isAlive()) {
            this.abortCharge();
            return;
        }

        this.necromancer.getLookControl().setLookAt(target, 30.0F, 30.0F);

        if (this.cooldownTicks > 0) {
            --this.cooldownTicks;
        }

        double distanceSqr = this.necromancer.distanceToSqr(target);
        boolean canSee = this.necromancer.getSensing().hasLineOfSight(target);

        if (distanceSqr > ATTACK_RANGE_SQR || !canSee) {
            this.necromancer.getNavigation().moveTo(target, 1.0D);
            this.abortCharge();
            return;
        }

        if (distanceSqr > STOP_RANGE_SQR && !this.charging) {
            this.necromancer.getNavigation().moveTo(target, 0.85D);
        } else {
            this.necromancer.getNavigation().stop();
        }

        if (this.cooldownTicks > 0) {
            this.abortCharge();
            return;
        }

        if (!this.charging) {
            this.beginCharge();
        }

        --this.windupTicks;

        if (this.windupTicks <= 0 && this.necromancer.level() instanceof ServerLevel serverLevel) {
            this.fireBeam(serverLevel, target);
            this.cooldownTicks = COOLDOWN_TIME;
            this.finishCharge();
        }
    }

    private void beginCharge() {
        this.charging = true;
        this.windupTicks = WINDUP_TIME;

        /*
         * Start the full visual timeline once.
         * Do not repeatedly call setBeaming(true) every tick.
         */
        //this.necromancer.startBeamAnimation();

        this.necromancer.playSound(
                smcm$SoundEvents.NECROMANCER_SPELL.get(),
                1.0F,
                1.0F
        );
    }

    private void finishCharge() {
        /*
         * Do not call setBeaming(false) here.
         * The entity's beam animation timeline should finish/recover naturally.
         */
        this.charging = false;
        this.windupTicks = 0;
    }

    private void abortCharge() {
        this.charging = false;
        this.windupTicks = 0;

        /*
         * If you add stopBeamAnimation(), call it here.
         * Otherwise, this at least tells the visual state to recover.
         */
        //this.necromancer.setBeaming(false);
    }

    private void fireBeam(ServerLevel level, LivingEntity target) {
        target.hurt(
                this.necromancer.damageSources().magic(),
                6.0F
        );

        level.playSound(
                null,
                this.necromancer.blockPosition(),
                smcm$SoundEvents.NECROMANCER_SPELL.get(),
                SoundSource.HOSTILE,
                1.0F,
                1.2F
        );

        // Add beam particles here later.
    }
}