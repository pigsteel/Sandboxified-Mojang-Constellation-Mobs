package com.github.pigsteel.eum.world.entity.monster.illager;

import com.github.pigsteel.eum.core.EUMSoundEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.golem.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.creaking.Creaking;
import net.minecraft.world.entity.monster.illager.AbstractIllager;
import net.minecraft.world.entity.npc.villager.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.level.Level;

public class Enchanter extends AbstractIllager { // Instead of spellcasting Illager, I'm going to make Enchanter be Abstract and implement my own functionality
    public Enchanter(EntityType<? extends Enchanter> type, Level level) {
        super(type, level);
		this.xpReward = 36;
    }

    @Override
    public void applyRaidBuffs(ServerLevel level, int wave, boolean isCaptain) {

    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, 0.5).add(Attributes.FOLLOW_RANGE, 18.0).add(Attributes.MAX_HEALTH, 16.0);
    }

    @Override
    public SoundEvent getCelebrateSound() {
        return EUMSoundEvents.ENCHANTER_CELEBRATE.get();
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return EUMSoundEvents.ENCHANTER_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(final DamageSource source) {
        return EUMSoundEvents.ENCHANTER_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return EUMSoundEvents.ENCHANTER_DEATH.get();
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Player.class, 8.0F, 0.5F, 0.7F));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Creaking.class, 8.0F, 0.5F, 0.7F));
		this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, IronGolem.class, 8.0F, 0.5F, 0.7F));
        this.goalSelector.addGoal(3, new RaiderOpenDoorGoal(this));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 0.6));
        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 15.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 15.0F));

		this.targetSelector.addGoal(1, new HurtByTargetGoal(this, Raider.class).setAlertOthers());
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
    }

    /*
    @Override
    public AbstractIllager.IllagerArmPose getArmPose() {
        return IllagerArmPose.SPELLCASTING;
    }
    */
}
