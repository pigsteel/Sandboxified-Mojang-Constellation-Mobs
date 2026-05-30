package com.github.pigsteel.smcm.client.renderer.entity;

import com.github.pigsteel.smcm.client.model.monster.NecromancerModel;
import com.github.pigsteel.smcm.client.renderer.entity.state.NecromancerRenderState;
import com.github.pigsteel.smcm.entity.Necromancer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.AbstractSkeletonRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.Identifier;

import javax.naming.Context;

public class NecromancerRenderer extends HumanoidMobRenderer<Necromancer, NecromancerRenderState, NecromancerModel<NecromancerRenderState>> {
    public NecromancerRenderer(EntityRendererProvider.Context context, ModelLayerLocation body) {
        super(context, new NecromancerModel<>(context.bakeLayer(body)), 1.0f);
    }

    @Override
    public Identifier getTextureLocation(NecromancerRenderState necromancerRenderState) {
        return null;
    }

    @Override
    public NecromancerRenderState createRenderState() {
        return null;
    }
}
