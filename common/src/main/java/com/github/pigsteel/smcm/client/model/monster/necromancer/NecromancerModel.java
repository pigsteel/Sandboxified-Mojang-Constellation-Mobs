package com.github.pigsteel.smcm.client.model.monster.necromancer;

import com.github.pigsteel.smcm.client.renderer.entity.state.NecromancerRenderState;
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

    public NecromancerModel(ModelPart root) {
        super(root);

        if (this.body.hasChild("robes_strip")) {
            this.redStrip = this.body.getChild("robes_strip");
            this.staffPivot = root.getChild("staff_pivot");
            this.staff = this.staffPivot.getChild("staff");
            this.leftPauldron = this.body.getChild("left_pauldron");
            this.rightPauldron = this.body.getChild("right_pauldron");
        } else { // because of Cloak :/
            this.redStrip = null;
            this.staffPivot = null;
            this.staff = null;
            this.leftPauldron = null;
            this.rightPauldron = null;
        }
    }

    @Override
    public void setupAnim(NecromancerRenderState state) {
        super.setupAnim(state);

        if (this.redStrip != null) { // again because of cloak, have to null check
            float forwardBackSway = state.capeLean + state.capeFlap;

            this.redStrip.xRot = Mth.clamp(forwardBackSway, -45.0F, 45.0F) * Mth.DEG_TO_RAD;
            this.redStrip.zRot = Mth.clamp(state.capeLean2 * 1.25F, -18.0F, 18.0F) * Mth.DEG_TO_RAD;

            setupStaffPose(state);
            this.setupPauldrons();
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

    private void setupStaffPose(NecromancerRenderState state) {
        boolean leftHanded = state.mainArm == HumanoidArm.LEFT;

        this.poseStaff(leftHanded);

        if (state.beamProgress > 0.0F) {
            this.poseBeam(leftHanded, state.beamProgress);
        }

        float summonProgress = state.summonProgress;

        if (summonProgress > 0.0F) {
            this.poseSummoning(leftHanded, summonProgress, state);
        }
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

    private void poseSummoning(boolean leftHanded, float progress, NecromancerRenderState state) {
        ModelPart mainArm = leftHanded ? this.leftArm : this.rightArm;
        ModelPart offArm = leftHanded ? this.rightArm : this.leftArm;

        float side = leftHanded ? 1.0F : -1.0F;
        float reverse = leftHanded ? -1.0F : 1.0F;

        progress = Mth.clamp(progress, 0.0F, 1.0F);

        /*
         * Optional easing. This makes the motion less linear and less robotic.
         */
        float eased = progress * progress * (3.0F - 2.0F * progress);

        /*
         * Main arm raises upward with the staff.
         */
        mainArm.xRot = Mth.lerp(eased, mainArm.xRot, -155.0F * Mth.DEG_TO_RAD);
        mainArm.yRot = Mth.lerp(eased, mainArm.yRot, reverse * 12.0F * Mth.DEG_TO_RAD);
        mainArm.zRot = Mth.lerp(eased, mainArm.zRot, reverse * -18.0F * Mth.DEG_TO_RAD);

        /*
         * Offhand extends outward/forward.
         */
        offArm.xRot = Mth.lerp(eased, offArm.xRot, -65.0F * Mth.DEG_TO_RAD);
        offArm.yRot = Mth.lerp(eased, offArm.yRot, -reverse * 35.0F * Mth.DEG_TO_RAD);
        offArm.zRot = Mth.lerp(eased, offArm.zRot, -reverse * 42.0F * Mth.DEG_TO_RAD);

        /*
         * Re-copy staff after modifying main arm, because the main arm pose changed.
         */
        copyStaffToHand(mainArm);

        float normalX = leftHanded ? 4.5F : -5.5F;
        float normalY = -2.0F;
        float normalZ = -10.0F;

        /*
         * Raised ritual position.
         * Tune Y/Z if it clips into the arm/head.
         */
        float summonX = leftHanded ? 5.0F : -6.0F;
        float summonY = -17.0F;
        float summonZ = -7.0F;

        this.staff.setPos(
                Mth.lerp(eased, normalX, summonX),
                Mth.lerp(eased, normalY, summonY),
                Mth.lerp(eased, normalZ, summonZ)
        );

        /*
         * During normal holding, staffPivot follows the arm.
         * During summoning, pull the pivot rotation toward vertical.
         *
         * If your staff model points along +Y when unrotated, this is vertical.
         */
        this.staffPivot.xRot = Mth.lerp(eased, this.staffPivot.xRot, 0.0F);
        this.staffPivot.yRot = Mth.lerp(eased, this.staffPivot.yRot, 0.0F);
        this.staffPivot.zRot = Mth.lerp(eased, this.staffPivot.zRot, 0.0F);

        /*
         * Keep the staff child itself unrotated so the vertical pivot pose actually wins.
         */
        this.staff.setRotation(0.0F, 0.0F, 0.0F);

        /*
         * Front strip flies back during summoning.
         */
        if (this.redStrip != null) {
            this.redStrip.xRot = Mth.lerp(
                    eased,
                    this.redStrip.xRot,
                    -5.0F * Mth.DEG_TO_RAD
            );

            float inverse = 1.0F - progress;
            float cloakProgress = 1.0F - inverse * inverse * inverse;
            float flutterStrength = cloakProgress;
            float flutterZ = Mth.sin(state.ageInTicks * 0.75F + 1.2F) * 5.0F * flutterStrength * Mth.DEG_TO_RAD;

            this.redStrip.zRot = Mth.lerp(
                    eased,
                    this.redStrip.zRot,
                    side * 10.0F * Mth.DEG_TO_RAD
            ) + flutterZ;
        }
    }

    private void poseBeam(boolean leftHanded, float progress) {
        ModelPart mainArm = leftHanded ? this.leftArm : this.rightArm;
        ModelPart offArm = leftHanded ? this.rightArm : this.leftArm;

        float side = leftHanded ? 1.0F : -1.0F;
        float reverse = leftHanded ? -1.0F : 1.0F;

        progress = Mth.clamp(progress, 0.0F, 1.0F);

        /*
         * Basic attack shape:
         * 0.00 - 0.38: draw staff back
         * 0.38 - 0.68: thrust / point forward
         * 0.68 - 1.00: hold forward beam pose
         */
        float windup = smoothstep(inverseLerp(0.0F, 0.38F, progress));
        float thrust = smoothstep(inverseLerp(0.38F, 0.68F, progress));

        /*
         * This keeps the whole beam pose blending in during windup,
         * then fully owning the arms after thrust.
         */
        float beamBlend = Math.max(windup, thrust);

        /*
         * Main arm pose.
         *
         * Windup:
         *   arm comes back/out to the side, staff still held low.
         *
         * Forward:
         *   arm points out at the target.
         */
        float windupMainX = -38.0F * Mth.DEG_TO_RAD;
        float windupMainY = reverse * 58.0F * Mth.DEG_TO_RAD;
        float windupMainZ = reverse * 30.0F * Mth.DEG_TO_RAD;

        float forwardMainX = -96.0F * Mth.DEG_TO_RAD;
        float forwardMainY = reverse * 2.0F * Mth.DEG_TO_RAD;
        float forwardMainZ = reverse * 4.0F * Mth.DEG_TO_RAD;

        float targetMainX = Mth.lerp(thrust, windupMainX, forwardMainX);
        float targetMainY = Mth.lerp(thrust, windupMainY, forwardMainY);
        float targetMainZ = Mth.lerp(thrust, windupMainZ, forwardMainZ);

        mainArm.xRot = Mth.lerp(beamBlend, mainArm.xRot, targetMainX);
        mainArm.yRot = Mth.lerp(beamBlend, mainArm.yRot, targetMainY);
        mainArm.zRot = Mth.lerp(beamBlend, mainArm.zRot, targetMainZ);

        /*
         * Offhand:
         * opens out as the staff winds back, then braces during the forward cast.
         */
        float windupOffX = -28.0F * Mth.DEG_TO_RAD;
        float windupOffY = -reverse * 42.0F * Mth.DEG_TO_RAD;
        float windupOffZ = -reverse * 30.0F * Mth.DEG_TO_RAD;

        float forwardOffX = -58.0F * Mth.DEG_TO_RAD;
        float forwardOffY = -reverse * 24.0F * Mth.DEG_TO_RAD;
        float forwardOffZ = -reverse * 46.0F * Mth.DEG_TO_RAD;

        float targetOffX = Mth.lerp(thrust, windupOffX, forwardOffX);
        float targetOffY = Mth.lerp(thrust, windupOffY, forwardOffY);
        float targetOffZ = Mth.lerp(thrust, windupOffZ, forwardOffZ);

        offArm.xRot = Mth.lerp(beamBlend, offArm.xRot, targetOffX);
        offArm.yRot = Mth.lerp(beamBlend, offArm.yRot, targetOffY);
        offArm.zRot = Mth.lerp(beamBlend, offArm.zRot, targetOffZ);

        /*
         * Re-copy the staff pivot after modifying the main arm.
         * This keeps the staff visually attached to the hand.
         */
        copyStaffToHand(mainArm);

        /*
         * Low grip on handle.
         *
         * More negative Y pushes the staff geometry upward relative to the hand,
         * making the hand appear lower on the shaft.
         */
        float gripX = leftHanded ? 4.5F : -5.5F;

        float windupStaffY = -8.5F;
        float windupStaffZ = -7.5F;

        float forwardStaffY = -8.5F;
        float forwardStaffZ = -15.5F;

        this.staff.setPos(
                gripX,
                Mth.lerp(thrust, windupStaffY, forwardStaffY),
                Mth.lerp(thrust, windupStaffZ, forwardStaffZ)
        );

        /*
         * Staff local rotation.
         *
         * Windup:
         *   staff angles back with the hand.
         *
         * Forward:
         *   staff straightens toward the target.
         */
        float windupStaffXRot = 24.0F * Mth.DEG_TO_RAD;
        float windupStaffYRot = reverse * 22.0F * Mth.DEG_TO_RAD;
        float windupStaffZRot = -reverse * 14.0F * Mth.DEG_TO_RAD;

        float forwardStaffXRot = -10.0F * Mth.DEG_TO_RAD;
        float forwardStaffYRot = 0.0F;
        float forwardStaffZRot = 0.0F;

        this.staff.xRot = Mth.lerp(thrust, windupStaffXRot, forwardStaffXRot);
        this.staff.yRot = Mth.lerp(thrust, windupStaffYRot, forwardStaffYRot);
        this.staff.zRot = Mth.lerp(thrust, windupStaffZRot, forwardStaffZRot);

        /*
         * Small recoil/energy hold once the staff is forward.
         * Optional, but it helps avoid the pose looking frozen.
         */
        if (progress > 0.68F) {
            float hold = smoothstep(inverseLerp(0.68F, 1.0F, progress));
            float tremor = Mth.sin(progress * 80.0F) * 1.5F * hold * Mth.DEG_TO_RAD;

            mainArm.xRot += tremor;
            this.staff.xRot += tremor * 0.5F;
            this.staff.zRot += side * tremor * 0.35F;
        }
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
                PartPose.offset(0.5F, -3.0F, 0.5F)
        );

        return LayerDefinition.create(mesh, 64, 64);
    }
}
