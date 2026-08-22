package com.github.pigsteel.smcm.world.entity.monster.redstonegolem;

import com.github.pigsteel.smcm.core.SMCMSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.AABB;

// Since this guy is so big we'll have to use a complex hitbox similar to the Ender Dragon
// Could maybe have limb health
public class RedstoneGolem extends Raider {
    private float glowAnimation;

    public RedstoneGolem(EntityType<? extends RedstoneGolem> type, Level level) {
        super(type, level);
        this.setPathfindingMalus(PathType.LEAVES, 0.0F);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0, true));
        this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.9, 32.0F));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

	@Override
	public void applyRaidBuffs(ServerLevel level, int wave, boolean isCaptain) {

	}

	public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 250.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0)
                .add(Attributes.ATTACK_DAMAGE, 15.0)
                .add(Attributes.STEP_HEIGHT, 1.0);
    }

	@Override
	public void handleDamageEvent(DamageSource source) {
		super.handleDamageEvent(source);

		this.walkAnimation.setSpeed(0.0F);
	}

    int i;

    @Override
    public void tick() {
        super.tick();
        i++;
        glowAnimation = Mth.cos(1.0F / 11.0F * i);
    }

    @Override
    public boolean canSpawnSprintParticle() {
        return this.getDeltaMovement().horizontalDistanceSqr() > 2.5000003E-7F && this.random.nextInt(5) == 0;
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (this.isAlive()) {
            if (this.isImmobile()) {
                this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.0);
            } else {
                double maxSpeed = this.getTarget() != null ? 0.35 : 0.3;
                double baseValue = this.getAttribute(Attributes.MOVEMENT_SPEED).getBaseValue();
                this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(Mth.lerp(0.1, baseValue, maxSpeed));
            }

            if (this.level() instanceof ServerLevel serverLevel && (this.horizontalCollision || this.verticalCollisionBelow) && serverLevel.getGameRules().get(GameRules.MOB_GRIEFING)) {
                boolean destroyedBlock = false;
                AABB bb = this.getBoundingBox().inflate(0.2);

                for (BlockPos pos : BlockPos.betweenClosed(
                        Mth.floor(bb.minX), Mth.floor(bb.minY), Mth.floor(bb.minZ), Mth.floor(bb.maxX), Mth.floor(bb.maxY), Mth.floor(bb.maxZ)
                )) {
                    BlockState state = serverLevel.getBlockState(pos);
                    Block block = state.getBlock();
                    if (block instanceof LeavesBlock) {
                        destroyedBlock = serverLevel.destroyBlock(pos, true, this) || destroyedBlock;
                    }
                }
            }
        }
    }

	@Override
	public SoundEvent getCelebrateSound() {
		return null;
	}

	public boolean hurt(final ServerLevel level, final RedstoneGolemPart part, final DamageSource source, float damage) {
        /*
        damage = this.phaseManager.getCurrentPhase().onHurt(source, damage);
        if (part != this.head) {
            damage = damage / 4.0F + Math.min(damage, 1.0F);
        }

        if (damage < 0.01F) {
            return false;
        }

        if (source.getEntity() instanceof Player || source.is(DamageTypeTags.ALWAYS_HURTS_ENDER_DRAGONS)) {
            float healthBefore = this.getHealth();
            this.reallyHurt(level, source, damage);
            if (this.phaseManager.getCurrentPhase().isSitting()) {
                this.sittingDamageReceived = this.sittingDamageReceived + healthBefore - this.getHealth();
                if (this.sittingDamageReceived > 0.25F * this.getMaxHealth()) {
                    this.sittingDamageReceived = 0.0F;
                    this.phaseManager.setPhase(EnderDragonPhase.TAKEOFF);
                }
            }
        }

         */

        return true;
    }

    public float getGlowAnimation(float partialTicks) {
        return glowAnimation;
    }

    @Override
    public SoundEvent getAmbientSound() {
        return SMCMSoundEvents.REDSTONE_GOLEM_AMBIENT.get();
    }

    @Override
    public SoundEvent getDeathSound() {
        return SMCMSoundEvents.REDSTONE_GOLEM_DEATH.get();
    }

    @Override
    protected float nextStep() {
        return this.moveDist + 0.55F;
    }

    @Override
    protected void playStepSound(final BlockPos pos, final BlockState blockState) {
        this.playSound(this.getStepSound(), 10.0F, 1.0F);
    }

    public SoundEvent getStepSound() {
        return SMCMSoundEvents.REDSTONE_GOLEM_STEP_LIGHT.get();
    }

    @Override
    public SoundEvent getHurtSound(DamageSource source) {
        return SMCMSoundEvents.REDSTONE_GOLEM_HURT.get();
    }
}
