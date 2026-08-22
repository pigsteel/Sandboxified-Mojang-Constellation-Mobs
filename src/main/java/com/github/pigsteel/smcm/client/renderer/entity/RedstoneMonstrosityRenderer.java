package com.github.pigsteel.smcm.client.renderer.entity;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.client.model.geom.SMCMModelLayers;
import com.github.pigsteel.smcm.client.model.monster.redstonemonstrosity.RedstoneMonstrosityModel;
import com.github.pigsteel.smcm.client.renderer.entity.state.RedstoneMonstrosityRenderState;
import com.github.pigsteel.smcm.world.entity.monster.redstonemonstrosity.RedstoneMonstrosity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class RedstoneMonstrosityRenderer extends MobRenderer<RedstoneMonstrosity, RedstoneMonstrosityRenderState, RedstoneMonstrosityModel> {
	private static final Identifier REDSTONE_MONSTROSITY_LOCATION = SMCM.id("textures/entity/redstone_monstrosity/redstone_monstrosity.png");

	public RedstoneMonstrosityRenderer(EntityRendererProvider.Context context) {
		super(context, new RedstoneMonstrosityModel(context.bakeLayer(SMCMModelLayers.REDSTONE_MONSTROSITY)), 5.0F);
	}

	@Override
	public Identifier getTextureLocation(RedstoneMonstrosityRenderState state) {
		return REDSTONE_MONSTROSITY_LOCATION;
	}

	@Override
	public RedstoneMonstrosityRenderState createRenderState() {
		return new RedstoneMonstrosityRenderState();
	}
}
