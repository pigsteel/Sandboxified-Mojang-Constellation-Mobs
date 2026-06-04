package com.github.pigsteel.smcm.client.model.monster.necromancer;

import com.github.pigsteel.smcm.client.renderer.entity.state.NecromancerRenderState;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class NecromancerModel<T extends NecromancerRenderState> extends HumanoidModel<NecromancerRenderState> {
    private final ModelPart redStrip;

    public NecromancerModel(ModelPart root) {
        super(root);

        this.redStrip = this.body.hasChild("robes_strip")
                ? this.body.getChild("robes_strip")
                : null;
    }

    @Override
    public void setupAnim(NecromancerRenderState state) {
        super.setupAnim(state);

        if (this.redStrip != null) {
            float forwardBackSway = state.capeLean + state.capeFlap;

            this.redStrip.xRot = Mth.clamp(forwardBackSway, -45.0F, 45.0F) * Mth.DEG_TO_RAD;
            this.redStrip.zRot = Mth.clamp(state.capeLean2 * 1.25F, -18.0F, 18.0F) * Mth.DEG_TO_RAD;
        }
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
        PartDefinition root = mesh.getRoot();
        PartDefinition body = root.getChild("body");
        PartDefinition head = root.getChild("head");

        /*
            The Crown!
         */
        head.addOrReplaceChild(
                "hat",
                CubeListBuilder.create()
                        .texOffs(32, 0)
                        .addBox(-4.0F, -8.5F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)),
                PartPose.ZERO
        );

        /*
         * Lower robe/body section from the bbmodel "body2" group.
         * Attached to body so it follows body rotation.
         */
        body.addOrReplaceChild(
                "robes",
                CubeListBuilder.create()
                        .texOffs(40, 44)
                        .addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F),
                PartPose.offset(0.0F, 12.0F, 0.0F)
        );

        /*
         * Thin red front cloak strip from the base texture.
         * The original cube is zero-depth, so I give it tiny thickness to avoid culling weirdness.
         */
        body.addOrReplaceChild(
                "robes_strip",
                CubeListBuilder.create()
                        .texOffs(48, 16)
                        .addBox(0.0F, 0.0F, 0.0F, 2.0F, 12.0F, 0.0F),
                PartPose.offset(-1.0F, 12.0F, -2.0F)
        );

        /*
         * Back upper cloak/collar from the base texture.
         */
        body.addOrReplaceChild(
                "collar",
                CubeListBuilder.create()
                        .texOffs(4, 32)
                        .addBox(-4.0F, -1.0F, -3.0F, 8.0F, 8.0F, 6.0F, new CubeDeformation(0.25F)),
                PartPose.offset(0.0F, 0.0F, 0.0F)
        );

        // From SkeletonModel
        PartDefinition rightArm = root.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F), PartPose.offset(-5.0F, 2.0F, 0.0F));
        PartDefinition leftArm = root.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F), PartPose.offset(5.0F, 2.0F, 0.0F));
        root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F), PartPose.offset(-2.0F, 12.0F, 0.0F));
        root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F), PartPose.offset(2.0F, 12.0F, 0.0F));

        /*
         * Pauldrons. These are attached to arms so they animate with the arm swing.
         * You may want to tweak these offsets after seeing them in-game.
         */
        leftArm.addOrReplaceChild(
                "left_pauldron",
                CubeListBuilder.create()
                        .texOffs(32, 32)
                        .mirror()
                        .addBox(-0.5F, -3.0F, -3.0F, 5.0F, 6.0F, 6.0F, new CubeDeformation(0.25F)),
                PartPose.offset(0F, 0F, 0.0F)
        );

        rightArm.addOrReplaceChild(
                "right_pauldron",
                CubeListBuilder.create()
                        .texOffs(32, 32)
                        .addBox(-4.5F, -3.0F, -3.0F, 5.0F, 6.0F, 6.0F, new CubeDeformation(0.25F)),
                PartPose.offset(0F, 0F, 0.0F)
        );

        return LayerDefinition.create(mesh, 64, 64);
    }
}
