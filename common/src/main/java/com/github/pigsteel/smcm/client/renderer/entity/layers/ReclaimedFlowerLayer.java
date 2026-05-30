package com.github.pigsteel.smcm.client.renderer.entity.layers;

import com.github.pigsteel.smcm.client.model.monster.zombie.ReclaimedModel;
import com.github.pigsteel.smcm.client.renderer.entity.state.ReclaimedRenderState;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;

public class ReclaimedFlowerLayer extends RenderLayer<ReclaimedRenderState, ReclaimedModel> {
    public ReclaimedFlowerLayer(RenderLayerParent<ReclaimedRenderState, ReclaimedModel> renderer) {
        super(renderer);
    }

    private void submitFlowerBlock(
            final PoseStack poseStack,
            final SubmitNodeCollector submitNodeCollector,
            final int lightCoords,
            final boolean appearsGlowingWithInvisibility,
            final int outlineColor,
            final BlockModelRenderState flowerModel,
            final int overlayCoords
    ) {
        if (appearsGlowingWithInvisibility) {
            flowerModel.submitOnlyOutline(poseStack, submitNodeCollector, lightCoords, overlayCoords, outlineColor);
        } else {
            flowerModel.submit(poseStack, submitNodeCollector, lightCoords, overlayCoords, outlineColor);
        }
    }

    @Override
    public void submit(
            final PoseStack poseStack,
            final SubmitNodeCollector submitNodeCollector,
            final int lightCoords,
            final ReclaimedRenderState state,
            final float yRot,
            final float xRot
    ) {
        if (!state.isBaby && !state.flowerModel.isEmpty()) {
            boolean appearsGlowingWithInvisibility = state.appearsGlowing() && state.isInvisible;
            if (!state.isInvisible || appearsGlowingWithInvisibility) {
                if (true) { // for dev
                    int overlayCoords = LivingEntityRenderer.getOverlayCoords(state, 0.0F);
                    poseStack.pushPose();
                    this.getParentModel().getHead().translateAndRotate(poseStack);
                    poseStack.translate(0.0F, -0.7F, -0.0F);
                    poseStack.mulPose(Axis.YP.rotationDegrees(-50.0F));
                    poseStack.scale(-0.8F, -0.8F, 0.8F);
                    poseStack.translate(-0.5F, -0.5F, -0.5F);
                    this.submitFlowerBlock(
                            poseStack, submitNodeCollector, lightCoords, appearsGlowingWithInvisibility, state.outlineColor, state.flowerModel, overlayCoords
                    );
                    poseStack.popPose();
                } else {
                    // for flowerbed config
                    int overlayCoords = LivingEntityRenderer.getOverlayCoords(state, 0.0F);
                    poseStack.pushPose();
                    this.getParentModel().getHead().translateAndRotate(poseStack);
                    poseStack.translate(-0.25F, -0.9F, 0.25F);
                    poseStack.mulPose(Axis.YP.rotationDegrees(0.0F));
                    final float scale = 1.0F;
                    poseStack.scale(-scale, -scale, scale);
                    poseStack.translate(-0.5F, -0.5F, -0.5F);
                    this.submitFlowerBlock(
                            poseStack, submitNodeCollector, lightCoords, appearsGlowingWithInvisibility, state.outlineColor, state.flowerModel, overlayCoords
                    );
                    poseStack.popPose();

                }
            }
        }
    }
}
