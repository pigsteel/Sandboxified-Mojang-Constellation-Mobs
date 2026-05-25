package com.github.pigsteel.smcm.client.renderer.entity;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.registry.ModelLayers;
import com.github.pigsteel.smcm.entity.skeleton.Sunken;
import net.minecraft.client.renderer.entity.AbstractSkeletonRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import net.minecraft.resources.Identifier;

public class SunkenRenderer extends AbstractSkeletonRenderer<Sunken, SkeletonRenderState> {
    private static final Identifier SUNKEN_LOCATION = Identifier.fromNamespaceAndPath(SMCM.MOD_ID,"textures/entity/skeleton/sunken.png");

    public SunkenRenderer(EntityRendererProvider.Context context) {
        super(context, ModelLayers.SUNKEN, ModelLayers.SUNKEN_ARMOR);
    }

    @Override
    public Identifier getTextureLocation(final SkeletonRenderState state) {
        return SUNKEN_LOCATION;
    }

    @Override
    public SkeletonRenderState createRenderState() {
        return new SkeletonRenderState();
    }
}
