package com.github.pigsteel.eum.client.renderer.entity;

import com.github.pigsteel.eum.EUM;
import com.github.pigsteel.eum.client.model.geom.EUMModelLayers;
import com.github.pigsteel.eum.client.model.monster.zombie.ReclaimedPukeModel;
import com.github.pigsteel.eum.client.renderer.entity.state.ReclaimedPukeRenderState;
import com.github.pigsteel.eum.world.entity.projectile.ReclaimedPuke;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class ReclaimedPukeRenderer extends EntityRenderer<ReclaimedPuke, ReclaimedPukeRenderState> {
    private static final ResourceLocation RECLAIMED_PUKE_LOCATION = EUM.id("textures/entity/reclaimed/reclaimed_puke.png");
    private final ReclaimedPukeModel model;

    public ReclaimedPukeRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new ReclaimedPukeModel(context.bakeLayer(EUMModelLayers.RECLAIMED_PUKE));
    }

    @Override
    public ReclaimedPukeRenderState createRenderState() {
        return new ReclaimedPukeRenderState();
    }

    public void submit(final ReclaimedPukeRenderState state, final PoseStack poseStack, final SubmitNodeCollector submitNodeCollector, final CameraRenderState camera) {
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(state.yRot - 90.0F));
        poseStack.mulPose(Axis.ZP.rotationDegrees(state.xRot));
        submitNodeCollector.submitModel(
                this.model, state, poseStack, RECLAIMED_PUKE_LOCATION, state.lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor, null
        );
        poseStack.popPose();
        super.submit(state, poseStack, submitNodeCollector, camera);
    }
}
