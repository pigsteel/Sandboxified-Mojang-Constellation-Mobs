package com.github.pigsteel.eum.client.renderer.entity.layers;

//? >= 1.21.2 {
import com.github.pigsteel.eum.EUM;
import com.github.pigsteel.eum.client.model.monster.necromancer.NecromancerModel;
import com.github.pigsteel.eum.client.renderer.entity.state.NecromancerRenderState;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;

public class NecromancerEyesLayer extends EyesLayer<NecromancerRenderState, NecromancerModel<NecromancerRenderState>> {
    private static final RenderType NECROMANCER_EYES = RenderTypes.eyes(Identifier.fromNamespaceAndPath(EUM.MOD_ID, "textures/entity/necromancer/necromancer_eyes.png"));

    public NecromancerEyesLayer(RenderLayerParent<NecromancerRenderState, NecromancerModel<NecromancerRenderState>> renderer) {
        super(renderer);
    }

    @Override
    public RenderType renderType() {
        return NECROMANCER_EYES;
    }
}
//?}
