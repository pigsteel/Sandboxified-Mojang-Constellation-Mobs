package com.github.pigsteel.smcm.client.model.monster.skeleton;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.client.renderer.entity.state.SunkenRenderState;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.monster.skeleton.SkeletonModel;
import net.minecraft.client.model.geom.builders.*;

public class CoralSunkenModel extends AbstractSunkenModel {
    private final ModelPart faceCoral;

    public CoralSunkenModel(ModelPart root) {
        super(root);
        this.faceCoral = root.getChild("head").getChild("face_coral");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
        PartDefinition root = mesh.getRoot();
        createDefaultSkeletonMesh(root);

        PartDefinition head = root.getChild("head");
        PartDefinition body = root.getChild("body");
        PartDefinition rightLeg = root.getChild("right_leg");

        head.addOrReplaceChild(
                "hat",
                CubeListBuilder.create(),
                PartPose.ZERO
        );

        // Blockbench: face_coral
        // from [-9, 30, -4] to [1, 40, -4], head origin [0, 24, 0]
        // Converted relative to head: x=-9, y=-16, z=-4, size 10x10x0
        head.addOrReplaceChild(
                "face_coral",
                CubeListBuilder.create()
                        .texOffs(32, 0)
                        .addBox(-1.0F, -16.0F, -4.001F, 10.0F, 10.0F, 0.0F),
                PartPose.ZERO
        );

        // Blockbench: head_coral
        // from [-5, 27, 2] to [1, 27, 6], head origin [0, 24, 0]
        // Converted relative to head: x=-5, y=-3, z=2, size 6x0x4
        head.addOrReplaceChild(
                "head_coral",
                CubeListBuilder.create()
                        .texOffs(28, 12)
                        .addBox(-1.0F, -3.0F, 2.0F, 6.0F, 0.0F, 4.0F),
                PartPose.ZERO
        );

        // Blockbench: hip_coral
        // Original coordinates are near model bottom. This is attached to body so it follows torso movement.
        // You may want to move this after seeing it in-game.
        body.addOrReplaceChild(
                "hip_coral",
                CubeListBuilder.create()
                        .texOffs(12, 30)
                        .addBox(2.0F, 12.0F, -2.0F, 2.0F, 2.0F, 0.0F),
                PartPose.ZERO
        );

        // Blockbench: leg_thing
        // from [0, 8, -2] to [4, 8, 2]
        // Attached to right_leg so it animates with that leg.
        rightLeg.addOrReplaceChild(
                "leg_thing",
                CubeListBuilder.create()
                        .texOffs(20, 4)
                        .addBox(-2.0F, 4.0F, -2.0F, 4.0F, 0.0F, 4.0F),

                PartPose.ZERO
        );

        return LayerDefinition.create(mesh, 64, 32);
    }

    @Override
    public void setupAnim(SunkenRenderState state) {
        super.setupAnim(state);
        this.faceCoral.visible = !state.isSheared;
    }
}
