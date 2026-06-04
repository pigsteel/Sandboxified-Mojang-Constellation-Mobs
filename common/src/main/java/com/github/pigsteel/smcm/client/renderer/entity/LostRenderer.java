package com.github.pigsteel.smcm.client.renderer.entity;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.client.renderer.entity.state.ReclaimedRenderState;
import com.github.pigsteel.smcm.entity.skeleton.Lost;
import com.github.pigsteel.smcm.entity.zombie.Reclaimed;
import com.github.pigsteel.smcm.registry.smcm$ModelLayers;
import net.minecraft.client.renderer.entity.AbstractSkeletonRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.state.BlockState;

import static net.minecraft.client.renderer.entity.AbstractMinecartRenderer.BLOCK_DISPLAY_CONTEXT;

public class LostRenderer extends AbstractSkeletonRenderer<Lost, SkeletonRenderState> {
    private static final Identifier LOST_LOCATION = Identifier.fromNamespaceAndPath(SMCM.MOD_ID,"textures/entity/skeleton/lost.png");

    public LostRenderer(EntityRendererProvider.Context context) {
        super(context, smcm$ModelLayers.LOST, smcm$ModelLayers.LOST_ARMOR);
    }

    @Override
    public Identifier getTextureLocation(final SkeletonRenderState state) {
        return LOST_LOCATION;
    }

    @Override
    public SkeletonRenderState createRenderState() {
        return new SkeletonRenderState();
    }

    public void extractRenderState(final Lost entity, final SkeletonRenderState state, final float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        //state.isCrouching = entity.isCrouching();
    }
}
