package com.github.pigsteel.smcm.entity.skeleton;

import com.github.pigsteel.smcm.entity.smcm$ProjectileUtil;
import com.github.pigsteel.smcm.registry.DataAttachments;
import com.github.pigsteel.smcm.registry.smcm$Registries;
import com.github.pigsteel.smcm.services.Services;
import net.minecraft.core.Holder;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.golem.IronGolem;
import net.minecraft.world.entity.animal.turtle.Turtle;
import net.minecraft.world.entity.animal.wolf.Wolf;
import net.minecraft.world.entity.monster.CrossbowAttackMob;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.skeleton.AbstractSkeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.variant.SpawnContext;
import net.minecraft.world.entity.variant.VariantUtils;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

public class Sunken extends AbstractSkeleton implements CrossbowAttackMob, Shearable {
    private static final EntityDataAccessor<Boolean> IS_CHARGING_CROSSBOW;
    private final RangedCrossbowAttackGoal crossbowGoal = new RangedCrossbowAttackGoal(this, 1.0, 16);

    public Sunken(EntityType<? extends Sunken> type, final Level level) {
        super(type, level);
        this.setPathfindingMalus(PathType.WATER, 0.0F);
    }

    protected void defineSynchedData(final SynchedEntityData.Builder entityData) {
        super.defineSynchedData(entityData);
        entityData.define(IS_CHARGING_CROSSBOW, false);
    }

    @Override
    public boolean canUseNonMeleeWeapon(ItemStack item) {
        return item.is(Items.CROSSBOW) || item.is(Items.BOW);
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

    @Override
    public void reassessWeaponGoal() {
        if (this.level() != null && !this.level().isClientSide()) {
            this.goalSelector.removeGoal(this.meleeGoal);
            this.goalSelector.removeGoal(this.bowGoal);
            this.goalSelector.removeGoal(this.crossbowGoal);
            ItemStack usedWeapon = this.getItemInHand(smcm$ProjectileUtil.getWeaponHoldingHand(this, new Item[] {Items.BOW, Items.CROSSBOW}));
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
    public SpawnGroupData finalizeSpawn(
            ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason spawnReason, @Nullable SpawnGroupData groupData
    ) {
        VariantUtils.selectVariantToSpawn(SpawnContext.create(level, this.blockPosition()), smcm$Registries.SUNKEN_VARIANT).ifPresent(this::setVariant);
        return super.finalizeSpawn(level, difficulty, spawnReason, groupData);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.FOLLOW_RANGE, (double)32.0F).add(Attributes.STEP_HEIGHT, (double)1.0F);
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

    }

    @Override
    public void performRangedAttack(final LivingEntity target, final float power) {
        ItemStack weaponItem = this.getItemInHand(smcm$ProjectileUtil.getWeaponHoldingHand(this, new Item[] { Items.BOW, Items.CROSSBOW } ));

        if(weaponItem.is(Items.CROSSBOW)) {
            this.performCrossbowAttack(this, 1.6F);
        } else if (weaponItem.is(Items.BOW)) {
            super.performRangedAttack(target, power);
        }
    }

    public boolean isChargingCrossbow() {
        return this.entityData.get(IS_CHARGING_CROSSBOW);
    }

    @Override
    public boolean readyForShearing() {
        return false;
    }

    public void setChargingCrossbow(boolean isCharging) {
        this.entityData.set(IS_CHARGING_CROSSBOW, isCharging);
    }

    public void onCrossbowAttackPerformed() {
        this.noActionTime = 0;
    }

    static {
        IS_CHARGING_CROSSBOW = SynchedEntityData.defineId(Sunken.class, EntityDataSerializers.BOOLEAN);
    }

    public ResourceKey<SunkenVariant> getVariantKey() {
        return Services.ATTACHMENTS.get(
                this,
                DataAttachments.SUNKEN_VARIANT
        );
    }

    public void setVariantKey(ResourceKey<SunkenVariant> variantKey) {
        Services.ATTACHMENTS.set(
                this,
                DataAttachments.SUNKEN_VARIANT,
                variantKey
        );
    }

    public Holder<SunkenVariant> getVariantHolder() {
        return this.level()
                .registryAccess()
                .lookupOrThrow(smcm$Registries.SUNKEN_VARIANT)
                .getOrThrow(this.getVariantKey());
    }

    public SunkenVariant getVariant() {
        return this.getVariantHolder().value();
    }

    public void setVariant(Holder<SunkenVariant> holder) {
        this.setVariantKey(
                holder.unwrapKey().orElse(SunkenVariants.NORMAL)
        );
    }

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
}
