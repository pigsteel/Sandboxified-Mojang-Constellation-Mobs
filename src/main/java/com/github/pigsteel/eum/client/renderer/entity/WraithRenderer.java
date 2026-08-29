package com.github.pigsteel.eum.client.renderer.entity;

//? >= 1.21.2 {
import com.github.pigsteel.eum.EUM;
import com.github.pigsteel.eum.client.model.geom.EUMModelLayers;
import com.github.pigsteel.eum.client.model.monster.wraith.WraithModel;
import com.github.pigsteel.eum.client.renderer.entity.state.WraithRenderState;
import com.github.pigsteel.eum.world.entity.monster.Wraith;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class WraithRenderer extends MobRenderer<Wraith, WraithRenderState, WraithModel> {
	private static final Identifier WRAITH_LOCATION = EUM.id("textures/entity/wraith/wraith.png");

	public WraithRenderer(EntityRendererProvider.Context context) {
		super(context, new WraithModel(context.bakeLayer(EUMModelLayers.WRAITH)), 0.0F);
	}

	@Override
	public WraithRenderState createRenderState() {
		return new WraithRenderState();
	}

	@Override
	public Identifier getTextureLocation(WraithRenderState state) {
		return WRAITH_LOCATION;
	}

	public void extractRenderState(final Wraith entity, final WraithRenderState state, final float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		//state.displayFireAnimation
	}
}
//?}
