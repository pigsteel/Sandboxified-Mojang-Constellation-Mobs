package com.github.pigsteel.eum.mixin.client;

import com.github.pigsteel.eum.world.entity.ZombieFrostbittenConversion;
import net.minecraft.client.model.monster.zombie.ZombieModel;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.world.entity.monster.zombie.Zombie;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractZombieRenderer.class)
public abstract class ZombieFrostbittenConversionRendererMixin <T extends Zombie, S extends ZombieRenderState, M extends ZombieModel<S>> {
    @Inject(method = "extractRenderState*", at = @At("TAIL"))
    public void eum$extractRenderState(T entity, S state, float partialTicks, CallbackInfo ci) {
        ZombieFrostbittenConversion duck = (ZombieFrostbittenConversion) entity;
        state.isConverting = state.isConverting || duck.eum$isFreezeConverting();
    }
}
