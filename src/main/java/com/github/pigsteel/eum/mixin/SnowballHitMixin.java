package com.github.pigsteel.eum.mixin;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.throwableitemprojectile.Snowball;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Snowball.class)
public abstract class SnowballHitMixin extends Projectile {
    /**
     * Access widened by fabric-transitive-access-wideners-v1 to accessible
     *
     * @param type
     * @param level
     */
    public SnowballHitMixin(EntityType<? extends Projectile> type, Level level) {
        super(type, level);
    }

    //@Inject(method = "onHitEntity", at = @At("TAIL"))
	/*
    private void eum$applyFrostbittenSnowballEffect(EntityHitResult hitResult, CallbackInfo ci) {
        Entity entity = hitResult.getEntity();

        if (!(entity instanceof LivingEntity living)) return;

        if (hitResult.getType() != HitResult.Type.ENTITY || entity instanceof Player player && (player.isCreative() || player.isSpectator())) {
            return;
        }

        Entity owner = this.getOwner();

        if (!(owner instanceof Frostbitten)) return;
        if (!(entity.level() instanceof ServerLevel serverLevel)) return;

        var damageSource = this.damageSources().thrown(this, owner);

        float difficulty = serverLevel
                .getCurrentDifficultyAt(owner.blockPosition())
                .getEffectiveDifficulty();

        boolean wasHurt = entity.hurtServer(serverLevel, damageSource, 1.0F);

		if(wasHurt) {
			living.setTicksFrozen(living.getTicksFrozen() + 270 + (int)(20 * difficulty));
		}
    }*/
}
