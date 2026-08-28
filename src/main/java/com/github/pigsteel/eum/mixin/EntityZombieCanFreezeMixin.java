package com.github.pigsteel.eum.mixin;

import com.github.pigsteel.eum.world.entity.ZombieFrostbittenConversion;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.zombie.Zombie;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityZombieCanFreezeMixin {
    @Inject(method = "canFreeze", at = @At("HEAD"), cancellable = true)
    private void eum$canFreeze(CallbackInfoReturnable<Boolean> cir) {
        try {
            Zombie zombie = (Zombie) (Object) this;
            if (((ZombieFrostbittenConversion) this).eum$canFreezeConvert(zombie)) {
                cir.setReturnValue(false);
            }
        } catch (Exception _) {} // guarding against weird itemstack freezing bug thing
    }
}
