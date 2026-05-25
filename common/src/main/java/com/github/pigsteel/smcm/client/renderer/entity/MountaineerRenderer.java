package com.github.pigsteel.smcm.client.renderer.entity;

import com.github.pigsteel.smcm.entity.illager.Mountaineer;
import net.minecraft.client.model.monster.illager.IllagerModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.client.renderer.entity.state.IllagerRenderState;
import net.minecraft.resources.Identifier;

public class MountaineerRenderer extends IllagerRenderer<Mountaineer, IllagerRenderState> {
    private static final Identifier MOUNTAINEER_LOCATION = Identifier.withDefaultNamespace("textures/entity/illager/mountaineer.png");

    protected MountaineerRenderer(EntityRendererProvider.Context context, IllagerModel<IllagerRenderState> model, float shadow) {
        super(context, model, shadow);
    }

    @Override
    public Identifier getTextureLocation(IllagerRenderState state) {
        return MOUNTAINEER_LOCATION;
    }

    @Override
    public IllagerRenderState createRenderState() {
        return null;
    }
}
