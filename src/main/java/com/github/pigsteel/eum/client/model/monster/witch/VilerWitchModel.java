package com.github.pigsteel.eum.client.model.monster.witch;

import com.github.pigsteel.eum.client.renderer.entity.state.VilerWitchRenderState;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.VillagerLikeModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.npc.VillagerModel;
import net.minecraft.util.Mth;

// Unfortunately since WitchModel doesn't implement generic params I can't extend it directly
public class VilerWitchModel extends EntityModel<VilerWitchRenderState> implements HeadedModel, VillagerLikeModel<VilerWitchRenderState> {
    protected final ModelPart nose;
    private final ModelPart head;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;
    private final ModelPart arms;

    public VilerWitchModel(final ModelPart root) {
        super(root);
        this.head = root.getChild("head");
        this.nose = this.head.getChild("nose");
        this.rightLeg = root.getChild("right_leg");
        this.leftLeg = root.getChild("left_leg");
        this.arms = root.getChild("arms");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = VillagerModel.createBodyModel();
        PartDefinition root = mesh.getRoot();
        PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F), PartPose.ZERO);
        PartDefinition hat = head.addOrReplaceChild(
                "hat", CubeListBuilder.create().texOffs(0, 37).addBox(-4.5F, -3.75F, -4.5F, 9.0F, 5.0F, 9.0F), PartPose.ZERO
        );
        hat.clearChild("hat_rim");
        PartDefinition hat1 = head.addOrReplaceChild(
                "hat1", CubeListBuilder.create().texOffs(0, 70).addBox(0.0F, 0.0F, 0.0F, 20.0F, 2.0F, 20.0F), PartPose.offset(-10.0F, -10.03125F, -10.0F)
        );
        PartDefinition hat2 = hat1.addOrReplaceChild(
                "hat2",
                CubeListBuilder.create().texOffs(0, 51).addBox(0.0F, 0.0F, 0.0F, 7.0F, 4.0F, 7.0F),
                PartPose.offsetAndRotation(6.75F, -4.0F, 7.0F, -0.05235988F, 0.0F, 0.02617994F)
        );
        PartDefinition hat3 = hat2.addOrReplaceChild(
                "hat3",
                CubeListBuilder.create().texOffs(0, 62).addBox(0.0F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F),
                PartPose.offsetAndRotation(1.75F, -4.0F, 2.0F, -0.10471976F, 0.0F, 0.05235988F)
        );
        hat3.addOrReplaceChild(
                "hat4",
                CubeListBuilder.create().texOffs(16, 67).addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.25F)),
                PartPose.offsetAndRotation(1.75F, -2.0F, 2.0F, (float) (-Math.PI / 15), 0.0F, 0.10471976F)
        );
        PartDefinition body = root.getChild("body");
        PartDefinition arms = root.addOrReplaceChild(
                "arms",
                CubeListBuilder.create()
                        .texOffs(44, 22)
                        .addBox(-8.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F)
                        .texOffs(44, 22)
                        .addBox(4.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, true)
                        .texOffs(40, 34)
                        .addBox(-4.0F, 2.0F, -2.0F, 8.0F, 4.0F, 4.0F),
                PartPose.offsetAndRotation(0.0F, 3.0F, -1.0F, -0.75F, 0.0F, 0.0F)
        );
        body.addOrReplaceChild(
                "jacket", CubeListBuilder.create().texOffs(30, 44).addBox(-5.0F, 0.0F, -3.5F, 10.0F, 19.0F, 7.0F), PartPose.ZERO
        );
        body.addOrReplaceChild(
                "belt", CubeListBuilder.create().texOffs(0, 92).addBox(-5.5F, 8.0F, -4.0F, 11.0F, 3.0F, 8.0F), PartPose.ZERO
        );

        arms.addOrReplaceChild(
                "sleeves",
                CubeListBuilder.create()
                        .texOffs(32, 0)
                        .addBox(-9.1F, -4.0F, -3.0F, 5.0F, 5.0F, 6.0F)
                        .texOffs(54, 0)
                        .addBox(3.9F, -4.0F, -3.0F, 5.0F, 5.0F, 6.0F),
                PartPose.ZERO
        );
        PartDefinition nose = head.getChild("nose");
        nose.addOrReplaceChild(
                "mole", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 3.0F, -6.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.25F)), PartPose.offset(0.0F, -2.0F, 0.0F)
        );
        root.addOrReplaceChild(
                "right_leg", CubeListBuilder.create().texOffs(0, 21).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F), PartPose.offset(-2.0F, 12.0F, 0.0F)
        ).addOrReplaceChild(
				"right_pants", CubeListBuilder.create().texOffs(64, 21).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 12.0F, 5.0F), PartPose.ZERO
		);
        root.addOrReplaceChild(
                "left_leg", CubeListBuilder.create().texOffs(0, 21).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F), PartPose.offset(2.0F, 12.0F, 0.0F)
        ).addOrReplaceChild(
				"left_pants", CubeListBuilder.create().texOffs(84, 21).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 12.0F, 5.0F), PartPose.ZERO
		);
        return LayerDefinition.create(mesh, 128, 128);
    }

    public void setupAnim(final VilerWitchRenderState state) {
        super.setupAnim(state);
        this.head.yRot = state.yRot * (float) (Math.PI / 180.0);
        this.head.xRot = state.xRot * (float) (Math.PI / 180.0);
        this.rightLeg.xRot = Mth.cos(state.walkAnimationPos * 0.6662F) * 1.4F * state.walkAnimationSpeed * 0.5F;
        this.leftLeg.xRot = Mth.cos(state.walkAnimationPos * 0.6662F + (float) Math.PI) * 1.4F * state.walkAnimationSpeed * 0.5F;
        float speed = 0.01F * (state.entityId % 10);
        this.nose.xRot = Mth.sin(state.ageInTicks * speed) * 4.5F * (float) (Math.PI / 180.0);
        this.nose.zRot = Mth.cos(state.ageInTicks * speed) * 2.5F * (float) (Math.PI / 180.0);
        if (state.isHoldingItem) {
            this.nose.setPos(0.0F, 1.0F, -1.5F);
            this.nose.xRot = -0.9F;
        }
    }

    public ModelPart getNose() {
        return this.nose;
    }

    @Override
    public ModelPart getHead() {
        return this.head;
    }

    public void translateToArms(final VilerWitchRenderState state, final PoseStack outputPoseStack) {
        this.root.translateAndRotate(outputPoseStack);
        this.arms.translateAndRotate(outputPoseStack);
    }
}
