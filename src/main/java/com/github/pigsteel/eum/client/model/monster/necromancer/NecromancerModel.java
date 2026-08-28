package com.github.pigsteel.eum.client.model.monster.necromancer;

import com.github.pigsteel.eum.client.animation.definitions.NecromancerAnimation;
import com.github.pigsteel.eum.client.renderer.entity.state.NecromancerRenderState;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Ease;
import net.minecraft.util.Mth;
import org.joml.Vector3f;

import static com.github.pigsteel.eum.util.AnimationUtil.lerpModelPartRotIntoVector;
import static com.github.pigsteel.eum.util.AnimationUtil.progress;

public class NecromancerModel<S extends NecromancerRenderState> extends EntityModel<S> {
	public final ModelPart hip;
	public final ModelPart body;
	private final ModelPart head;
    private final ModelPart leftPauldron;
    private final ModelPart rightPauldron;
	private final ModelPart cloak;
	private final ModelPart leftLeg;
	private final ModelPart leftArm;
	private final ModelPart rightArm;
	private final ModelPart rightLeg;
	private final ModelPart redStrip;
	public final ModelPart staffArmPivot;
	public final ModelPart staffHandPivot;
	public final ModelPart staff;
	private boolean isLeftHanded;
	private ModelPart mainArm;
	private ModelPart offArm;

    private final KeyframeAnimation summonAnimation;
	private final KeyframeAnimation summonAnimationLeft;
    private final KeyframeAnimation shootingAnimation;
	private final KeyframeAnimation shootingAnimationLeft;

    public NecromancerModel(final ModelPart root) {
        super(root);

		this.hip = root.getChild("hip");
		this.body = this.hip.getChild("body");
		this.head = this.body.getChild("head");
		this.rightLeg = this.hip.getChild("right_leg");
		this.leftLeg = this.hip.getChild("left_leg");
		this.rightArm = this.body.getChild("right_arm");
		this.leftArm = this.body.getChild("left_arm");
		this.redStrip = this.hip.getChild("robes_strip");
		this.staffArmPivot = this.body.getChild("staff_arm_pivot");
		this.staffHandPivot = this.staffArmPivot.getChild("staff_hand_pivot");
		this.staff = this.staffHandPivot.getChild("staff");
		this.leftPauldron = this.body.getChild("left_pauldron");
		this.rightPauldron = this.body.getChild("right_pauldron");
		this.cloak = this.body.getChild("cloak");
		this.summonAnimation = NecromancerAnimation.NECROMANCER_SUMMON.bake(root);
		this.summonAnimationLeft = NecromancerAnimation.NECROMANCER_SUMMON_LEFT.bake(root);
		this.shootingAnimation = NecromancerAnimation.NECROMANCER_SHOOT.bake(root);
		this.shootingAnimationLeft = NecromancerAnimation.NECROMANCER_SHOOT_LEFT.bake(root);
    }

    @Override
    public void setupAnim(final S state) {
        super.setupAnim(state);
		this.isLeftHanded = state.isLeftHanded;
		if(!isLeftHanded) {
			this.mainArm = this.rightArm;
			this.offArm = this.leftArm;
		} else {
			this.mainArm = this.leftArm;
			this.offArm = this.rightArm;
		}

		float forwardBackSway = state.capeLean + state.capeFlap;

		this.redStrip.xRot = Mth.clamp(forwardBackSway, -45.0F, 45.0F) * Mth.DEG_TO_RAD;
		this.redStrip.zRot = Mth.clamp(state.capeLean2 * 1.25F, -18.0F, 18.0F) * Mth.DEG_TO_RAD;
		this.setupCloakAnim(state);
		this.animateWalk(state);

		if(state.shootingAnimationState.isStarted()) {
			this.mainArm.offsetRotation(new Vector3f(head.xRot, head.yRot, 0.0F));
		}

		this.runAnimations(state);

		this.head.xRot += state.xRot * ((float)Math.PI / 180F);
		this.head.yRot += state.yRot * ((float)Math.PI / 180F);

		this.adjustPauldrons();

		parentStaffToMainHand(); // This is just because we can possibly have a necromancer switch arms with the /data command, so we can't just parent the staff

		this.staff.y -= 4.0F;

		this.broadcastInfoToState(state);
    }

	public void broadcastInfoToState(final NecromancerRenderState state) {
		PoseStack poseStack = new PoseStack();

		this.root.translateAndRotate(poseStack);
		this.hip.translateAndRotate(poseStack);
		this.body.translateAndRotate(poseStack);
		this.staffArmPivot.translateAndRotate(poseStack);
		this.staffHandPivot.translateAndRotate(poseStack);
		this.staff.translateAndRotate(poseStack);

		Vector3f staffPosition = new Vector3f();
		poseStack.last().pose().transformPosition(staffPosition);
		staffPosition.add(new Vector3f(0, -1.6F, 0));
		staffPosition.rotate(Axis.YP.rotationDegrees(state.bodyRot + 180.0F));
		staffPosition.mul(new Vector3f(-1F, -1F, 1F).mul(state.scale));

		state.staffBallPosition.set(staffPosition);
	}

	public void setupCloakAnim(final NecromancerRenderState state) {
		float backSway = state.capeLean + state.capeFlap;
		float sideSway = state.capeLean2;

		float normalXRot = Mth.clamp(backSway, -1.0F, 90.0F) * Mth.DEG_TO_RAD;
		float normalZRot = Mth.clamp(sideSway, -16.0F, 16.0F) * Mth.DEG_TO_RAD;

		float time = state.summonAnimationState.isStarted() ? (float) Mth.clamp((state.summonAnimationState.getTimeInMillis(state.ageInTicks) / 1000.0D) / NecromancerAnimation.NECROMANCER_SUMMON_LENGTH, 0.0F, 1.0F) : 0.0F;
		// simple ease in ease out
		float in = Ease.inExpo(progress(time, 0F, 0.1F));
		float out = Ease.outSine(progress(time, 0.7F, 1.0F));

		float inverse = 1.0F - (in - out);
		float cloakProgress = 1.0F - inverse * inverse * inverse;

		float sideToSway = state.isLeftHanded ? -1.0F : 1.0F;

		float summonXRot = 55.0F * Mth.DEG_TO_RAD;
		float summonZRot = (Mth.clamp(sideSway * 1.35F - 20.0F * cloakProgress * sideToSway, -80.0F, 80.0F)) * Mth.DEG_TO_RAD;

		float flutterStrength = cloakProgress;
		float flutterX = Mth.sin(state.ageInTicks * 0.45F) * 3.0F * flutterStrength * Mth.DEG_TO_RAD;
		float flutterZ = Mth.sin(state.ageInTicks * 0.75F + 1.2F) * 5.0F * flutterStrength * Mth.DEG_TO_RAD;

		this.cloak.xRot += Mth.lerp(cloakProgress, normalXRot, summonXRot) + flutterX;
		this.cloak.yRot += 0.0F;
		this.cloak.zRot += Mth.lerp(cloakProgress, normalZRot, summonZRot) + flutterZ;
	}

    private void adjustPauldrons() {
        float inherit = 0.2F;

        this.leftPauldron.xRot = this.leftArm.xRot * inherit;
        this.leftPauldron.yRot = this.leftArm.yRot * inherit;
        this.leftPauldron.zRot = this.leftArm.zRot * inherit;

        this.rightPauldron.xRot = this.rightArm.xRot * inherit;
        this.rightPauldron.yRot = this.rightArm.yRot * inherit;
        this.rightPauldron.zRot = this.rightArm.zRot * inherit;
    }

	private void resetArmRot(ModelPart arm) {
		arm.setRotation(0.0F, 0.0F, 0.0F);
	}

	private void animateWalk(NecromancerRenderState state) {
		float mainHandOffset = isLeftHanded ? -1.0F : 1.0F;
		float animationPos = state.walkAnimationPos;
		float animationSpeed = state.walkAnimationSpeed;

		this.offArm.xRot += Mth.cos((double)(animationPos * 0.6662F)) * 2.0F * animationSpeed * 0.5F / state.speedValue;
		this.rightLeg.xRot += Mth.cos((double)(animationPos * 0.6662F)) * 1.4F * animationSpeed / state.speedValue;
		this.leftLeg.xRot += Mth.cos((double)(animationPos * 0.6662F + (float)Math.PI)) * 1.4F * animationSpeed / state.speedValue;
		this.rightLeg.yRot += 0.005F;
		this.leftLeg.yRot += -0.005F;
		this.rightLeg.zRot += 0.005F;
		this.leftLeg.zRot += -0.005F;

		float speedModifier = 0.2F * animationSpeed;

		this.mainArm.xRot += -Mth.HALF_PI + mainHandOffset * Mth.cos((double)(animationPos * 0.6662F + (float)Math.PI)) * speedModifier / state.speedValue;
		this.mainArm.yRot += 0.174533F * mainHandOffset;
	}

	private void runAnimations(NecromancerRenderState state) {
		if(!isLeftHanded) {
			this.shootingAnimation.apply(state.shootingAnimationState, state.ageInTicks);
			this.summonAnimation.apply(state.summonAnimationState, state.ageInTicks);
		} else {
			this.summonAnimationLeft.apply(state.summonAnimationState, state.ageInTicks);
			this.shootingAnimationLeft.apply(state.shootingAnimationState, state.ageInTicks);
		}
	}

	private void parentStaffToMainHand() {
		staffArmPivot.offsetPos(new Vector3f(this.mainArm.x, this.mainArm.y, this.mainArm.z));
		staffArmPivot.offsetRotation(new Vector3f(this.mainArm.xRot, this.mainArm.yRot, this.mainArm.zRot));
	}

	private void lerpIntoSummonPose(float progress, ModelPart mainArm, ModelPart offArm, float mainHandOffset) {
		lerpModelPartRotIntoVector(progress, head, 0f, 0f, 0f);
		lerpModelPartRotIntoVector(progress, mainArm, -Mth.HALF_PI, 0f, 0f);
	}

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
		PartDefinition hip = root.addOrReplaceChild("hip", CubeListBuilder.create(), PartPose.offset(0.0F, 12.0F, 0.0F));

        PartDefinition body = hip.addOrReplaceChild("body", CubeListBuilder.create()
				.texOffs(16, 16)
				.addBox(-4.0F, -12.0F, -2.0F, 8.0F, 12.0F, 4.0F),
				PartPose.offset(0.0F, 0.0F, 0.0F)
		);

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create()
				.texOffs(0, 0)
				.addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F),
				PartPose.offset(0.0F, -12.0F, 0.0F)
		);

        // the crown!!!
        head.addOrReplaceChild(
                "hat",
                CubeListBuilder.create()
                        .texOffs(32, 0)
                        .addBox(-4.0F, -8.5F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)),
                PartPose.ZERO
        );

        hip.addOrReplaceChild(
                "robes",
                CubeListBuilder.create()
                        .texOffs(40, 44)
                        .addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F),
				PartPose.ZERO
        );

        hip.addOrReplaceChild(
                "robes_strip",
                CubeListBuilder.create()
                        .texOffs(48, 16)
                        .addBox(-1.0F, 0.0F, 0.0F, 2.0F, 12.0F, 0.0F),
                PartPose.offset(0.0F, 0.0F, -2.0F)
        );

        body.addOrReplaceChild(
                "collar",
                CubeListBuilder.create()
                        .texOffs(4, 32)
                        .addBox(-4.0F, -1.0F, -3.0F, 8.0F, 8.0F, 6.0F, new CubeDeformation(0.25F)),
				PartPose.offset(0.0F, -12.0F, 0.0F)
        );

		body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F), PartPose.offset(-5.0F, -10.0F, 0.0F));
        body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F), PartPose.offset(5.0F, -10.0F, 0.0F));
        hip.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F), PartPose.offset(-2.0F, 0.0F, 0.0F));
        hip.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F), PartPose.offset(2.0F, 0.0F, 0.0F));

        body.addOrReplaceChild(
                "right_pauldron",
                CubeListBuilder.create()
                        .texOffs(32, 32)
                        .addBox(-4.5F, -3.0F, -3.0F, 5.0F, 6.0F, 6.0F, new CubeDeformation(0.25F)),
                PartPose.offset(-5.0F, -10.0F, 0.0F)
        );

		body.addOrReplaceChild(
				"left_pauldron",
				CubeListBuilder.create()
						.texOffs(32, 32)
						.mirror()
						.addBox(-0.5F, -3.0F, -3.0F, 5.0F, 6.0F, 6.0F, new CubeDeformation(0.25F)),
				PartPose.offset(5.0F, -10.0F, 0.0F)
		);

        PartDefinition staffToArmPivot = body.addOrReplaceChild(
                "staff_arm_pivot",
                CubeListBuilder.create(),
                PartPose.ZERO
        );

		PartDefinition staffHandPivot = staffToArmPivot.addOrReplaceChild(
				"staff_hand_pivot",
				CubeListBuilder.create(),
				PartPose.offsetAndRotation(0.0F, 10.0F, 0.0F, Mth.HALF_PI, 0.0F, 0.0F)
		);

		body.addOrReplaceChild(
				"cloak",
				CubeListBuilder.create()
						.texOffs(0, 64)
						.addBox(-8.0F, 0.0F, -2.5F, 16.0F, 24.0F, 5.0F),
				PartPose.offset(0.0F, -12.0F, 0.0F)
		);

		staffHandPivot.addOrReplaceChild(
                "staff",
                CubeListBuilder.create()
                        // shaft
                        .texOffs(0, 30)
                        .addBox(-0.5F, 1.5F, -0.5F, 1.0F, 24.0F, 1.0F)

                        // prong connectors
                        .texOffs(0, 2)
                        .addBox(-0.5F, 1.5F, -1.5F, 1.0F, 1.0F, 1.0F)
                        .texOffs(0, 2)
                        .addBox(-0.5F, 1.5F, 0.5F, 1.0F, 1.0F, 1.0F)
                        .texOffs(0, 2)
                        .addBox(0.5F, 1.5F, -0.5F, 1.0F, 1.0F, 1.0F)
                        .texOffs(0, 2)
                        .addBox(-1.5F, 1.5F, -0.5F, 1.0F, 1.0F, 1.0F)

                        // four raised prongs
                        .texOffs(0, 4)
                        .addBox(-2.5F, -0.5F, -0.5F, 1.0F, 3.0F, 1.0F)
                        .texOffs(0, 4)
                        .addBox(1.5F, -0.5F, -0.5F, 1.0F, 3.0F, 1.0F)
                        .texOffs(0, 4)
                        .addBox(-0.5F, -0.5F, -2.5F, 1.0F, 3.0F, 1.0F)
                        .texOffs(0, 4)
                        .addBox(-0.5F, -0.5F, 1.5F, 1.0F, 3.0F, 1.0F)

						// orb
						.texOffs(24, 2)
						.addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F),

                PartPose.offset(0, 0, 0)
        );

        return LayerDefinition.create(mesh, 64, 128);
    }
}
