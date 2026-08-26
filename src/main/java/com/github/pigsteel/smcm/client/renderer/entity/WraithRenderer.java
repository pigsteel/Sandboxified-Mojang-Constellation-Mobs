package com.github.pigsteel.smcm.client.renderer.entity;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.client.model.geom.SMCMModelLayers;
import com.github.pigsteel.smcm.client.model.monster.wraith.WraithModel;
import com.github.pigsteel.smcm.client.renderer.entity.state.WraithRenderState;
import com.github.pigsteel.smcm.world.entity.monster.Wraith;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.Identifier;

public class WraithRenderer extends LivingEntityRenderer<Wraith, WraithRenderState, WraithModel> {
	private static final Identifier WRAITH_LOCATION = SMCM.id("textures/entity/wraith/wraith.png");

	public WraithRenderer(EntityRendererProvider.Context context) {
		super(context, new WraithModel(context.bakeLayer(SMCMModelLayers.WRAITH)), 0.0F);
	}

	@Override
	public WraithRenderState createRenderState() {
		return new WraithRenderState();
	}

	@Override
	public Identifier getTextureLocation(WraithRenderState state) {
		return WRAITH_LOCATION;
	}

	public void extractRenderState(final Wraith entity, final WraithRenderState state, final float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		//state.displayFireAnimation
	}
}
