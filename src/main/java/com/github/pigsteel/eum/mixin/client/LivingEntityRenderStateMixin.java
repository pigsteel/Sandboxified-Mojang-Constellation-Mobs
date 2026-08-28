package com.github.pigsteel.eum.mixin.client;

import com.github.pigsteel.eum.client.renderer.entity.state.LivingEntityRenderStateExtensions;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(LivingEntityRenderState.class)
public abstract class LivingEntityRenderStateMixin implements LivingEntityRenderStateExtensions {
	@Unique
	public boolean isEnchanted;

	@Override
	public boolean eum$isEnchanted() {
		return isEnchanted;
	}
}
