package com.github.pigsteel.smcm.client.renderer.entity;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.client.model.geom.smcm$ModelLayers;
import com.github.pigsteel.smcm.client.model.monster.redstonegolem.RedstoneGolemModel;
import com.github.pigsteel.smcm.client.renderer.entity.layers.TintedEmissiveLayer;
import com.github.pigsteel.smcm.client.renderer.entity.state.RedstoneGolemRenderState;
import com.github.pigsteel.smcm.world.entity.monster.redstonegolem.RedstoneGolem;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.LivingEntityEmissiveLayer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;

public class RedstoneGolemRenderer extends MobRenderer<RedstoneGolem, RedstoneGolemRenderState, RedstoneGolemModel> {
    private static final Identifier REDSTONE_GOLEM_LOCATION = SMCM.id("textures/entity/redstone_golem/redstone_golem.png");
    private static final Identifier GLOW_LOCATION = SMCM.id("textures/entity/redstone_golem/redstone_golem_glow.png");
    private static final Identifier EYES_LOCATION = SMCM.id("textures/entity/redstone_golem/redstone_golem_eyes.png");

    public RedstoneGolemRenderer(EntityRendererProvider.Context context) {
        super(context, new RedstoneGolemModel(context.bakeLayer(smcm$ModelLayers.REDSTONE_GOLEM)), 1.5F);
        RedstoneGolemModel redstoneGlowModel = new RedstoneGolemModel(context.bakeLayer(smcm$ModelLayers.REDSTONE_GOLEM_GLOW));
        RedstoneGolemModel eyesModel = new RedstoneGolemModel(context.bakeLayer(smcm$ModelLayers.REDSTONE_GOLEM_EYES));
        this.addLayer(
                new LivingEntityEmissiveLayer<>(
                        this, renderState -> EYES_LOCATION, (golem, ageInTicks) -> 1.0F, eyesModel, RenderTypes::entityTranslucentEmissive, false
                )
        );

        this.addLayer(
                new TintedEmissiveLayer<>(
                        this,
                        renderState -> GLOW_LOCATION,
                        (renderState, ageInTicks) -> renderState.glowAnimation,
                        (renderState, ageInTicks, alpha) -> TintedEmissiveLayer.redGlow(renderState.glowAnimation),
                        redstoneGlowModel,
                        RenderTypes::eyes,
                        false
                )
        );
    }

    @Override
    public Identifier getTextureLocation(RedstoneGolemRenderState state) {
        return REDSTONE_GOLEM_LOCATION;
    }

    @Override
    public RedstoneGolemRenderState createRenderState() {
        return new RedstoneGolemRenderState();
    }

    public void extractRenderState(final RedstoneGolem entity, final RedstoneGolemRenderState state, final float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.glowAnimation = entity.getGlowAnimation(partialTicks);
    }
}
