package com.github.pigsteel.eum.client.model.monster.wildfire;

//? >= 1.21.2 {
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.monster.blaze.BlazeModel;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

public class WildfireModel extends EntityModel<LivingEntityRenderState> {
	public WildfireModel(ModelPart root) {
		super(root);
	}

	public static LayerDefinition createBodyLayer() {
		return BlazeModel.createBodyLayer();
	}
}
//?}
