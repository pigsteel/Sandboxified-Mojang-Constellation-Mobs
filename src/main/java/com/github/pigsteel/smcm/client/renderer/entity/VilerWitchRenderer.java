package com.github.pigsteel.smcm.client.renderer.entity;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.client.model.geom.SMCMModelLayers;
import com.github.pigsteel.smcm.client.model.monster.witch.VilerWitchModel;
import com.github.pigsteel.smcm.client.renderer.entity.state.VilerWitchRenderState;
import com.github.pigsteel.smcm.world.entity.monster.VilerWitch;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class VilerWitchRenderer extends MobRenderer<VilerWitch, VilerWitchRenderState, VilerWitchModel> {
    private static final Identifier VILER_WITCH_LOCATION = SMCM.id("textures/entity/witch/viler_witch.png");

    public VilerWitchRenderer(EntityRendererProvider.Context context) {
        super(context, new VilerWitchModel(context.bakeLayer(SMCMModelLayers.VILER_WITCH)), 0.5F);
        //this.addLayer(new WitchItemLayer(this)); // doesn't work due to non-extendibility
    }


    @Override
    public VilerWitchRenderState createRenderState() {
        return new VilerWitchRenderState();
    }

    @Override
    public Identifier getTextureLocation(VilerWitchRenderState state) {
        return VILER_WITCH_LOCATION;
    }
}
