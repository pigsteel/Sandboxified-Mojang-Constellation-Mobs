package com.github.pigsteel.smcm.client.model.monster.zombie;

import com.github.pigsteel.smcm.client.renderer.entity.FrostbittenRenderer;
import com.github.pigsteel.smcm.client.renderer.entity.state.FrostbittenRenderState;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.model.monster.zombie.ZombieModel;
import net.minecraft.world.entity.HumanoidArm;

import static net.minecraft.client.model.AnimationUtils.bobArms;

// So we can rotate the arm for snowballs
public class FrostbittenModel extends ZombieModel<FrostbittenRenderState> {
    public FrostbittenModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer(final CubeDeformation g) {
        MeshDefinition mesh = HumanoidModel.createMesh(g, 0.0F);
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild(
                "left_arm", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, g), PartPose.offset(5.0F, 2.0F, 0.0F)
        );
        root.addOrReplaceChild(
                "left_leg", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, g), PartPose.offset(1.9F, 12.0F, 0.0F)
        );
        return LayerDefinition.create(mesh, 64, 64);
    }

    public void setupAnim(final FrostbittenRenderState state) {
        super.setupAnim(state);

        if (state.isThrowingSnowball) {
            this.smcm$poseSnowballThrow(state, state.isAggressive);
        }
    }

    private void smcm$poseSnowballThrow(FrostbittenRenderState state, boolean aggressive) {
        boolean isLeftHanded = state.mainArm == HumanoidArm.LEFT;
        ModelPart mainArm = isLeftHanded ? this.leftArm : this.rightArm;
        ModelPart offHand = isLeftHanded ? this.rightArm : this.leftArm;

        float armDrop = -(float)Math.PI / (aggressive ? 1.5F : 2.25F);

        // Right arm pulled back near the head/shoulder.
        mainArm.xRot = armDrop + -2.0F;
        mainArm.yRot = 0.2F;
        mainArm.zRot = 0F;

        // Slight support pose for the left arm.
        offHand.xRot = armDrop + 0.05F;
        offHand.yRot = 0.1F;
        offHand.zRot = 0F;

        bobArms(rightArm, leftArm, state.ageInTicks);
    }
}
