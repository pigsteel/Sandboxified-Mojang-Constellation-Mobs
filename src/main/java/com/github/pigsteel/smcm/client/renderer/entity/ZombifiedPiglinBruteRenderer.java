package com.github.pigsteel.smcm.client.renderer.entity;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.client.model.geom.SMCMModelLayers;
import com.github.pigsteel.smcm.world.entity.monster.zombie.ZombifiedPiglinBrute;
import net.minecraft.client.model.monster.piglin.AdultZombifiedPiglinModel;
import net.minecraft.client.model.monster.piglin.ZombifiedPiglinModel;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.state.ZombifiedPiglinRenderState;
import net.minecraft.resources.Identifier;

// See vilerwitchrenderer, same problem
public class ZombifiedPiglinBruteRenderer extends HumanoidMobRenderer<ZombifiedPiglinBrute, ZombifiedPiglinRenderState, ZombifiedPiglinModel> {
    public static final Identifier ZOMBIFIED_PIGLIN_BRUTE_LOCATION = SMCM.id("textures/entity/piglin/zombified_piglin_brute.png");

    public ZombifiedPiglinBruteRenderer(EntityRendererProvider.Context context) {
        // No baby variant
        super(context, new AdultZombifiedPiglinModel(context.bakeLayer(SMCMModelLayers.ZOMBIFIED_PIGLIN_BRUTE)), 0.5F);
        this.addLayer(
                new HumanoidArmorLayer<>(
                        this,
                        ArmorModelSet.bake(SMCMModelLayers.ZOMBIFIED_PIGLIN_BRUTE_ARMOR, context.getModelSet(), AdultZombifiedPiglinModel::new), context.getEquipmentRenderer()
                )
        );
    }

    @Override
    public Identifier getTextureLocation(ZombifiedPiglinRenderState state) {
        return ZOMBIFIED_PIGLIN_BRUTE_LOCATION;
    }

    @Override
    public ZombifiedPiglinRenderState createRenderState() {
        return new ZombifiedPiglinRenderState();
    }
}
