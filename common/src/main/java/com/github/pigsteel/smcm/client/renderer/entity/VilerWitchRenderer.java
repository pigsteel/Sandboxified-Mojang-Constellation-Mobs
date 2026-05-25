package com.github.pigsteel.smcm.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.WitchRenderer;
import net.minecraft.client.renderer.entity.state.WitchRenderState;
import net.minecraft.resources.Identifier;

public class VilerWitchRenderer extends WitchRenderer {
    private static final Identifier VILER_WITCH_LOCATION = Identifier.withDefaultNamespace("textures/entity/viler_witch/viler_witch.png");

    public VilerWitchRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public Identifier getTextureLocation(final WitchRenderState state) {
        return VILER_WITCH_LOCATION;
    }
}
