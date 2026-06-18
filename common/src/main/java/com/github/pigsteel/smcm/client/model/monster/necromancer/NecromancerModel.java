package com.github.pigsteel.smcm.client.model.monster.necromancer;

import com.github.pigsteel.smcm.client.animation.definitions.NecromancerAnimation;
import com.github.pigsteel.smcm.client.renderer.entity.state.NecromancerRenderState;
import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;

public class NecromancerModel<T extends NecromancerRenderState> extends HumanoidModel<NecromancerRenderState> {
    private final ModelPart redStrip;
    private final ModelPart staffPivot;
    private final ModelPart staff;
    private final ModelPart leftPauldron;
    private final ModelPart rightPauldron;

    private final KeyframeAnimation summonAnimation;
    private final KeyframeAnimation shootingAnimation;

    public NecromancerModel(ModelPart root) {
        super(root);

        if (this.body.hasChild("robes_strip")) {
            this.redStrip = this.body.getChild("robes_strip");
            this.staffPivot = root.getChild("staff_pivot");
            this.staff = this.staffPivot.getChild("staff");
            this.leftPauldron = this.body.getChild("left_pauldron");
            this.rightPauldron = this.body.getChild("right_pauldron");
            this.summonAnimation = NecromancerAnimation.NECROMANCER_SUMMON.bake(root);
            this.shootingAnimation = NecromancerAnimation.NECROMANCER_SHOOT.bake(root);
        } else { // because of Cloak :/
            this.redStrip = null;
            this.staffPivot = null;
            this.staff = null;
            this.leftPauldron = null;
            this.rightPauldron = null;
            this.summonAnimation = null;
            this.shootingAnimation = null;
        }
    }

    @Override
    public void setupAnim(NecromancerRenderState state) {
        super.setupAnim(state);

        if (this.redStrip != null) { // again because of cloak, have to null check
            float forwardBackSway = state.capeLean + state.capeFlap;

            this.redStrip.xRot = Mth.clamp(forwardBackSway, -45.0F, 45.0F) * Mth.DEG_TO_RAD;
            this.redStrip.zRot = Mth.clamp(state.capeLean2 * 1.25F, -18.0F, 18.0F) * Mth.DEG_TO_RAD;

            this.setupPauldrons();
            this.summonAnimation.apply(state.summonAnimationState, state.ageInTicks);
            this.shootingAnimation.apply(state.shootingAnimationState, state.ageInTicks);
        }
    }

    private void setupPauldrons() {
        float inherit = 0.2F;

        this.leftPauldron.xRot = this.leftArm.xRot * inherit;
        this.leftPauldron.yRot = this.leftArm.yRot * inherit;
        this.leftPauldron.zRot = this.leftArm.zRot * inherit;

        this.rightPauldron.xRot = this.rightArm.xRot * inherit;
        this.rightPauldron.yRot = this.rightArm.yRot * inherit;
        this.rightPauldron.zRot = this.rightArm.zRot * inherit;
    }

    private void poseStaff(boolean leftHanded) {
        float armDampener = 0.15F;

        ModelPart mainArm = leftHanded ? this.leftArm : this.rightArm;
        ModelPart offArm = leftHanded ? this.rightArm : this.leftArm;
        float xOffset = leftHanded ? 4.5F : -5.5F;
        float reverse = leftHanded ? -1.0F : 1.0F;

        // Arm bent/raised forward around 90 degrees.
        mainArm.xRot = -Mth.HALF_PI + mainArm.xRot * armDampener;
        mainArm.yRot = reverse * 8.0F * Mth.DEG_TO_RAD + mainArm.yRot * armDampener;
        mainArm.zRot = reverse * 4.0F * Mth.DEG_TO_RAD + mainArm.zRot * armDampener;

        // Optional: keep offhand calmer.
        offArm.xRot *= 0.25F;
        offArm.zRot -= 0.135F * reverse;

        copyStaffToHand(mainArm);

        this.staff.setPos(xOffset, -1.75F, -10.0F);

        this.staff.setRotation(0.0F, 0.0F, 0.0F);
    }

    private static void copyRotation(ModelPart target, ModelPart source) {
        target.xRot = source.xRot + Mth.HALF_PI;
        target.yRot = source.yRot;
        target.zRot = source.zRot;
    }

    private void copyStaffToHand(ModelPart arm) {
        copyRotation(this.staffPivot, arm);
    }

    private static float smoothstep(float progress) {
        progress = Mth.clamp(progress, 0.0F, 1.0F);
        return progress * progress * (3.0F - 2.0F * progress);
    }

    private static float inverseLerp(float min, float max, float value) {
        return Mth.clamp((value - min) / (max - min), 0.0F, 1.0F);
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
        body.addOrReplaceChild(
                "left_pauldron",
                CubeListBuilder.create()
                        .texOffs(32, 32)
                        .mirror()
                        .addBox(-0.5F, -3.0F, -3.0F, 5.0F, 6.0F, 6.0F, new CubeDeformation(0.25F)),
                PartPose.offset(5.0F, 2.0F, 0.0F)
        );

        body.addOrReplaceChild(
                "right_pauldron",
                CubeListBuilder.create()
                        .texOffs(32, 32)
                        .addBox(-4.5F, -3.0F, -3.0F, 5.0F, 6.0F, 6.0F, new CubeDeformation(0.25F)),
                PartPose.offset(-5.0F, 2.0F, 0.0F)
        );

        PartDefinition staffPivot = root.addOrReplaceChild(
                "staff_pivot",
                CubeListBuilder.create(),
                PartPose.ZERO
        );

        PartDefinition staff = staffPivot.addOrReplaceChild(
                "staff",
                CubeListBuilder.create()
                        // shaft
                        // BB: [-1, 0, -13] -> [0, 24, -12]
                        .texOffs(0, 30)
                        .addBox(0.0F, 0.0F, 0.0F, 1.0F, 24.0F, 1.0F)

                        // small top blocks / ring pieces
                        // BB y 23..24 -> MC local y: -1..0 relative to pivot y=1
                        .texOffs(0, 2)
                        .addBox(0.0F, 0.0F, -1.0F, 1.0F, 1.0F, 1.0F)
                        .texOffs(0, 2)
                        .addBox(0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F)
                        .texOffs(0, 2)
                        .addBox(1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F)
                        .texOffs(0, 2)
                        .addBox(-1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F)

                        // orb
                        // BB: [-2, 24, -14] -> [1, 27, -11]
                        // MC local y = (24 - 27) - 1 = -4
                        .texOffs(24, 2)
                        .addBox(-1.0F, -3.0F, -1.0F, 3.0F, 3.0F, 3.0F)

                        // four raised prongs
                        // if BB y 24..27, same local y -4
                        .texOffs(0, 4)
                        .addBox(-2.0F, -2.0F, -0.0F, 1.0F, 3.0F, 1.0F)
                        .texOffs(0, 4)
                        .addBox(2.0F, -2.0F, -0.0F, 1.0F, 3.0F, 1.0F)
                        .texOffs(0, 4)
                        .addBox(-0.0F, -2.0F, -2.0F, 1.0F, 3.0F, 1.0F)
                        .texOffs(0, 4)
                        .addBox(-0.0F, -2.0F, 2.0F, 1.0F, 3.0F, 1.0F),
                PartPose.offset(-0.5F, -3.0F, -0.5F)
        );

        return LayerDefinition.create(mesh, 64, 64);
    }
}
