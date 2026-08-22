package com.github.pigsteel.smcm.world.entity.projectile;

import com.github.pigsteel.smcm.core.SMCMEntityTypes;
import com.github.pigsteel.smcm.core.SMCMMobEffects;
import com.github.pigsteel.smcm.core.SMCMParticleTypes;
import com.github.pigsteel.smcm.core.SMCMSoundEvents;
import com.github.pigsteel.smcm.world.entity.monster.necromancer.Necromancer;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class NecromancerBall extends Projectile {
	private int age;

	public NecromancerBall(EntityType<? extends NecromancerBall> type, Level level) {
		super(type, level);
		age = 0;
	}

	public <E extends Necromancer> NecromancerBall(E necromancer, Level level) {
		this(SMCMEntityTypes.NECROMANCER_BALL.get(), level);
		this.setPos(necromancer.getX(), necromancer.getFiringYPosition(), necromancer.getZ());
		this.setOwner(necromancer);
	}

	public void push(final double xa, final double ya, final double za) {
	}

	protected void onHit(final HitResult hitResult) {
		super.onHit(hitResult);
		if (!this.level().isClientSide()) {
			this.discard();
		}

	}

	protected void onHitEntity(final EntityHitResult hitResult) {
		super.onHitEntity(hitResult);
		Level level = this.level();
		if (level instanceof ServerLevel serverLevel) {
			Entity entity = hitResult.getEntity();
			Entity owner = this.getOwner();
			boolean wasHurt;
			if (owner instanceof LivingEntity livingOwner) {
				DamageSource damageSource = this.damageSources().indirectMagic(entity, livingOwner);
				wasHurt = entity.hurtServer(serverLevel, damageSource, 4.0F);
				if (wasHurt) {
					this.playSound(SMCMSoundEvents.NECROMANCER_BALL_HIT.get(), 1.0F, 1.0F);
					EnchantmentHelper.doPostAttackEffects(serverLevel, entity, damageSource);
					if (entity instanceof LivingEntity livingEntity) {
						if(livingEntity.hasEffect(SMCMMobEffects.CORRUPTION.get())) {
							livingEntity.addEffect(new MobEffectInstance(SMCMMobEffects.CORRUPTION.get(), 60 * 20, livingEntity.getEffect(SMCMMobEffects.CORRUPTION.get()).getAmplifier() + 1));
						} else {
							livingEntity.addEffect(new MobEffectInstance(SMCMMobEffects.CORRUPTION.get(), 60 * 20, 0));
						}
					}
				}
			}
		}
	}

	@Override
	public void tick() {
		Entity owner = this.getOwner();
		Vec3 movement = this.getDeltaMovement();
		this.age++;

		if (this.level().hasChunkAt(this.blockPosition()) && age < 100) {
			HitResult hitResult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity, ClipContext.Block.COLLIDER);
			Vec3 newPosition;
			boolean impacted = hitResult.getType() != HitResult.Type.MISS;
			//? neoforge {
			/*impacted = impacted && !net.neoforged.neoforge.event.EventHooks.onProjectileImpact(this, hitResult);
			*///?}
			if (impacted) {
				newPosition = hitResult.getLocation();
			} else {
				newPosition = this.position().add(this.getDeltaMovement());
			}

			float yRot = (float)(Mth.atan2(movement.z, movement.x) * Mth.RAD_TO_DEG - 90.0F);
			float xRot = (float)(Mth.atan2(
					movement.horizontalDistance(),
					movement.y
			) * Mth.RAD_TO_DEG - 90.0F);

			this.setYRot(yRot);
			this.setXRot(xRot);
			this.setPos(newPosition);

			this.applyEffectsFromBlocks();
			if (this.shouldBurn()) {
				this.igniteForSeconds(1.0F);
			}

			if (hitResult.getType() != HitResult.Type.MISS && this.isAlive() && impacted) {
				this.hitTargetOrDeflectSelf(hitResult);
			}

			this.createParticleTrail();

			super.tick();
		} else {
			this.discard();
		}
	}

	private void createParticleTrail() {
		Vec3 position = this.position();
		for(int i = 0; i < 3; i++) {
			double xx = position.x - 0.2D + (random.nextDouble() * 0.4D);
			double yx = position.y - 0.2D + (random.nextDouble() * 0.4D);
			double zx = position.z - 0.2D + (random.nextDouble() * 0.4D);
			this.level().addParticle(SMCMParticleTypes.NECROMANCER_MAGIC.get(), xx, yx, zx, 0.0F, 0.0F, 0.0F);
		}
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder entityData) {

	}

	protected AABB makeBoundingBox(final Vec3 position) {
		float width = this.getType().getDimensions().width() / 2.0F;
		return new AABB(position.x - (double)width, position.y - (double)width, position.z - (double)width, position.x + (double)width, position.y + (double)width, position.z + (double)width);
	}

	protected boolean canHitEntity(final Entity entity) {
		return !(entity instanceof NecromancerBall) && !entity.is(EntityTypeTags.UNDEAD) && super.canHitEntity(entity);
	}

	public boolean canCollideWith(final Entity entity) {
		return !(entity instanceof NecromancerBall) && !entity.is(EntityTypeTags.UNDEAD) && super.canCollideWith(entity);
	}

	protected boolean shouldBurn() {
		return false;
	}
}
