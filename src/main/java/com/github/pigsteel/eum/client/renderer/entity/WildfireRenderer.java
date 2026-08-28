package com.github.pigsteel.eum.client.renderer.entity;

import com.github.pigsteel.eum.EUM;
import com.github.pigsteel.eum.client.model.geom.EUMModelLayers;
import com.github.pigsteel.eum.client.model.monster.wildfire.WildfireModel;
import com.github.pigsteel.eum.world.entity.monster.Wildfire;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;

public class WildfireRenderer extends MobRenderer<Wildfire, LivingEntityRenderState, WildfireModel> {
	private static final ResourceLocation WILDFIRE_LOCATION = EUM.id("textures/entity/wildfire/wildfire.png");

	public WildfireRenderer(EntityRendererProvider.Context context) {
		super(context, new WildfireModel(context.bakeLayer(EUMModelLayers.WILDFIRE)), 0.0F);
	}

	@Override
	public ResourceLocation getTextureLocation(LivingEntityRenderState state) {
		return WILDFIRE_LOCATION;
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}
}
