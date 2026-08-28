package com.github.pigsteel.eum.client.renderer.entity;

import com.github.pigsteel.eum.EUM;
import com.github.pigsteel.eum.client.model.geom.EUMModelLayers;
import com.github.pigsteel.eum.client.model.monster.necromancer.NecromancerBallModel;
import com.github.pigsteel.eum.client.renderer.entity.state.NecromancerBallRenderState;
import com.github.pigsteel.eum.world.entity.projectile.NecromancerBall;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderSetup;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.TextureTransform;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.LightCoordsUtil;

public class NecromancerBallRenderer extends EntityRenderer<NecromancerBall, NecromancerBallRenderState> {
	private final NecromancerBallModel model;
	private static final ResourceLocation NECROMANCER_BALL_LOCATION = EUM.id("textures/entity/projectiles/necromancer_ball.png");

	public NecromancerBallRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.model = new NecromancerBallModel(context.bakeLayer(EUMModelLayers.NECROMANCER_BALL));

	}

	@Override
	public NecromancerBallRenderState createRenderState() {
		return new NecromancerBallRenderState();
	}

	@Override
	public void submit(final NecromancerBallRenderState state, final PoseStack poseStack, final SubmitNodeCollector submitNodeCollector, final CameraRenderState camera) {
		float offsetValue = this.animOffset(state.ageInTicks);
		int currentFrame = (int)(offsetValue % 8);
		float vOffset = currentFrame * (0.125F);

		RenderType renderType = necromancerBallFire(NECROMANCER_BALL_LOCATION, 0.0F, vOffset);
		poseStack.pushPose();
		poseStack.mulPose(Axis.YP.rotationDegrees(-state.yRot));
		poseStack.mulPose(Axis.XP.rotationDegrees(state.xRot));
		poseStack.scale(-1.0F, -1.0F, 1.0F);

		submitNodeCollector.submitModel(
				this.model,
				state,
				poseStack,
				renderType,
				LightCoordsUtil.FULL_BRIGHT,
				OverlayTexture.NO_OVERLAY,
				state.outlineColor,
				(ModelFeatureRenderer.CrumblingOverlay) null
		);
		poseStack.popPose();
		super.submit(state, poseStack, submitNodeCollector, camera);
	}

	protected float animOffset(final float t) {
		return t * 0.34F;
	}

	public void extractRenderState(final NecromancerBall entity, final NecromancerBallRenderState state, final float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.xRot = entity.getXRot(partialTicks);
		state.yRot = entity.getYRot(partialTicks);
	}

	public static RenderType necromancerBallFire(final ResourceLocation texture, final float uOffset, final float vOffset) {
		return RenderType.create("necromancer_ball_fire", RenderSetup.builder(RenderPipelines.BREEZE_WIND).withTexture("Sampler0", texture).setTextureTransform(new TextureTransform.OffsetTextureTransform(uOffset, vOffset)).useLightmap().sortOnUpload().createRenderSetup());
	}
}
