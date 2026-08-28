package com.github.pigsteel.eum.client.renderer.entity;

import com.github.pigsteel.eum.EUM;
import com.github.pigsteel.eum.client.model.geom.EUMModelLayers;
import com.github.pigsteel.eum.world.entity.monster.skeleton.Lost;
import net.minecraft.client.renderer.entity.AbstractSkeletonRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import net.minecraft.resources.ResourceLocation;

public class LostRenderer extends AbstractSkeletonRenderer<Lost, SkeletonRenderState> {
    private static final ResourceLocation LOST_LOCATION = ResourceLocation.fromNamespaceAndPath(EUM.MOD_ID,"textures/entity/skeleton/lost.png");

    public LostRenderer(EntityRendererProvider.Context context) {
        super(context, EUMModelLayers.LOST, EUMModelLayers.LOST_ARMOR);
    }

    @Override
    public ResourceLocation getTextureLocation(final SkeletonRenderState state) {
        return LOST_LOCATION;
    }

    @Override
    public SkeletonRenderState createRenderState() {
        return new SkeletonRenderState();
    }

    public void extractRenderState(final Lost entity, final SkeletonRenderState state, final float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
    }
}
