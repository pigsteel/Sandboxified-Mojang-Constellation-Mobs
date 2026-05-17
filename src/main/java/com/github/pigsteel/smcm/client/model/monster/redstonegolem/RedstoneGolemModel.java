package com.github.pigsteel.smcm.client.model.monster.redstonegolem;

import com.github.pigsteel.smcm.client.animation.definitions.RedstoneGolemAnimation;
import com.github.pigsteel.smcm.client.renderer.entity.state.RedstoneGolemRenderState;
import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

import java.util.Set;

public class RedstoneGolemModel extends EntityModel<RedstoneGolemRenderState> {
    private final KeyframeAnimation walkAnimation;

    protected final ModelPart bone;
    protected final ModelPart body;
    protected final ModelPart upperBody;
    protected final ModelPart head;
    protected final ModelPart leftLeg;
    protected final ModelPart leftArm;
    protected final ModelPart rightArm;
    protected final ModelPart leftForearm;
    protected final ModelPart rightForearm;
    protected final ModelPart rightLeg;

    public RedstoneGolemModel(ModelPart root) {
        super(root);
        this.bone = root.getChild("bone");
        this.body = this.bone.getChild("body");
        this.upperBody = this.body.getChild("upper_body");
        this.head = this.upperBody.getChild("head");
        this.rightLeg = this.bone.getChild("right_leg");
        this.leftLeg = this.bone.getChild("left_leg");
        this.rightArm = this.upperBody.getChild("right_arm");
        this.leftArm = this.upperBody.getChild("left_arm");
        this.rightForearm = this.rightArm.getChild("right_forearm");
        this.leftForearm = this.leftArm.getChild("left_forearm");

        this.walkAnimation = RedstoneGolemAnimation.WALK_ANIMATION.bake(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        PartDefinition bone = root.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F)); // This is how we rotate the entire body at once
        PartDefinition body = bone.addOrReplaceChild(
                "body", CubeListBuilder.create().texOffs(120, 36).addBox(-11.0F, -8.0F, -7.0F, 22.0F, 8.0F, 14.0F), PartPose.offset(0.0F, -20.0F, 0.0F)
        );
        PartDefinition upperBody = body.addOrReplaceChild(
                "upper_body", CubeListBuilder.create().texOffs(0, 0).addBox(-20.0F, -32.0F, -10.0F, 40.0F, 32.0F, 20.0F), PartPose.offset(0.0F, -8.0F, 0.0F)
        );
        upperBody.addOrReplaceChild(
                "redstone_core", CubeListBuilder.create().texOffs(49, 90).addBox(-8.0F,-8.0F,0.0F, 16.0F, 16.0F, 16.0F), PartPose.offset(0.0F, -12.0F, 0.0F)
        );

        upperBody.addOrReplaceChild(
                "head", CubeListBuilder.create().texOffs(120, 0).addBox(-8.0F, -8.0F, -12.0F, 16.0F, 16.0F, 12.0F), PartPose.offset(0.0F, -23.95F, -10.0F)
        );

        float fingerOutset = 17.0F;

        PartDefinition rightArm = upperBody.addOrReplaceChild(
                "right_arm", CubeListBuilder.create().texOffs(0, 52).addBox(-7.0F, -4.0F, -6.0F, 14.0F, 24.0F, 12.0F), PartPose.offset(-27.0F, -26.0F, 0.0F)
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
                "left_arm", CubeListBuilder.create().texOffs(113, 90).addBox(-7.0F, -4.0F, -6.0F, 14.0F, 24.0F, 12.0F), PartPose.offset(27.0F, -26.0F, 0.0F)
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

        return LayerDefinition.create(mesh, 256, 128);
    }

    public static LayerDefinition createGlowLayer() {
        return createBodyLayer().apply((mesh) -> mesh);
    }

    public static LayerDefinition createEyesLayer() {
        return createBodyLayer().apply((mesh) -> {
            mesh.getRoot().retainExactParts(Set.of("head"));
            return mesh;
        });
    }

    public void setupAnim(final RedstoneGolemRenderState state) {
        super.setupAnim(state);
        this.animateHeadLookTarget(state.yRot, state.xRot);
        this.walkAnimation.applyWalk(state.walkAnimationPos, state.walkAnimationSpeed, 3.0F, 100.0F);
    }

    private void animateHeadLookTarget(float yRot, float xRot) {
        this.head.xRot = xRot * ((float)Math.PI / 180F);
        this.head.yRot = yRot * ((float)Math.PI / 180F);
    }

    private void animateWalk(final float animationPos, final float animationSpeed) {
        float speedModifier = Math.min(0.5F, 3.0F * animationSpeed);
        float adjustedPos = animationPos * 0.8662F;
        float adjustedPosCosine = Mth.cos(adjustedPos);
        float adjustedPosSine = Mth.sin(adjustedPos);
        this.body.zRot = 0.1F * adjustedPosSine * speedModifier;
        this.upperBody.xRot = 0.22F;
        this.leftLeg.xRot = -1.0F * Mth.cos(adjustedPos + (float) Math.PI) * speedModifier;
        this.rightLeg.xRot = 1.0F * Mth.cos(adjustedPos + (float) Math.PI) * speedModifier;
        this.leftArm.xRot = 0.24F + -(1.0F * adjustedPosCosine * speedModifier );
        this.leftForearm.xRot = -0.5F;
        this.leftArm.zRot = 0.0F;
        this.rightArm.xRot = 0.24F + (1.0F * adjustedPosCosine * speedModifier);
        this.rightForearm.xRot = -0.5F;
        this.rightArm.zRot = 0.0F;
        this.resetArmPoses();
    }

    private void resetArmPoses() {
        this.leftArm.yRot = 0.0F;
        this.rightArm.yRot = 0.0F;
    }
}
