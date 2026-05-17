package com.github.pigsteel.smcm.world.entity.monster;

import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrownLingeringPotion;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class VilerWitch extends Witch {
    public VilerWitch(EntityType<? extends VilerWitch> type, Level level) {
        super(type, level);
    }

    @Override
    protected float getDamageAfterMagicAbsorb(final DamageSource damageSource, float damage) {
        damage = super.getDamageAfterMagicAbsorb(damageSource, damage);
        if (damageSource.getEntity() == this) {
            damage = 0.0F;
        }

        if (damageSource.is(DamageTypeTags.WITCH_RESISTANT_TO)) {
            damage *= 0.15F;
        }

        return damage;
    }

	@Override
	public float getVoicePitch() {
		return super.getVoicePitch() - 0.1F;
	}

    @Override
    public void performRangedAttack(final LivingEntity target, final float power) {
        if (!this.isDrinkingPotion()) {
            Vec3 targetMovement = target.getDeltaMovement();
            double xd = target.getX() + targetMovement.x - this.getX();
            double yd = target.getEyeY() - 1.1F - this.getY();
            double zd = target.getZ() + targetMovement.z - this.getZ();
            double dist = Math.sqrt(xd * xd + zd * zd);
            Holder<Potion> potion = Potions.HARMING;
            if (target instanceof Raider) {
                if (target.getHealth() <= 4.0F) {
                    potion = Potions.HEALING;
                } else {
                    potion = Potions.REGENERATION;
                }

                this.setTarget(null);
            } else if (dist >= 8.0 && !target.hasEffect(MobEffects.SLOWNESS)) {
                potion = Potions.SLOWNESS;
            } else if (target.getHealth() >= 8.0F && !target.hasEffect(MobEffects.POISON)) {
                potion = Potions.POISON;
            } else if (dist <= 3.0 && !target.hasEffect(MobEffects.WEAKNESS) && this.random.nextFloat() < 0.25F) {
                potion = Potions.WEAKNESS;
            }

            if (this.level() instanceof ServerLevel serverLevel) {
                ItemStack itemStack = PotionContents.createItemStack(Items.LINGERING_POTION, potion);
                Projectile.spawnProjectileUsingShoot(ThrownLingeringPotion::new, serverLevel, itemStack, this, xd, yd + dist * 0.2, zd, dist <= 2.0 ? 0.45F : 0.75F, 8.0F);
            }

            if (!this.isSilent()) {
                this.level()
                        .playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.WITCH_THROW, this.getSoundSource(), 1.0F, 0.8F + this.random.nextFloat() * 0.4F);
            }
        }
    }
}
