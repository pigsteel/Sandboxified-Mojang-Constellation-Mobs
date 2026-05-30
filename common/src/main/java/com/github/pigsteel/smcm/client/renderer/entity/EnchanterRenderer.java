package com.github.pigsteel.smcm.client.renderer.entity;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.client.model.monster.illager.EnchanterModel;
import com.github.pigsteel.smcm.registry.smcm$ModelLayers;
import com.github.pigsteel.smcm.client.renderer.entity.state.EnchanterRenderState;
import com.github.pigsteel.smcm.entity.illager.Enchanter;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.resources.Identifier;

public class EnchanterRenderer extends IllagerRenderer<Enchanter, EnchanterRenderState> {
    private static final Identifier ENCHANTER = Identifier.fromNamespaceAndPath(SMCM.MOD_ID,"textures/entity/illager/enchanter.png");

    public EnchanterRenderer(EntityRendererProvider.Context context) {
        super(context, new EnchanterModel<>(context.bakeLayer(smcm$ModelLayers.ENCHANTER)), 0.5F);
    }

    @Override
    public Identifier getTextureLocation(EnchanterRenderState state) {
        return ENCHANTER;
    }

    @Override
    public EnchanterRenderState createRenderState() {
        return new EnchanterRenderState();
    }
}
