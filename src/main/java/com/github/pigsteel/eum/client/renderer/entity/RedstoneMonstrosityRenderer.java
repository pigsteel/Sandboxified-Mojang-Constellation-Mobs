package com.github.pigsteel.eum.client.renderer.entity;

import com.github.pigsteel.eum.EUM;
import com.github.pigsteel.eum.client.model.geom.EUMModelLayers;
import com.github.pigsteel.eum.client.model.monster.redstonemonstrosity.RedstoneMonstrosityModel;
import com.github.pigsteel.eum.client.renderer.entity.state.RedstoneMonstrosityRenderState;
import com.github.pigsteel.eum.world.entity.monster.redstonemonstrosity.RedstoneMonstrosity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class RedstoneMonstrosityRenderer extends MobRenderer<RedstoneMonstrosity, RedstoneMonstrosityRenderState, RedstoneMonstrosityModel> {
	private static final Identifier REDSTONE_MONSTROSITY_LOCATION = EUM.id("textures/entity/redstone_monstrosity/redstone_monstrosity.png");

	public RedstoneMonstrosityRenderer(EntityRendererProvider.Context context) {
		super(context, new RedstoneMonstrosityModel(context.bakeLayer(EUMModelLayers.REDSTONE_MONSTROSITY)), 5.0F);
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
