package com.github.pigsteel.smcm.client.renderer.entity.layers;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.client.model.monster.zombie.BabyFrostbittenModel;
import com.github.pigsteel.smcm.client.model.monster.zombie.FrostbittenModel;
import com.github.pigsteel.smcm.client.renderer.entity.FrostbittenRenderer;
import com.github.pigsteel.smcm.registry.smcm$ModelLayers;
import com.github.pigsteel.smcm.client.renderer.entity.state.FrostbittenRenderState;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.SlimeRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;

public class FrostbittenOuterLayer extends RenderLayer<FrostbittenRenderState, FrostbittenModel> {
    private static final Identifier FROSTBITTEN_OUTER_LAYER_LOCATION = Identifier.fromNamespaceAndPath(SMCM.MOD_ID,"textures/entity/zombie/frostbitten_outer_layer.png");
    private static final Identifier BABY_FROSTBITTEN_OUTER_LAYER_LOCATION = Identifier.fromNamespaceAndPath(SMCM.MOD_ID,"textures/entity/zombie/frostbitten_outer_layer_baby.png");
    private final FrostbittenModel model;
    private final FrostbittenModel babyModel;

    public FrostbittenOuterLayer(RenderLayerParent<FrostbittenRenderState, FrostbittenModel> renderer, final EntityModelSet modelSet) {
        super(renderer);
        this.model = new FrostbittenModel(modelSet.bakeLayer(smcm$ModelLayers.FROSTBITTEN_OUTER_LAYER));
        this.babyModel = new BabyFrostbittenModel(modelSet.bakeLayer(smcm$ModelLayers.FROSTBITTEN_BABY_OUTER_LAYER));
    }

    public void submit(final PoseStack poseStack, final SubmitNodeCollector submitNodeCollector, final int lightCoords, final FrostbittenRenderState state, final float yRot, final float xRot) {
        boolean appearsGlowingWithInvisibility = state.appearsGlowing() && state.isInvisible;
        FrostbittenModel model = state.isBaby ? this.babyModel : this.model;
        Identifier layerLocation = state.isBaby ? BABY_FROSTBITTEN_OUTER_LAYER_LOCATION : FROSTBITTEN_OUTER_LAYER_LOCATION;
        if (!state.isInvisible || appearsGlowingWithInvisibility) {
            int overlayCoords = FrostbittenRenderer.getOverlayCoords(state, 0.0F);
            if (appearsGlowingWithInvisibility) {
                submitNodeCollector.order(1).submitModel(model, state, poseStack, RenderTypes.outline(layerLocation), lightCoords, overlayCoords, state.outlineColor, (ModelFeatureRenderer.CrumblingOverlay)null);;
            } else {
                submitNodeCollector.order(1).submitModel(model, state, poseStack, RenderTypes.entityTranslucent(layerLocation), lightCoords, overlayCoords, state.outlineColor, (ModelFeatureRenderer.CrumblingOverlay)null);
            }
        }
    }
}
