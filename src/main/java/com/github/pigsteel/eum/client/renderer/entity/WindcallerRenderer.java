package com.github.pigsteel.eum.client.renderer.entity;

import com.github.pigsteel.eum.EUM;
import com.github.pigsteel.eum.client.model.geom.EUMModelLayers;
import com.github.pigsteel.eum.client.model.monster.windcaller.WindcallerModel;
import com.github.pigsteel.eum.client.renderer.entity.state.WindcallerRenderState;
import com.github.pigsteel.eum.world.entity.monster.illager.Windcaller;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.resources.Identifier;

public class WindcallerRenderer extends IllagerRenderer<Windcaller, WindcallerRenderState> {
    private static final Identifier WINDCALLER_LOCATION = EUM.id("textures/entity/illager/windcaller.png");

    public WindcallerRenderer(EntityRendererProvider.Context context) {
        super(context, new WindcallerModel<>(context.bakeLayer(EUMModelLayers.WINDCALLER)), 0.5F);
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
