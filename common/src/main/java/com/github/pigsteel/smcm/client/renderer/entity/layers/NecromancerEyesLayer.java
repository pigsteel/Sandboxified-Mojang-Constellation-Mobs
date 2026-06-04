package com.github.pigsteel.smcm.client.renderer.entity.layers;

import com.github.pigsteel.smcm.client.model.monster.necromancer.NecromancerModel;
import com.github.pigsteel.smcm.client.renderer.entity.state.NecromancerRenderState;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import com.github.pigsteel.smcm.SMCM;

public class NecromancerEyesLayer extends EyesLayer<NecromancerRenderState, NecromancerModel<NecromancerRenderState>> {
    private static final RenderType NECROMANCER_EYES = RenderTypes.eyes(Identifier.fromNamespaceAndPath(SMCM.MOD_ID, "textures/entity/necromancer/necromancer_eyes.png"));

    public NecromancerEyesLayer(RenderLayerParent<NecromancerRenderState, NecromancerModel<NecromancerRenderState>> renderer) {
        super(renderer);
    }

    @Override
    public RenderType renderType() {
        return NECROMANCER_EYES;
    }
}
