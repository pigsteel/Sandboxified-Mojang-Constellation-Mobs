package com.github.pigsteel.smcm.client.renderer.entity;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.client.model.geom.SMCMModelLayers;
import com.github.pigsteel.smcm.client.model.monster.wildfire.WildfireModel;
import com.github.pigsteel.smcm.world.entity.monster.Wildfire;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

public class WildfireRenderer extends MobRenderer<Wildfire, LivingEntityRenderState, WildfireModel> {
	private static final Identifier WILDFIRE_LOCATION = SMCM.id("textures/entity/wildfire/wildfire.png");

	public WildfireRenderer(EntityRendererProvider.Context context) {
		super(context, new WildfireModel(context.bakeLayer(SMCMModelLayers.WILDFIRE)), 0.0F);
	}

	@Override
	public Identifier getTextureLocation(LivingEntityRenderState state) {
		return WILDFIRE_LOCATION;
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}
}
