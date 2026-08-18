package com.github.pigsteel.smcm.client.model.monster.wraith;

import com.github.pigsteel.smcm.client.renderer.entity.state.WraithRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.monster.blaze.BlazeModel;

public class WraithModel extends EntityModel<WraithRenderState> {
	public WraithModel(ModelPart root) {
		super(root);
	}

	public static LayerDefinition createBodyLayer() {
		return BlazeModel.createBodyLayer();
	}
}
