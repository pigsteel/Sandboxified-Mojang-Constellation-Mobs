package com.github.pigsteel.eum.client.renderer.entity;

//? >= 1.21.2 {
import com.github.pigsteel.eum.EUM;
import com.github.pigsteel.eum.client.model.geom.EUMModelLayers;
import com.github.pigsteel.eum.client.model.monster.zombie.BabyFrostbittenModel;
import com.github.pigsteel.eum.client.model.monster.zombie.FrostbittenModel;
import com.github.pigsteel.eum.client.renderer.entity.layers.FrostbittenOuterLayer;
import com.github.pigsteel.eum.client.renderer.entity.state.FrostbittenRenderState;
import com.github.pigsteel.eum.world.entity.monster.zombie.Frostbitten;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;

public class FrostbittenRenderer extends AbstractZombieRenderer<Frostbitten, FrostbittenRenderState, FrostbittenModel> {
    private static final Identifier FROSTBITTEN_LOCATION = Identifier.fromNamespaceAndPath(EUM.MOD_ID,"textures/entity/zombie/frostbitten.png");
    private static final Identifier BABY_FROSTBITTEN_LOCATION = Identifier.fromNamespaceAndPath(EUM.MOD_ID,"textures/entity/zombie/frostbitten_baby.png");

    public FrostbittenRenderer(final EntityRendererProvider.Context context) {
        super(
                context,
                new FrostbittenModel(context.bakeLayer(EUMModelLayers.FROSTBITTEN)),
                new BabyFrostbittenModel(context.bakeLayer(EUMModelLayers.FROSTBITTEN_BABY)),
                ArmorModelSet.bake(EUMModelLayers.FROSTBITTEN_ARMOR, context.getModelSet(), FrostbittenModel::new),
                ArmorModelSet.bake(EUMModelLayers.FROSTBITTEN_BABY_ARMOR, context.getModelSet(), BabyFrostbittenModel::new)
        );
        this.addLayer(new FrostbittenOuterLayer(this, context.getModelSet()));
    }

    @Override
    public FrostbittenRenderState createRenderState() {
        return new FrostbittenRenderState();
    }

    @Override
    public void extractRenderState(final Frostbitten entity, final FrostbittenRenderState state, final float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.isShaking = entity.isShaking();
        state.isThrowingSnowball = entity.isAimingSnowball();
    }

    @Override
    public Identifier getTextureLocation(final FrostbittenRenderState state) {
        return state.isBaby ? BABY_FROSTBITTEN_LOCATION : FROSTBITTEN_LOCATION;
    }

    @Override
    public boolean isShaking(FrostbittenRenderState state) {
        return state.isShaking;
    }
}
//?} <= 1.21.1 {

//?}
