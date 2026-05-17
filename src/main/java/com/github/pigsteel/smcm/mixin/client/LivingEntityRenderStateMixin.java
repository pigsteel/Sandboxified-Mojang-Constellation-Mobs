package com.github.pigsteel.smcm.mixin.client;

import com.github.pigsteel.smcm.client.renderer.entity.state.LivingEntityRenderStateExtensions;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(LivingEntityRenderState.class)
public abstract class LivingEntityRenderStateMixin implements LivingEntityRenderStateExtensions {
	@Unique
	public boolean isEnchanted;

	@Override
	public boolean smcm$isEnchanted() {
		return isEnchanted;
	}
}
