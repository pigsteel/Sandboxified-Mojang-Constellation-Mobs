package com.github.pigsteel.smcm.client.renderer.entity;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.registry.smcm$ModelLayers;
import com.github.pigsteel.smcm.entity.illager.Bruiser;
import net.minecraft.client.model.monster.illager.IllagerModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.state.IllagerRenderState;
import net.minecraft.resources.Identifier;

public class BruiserRenderer extends IllagerRenderer<Bruiser, IllagerRenderState> {
    private static final Identifier BRUISER = Identifier.fromNamespaceAndPath(SMCM.MOD_ID,"textures/entity/illager/bruiser.png");

    public BruiserRenderer(EntityRendererProvider.Context context) {
        super(context, new IllagerModel<>(context.bakeLayer(smcm$ModelLayers.BRUISER)), 0.5F);
        this.addLayer(new ItemInHandLayer<>(this));
    }

    @Override
    public IllagerRenderState createRenderState() {
        return new IllagerRenderState();
    }

    @Override
    public Identifier getTextureLocation(IllagerRenderState state) {
        return BRUISER;
    }

}
