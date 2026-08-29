package com.github.pigsteel.eum.client.renderer.entity;

//? >= 1.21.2 {
import com.github.pigsteel.eum.EUM;
import com.github.pigsteel.eum.client.model.geom.EUMModelLayers;
import com.github.pigsteel.eum.world.entity.monster.illager.Bruiser;
import net.minecraft.client.model.monster.illager.IllagerModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.state.IllagerRenderState;
import net.minecraft.resources.Identifier;

public class BruiserRenderer extends IllagerRenderer<Bruiser, IllagerRenderState> {
    private static final Identifier BRUISER_LOCATION = Identifier.fromNamespaceAndPath(EUM.MOD_ID,"textures/entity/illager/bruiser.png");

    public BruiserRenderer(EntityRendererProvider.Context context) {
        super(context, new IllagerModel<>(context.bakeLayer(EUMModelLayers.BRUISER)), 0.5F);
        this.addLayer(new ItemInHandLayer<>(this));
    }

    @Override
    public IllagerRenderState createRenderState() {
        return new IllagerRenderState();
    }

    @Override
    public Identifier getTextureLocation(IllagerRenderState state) {
        return BRUISER_LOCATION;
    }

}
//?}
