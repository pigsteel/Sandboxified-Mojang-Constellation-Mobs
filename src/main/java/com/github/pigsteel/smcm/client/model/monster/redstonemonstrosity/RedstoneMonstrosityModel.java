package com.github.pigsteel.smcm.client.model.monster.redstonemonstrosity;

import com.github.pigsteel.smcm.client.renderer.entity.state.RedstoneMonstrosityRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class RedstoneMonstrosityModel extends EntityModel<RedstoneMonstrosityRenderState> {


    public RedstoneMonstrosityModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        PartDefinition bone = root.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
        PartDefinition body = bone.addOrReplaceChild(
                "body", CubeListBuilder.create().texOffs(120, 36).addBox(-11.0F, -8.0F, -7.0F, 22.0F, 8.0F, 14.0F), PartPose.offset(0.0F, -20.0F, 0.0F)
        );
        PartDefinition upperBody = body.addOrReplaceChild(
                "upper_body", CubeListBuilder.create().texOffs(0, 0).addBox(-20.0F, -32.0F, -20.0F, 40.0F, 32.0F, 20.0F), PartPose.offset(0.0F, -8.0F, 10.0F)
        );
        upperBody.addOrReplaceChild(
                "redstone_core", CubeListBuilder.create().texOffs(49, 90).addBox(-8.0F,-8.0F,0.0F, 16.0F, 16.0F, 16.0F), PartPose.offset(0.0F, -12.0F, -10.0F)
        );

        upperBody.addOrReplaceChild(
                "head", CubeListBuilder.create().texOffs(120, 0).addBox(-8.0F, -8.0F, -12.0F, 16.0F, 16.0F, 12.0F), PartPose.offset(0.0F, -23.95F, -20.0F)
        );

        float fingerOutset = 17.0F;

        PartDefinition rightArm = upperBody.addOrReplaceChild(
                "right_arm", CubeListBuilder.create().texOffs(0, 52).addBox(-7.0F, -4.0F, -6.0F, 14.0F, 24.0F, 12.0F), PartPose.offset(-27.0F, -26.0F, -10.0F)
        );
        PartDefinition rightForearm = rightArm.addOrReplaceChild(
                "right_forearm", CubeListBuilder.create().texOffs(3, 88).addBox(-5.5F, 0.0F, -6.0F, 11.0F, 22.0F, 12.0F), PartPose.offset(-4.0F, 20.0F, 0.0F)
        );
        rightForearm.addOrReplaceChild(
                "right_finger1", CubeListBuilder.create().texOffs(97, 55).addBox(-1.5F, 3.0F, -2.5F, 3.0F, 10.0F, 5.0F), PartPose.offset(-3.0F, fingerOutset, 3.0F)
        );
        rightForearm.addOrReplaceChild(
                "right_finger2", CubeListBuilder.create().texOffs(97, 55).addBox(-1.5F, 3.0F, -2.5F, 3.0F, 10.0F, 5.0F), PartPose.offset(-3.0F, fingerOutset, -3.0F)
        );
        rightForearm.addOrReplaceChild(
                "right_thumb", CubeListBuilder.create().texOffs(81, 57).addBox(-1.5F, 3.0F, -2.5F, 3.0F, 8.0F, 5.0F), PartPose.offset(3.0F, fingerOutset, 0.0F)
        );

        PartDefinition leftArm = upperBody.addOrReplaceChild(
                "left_arm", CubeListBuilder.create().texOffs(113, 90).addBox(-7.0F, -4.0F, -6.0F, 14.0F, 24.0F, 12.0F), PartPose.offset(27.0F, -26.0F, -10.0F)
        );
        PartDefinition leftForearm = leftArm.addOrReplaceChild(
                "left_forearm", CubeListBuilder.create().texOffs(176, 0).addBox(-5.5F, 0.0F, -6.0F, 11.0F, 22.0F, 12.0F), PartPose.offset(4.0F, 20.0F, 0.0F)
        );
        leftForearm.addOrReplaceChild(
                "left_finger1", CubeListBuilder.create().texOffs(97, 55).addBox(-1.5F, 3.0F, -2.5F, 3.0F, 10.0F, 5.0F).mirror(true), PartPose.offset(3.0F, fingerOutset, 3.0F)
        );
        leftForearm.addOrReplaceChild(
                "left_finger2", CubeListBuilder.create().texOffs(97, 55).addBox(-1.5F, 3.0F, -2.5F, 3.0F, 10.0F, 5.0F).mirror(true), PartPose.offset(3.0F, fingerOutset, -3.0F)
        );
        leftForearm.addOrReplaceChild(
                "left_thumb", CubeListBuilder.create().texOffs(81, 57).addBox(-1.5F, 3.0F, -2.5F, 3.0F, 8.0F, 5.0F).mirror(true), PartPose.offset(-3.0F, fingerOutset, 0.0F)
        );

        bone.addOrReplaceChild(
                "right_leg", CubeListBuilder.create().texOffs(113, 58).addBox(-6.0F, 0.0F, -6.0F, 12.0F, 20.0F, 12.0F), PartPose.offset(-14F, -20.0F, 0.0F)
        );
        bone.addOrReplaceChild(
                "left_leg", CubeListBuilder.create().texOffs(161, 58).addBox(-6.0F, 0.0F, -6.0F, 12.0F, 20.0F, 12.0F), PartPose.offset(14F, -20.0F, 0.0F)
        );

        return LayerDefinition.create(mesh, 256, 512);
    }
}
