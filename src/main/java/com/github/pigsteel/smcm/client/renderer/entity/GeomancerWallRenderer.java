package com.github.pigsteel.smcm.client.renderer.entity;

import com.github.pigsteel.smcm.client.renderer.entity.state.GeomancerWallRenderState;
import com.github.pigsteel.smcm.world.entity.projectile.GeomancerWall;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class GeomancerWallRenderer extends EntityRenderer<GeomancerWall, GeomancerWallRenderState> {
	protected GeomancerWallRenderer(EntityRendererProvider.Context context) {
		super(context);
	}

	@Override
	public GeomancerWallRenderState createRenderState() {
		return new GeomancerWallRenderState();
	}
}
