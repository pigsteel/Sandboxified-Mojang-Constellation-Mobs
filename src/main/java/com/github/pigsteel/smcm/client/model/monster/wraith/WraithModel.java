package com.github.pigsteel.smcm.client.model.monster.wraith;

import com.github.pigsteel.smcm.client.renderer.entity.state.WraithRenderState;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.monster.blaze.BlazeModel;
import net.minecraft.world.entity.HumanoidArm;

public class WraithModel extends EntityModel<WraithRenderState> implements HeadedModel {
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart bone;
	private final ModelPart rightArm;
	private final ModelPart leftArm;

	public WraithModel(ModelPart root) {
		super(root);
		this.bone = root.getChild("bone");
		this.body = bone.getChild("body");
		this.rightArm = body.getChild("right_arm");
		this.leftArm = body.getChild("left_arm");
		this.head = body.getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition root = mesh.getRoot();
		PartDefinition bone = root.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.ZERO);

		PartDefinition body = bone.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.ZERO);
		body.addOrReplaceChild("robes", CubeListBuilder.create(), PartPose.ZERO);
		
		body.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.ZERO);

		PartDefinition rightArm = body.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.ZERO);
		rightArm.addOrReplaceChild("right_sleeve", CubeListBuilder.create(), PartPose.ZERO);

		PartDefinition leftArm = body.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.ZERO);
		leftArm.addOrReplaceChild("left_sleeve", CubeListBuilder.create(), PartPose.ZERO);

		return LayerDefinition.create(mesh, 64, 64);
	}

	@Override
	public ModelPart getHead() {
		return this.head;
	}
}
