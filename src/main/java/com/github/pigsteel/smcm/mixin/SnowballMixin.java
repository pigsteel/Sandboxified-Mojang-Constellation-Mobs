package com.github.pigsteel.smcm.mixin;

import com.github.pigsteel.smcm.entity.zombie.Frostbitten;
import com.github.pigsteel.smcm.registry.EntityTypeRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityReference;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.throwableitemprojectile.Snowball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(Snowball.class)
public abstract class SnowballMixin extends Projectile {
    /**
     * Access widened by fabric-transitive-access-wideners-v1 to accessible
     *
     * @param type
     * @param level
     */
    public SnowballMixin(EntityType<? extends Projectile> type, Level level) {
        super(type, level);
    }

    @ModifyVariable(
            method = "onHitEntity",
            at = @At("STORE"),
            ordinal = 0
    )
    private int smcm$modifyDamage(int original, EntityHitResult hitResult) {
        Entity entity = hitResult.getEntity();

        // original = 3 or 0 depending on Blaze check
        if (this.getOwner() instanceof Frostbitten) {
            return original + 2;
        }

        return original;
    }
}
