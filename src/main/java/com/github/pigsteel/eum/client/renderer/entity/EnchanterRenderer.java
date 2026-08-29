package com.github.pigsteel.eum.client.renderer.entity;

//? >= 1.21.2 {
import com.github.pigsteel.eum.EUM;
import com.github.pigsteel.eum.client.model.geom.EUMModelLayers;
import com.github.pigsteel.eum.client.model.monster.enchanter.EnchanterModel;
import com.github.pigsteel.eum.client.renderer.entity.layers.EnchanterBookLayer;
import com.github.pigsteel.eum.client.renderer.entity.state.EnchanterRenderState;
import com.github.pigsteel.eum.world.entity.monster.illager.Enchanter;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;

public class EnchanterRenderer extends ExtendableIllagerRenderer<Enchanter, EnchanterRenderState, EnchanterModel<EnchanterRenderState>> {
    private static final Identifier ENCHANTER_LOCATION = Identifier.fromNamespaceAndPath(EUM.MOD_ID,"textures/entity/illager/enchanter.png");

    public EnchanterRenderer(EntityRendererProvider.Context context) {
        super(context, new EnchanterModel(context.bakeLayer(EUMModelLayers.ENCHANTER)), 0.5F);
		this.addLayer(new EnchanterBookLayer(this, context.getModelSet()));
    }

    @Override
    public Identifier getTextureLocation(EnchanterRenderState state) {
        return ENCHANTER_LOCATION;
    }

    @Override
    public EnchanterRenderState createRenderState() {
        return new EnchanterRenderState();
    }

	public void extractRenderState(final Enchanter entity, final EnchanterRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}
}
//?}
