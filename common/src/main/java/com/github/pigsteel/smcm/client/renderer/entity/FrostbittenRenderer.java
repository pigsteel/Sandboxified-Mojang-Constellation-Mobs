package com.github.pigsteel.smcm.client.renderer.entity;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.client.model.monster.zombie.BabyFrostbittenModel;
import com.github.pigsteel.smcm.client.model.monster.zombie.FrostbittenModel;
import com.github.pigsteel.smcm.registry.smcm$ModelLayers;
import com.github.pigsteel.smcm.client.renderer.entity.layers.FrostbittenOuterLayer;
import com.github.pigsteel.smcm.client.renderer.entity.state.FrostbittenRenderState;
import com.github.pigsteel.smcm.entity.zombie.Frostbitten;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;

public class FrostbittenRenderer extends AbstractZombieRenderer<Frostbitten, FrostbittenRenderState, FrostbittenModel> {
    private static final Identifier FROSTBITTEN_LOCATION = Identifier.fromNamespaceAndPath(SMCM.MOD_ID,"textures/entity/zombie/frostbitten.png");
    private static final Identifier BABY_FROSTBITTEN_LOCATION = Identifier.fromNamespaceAndPath(SMCM.MOD_ID,"textures/entity/zombie/frostbitten_baby.png");

    public FrostbittenRenderer(final EntityRendererProvider.Context context) {
        super(
                context,
                new FrostbittenModel(context.bakeLayer(smcm$ModelLayers.FROSTBITTEN)),
                new BabyFrostbittenModel(context.bakeLayer(smcm$ModelLayers.FROSTBITTEN_BABY)),
                ArmorModelSet.bake(smcm$ModelLayers.FROSTBITTEN_ARMOR, context.getModelSet(), FrostbittenModel::new),
                ArmorModelSet.bake(smcm$ModelLayers.FROSTBITTEN_BABY_ARMOR, context.getModelSet(), BabyFrostbittenModel::new)
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
