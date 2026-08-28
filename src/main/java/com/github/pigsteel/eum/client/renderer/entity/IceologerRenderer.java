package com.github.pigsteel.eum.client.renderer.entity;

import com.github.pigsteel.eum.EUM;
import com.github.pigsteel.eum.client.model.geom.EUMModelLayers;
import com.github.pigsteel.eum.client.model.monster.iceologer.IceologerModel;
import com.github.pigsteel.eum.client.renderer.entity.state.IceologerRenderState;
import com.github.pigsteel.eum.world.entity.monster.illager.Iceologer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.resources.Identifier;

public class IceologerRenderer extends IllagerRenderer<Iceologer, IceologerRenderState> {
    private static final Identifier ICEOLOGER_LOCATION = EUM.id("textures/entity/illager/iceologer.png");

    public IceologerRenderer(EntityRendererProvider.Context context) {
        super(context, new IceologerModel(context.bakeLayer(EUMModelLayers.ICEOLOGER)), 0.5F);
    }

    @Override
    public Identifier getTextureLocation(IceologerRenderState state) {
        return ICEOLOGER_LOCATION;
    }

    @Override
    public IceologerRenderState createRenderState() {
        return new IceologerRenderState();
    }
}
