package com.github.pigsteel.smcm.entity;

import com.github.pigsteel.smcm.entity.ai.goal.NecromancerBeamAttackGoal;
import com.github.pigsteel.smcm.entity.ai.goal.NecromancerSummonGoal;
import com.github.pigsteel.smcm.registry.smcm$SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.UUIDUtil;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FleeSunGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RestrictSunGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.golem.IronGolem;
import net.minecraft.world.entity.animal.turtle.Turtle;
import net.minecraft.world.entity.animal.wolf.Wolf;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Necromancer extends Monster {
    private static final String SUMMONED_MOBS_TAG = "SummonedMobs";

    private final Set<UUID> summonedMobs = new HashSet<>();

    private boolean cloakInitialized;

    public double cloakX;
    public double cloakY;
    public double cloakZ;

    public double cloakXOld;
    public double cloakYOld;
    public double cloakZOld;

    public final AnimationState summonAnimationState = new AnimationState();
    public final AnimationState shootingAnimationState = new AnimationState();

    public Necromancer(EntityType<? extends Monster> type, Level level) {
        super(type, level);

        this.cloakX = this.getX();
        this.cloakY = this.getY();
        this.cloakZ = this.getZ();

        this.cloakXOld = this.cloakX;
        this.cloakYOld = this.cloakY;
        this.cloakZOld = this.cloakZ;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new RestrictSunGoal(this));
        this.goalSelector.addGoal(3, new FleeSunGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Wolf.class, 6.0F, 1.0D, 1.2D));

        this.goalSelector.addGoal(4, new NecromancerSummonGoal(this));
        this.goalSelector.addGoal(5, new NecromancerBeamAttackGoal(this));

        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 16.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Turtle.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> accessor) {
        super.onSyncedDataUpdated(accessor);
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);

        ValueOutput.TypedOutputList<UUID> summonedMobs =
                output.list(SUMMONED_MOBS_TAG, UUIDUtil.CODEC);

        for (UUID uuid : this.summonedMobs) {
            summonedMobs.add(uuid);
        }
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);

        this.summonedMobs.clear();

        for (UUID uuid : input.listOrEmpty(SUMMONED_MOBS_TAG, UUIDUtil.CODEC)) {
            this.summonedMobs.add(uuid);
        }
    }

    @Override
    public void tick() {
        super.tick();

        this.tickCloak();
    }


    @Override
    public void aiStep() {
        super.aiStep();

        if (this.level().isClientSide() && this.getNecromancerPose() == NecromancerPose.SUMMONING) {
            this.spawnSummoningOrbParticles(this.summonAnimationState);
        }
    }

    private void spawnSummoningOrbParticles(AnimationState state) {
        float bodyYaw = this.yBodyRot * Mth.DEG_TO_RAD;

        double scale = this.getScale();

        double forwardX = -Mth.sin(bodyYaw);
        double forwardZ = Mth.cos(bodyYaw);

        double rightX = Mth.cos(bodyYaw);
        double rightZ = Mth.sin(bodyYaw);

        /*
         * Main-hand side. Flip this if particles appear on the wrong side.
         */
        double inverse = this.getMainArm() == HumanoidArm.LEFT ? 1.0D : -1.0D;

        double sideOffset = 0.45D * scale * inverse;
        double forwardOffset = 0.5D * scale;
        double heightOffset = 3.45D * scale;

        double orbX = this.getX()
                + rightX * sideOffset
                + forwardX * forwardOffset;

        double orbY = this.getY() + heightOffset;

        double orbZ = this.getZ()
                + rightZ * sideOffset
                + forwardZ * forwardOffset;

        double swirl = this.tickCount * 0.45D;
        double jitterRadius = 1.1D * scale;

        double jitterX = Math.cos(swirl) * jitterRadius;
        double jitterZ = Math.sin(swirl) * jitterRadius;

        this.level().addParticle(
                ParticleTypes.TRIAL_OMEN,
                orbX + jitterX,
                orbY + this.getRandom().nextGaussian() * 0.03D,
                orbZ + jitterZ,
                0.0D,
                0.01D,
                0.0D
        );
    }

    private void tickCloak() {
        if (!this.cloakInitialized) {
            this.resetCloakPosition();
            return;
        }

        this.cloakXOld = this.cloakX;
        this.cloakYOld = this.cloakY;
        this.cloakZOld = this.cloakZ;

        double dx = this.getX() - this.cloakX;
        double dy = this.getY() - this.cloakY;
        double dz = this.getZ() - this.cloakZ;

        double maxDistance = 10.0D;

        if (dx * dx + dy * dy + dz * dz > maxDistance * maxDistance) {
            this.resetCloakPosition();
            return;
        }

        this.cloakX += dx * 0.25D;
        this.cloakY += dy * 0.25D;
        this.cloakZ += dz * 0.25D;
    }

    public void resetCloakPosition() {
        this.cloakX = this.getX();
        this.cloakY = this.getY();
        this.cloakZ = this.getZ();

        this.cloakXOld = this.cloakX;
        this.cloakYOld = this.cloakY;
        this.cloakZOld = this.cloakZ;

        this.cloakInitialized = true;
    }

    public Set<UUID> getSummonedMobs() {
        return this.summonedMobs;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 100.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.FOLLOW_RANGE, 32.0D);
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

    public NecromancerPose getNecromancerPose() {
        return NecromancerPose.STANDING;
    }

    public enum NecromancerPose implements StringRepresentable {
        STANDING(0, "standing"),
        DYING(1, "dying"),
        SUMMONING(2, "summoning"),
        SHOOTING(3, "shooting");

        private final int id;
        private final String name;

        NecromancerPose(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String getSerializedName() {
            return this.name;
        }
    }
}