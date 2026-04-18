package com.github.pigsteel.smcm.client.renderer.entity;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.client.model.monster.zombie.BabyReclaimedModel;
import com.github.pigsteel.smcm.client.model.monster.zombie.ReclaimedModel;
import com.github.pigsteel.smcm.client.registry.ModelLayers;
import com.github.pigsteel.smcm.client.renderer.entity.layers.ReclaimedOuterLayer;
import com.github.pigsteel.smcm.entity.zombie.Reclaimed;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.DrownedOuterLayer;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.resources.Identifier;

public class ReclaimedRenderer extends AbstractZombieRenderer<Reclaimed, ZombieRenderState, ReclaimedModel> {
    private static final Identifier RECLAIMED_LOCATION = Identifier.fromNamespaceAndPath(SMCM.MOD_ID,"textures/entity/zombie/reclaimed.png");
    private static final Identifier BABY_RECLAIMED_LOCATION = Identifier.fromNamespaceAndPath(SMCM.MOD_ID,"textures/entity/zombie/reclaimed_baby.png");

    public ReclaimedRenderer(EntityRendererProvider.Context context) {
        super(
                context,
                new ReclaimedModel(context.bakeLayer(ModelLayers.RECLAIMED)),
                new BabyReclaimedModel(context.bakeLayer(ModelLayers.RECLAIMED_BABY)),
                ArmorModelSet.bake(ModelLayers.RECLAIMED_ARMOR, context.getModelSet(), ReclaimedModel::new),
                ArmorModelSet.bake(ModelLayers.RECLAIMED_BABY_ARMOR, context.getModelSet(), BabyReclaimedModel::new)
        );
        this.addLayer(new ReclaimedOuterLayer(this, context.getModelSet()));
    }

    @Override
    public ZombieRenderState createRenderState() {
        return new ZombieRenderState();
    }

    @Override
    public Identifier getTextureLocation(final ZombieRenderState state) {
        return state.isBaby ? BABY_RECLAIMED_LOCATION : RECLAIMED_LOCATION;
    }
}
