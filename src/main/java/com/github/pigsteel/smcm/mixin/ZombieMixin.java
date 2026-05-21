package com.github.pigsteel.smcm.mixin;

import net.minecraft.world.entity.monster.zombie.Zombie;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(Zombie.class)
public class ZombieMixin implements ZombieFrostbittenConversion {

    @Inject(method = "<init>", at = @At("HEAD"))

    @Override
    public void doFreezeConversion() {

    }

    @Override
    public void setFreezeConverting(boolean isConverting) {

    }
}
