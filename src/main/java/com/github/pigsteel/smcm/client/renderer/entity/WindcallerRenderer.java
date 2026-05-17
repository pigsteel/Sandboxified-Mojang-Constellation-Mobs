package com.github.pigsteel.smcm.client.renderer.entity;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.client.model.geom.smcm$ModelLayers;
import com.github.pigsteel.smcm.client.model.monster.windcaller.WindcallerModel;
import com.github.pigsteel.smcm.client.renderer.entity.state.WindcallerRenderState;
import com.github.pigsteel.smcm.world.entity.monster.illager.Windcaller;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.resources.Identifier;

public class WindcallerRenderer extends IllagerRenderer<Windcaller, WindcallerRenderState> {
    private static final Identifier WINDCALLER_LOCATION = SMCM.id("textures/entity/illager/windcaller.png");

    public WindcallerRenderer(EntityRendererProvider.Context context) {
        super(context, new WindcallerModel<>(context.bakeLayer(smcm$ModelLayers.WINDCALLER)), 0.5F);
    }

    @Override
    public Identifier getTextureLocation(WindcallerRenderState state) {
        return WINDCALLER_LOCATION;
    }

    @Override
    public WindcallerRenderState createRenderState() {
        return new WindcallerRenderState();
    }
}
