package com.github.pigsteel.smcm.client.renderer.entity;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.client.model.geom.smcm$ModelLayers;
import com.github.pigsteel.smcm.client.renderer.entity.state.GeomancerRenderState;
import com.github.pigsteel.smcm.world.entity.monster.illager.Geomancer;
import net.minecraft.client.model.monster.illager.IllagerModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.resources.Identifier;

public class GeomancerRenderer extends IllagerRenderer<Geomancer, GeomancerRenderState> {
    private static final Identifier GEOMANCER_LOCATION = SMCM.id("textures/entity/illager/geomancer.png");

    public GeomancerRenderer(EntityRendererProvider.Context context) {
        super(context, new IllagerModel<>(context.bakeLayer(smcm$ModelLayers.GEOMANCER)), 0.5F);
    }

    @Override
    public Identifier getTextureLocation(GeomancerRenderState state) {
        return GEOMANCER_LOCATION;
    }

    @Override
    public GeomancerRenderState createRenderState() {
        return new GeomancerRenderState();
    }
}
