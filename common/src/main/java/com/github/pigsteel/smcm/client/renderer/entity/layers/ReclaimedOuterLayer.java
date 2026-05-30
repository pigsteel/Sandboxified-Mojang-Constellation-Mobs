package com.github.pigsteel.smcm.client.renderer.entity.layers;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.client.model.monster.zombie.BabyReclaimedModel;
import com.github.pigsteel.smcm.client.model.monster.zombie.ReclaimedModel;
import com.github.pigsteel.smcm.registry.smcm$ModelLayers;
import com.github.pigsteel.smcm.client.renderer.entity.state.ReclaimedRenderState;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.Identifier;

public class ReclaimedOuterLayer extends RenderLayer<ReclaimedRenderState, ReclaimedModel> {
    private static final Identifier RECLAIMED_OUTER_LAYER_LOCATION = Identifier.fromNamespaceAndPath(SMCM.MOD_ID,"textures/entity/zombie/reclaimed_outer_layer.png");
    private static final Identifier BABY_RECLAIMED_OUTER_LAYER_LOCATION = Identifier.fromNamespaceAndPath(SMCM.MOD_ID,"textures/entity/zombie/reclaimed_outer_layer_baby.png");
    private final ReclaimedModel model;
    private final ReclaimedModel babyModel;

    public ReclaimedOuterLayer(final RenderLayerParent<ReclaimedRenderState, ReclaimedModel> renderer, final EntityModelSet modelSet) {
        super(renderer);
        this.model = new ReclaimedModel(modelSet.bakeLayer(smcm$ModelLayers.RECLAIMED_OUTER_LAYER));
        this.babyModel = new BabyReclaimedModel(modelSet.bakeLayer(smcm$ModelLayers.RECLAIMED_BABY_OUTER_LAYER));
    }

    public void submit(final PoseStack poseStack, final SubmitNodeCollector submitNodeCollector, final int lightCoords, final ReclaimedRenderState state, final float yRot, final float xRot) {
        ReclaimedModel model = state.isBaby ? this.babyModel : this.model;
        Identifier layerLocation = state.isBaby ? BABY_RECLAIMED_OUTER_LAYER_LOCATION : RECLAIMED_OUTER_LAYER_LOCATION;
        coloredCutoutModelCopyLayerRender(model, layerLocation, poseStack, submitNodeCollector, lightCoords, state, -1, 1);
    }
}