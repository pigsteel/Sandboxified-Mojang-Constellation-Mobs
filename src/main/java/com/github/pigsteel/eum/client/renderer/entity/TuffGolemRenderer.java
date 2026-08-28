package com.github.pigsteel.eum.client.renderer.entity;

import com.github.pigsteel.eum.world.entity.animal.golem.TuffGolem;
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
