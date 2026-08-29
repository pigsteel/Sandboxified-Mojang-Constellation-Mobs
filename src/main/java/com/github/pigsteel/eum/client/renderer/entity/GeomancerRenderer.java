package com.github.pigsteel.eum.client.renderer.entity;

//? >= 1.21.2 {
import com.github.pigsteel.eum.EUM;
import com.github.pigsteel.eum.client.model.geom.EUMModelLayers;
import com.github.pigsteel.eum.client.renderer.entity.state.GeomancerRenderState;
import com.github.pigsteel.eum.world.entity.monster.illager.Geomancer;
import net.minecraft.client.model.monster.illager.IllagerModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.resources.Identifier;

public class GeomancerRenderer extends IllagerRenderer<Geomancer, GeomancerRenderState> {
    private static final Identifier GEOMANCER_LOCATION = EUM.id("textures/entity/illager/geomancer.png");

    public GeomancerRenderer(EntityRendererProvider.Context context) {
        super(context, new IllagerModel<>(context.bakeLayer(EUMModelLayers.GEOMANCER)), 0.5F);
    }

    @Override
    public Identifier getTextureLocation(GeomancerRenderState state) {
        return GEOMANCER_LOCATION;
    }

    @Override
    public GeomancerRenderState createRenderState() {
        return new GeomancerRenderState();
    }
}
//?} <= 1.21.1 {


//?}
