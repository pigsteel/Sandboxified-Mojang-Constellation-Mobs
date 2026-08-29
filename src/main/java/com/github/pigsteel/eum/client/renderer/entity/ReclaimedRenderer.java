package com.github.pigsteel.eum.client.renderer.entity;

//? >= 1.21.2 {
import com.github.pigsteel.eum.EUM;
import com.github.pigsteel.eum.client.model.geom.EUMModelLayers;
import com.github.pigsteel.eum.client.model.monster.zombie.BabyReclaimedModel;
import com.github.pigsteel.eum.client.model.monster.zombie.ReclaimedModel;
import com.github.pigsteel.eum.client.renderer.entity.layers.ReclaimedFlowerLayer;
import com.github.pigsteel.eum.client.renderer.entity.layers.ReclaimedOuterLayer;
import com.github.pigsteel.eum.client.renderer.entity.state.ReclaimedRenderState;
import com.github.pigsteel.eum.world.entity.monster.zombie.Reclaimed;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.state.BlockState;

import static net.minecraft.client.renderer.entity.AbstractMinecartRenderer.BLOCK_DISPLAY_CONTEXT;

public class ReclaimedRenderer extends AbstractZombieRenderer<Reclaimed, ReclaimedRenderState, ReclaimedModel> {
    private static final Identifier RECLAIMED_LOCATION = Identifier.fromNamespaceAndPath(EUM.MOD_ID,"textures/entity/zombie/reclaimed.png");
    private static final Identifier BABY_RECLAIMED_LOCATION = Identifier.fromNamespaceAndPath(EUM.MOD_ID,"textures/entity/zombie/reclaimed_baby.png");
    private final BlockModelResolver blockModelResolver;

    public ReclaimedRenderer(EntityRendererProvider.Context context) {
        super(
                context,
                new ReclaimedModel(context.bakeLayer(EUMModelLayers.RECLAIMED)),
                new BabyReclaimedModel(context.bakeLayer(EUMModelLayers.RECLAIMED_BABY)),
                ArmorModelSet.bake(EUMModelLayers.RECLAIMED_ARMOR, context.getModelSet(), ReclaimedModel::new),
                ArmorModelSet.bake(EUMModelLayers.RECLAIMED_BABY_ARMOR, context.getModelSet(), BabyReclaimedModel::new)
        );
        this.blockModelResolver = context.getBlockModelResolver();
        this.addLayer(new ReclaimedOuterLayer(this, context.getModelSet()));
        this.addLayer(new ReclaimedFlowerLayer(this));
    }

    @Override
    public ReclaimedRenderState createRenderState() {
        return new ReclaimedRenderState();
    }

    @Override
    public Identifier getTextureLocation(final ReclaimedRenderState state) {
        return state.isBaby ? BABY_RECLAIMED_LOCATION : RECLAIMED_LOCATION;
    }

    public void extractRenderState(final Reclaimed entity, final ReclaimedRenderState state, final float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        BlockState flowerBlockState = entity.getHeadFlower();
        if (flowerBlockState != null) {
            this.blockModelResolver.update(state.flowerModel, flowerBlockState, BLOCK_DISPLAY_CONTEXT);
        } else {
            state.flowerModel.clear();
        }
    }
}
//?}
