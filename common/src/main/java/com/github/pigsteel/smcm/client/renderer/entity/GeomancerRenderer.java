package com.github.pigsteel.smcm.client.renderer.entity;

import com.github.pigsteel.smcm.client.renderer.entity.state.GeomancerRenderState;
import com.github.pigsteel.smcm.entity.illager.Geomancer;
import net.minecraft.client.model.monster.illager.IllagerModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.resources.Identifier;

public class GeomancerRenderer extends IllagerRenderer<Geomancer, GeomancerRenderState> {
    protected GeomancerRenderer(EntityRendererProvider.Context context, IllagerModel<GeomancerRenderState> model, float shadow) {
        super(context, model, shadow);
    }

    @Override
    public Identifier getTextureLocation(GeomancerRenderState state) {
        return null;
    }

    @Override
    public GeomancerRenderState createRenderState() {
        return null;
    }
}
