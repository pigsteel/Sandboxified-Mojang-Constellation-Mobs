package com.github.pigsteel.smcm.world.entity.monster.necromancer;

import com.github.pigsteel.smcm.core.smcm$EntityDataSerializers;
import com.github.pigsteel.smcm.core.smcm$ParticleTypes;
import com.github.pigsteel.smcm.core.smcm$SoundEvents;
import com.github.pigsteel.smcm.util.EntityTypesUtil;
import com.github.pigsteel.smcm.world.entity.ai.sensing.smcm$SensorTypes;
import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.core.UUIDUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.util.profiling.Profiler;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.jspecify.annotations.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.IntFunction;

public class Necromancer extends Monster {
    private static final String SUMMONED_MOBS_TAG = "SummonedMobs";
	private static final Brain.Provider<Necromancer> BRAIN_PROVIDER;
	private static final EntityDataAccessor<NecromancerSpell> DATA_SPELL;
	private static final int DEFAULT_SPELLCASTING_TICKS = 0;
	protected int spellCastingTickCount = 0;

	private NecromancerSpell currentSpell;

    private final Set<UUID> summonedMobs = new HashSet<>();

    private boolean cloakInitialized;

	private Vector3f orbPosition = new Vector3f(0.0F);

    public double cloakX;
    public double cloakY;
    public double cloakZ;

    public double cloakXOld;
    public double cloakYOld;
    public double cloakZOld;

    public final AnimationState summonAnimationState = new AnimationState();
    public final AnimationState shootingAnimationState = new AnimationState();

    public Necromancer(EntityType<? extends Necromancer> type, Level level) {
        super(type, level);

        this.cloakX = this.getX();
        this.cloakY = this.getY();
        this.cloakZ = this.getZ();

        this.cloakXOld = this.cloakX;
        this.cloakYOld = this.cloakY;
        this.cloakZOld = this.cloakZ;

		this.currentSpell = NecromancerSpell.NONE;

		this.xpReward = 100;
    }

	@Override
	protected Brain<Necromancer> makeBrain(final Brain.Packed packedBrain) {
		Brain<Necromancer> brain = BRAIN_PROVIDER.makeBrain(this, packedBrain);
		return brain;
	}

	public void setOrbPosition(Vector3fc position) {
		this.orbPosition = (Vector3f) position;
	}

	@Override
	public Brain<Necromancer> getBrain() {
		return (Brain<Necromancer>) super.getBrain();
	}

    @Override
	protected void defineSynchedData(final SynchedEntityData.Builder entityData) {
		super.defineSynchedData(entityData);
		entityData.define(DATA_SPELL, NecromancerSpell.NONE);
	}

	@Override
	public void onSyncedDataUpdated(EntityDataAccessor<?> accessor) {
		if (this.level().isClientSide() && DATA_SPELL.equals(accessor)) {
			switch (this.getCurrentSpell()) {
				case NecromancerSpell.NONE -> resetAnimations();
				case NecromancerSpell.SUMMON -> triggerSummoningAnimation();
				case NecromancerSpell.SHOOT -> this.shootingAnimationState.start(this.tickCount);
			}
		}

		super.onSyncedDataUpdated(accessor);
	}

	public void resetAnimations() {
		this.summonAnimationState.stop();
		this.shootingAnimationState.stop();
	}

	public void triggerSummoningAnimation() {
		this.summonAnimationState.start(this.tickCount);

		this.spellCastingTickCount = 0;
	}

	public boolean isCastingSpell() {
		return this.getCurrentSpell() != NecromancerSpell.NONE;
	}

	public void setIsCastingSpell(final NecromancerSpell spell) {
		this.currentSpell = spell;
		this.entityData.set(DATA_SPELL, spell);
	}

	public NecromancerSpell getCurrentSpell() {
		return !this.level().isClientSide() ? this.currentSpell : this.entityData.get(DATA_SPELL);
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
    public void baseTick() {
        super.baseTick();
        if (this.level().isClientSide()) {

        }
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (this.level().isClientSide()) {
			if(this.summonAnimationState.isStarted()) spellCastingTickCount++;

            this.spawnSummoningOrbParticles();
        }
    }

	@Override
	public boolean canAttack(LivingEntity target) {
		return (target.is(EntityTypesUtil.PLAYER) || target.is(EntityTypesUtil.IRON_GOLEM)) && super.canAttack(target);
	}

	public @Nullable LivingEntity getTarget() {
		return this.getTargetFromBrain();
	}

    private void spawnSummoningOrbParticles() {
        /*float bodyYaw = this.yBodyRot * Mth.DEG_TO_RAD;

        double scale = this.getScale();

        double forwardX = -Mth.sin(bodyYaw);
        double forwardZ = Mth.cos(bodyYaw);

        double rightX = Mth.cos(bodyYaw);
        double rightZ = Mth.sin(bodyYaw);

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
        double jitterZ = Math.sin(swirl) * jitterRadius;*/

        this.level().addParticle(
				smcm$ParticleTypes.NECROMANCER_MAGIC.get(),
                this.getX() + orbPosition.x,
				this.getY() + orbPosition.y,
				this.getZ() + orbPosition.z,
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

	public void addSummonedMob(LivingEntity entity) {
		this.getSummonedMobs().add(entity.getUUID());
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

	public double getFiringYPosition() {
		return this.getY() + (double)(this.getBbHeight() / 2.0F) + (double)0.1F;
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

	public SoundEvent getPrepareSummonSound() {
		Component customName = this.getCustomName();
		if(customName != null && customName.getString().equals("Sandy")) {
			return smcm$SoundEvents.NECROMANCER_PREPARE_SUMMON_ALT.get();
		} else {
			return smcm$SoundEvents.NECROMANCER_PREPARE_SUMMON.get();
		}
	}

	protected void customServerAiStep(final ServerLevel level) {
		ProfilerFiller profiler = Profiler.get();
		profiler.push("necromancerBrain");
		this.getBrain().tick(level, this);
		profiler.pop();
		NecromancerAi.updateActivity(this.getBrain());
		super.customServerAiStep(level);
	}

	static {
		BRAIN_PROVIDER = Brain.provider(
				List.of(SensorType.NEAREST_LIVING_ENTITIES, SensorType.HURT_BY, SensorType.NEAREST_PLAYERS, smcm$SensorTypes.NECROMANCER_ATTACK_ENTITY_SENSOR.get()),
				NecromancerAi::getActivities
		);
		DATA_SPELL = SynchedEntityData.defineId(Necromancer.class, smcm$EntityDataSerializers.NECROMANCER_SPELL);
	}

	public enum NecromancerSpell implements StringRepresentable {
		NONE(0, "none"),
		SUMMON(1, "summon"),
		SHOOT(2, "shoot"),
		BLAST(3, "blast");

		public static final IntFunction<NecromancerSpell> BY_ID = ByIdMap.continuous(NecromancerSpell::id, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
		public static final Codec<NecromancerSpell> CODEC = StringRepresentable.fromEnum(NecromancerSpell::values);
		public static final StreamCodec<ByteBuf, NecromancerSpell> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, NecromancerSpell::id);

		private final int id;
		private final String name;

		NecromancerSpell(int id, String name) {
			this.id = id;
			this.name = name;
		}

		public int id() { return this.id; }

		@Override
		public String getSerializedName() {
			return this.name;
		}
	}
}
