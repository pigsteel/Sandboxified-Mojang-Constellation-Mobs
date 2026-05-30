package com.github.pigsteel.smcm.mixin;

import com.github.pigsteel.smcm.registry.smcm$EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.zombie.Zombie;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityZombieCanFreezeMixin {
    @Inject(method = "canFreeze", at = @At("HEAD"), cancellable = true)
    private void smcm$canFreeze(CallbackInfoReturnable<Boolean> cir) {
        if ((Object) this instanceof Zombie zombie) {
            cir.setReturnValue(false);
        }
    }
}
