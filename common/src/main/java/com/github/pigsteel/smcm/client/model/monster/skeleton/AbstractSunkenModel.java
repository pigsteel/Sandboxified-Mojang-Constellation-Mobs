package com.github.pigsteel.smcm.client.model.monster.skeleton;

import com.github.pigsteel.smcm.client.renderer.entity.state.SunkenRenderState;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.model.monster.skeleton.SkeletonModel;

public class AbstractSunkenModel extends SkeletonModel<SunkenRenderState> {
    public AbstractSunkenModel(ModelPart root) {
        super(root);

        this.hat.visible = false;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
        PartDefinition root = mesh.getRoot();
        createDefaultSkeletonMesh(root);

        PartDefinition head = root.getChild("head");
        PartDefinition rightLeg = root.getChild("right_leg");
        PartDefinition leftLeg = root.getChild("left_leg");

        head.addOrReplaceChild(
                "hat",
                CubeListBuilder.create(),
                PartPose.ZERO
        );

        head.addOrReplaceChild(
                "seagrass_first",
                CubeListBuilder.create()
                        .texOffs(32, 8)
                        .addBox(-4.0F, -16.0F, 0.0F, 8.0F, 8.0F, 0.0F),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F)
        );

        head.addOrReplaceChild(
                "seagrass_second",
                CubeListBuilder.create()
                        .texOffs(32, 0)
                        .addBox(-4.0F, -16.0F, 0.0F, 8.0F, 8.0F, 0.0F),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F)
        );

        rightLeg.addOrReplaceChild(
                "right_leg_thing",
                CubeListBuilder.create()
                        .texOffs(20, 4)
                        .addBox(-2.0F, 6.0F, -2.0F, 4.0F, 0.0F, 4.0F),

                PartPose.ZERO
        );

        leftLeg.addOrReplaceChild(
                "left_leg_thing",
                CubeListBuilder.create()
                        .texOffs(23, 1)
                        .addBox(-1.0F, 4.0F, -1.0F, 3.0F, 0.0F, 3.0F),

                PartPose.ZERO
        );

        head.addOrReplaceChild(
                "shelf_thing",
                CubeListBuilder.create()
                        .texOffs(48, 0)
                        .addBox(-5.0F, -4.0F, 3.0F, 4.0F, 1.0F, 2.0F),
                PartPose.ZERO
        );

        return LayerDefinition.create(mesh, 64, 32);
    }
}
