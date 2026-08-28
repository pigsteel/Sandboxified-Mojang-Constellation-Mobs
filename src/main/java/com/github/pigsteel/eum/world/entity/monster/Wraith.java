package com.github.pigsteel.eum.world.entity.monster;

import com.github.pigsteel.eum.core.EUMSoundEvents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

//? neoforge {
import net.neoforged.neoforge.common.damagesource.DamageContainer;
//?}

public class Wraith extends Monster {
    public Wraith(EntityType<? extends Wraith> type, Level level) {
        super(type, level);
    }

	protected boolean isAffectedByBlocks() {
		return !this.isRemoved();
	}

	public void tick() {
		this.noPhysics = true;
		super.tick();
		this.noPhysics = false;
		this.setNoGravity(true);
	}

	protected float getDamageAfterMagicAbsorb(DamageSource damageSource, float damage) {
		damage = super.getDamageAfterMagicAbsorb(damageSource, damage);
		//? neoforge {
		if (damageSource.is(DamageTypeTags.IS_FIRE)) {
			this.damageContainers.peek().setNewDamage(damage + 3.0F);
			damage += 3.0F;
		}
		//?} fabric {
		/*if (damageSource.is(DamageTypeTags.IS_FIRE)) {
			damage += 3.0F;
		}
		*///?}

		return damage;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return EUMSoundEvents.WRAITH_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(final DamageSource source) {
		return EUMSoundEvents.WRAITH_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return EUMSoundEvents.WRAITH_DEATH.get();
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes()
				.add(Attributes.FOLLOW_RANGE, 35.0)
				.add(Attributes.MOVEMENT_SPEED, 1F);
	}

	@Override
	protected void registerGoals() {
		this.addBehaviourGoals();
	}

	protected void addBehaviourGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
		this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, Player.class, true));
	}
}
