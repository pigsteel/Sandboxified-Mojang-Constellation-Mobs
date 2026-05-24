package com.github.pigsteel.smcm.mixin;

import com.github.pigsteel.smcm.entity.zombie.Frostbitten;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.throwableitemprojectile.Snowball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

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
