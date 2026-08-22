package com.github.pigsteel.smcm.client.model.monster.necromancer;

import com.github.pigsteel.smcm.client.renderer.entity.state.NecromancerBallRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

public class NecromancerBallModel extends EntityModel<NecromancerBallRenderState> {
	//private final ModelPart bone;

	public NecromancerBallModel(ModelPart root) {
		super(root);
		//bone = root.getChild("bone");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition root = mesh.getRoot();
		PartDefinition bone = root.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.ZERO);
		bone.addOrReplaceChild("ball", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -3.0F, -3.0F, 16.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.rotation(0.0F, Mth.HALF_PI, 0.0F));
		return LayerDefinition.create(mesh, 64, 128);
	}

	@Override
	public void setupAnim(NecromancerBallRenderState state) {
		super.setupAnim(state);
	}
}
