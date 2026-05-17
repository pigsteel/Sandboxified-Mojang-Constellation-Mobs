package com.github.pigsteel.smcm.world.entity.projectile;

import com.github.pigsteel.smcm.core.smcm$EntityTypes;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class FrostbittenSnowball extends ThrowableItemProjectile {
	public FrostbittenSnowball(final EntityType<? extends FrostbittenSnowball> type, final Level level) {
		super(type, level);
	}

	public FrostbittenSnowball(final Level level, final LivingEntity mob, final ItemStack itemStack) {
		super(smcm$EntityTypes.FROSTBITTEN_SNOWBALL.get(), mob, level, itemStack);
	}

	public FrostbittenSnowball(final Level level, final double x, final double y, final double z, final ItemStack itemStack) {
		super(smcm$EntityTypes.FROSTBITTEN_SNOWBALL.get(), x, y, z, level, itemStack);
	}

	protected Item getDefaultItem() {
		return Items.SNOWBALL;
	}

	private ParticleOptions getParticle() {
		ItemStack item = this.getItem();
		return (ParticleOptions)(item.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemParticleOption(ParticleTypes.ITEM, ItemStackTemplate.fromNonEmptyStack(item)));
	}

	public void handleEntityEvent(final byte id) {
		if (id == 3) {
			ParticleOptions particle = this.getParticle();

			for(int i = 0; i < 8; ++i) {
				this.level().addParticle(particle, this.getX(), this.getY(), this.getZ(), (double)0.0F, (double)0.0F, (double)0.0F);
			}
		}

	}

	protected void onHitEntity(final EntityHitResult hitResult) {
		super.onHitEntity(hitResult);
		Entity entity = hitResult.getEntity();
		int damage = entity instanceof Blaze ? 3 : 1;
		Level level = this.level();
		if(level instanceof ServerLevel serverLevel) {
			boolean wasHurt = entity.hurtServer(serverLevel, this.damageSources().thrown(this, this.getOwner()), (float)damage);
			if(wasHurt && this.getOwner() instanceof LivingEntity thrower && entity instanceof LivingEntity living) {
				float difficulty = serverLevel
						.getCurrentDifficultyAt(thrower.blockPosition())
						.getEffectiveDifficulty();

				living.setTicksFrozen(living.getTicksFrozen() + 270 + (int)(20 * difficulty));
			}
		}
	}

	protected void onHit(final HitResult hitResult) {
		super.onHit(hitResult);
		if (!this.level().isClientSide()) {
			this.level().broadcastEntityEvent(this, (byte)3);
			this.discard();
		}

	}

	public void tick() {
		Vec3 position = this.position();

		for (int i = 0; i < 2; i++) {
			this.level()
					.addParticle(
							ParticleTypes.SNOWFLAKE,
							position.x,
							position.y,
							position.z,
							0,
							0.1,
							0
					);
		}

		super.tick();
	}
}
