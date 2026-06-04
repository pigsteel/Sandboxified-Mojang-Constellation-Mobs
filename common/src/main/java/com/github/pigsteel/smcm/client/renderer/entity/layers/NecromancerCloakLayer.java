package com.github.pigsteel.smcm.client.renderer.entity.layers;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.client.model.monster.necromancer.NecromancerCloakModel;
import com.github.pigsteel.smcm.client.model.monster.necromancer.NecromancerModel;
import com.github.pigsteel.smcm.client.renderer.entity.state.NecromancerRenderState;
import com.github.pigsteel.smcm.registry.smcm$ModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;

public class NecromancerCloakLayer extends RenderLayer<NecromancerRenderState, NecromancerModel<NecromancerRenderState>> {
    private static final Identifier CLOAK_TEXTURE = Identifier.fromNamespaceAndPath(
            SMCM.MOD_ID,
            "textures/entity/necromancer/cloak.png"
    );

    private final NecromancerCloakModel model;

    public NecromancerCloakLayer(
            RenderLayerParent<NecromancerRenderState, NecromancerModel<NecromancerRenderState>> renderer,
            EntityModelSet modelSet
    ) {
        super(renderer);
        this.model = new NecromancerCloakModel(
                modelSet.bakeLayer(smcm$ModelLayers.NECROMANCER_CLOAK)
        );
    }

    @Override
    public void submit(
            PoseStack poseStack,
            SubmitNodeCollector submitNodeCollector,
            int lightCoords,
            NecromancerRenderState state,
            float yRot,
            float xRot
    ) {
        if(!state.isInvisible) {
            poseStack.pushPose();

            submitNodeCollector.submitModel(
                    this.model,
                    state,
                    poseStack,
                    RenderTypes.entityCutout(CLOAK_TEXTURE),
                    lightCoords,
                    OverlayTexture.NO_OVERLAY,
                    state.outlineColor,
                    (ModelFeatureRenderer.CrumblingOverlay) null
            );

            poseStack.popPose();
        }
    }
}
