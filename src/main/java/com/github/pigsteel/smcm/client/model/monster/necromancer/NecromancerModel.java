package com.github.pigsteel.smcm.client.model.monster.necromancer;

import com.github.pigsteel.smcm.client.animation.definitions.NecromancerAnimation;
import com.github.pigsteel.smcm.client.renderer.entity.state.NecromancerRenderState;
import com.github.pigsteel.smcm.util.AnimationUtil;
import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.HumanoidModel;
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

import java.util.Set;

import static com.github.pigsteel.smcm.util.AnimationUtil.lerpModelPartRotIntoVector;
import static com.github.pigsteel.smcm.util.AnimationUtil.progress;

public class NecromancerModel<S extends NecromancerRenderState> extends HumanoidModel<S> {
    private final ModelPart redStrip;
    private final ModelPart staffPivot;
    private final ModelPart staff;
    private final ModelPart leftPauldron;
    private final ModelPart rightPauldron;
	private final ModelPart cloak;
	private final ModelPart leftArm;
	private final ModelPart rightArm;

    private final KeyframeAnimation summonAnimation;
	private final KeyframeAnimation summonAnimationLeft;
    private final KeyframeAnimation shootingAnimation;

    public NecromancerModel(final ModelPart root) {
        super(root);

		this.redStrip = this.body.getChild("robes_strip");
		this.staffPivot = root.getChild("staff_pivot");
		this.staff = this.staffPivot.getChild("staff");
		this.leftPauldron = this.body.getChild("left_pauldron");
		this.rightPauldron = this.body.getChild("right_pauldron");
		this.cloak = this.body.getChild("cloak");
		this.leftArm = root.getChild("left_arm");
		this.rightArm = root.getChild("right_arm");
		this.summonAnimation = NecromancerAnimation.NECROMANCER_SUMMON.bake(root);
		this.summonAnimationLeft = NecromancerAnimation.NECROMANCER_SUMMON_LEFT.bake(root);
		this.shootingAnimation = NecromancerAnimation.NECROMANCER_SHOOT.bake(root);
    }

	public static LayerDefinition createMainLayer() {
		return createBodyLayer().apply((mesh) -> {
			mesh.getRoot().getChild("body").clearChild("cloak");
			return mesh;
		});
	}

	public static LayerDefinition createCloakLayer() {
		return createBodyLayer().apply((mesh) -> {
			mesh.getRoot().retainExactParts(Set.of("cloak"));
			return mesh;
		});
	}

    @Override
    public void setupAnim(final S state) {
        super.setupAnim(state);
		float forwardBackSway = state.capeLean + state.capeFlap;

		this.redStrip.xRot = Mth.clamp(forwardBackSway, -45.0F, 45.0F) * Mth.DEG_TO_RAD;
		this.redStrip.zRot = Mth.clamp(state.capeLean2 * 1.25F, -18.0F, 18.0F) * Mth.DEG_TO_RAD;

		this.animateWalk(state);
		boolean isLeftHanded = state.isLeftHanded;
		if(!isLeftHanded) {
			this.summonAnimation.apply(state.summonAnimationState, state.ageInTicks);
			this.shootingAnimation.apply(state.shootingAnimationState, state.ageInTicks);
		} else {
			this.summonAnimationLeft.apply(state.summonAnimationState, state.ageInTicks);
		}

		this.animatePauldrons();

		this.setupCloakAnim(state);
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

		/*
		 * Flutter only appears during summoning.
		 * Multiplying by cloakProgress makes it fade in with the cloak.
		 */
		float flutterStrength = cloakProgress;
		float flutterX = Mth.sin(state.ageInTicks * 0.45F) * 3.0F * flutterStrength * Mth.DEG_TO_RAD;
		float flutterZ = Mth.sin(state.ageInTicks * 0.75F + 1.2F) * 5.0F * flutterStrength * Mth.DEG_TO_RAD;

		this.cloak.xRot = Mth.lerp(cloakProgress, normalXRot, summonXRot) + flutterX;
		this.cloak.yRot = 0.0F;
		this.cloak.zRot = Mth.lerp(cloakProgress, normalZRot, summonZRot) + flutterZ;
	}

    private void animatePauldrons() {
        float inherit = 0.2F;

        this.leftPauldron.xRot = this.leftArm.xRot * inherit;
        this.leftPauldron.yRot = this.leftArm.yRot * inherit;
        this.leftPauldron.zRot = this.leftArm.zRot * inherit;

        this.rightPauldron.xRot = this.rightArm.xRot * inherit;
        this.rightPauldron.yRot = this.rightArm.yRot * inherit;
        this.rightPauldron.zRot = this.rightArm.zRot * inherit;
    }

	private void animateWalk(NecromancerRenderState state) {
		ModelPart mainArm;
		ModelPart offArm;
		boolean isLeftHanded = state.isLeftHanded;
		float mainHandOffset = isLeftHanded ? -1.0F : 1.0F;
		float animationPos = state.walkAnimationPos;
		float animationSpeed = state.walkAnimationSpeed;

		if(!isLeftHanded) {
			mainArm = this.rightArm;
			offArm = this.leftArm;
		} else {
			mainArm = this.leftArm;
			offArm = this.rightArm;
		}

		mainArm.xRot = 0.0F;
		mainArm.yRot = 0.0F;
		mainArm.zRot = 0.0F;

		float speedModifier = 0.2F * animationSpeed;

		mainArm.xRot = -Mth.HALF_PI + mainHandOffset * Mth.cos((double)(animationPos * 0.6662F + (float)Math.PI)) * speedModifier / state.speedValue;
		mainArm.yRot += 0.174533F * mainHandOffset;

		if(state.shootingAnimationState.isStarted()) {
			mainArm.offsetRotation(new Vector3f(this.head.xRot, this.head.yRot, this.head.zRot));
		}

		float time = state.summonAnimationState.isStarted() ? (float) Mth.clamp((state.summonAnimationState.getTimeInMillis(state.ageInTicks) / 1000.0D) / NecromancerAnimation.NECROMANCER_SUMMON_LENGTH, 0.0F, 1.0F) : 0.0F;

		float statueStrength = Ease.inSine(AnimationUtil.progress(time, 0.0f, 0.2f)) - Ease.outSine(AnimationUtil.progress(time, 0.4f, 0.5f));

		lerpIntoTPose(statueStrength, mainArm, offArm, mainHandOffset);

		// Where we do staff stuff
		translateAndRotateStaffToArm(mainArm);
		staff.z -= 10;
	}

	private void translateAndRotateStaffToArm(ModelPart arm) {
		staffPivot.offsetPos(new Vector3f(arm.x, arm.y, arm.z));
		staffPivot.offsetRotation(new Vector3f(arm.xRot + Mth.HALF_PI, arm.yRot, arm.zRot));
	}

	private void lerpIntoTPose(float progress, ModelPart mainArm, ModelPart offArm, float mainHandOffset) {
		lerpModelPartRotIntoVector(progress, head, 0f, 0f, 0f);
		lerpModelPartRotIntoVector(progress, mainArm, -Mth.HALF_PI, 0f, 0f);
	}

    private static LayerDefinition createBodyLayer() {
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
		root.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F), PartPose.offset(-5.0F, 2.0F, 0.0F));
        root.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F), PartPose.offset(5.0F, 2.0F, 0.0F));
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

		body.addOrReplaceChild(
				"cloak",
				CubeListBuilder.create()
						.texOffs(0, 0)
						.addBox(-8.0F, 0.0F, -2.5F, 16.0F, 24.0F, 5.0F),
				PartPose.ZERO
		);

		staffPivot.addOrReplaceChild(
                "staff",
                CubeListBuilder.create()
                        // shaft
                        .texOffs(0, 30)
                        .addBox(0.0F, 0.0F, 0.0F, 1.0F, 24.0F, 1.0F)

                        // prong connectors
                        .texOffs(0, 2)
                        .addBox(0.0F, 0.0F, -1.0F, 1.0F, 1.0F, 1.0F)
                        .texOffs(0, 2)
                        .addBox(0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F)
                        .texOffs(0, 2)
                        .addBox(1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F)
                        .texOffs(0, 2)
                        .addBox(-1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F)

                        // four raised prongs
                        .texOffs(0, 4)
                        .addBox(-2.0F, -2.0F, -0.0F, 1.0F, 3.0F, 1.0F)
                        .texOffs(0, 4)
                        .addBox(2.0F, -2.0F, -0.0F, 1.0F, 3.0F, 1.0F)
                        .texOffs(0, 4)
                        .addBox(-0.0F, -2.0F, -2.0F, 1.0F, 3.0F, 1.0F)
                        .texOffs(0, 4)
                        .addBox(-0.0F, -2.0F, 2.0F, 1.0F, 3.0F, 1.0F)

						// orb
						.texOffs(24, 2)
						.addBox(-1.0F, -3.0F, -1.0F, 3.0F, 3.0F, 3.0F),

                PartPose.offset(-0.5F, -3.0F, -0.5F)
        );

        return LayerDefinition.create(mesh, 64, 64);
    }
}
