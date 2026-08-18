package com.github.pigsteel.smcm.client.renderer.entity;

import com.github.pigsteel.smcm.world.entity.animal.golem.TuffGolem;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

public class TuffGolemRenderer extends EntityRenderer<TuffGolem, LivingEntityRenderState> {
	public TuffGolemRenderer(EntityRendererProvider.Context context) {
		super(context);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}
}
