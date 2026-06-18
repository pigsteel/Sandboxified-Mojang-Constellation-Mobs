package com.github.pigsteel.smcm.mixin;

import com.github.pigsteel.smcm.entity.zombie.Frostbitten;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.throwableitemprojectile.Snowball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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

    @Inject(method = "onHitEntity", at = @At("TAIL"))
    private void smcm$applyFrostbittenSnowballEffect(EntityHitResult hitResult, CallbackInfo ci) {
        Entity entity = hitResult.getEntity();

        if (!(entity instanceof LivingEntity living)) return;

        if (living instanceof Player player && (player.isCreative() || player.isSpectator())) {
            return;
        }

        Entity owner = this.getOwner();

        if (!(owner instanceof Frostbitten)) return;
        if (!(entity.level() instanceof ServerLevel serverLevel)) return;

        var damageSource = this.damageSources().thrown(this, owner);

        float difficulty = serverLevel
                .getCurrentDifficultyAt(owner.blockPosition())
                .getEffectiveDifficulty();

        living.setTicksFrozen(living.getTicksFrozen() + 270 + (int)(20 * difficulty));
        entity.hurt(damageSource, 1.0F);
    }
}
