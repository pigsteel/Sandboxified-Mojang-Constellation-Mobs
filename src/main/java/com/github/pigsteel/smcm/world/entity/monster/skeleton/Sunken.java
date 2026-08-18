package com.github.pigsteel.smcm.world.entity.monster.skeleton;

import com.github.pigsteel.smcm.core.smcm$DataComponents;
import com.github.pigsteel.smcm.core.smcm$EntityDataSerializers;
import com.github.pigsteel.smcm.core.smcm$LootTables;
import com.github.pigsteel.smcm.core.smcm$Registries;
import com.github.pigsteel.smcm.core.smcm$SoundEvents;
import com.github.pigsteel.smcm.world.entity.ProjectileUtil;
import com.github.pigsteel.smcm.world.entity.monster.zombie.Reclaimed;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Shearable;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FleeSunGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RangedCrossbowAttackGoal;
import net.minecraft.world.entity.ai.goal.RestrictSunGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.golem.IronGolem;
import net.minecraft.world.entity.animal.turtle.Turtle;
import net.minecraft.world.entity.animal.wolf.Wolf;
import net.minecraft.world.entity.monster.CrossbowAttackMob;
import net.minecraft.world.entity.monster.skeleton.AbstractSkeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.variant.SpawnContext;
import net.minecraft.world.entity.variant.VariantUtils;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.Nullable;

public class Sunken extends AbstractSkeleton implements CrossbowAttackMob, Shearable {
    private static final int TIME_BEFORE_CORAL_DEATH = 500;

    private static final EntityDataAccessor<Holder<SunkenVariant>> DATA_VARIANT_ID;
    private static final EntityDataAccessor<Boolean> IS_CHARGING_CROSSBOW;
    private static final EntityDataAccessor<Boolean> IS_SHEARED;
    private static final EntityDataAccessor<Boolean> IS_CORAL_DEAD;
    private final RangedCrossbowAttackGoal<Sunken> crossbowGoal = new RangedCrossbowAttackGoal<>(this, 1.0, 16);
    private int coralDeathTimer;

    public Sunken(EntityType<? extends Sunken> type, final Level level) {
        super(type, level);
        this.setPathfindingMalus(PathType.WATER, 0.0F);
    }

    protected void defineSynchedData(final SynchedEntityData.Builder entityData) {
        super.defineSynchedData(entityData);
        entityData.define(DATA_VARIANT_ID, VariantUtils.getDefaultOrAny(this.registryAccess(), SunkenVariants.NORMAL));
        entityData.define(IS_CHARGING_CROSSBOW, false);
        entityData.define(IS_SHEARED, false);
        entityData.define(IS_CORAL_DEAD, false);
    }

    @Override
    public InteractionResult mobInteract(final Player player, final InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (itemStack.is(Items.SHEARS) && this.readyForShearing()){
            if (this.level() instanceof ServerLevel level) {
                this.shear(level, SoundSource.PLAYERS, itemStack);
                this.gameEvent(GameEvent.SHEAR, player);
                itemStack.hurtAndBreak(1, player, hand.asEquipmentSlot());
            }

            return InteractionResult.SUCCESS;
        } else {
            return super.mobInteract(player, hand);
        }
    }

    @Override
    public void tick() {
        super.tick();

        if(!this.level().isClientSide() && this.isAlive() && !this.isNoAi() && !this.isCoralDead()) {
            if(this.isInWater()) {
                this.coralDeathTimer = 0;
            } else {
                if (this.coralDeathTimer < TIME_BEFORE_CORAL_DEATH) {
                    this.coralDeathTimer++;
                } else {
                    this.setIsCoralDead(true);
                }
            }
        }
    }

    @Override
    public boolean canUseNonMeleeWeapon(ItemStack item) {
        return item.is(Items.CROSSBOW) || item.is(Items.BOW);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new RestrictSunGoal(this));
        this.goalSelector.addGoal(3, new FleeSunGoal(this, 1.0));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Wolf.class, 6.0F, 1.0, 1.2));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, IronGolem.class, true));
    }

    @Override
    public void reassessWeaponGoal() {
        if (!this.level().isClientSide()) {
            this.goalSelector.removeGoal(this.meleeGoal);
            this.goalSelector.removeGoal(this.bowGoal);
            this.goalSelector.removeGoal(this.crossbowGoal);
            ItemStack usedWeapon = this.getItemInHand(ProjectileUtil.getWeaponHoldingHand(this, new Item[] {Items.BOW, Items.CROSSBOW}));
            if (usedWeapon.is(Items.CROSSBOW)) {
                this.goalSelector.addGoal(4, this.crossbowGoal);
            } else if (usedWeapon.is(Items.BOW)) {
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
    protected void addAdditionalSaveData(final ValueOutput output) {
        super.addAdditionalSaveData(output);
        VariantUtils.writeVariant(output, this.getVariant());
        output.putBoolean("Sheared", this.isSheared());
        output.putBoolean("CoralDead", this.isCoralDead());
        output.putInt("CoralDeathTimer", this.coralDeathTimer);
    }

    @Override
    protected void readAdditionalSaveData(final ValueInput input) {
        super.readAdditionalSaveData(input);
        VariantUtils.readVariant(input, smcm$Registries.SUNKEN_VARIANT).ifPresent(this::setVariant);
        this.coralDeathTimer = input.getIntOr("CoralDeathTimer", 0);
        this.setIsCoralDead(input.getBooleanOr("CoralDead", false));
        this.setSheared(input.getBooleanOr("Sheared", false));
    }

    @Override
    public @Nullable SpawnGroupData finalizeSpawn(
            ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason spawnReason, @Nullable SpawnGroupData groupData
    ) {
        VariantUtils.selectVariantToSpawn(SpawnContext.create(level, this.blockPosition()), smcm$Registries.SUNKEN_VARIANT).ifPresent(this::setVariant);
        return super.finalizeSpawn(level, difficulty, spawnReason, groupData);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return AbstractSkeleton.createAttributes().add(Attributes.FOLLOW_RANGE, (double)32.0F).add(Attributes.STEP_HEIGHT, (double)1.0F);
    }

    @Override
    protected void populateDefaultEquipmentSlots(final RandomSource random, final DifficultyInstance difficulty) {
        super.populateDefaultEquipmentSlots(random, difficulty);
        ItemStack spawnWeapon = (double) this.random.nextFloat() < (double) 0.5F ? new ItemStack(Items.CROSSBOW) : new ItemStack(Items.BOW);
        this.setItemSlot(EquipmentSlot.MAINHAND, spawnWeapon);
    }

    @Override
    public SoundEvent getAmbientSound() {
        return SoundEvents.BOGGED_AMBIENT;
    }

    @Override
    public SoundEvent getHurtSound(final DamageSource source) {
        return SoundEvents.BOGGED_HURT;
    }

    @Override
    public SoundEvent getDeathSound() {
        return SoundEvents.BOGGED_DEATH;
    }

    public SoundEvent getStepSound() {
        return SoundEvents.BOGGED_STEP;
    }

    @Override
    public void shear(ServerLevel level, SoundSource soundSource, ItemStack tool) {
		level.playSound(null, this, smcm$SoundEvents.SUNKEN_SHEAR.get(), soundSource, 1.0F, 1.0F);

		this.dropFromShearingLootTable(
				level,
				smcm$LootTables.SHEAR_SUNKEN,
				tool,
				(l, drop) -> this.spawnAtLocation(l, drop, this.getEyeHeight())
		);

        setSheared(true);
    }

    @Override
    public void performRangedAttack(final LivingEntity target, final float power) {
        ItemStack weaponItem = this.getItemInHand(ProjectileUtil.getWeaponHoldingHand(this, new Item[] { Items.BOW, Items.CROSSBOW } ));

        if(weaponItem.is(Items.CROSSBOW)) {
            this.performCrossbowAttack(this, 1.6F);
        } else if (weaponItem.is(Items.BOW)) {
            super.performRangedAttack(target, power);
        }
    }

    public boolean isSheared() {
        return this.getEntityData().get(IS_SHEARED);
    }

    public boolean isCoralDead() {
        return (Boolean) this.getEntityData().get(IS_CORAL_DEAD);
    }

    public void setIsCoralDead(boolean isCoralDead) {
        this.entityData.set(IS_CORAL_DEAD, isCoralDead);
    }

    public void setSheared(boolean isSheared) {
        this.entityData.set(IS_SHEARED, isSheared);
    }

    public boolean isChargingCrossbow() {
        return this.entityData.get(IS_CHARGING_CROSSBOW);
    }

    @Override
    public boolean readyForShearing() {
        return this.getVariant().value().modelAndTexture().model().isCoral() && !this.isSheared();
    }

    public void setChargingCrossbow(boolean isCharging) {
        this.entityData.set(IS_CHARGING_CROSSBOW, isCharging);
    }

    public void onCrossbowAttackPerformed() {
        this.noActionTime = 0;
    }

    static {
		DATA_VARIANT_ID = SynchedEntityData.defineId(Sunken.class, smcm$EntityDataSerializers.SUNKEN_VARIANT);
        IS_CHARGING_CROSSBOW = SynchedEntityData.defineId(Sunken.class, EntityDataSerializers.BOOLEAN);
        IS_SHEARED = SynchedEntityData.defineId(Sunken.class, EntityDataSerializers.BOOLEAN);
        IS_CORAL_DEAD = SynchedEntityData.defineId(Sunken.class, EntityDataSerializers.BOOLEAN);
    }

    public Holder<SunkenVariant> getVariant() {
        return this.entityData.get(DATA_VARIANT_ID);
    }

    public void setVariant(Holder<SunkenVariant> variant) {
        this.entityData.set(DATA_VARIANT_ID, variant);
    }

    protected void applyImplicitComponents(final DataComponentGetter components) {
        this.applyImplicitComponentIfPresent(components, smcm$DataComponents.SUNKEN_VARIANT.get());
		this.applyImplicitComponentIfPresent(components, smcm$DataComponents.IS_CORAL_DEAD.get());
    }


	@Override
	public <T> @Nullable T get(final DataComponentType<? extends T> type) {
		if (type == smcm$DataComponents.SUNKEN_VARIANT.get()) {
			return (T) castComponentValue(smcm$DataComponents.SUNKEN_VARIANT.get(), this.getVariant());
		} else if (type == smcm$DataComponents.IS_CORAL_DEAD.get()) {
			return (T) castComponentValue(smcm$DataComponents.IS_CORAL_DEAD.get(), this.isCoralDead());
		}

		return super.get(type);
	}

	@Override
	protected <T> boolean applyImplicitComponent(final DataComponentType<T> type, final T value) {
		if (type == smcm$DataComponents.SUNKEN_VARIANT.get()) {
			this.setVariant(castComponentValue(smcm$DataComponents.SUNKEN_VARIANT.get(), value));
			return true;
		} else if (type == smcm$DataComponents.IS_CORAL_DEAD.get()) {
			this.setIsCoralDead(castComponentValue(smcm$DataComponents.IS_CORAL_DEAD.get(), value));
			return true;
		} else {
			return super.applyImplicitComponent(type, value);
		}
	}

    /*
    protected void travelInWater(final Vec3 input, final double baseGravity, final boolean isFalling, final double oldY) {
        float slowDown = this.isSprinting() ? 0.9F : this.getWaterSlowDown();
        float sunkenSpeedFactor = 1.8F;
        float speed = 0.02F * sunkenSpeedFactor;
        float waterWalker = (float)this.getAttributeValue(Attributes.WATER_MOVEMENT_EFFICIENCY);
        if (!this.onGround()) {
            waterWalker *= 0.5F;
        }

        if (waterWalker > 0.0F) { // IF depth strider is higher than 0 (meaningless in our case)
            slowDown += (0.54600006F - slowDown) * waterWalker;
            speed += (this.getSpeed() - speed) * waterWalker;
        }

        if (this.hasEffect(MobEffects.DOLPHINS_GRACE)) {
            slowDown = 0.96F;
        }

        this.moveRelative(speed, input);
        this.move(MoverType.SELF, this.getDeltaMovement());
        Vec3 ladderMovement = this.getDeltaMovement();
        if (this.horizontalCollision && this.onClimbable()) {
            ladderMovement = new Vec3(ladderMovement.x, 0.2, ladderMovement.z);
        }

        ladderMovement = ladderMovement.multiply(slowDown, 0.8F, slowDown);
        this.setDeltaMovement(this.getFluidFallingAdjustedMovement(baseGravity, isFalling, ladderMovement));
        this.jumpOutOfFluid(oldY);
    }
     */

    /*
    public Vec3 getFluidFallingAdjustedMovement(final double baseGravity, final boolean isFalling, final Vec3 movement) {
        if (baseGravity != 0.0 && !this.isSprinting()) {
            double yd;

            if (isFalling && Math.abs(movement.y - 0.005) >= 0.003 && Math.abs(movement.y - baseGravity / 8.0) < 0.003) {
                yd = -0.003;
            } else {
                yd = movement.y - baseGravity / 8.0;
            }

            return new Vec3(movement.x, yd, movement.z);
        } else {
            return movement;
        }
    }
     */
}
