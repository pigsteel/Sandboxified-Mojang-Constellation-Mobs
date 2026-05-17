package com.github.pigsteel.smcm.client.renderer.entity;

import com.github.pigsteel.smcm.client.renderer.entity.state.IceologerRenderState;
import com.github.pigsteel.smcm.entity.illager.Iceologer;
import net.minecraft.client.model.monster.illager.IllagerModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.resources.Identifier;

public class IceologerRenderer extends IllagerRenderer<Iceologer, IceologerRenderState> {
    protected IceologerRenderer(EntityRendererProvider.Context context, IllagerModel<IceologerRenderState> model, float shadow) {
        super(context, model, shadow);
    }

    @Override
    public Identifier getTextureLocation(IceologerRenderState state) {
        return null;
    }

    @Override
    public IceologerRenderState createRenderState() {
        return null;
    }
}
