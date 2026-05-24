package com.github.pigsteel.smcm.entity.illager;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.golem.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.creaking.Creaking;
import net.minecraft.world.entity.monster.illager.AbstractIllager;
import net.minecraft.world.entity.npc.villager.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import net.minecraft.world.level.block.entity.BannerPatterns;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.Nullable;

import static net.minecraft.world.entity.raid.Raid.getOminousBannerTemplate;

public class Bruiser extends AbstractIllager {
    public Bruiser(EntityType<? extends Bruiser> type, Level level) {
        super(type, level);
    }

    @Override
    public void applyRaidBuffs(ServerLevel level, int wave, boolean isCaptain) {

    }

    @Override
    public SoundEvent getCelebrateSound() {
        return SoundEvents.RAVAGER_CELEBRATE;
    }

    @Override
    public SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.RAVAGER_HURT;
    }

    @Override
    public SoundEvent getDeathSound() {
        return SoundEvents.RAVAGER_DEATH;
    }

    @Override
    public SoundEvent getAmbientSound() {
        return SoundEvents.RAVAGER_AMBIENT;
    }

    @Override
    protected void playStepSound(final BlockPos pos, final BlockState blockState) {
        this.playSound(SoundEvents.RAVAGER_STEP, 0.15F, 1.0F);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.35F)
                .add(Attributes.FOLLOW_RANGE, 12.0)
                .add(Attributes.MAX_HEALTH, 30.0)
                .add(Attributes.ATTACK_DAMAGE, 5.0);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new AvoidEntityGoal(this, Creaking.class, 8.0F, 0.5, 0.7));
        this.goalSelector.addGoal(3, new RaiderOpenDoorGoal(this));
        this.goalSelector.addGoal(4, new HoldGroundAttackGoal(this, 10.0F));
        this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 0.5, false));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this, Raider.class).setAlertOthers());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, AbstractVillager.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, IronGolem.class, true));
        this.goalSelector.addGoal(8, new RandomStrollGoal(this, 0.1));
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));
    }

    @Override
    public IllagerArmPose getArmPose() {
        return this.isAggressive() ? IllagerArmPose.ATTACKING : IllagerArmPose.NEUTRAL;
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(
            final ServerLevelAccessor level, final DifficultyInstance difficulty, final EntitySpawnReason spawnReason, @Nullable final SpawnGroupData groupData
    ) {
        SpawnGroupData spawnGroupData = super.finalizeSpawn(level, difficulty, spawnReason, groupData);
        this.getNavigation().setCanOpenDoors(true);
        RandomSource random = level.getRandom();
        this.populateDefaultEquipmentSlots(random, difficulty);
        this.populateDefaultEquipmentEnchantments(level, random, difficulty);
        return spawnGroupData;
    }

    @Override
    protected void populateDefaultEquipmentSlots(final RandomSource random, final DifficultyInstance difficulty) {
        if (this.getCurrentRaid() == null) {
            var patternGetter = this.registryAccess().lookupOrThrow(Registries.BANNER_PATTERN);
            this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.MACE));

            ItemStack shield = new ItemStack(Items.SHIELD);

            shield.set(DataComponents.BANNER_PATTERNS, new BannerPatternLayers.Builder()
                    .addIfRegistered(patternGetter, BannerPatterns.RHOMBUS_MIDDLE, DyeColor.CYAN)
                    .addIfRegistered(patternGetter, BannerPatterns.STRIPE_BOTTOM, DyeColor.LIGHT_GRAY)
                    .addIfRegistered(patternGetter, BannerPatterns.STRIPE_CENTER, DyeColor.GRAY)
                    .addIfRegistered(patternGetter, BannerPatterns.BORDER, DyeColor.LIGHT_GRAY)
                    .addIfRegistered(patternGetter, BannerPatterns.STRIPE_MIDDLE, DyeColor.BLACK)
                    .addIfRegistered(patternGetter, BannerPatterns.HALF_HORIZONTAL, DyeColor.LIGHT_GRAY)
                    .addIfRegistered(patternGetter, BannerPatterns.CIRCLE_MIDDLE, DyeColor.LIGHT_GRAY)
                    .addIfRegistered(patternGetter, BannerPatterns.BORDER, DyeColor.BLACK)
                    .build());

            this.setItemSlot(EquipmentSlot.OFFHAND, shield);
        }
    }

    public static ItemStack getOminousBannerInstance(final HolderGetter<BannerPattern> patternGetter) {
        return getOminousBannerTemplate(patternGetter).create();
    }
}
