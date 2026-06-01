package com.github.pigsteel.smcm.client.renderer.entity;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.client.model.monster.skeleton.SunkenModel;
import com.github.pigsteel.smcm.client.renderer.entity.state.SunkenRenderState;
import com.github.pigsteel.smcm.entity.skeleton.SunkenVariant;
import com.github.pigsteel.smcm.registry.smcm$ModelLayers;
import com.github.pigsteel.smcm.entity.skeleton.Sunken;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.AbstractSkeletonRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.Map;

public class SunkenRenderer extends AbstractSkeletonRenderer<Sunken, SunkenRenderState> {
    private static final Identifier SUNKEN_LOCATION = Identifier.fromNamespaceAndPath(SMCM.MOD_ID,"textures/entity/skeleton/sunken.png");

    private final Map<SunkenVariant.ModelType, SunkenModel> models;

    public SunkenRenderer(EntityRendererProvider.Context context) {
        super(
                context,
                smcm$ModelLayers.SUNKEN,
                smcm$ModelLayers.SUNKEN_ARMOR
        );

        this.models = bakeModels(context);
    }

    private static Map<SunkenVariant.ModelType, SunkenModel> bakeModels(EntityRendererProvider.Context context) {
        SunkenModel normal = new SunkenModel(context.bakeLayer(smcm$ModelLayers.SUNKEN));
        SunkenModel cold = new SunkenModel(context.bakeLayer(smcm$ModelLayers.SUNKEN_COLD));
        SunkenModel warm = new SunkenModel(context.bakeLayer(smcm$ModelLayers.SUNKEN_WARM));

        return Maps.newEnumMap(Map.of(
                SunkenVariant.ModelType.NORMAL, normal,
                SunkenVariant.ModelType.FROZEN, cold,
                SunkenVariant.ModelType.BUBBLE_CORAL, warm,
                SunkenVariant.ModelType.FIRE_CORAL, warm,
                SunkenVariant.ModelType.HORN_CORAL, warm
        ));
    }

    @Override
    public void submit(
            SunkenRenderState state,
            PoseStack poseStack,
            SubmitNodeCollector submitNodeCollector,
            CameraRenderState camera
    ) {
        this.model = this.models.getOrDefault(
                state.variant.modelAndTexture().model(),
                this.models.get(SunkenVariant.ModelType.NORMAL)
        );

        super.submit(state, poseStack, submitNodeCollector, camera);
    }

    @Override
    public SunkenRenderState createRenderState() {
        return new SunkenRenderState();
    }

    @Override
    protected HumanoidModel.ArmPose getArmPose(Sunken entity, final HumanoidArm arm) {
        ItemStack heldItem = entity.getMainHandItem();

        if (heldItem.is(Items.CROSSBOW)) {
            if (entity.isChargingCrossbow()) {
                return HumanoidModel.ArmPose.CROSSBOW_CHARGE;
            } else if (CrossbowItem.isCharged(heldItem)) {
                return HumanoidModel.ArmPose.CROSSBOW_HOLD;
            }
        }

        return super.getArmPose(entity, arm);
    }

    public void extractRenderState(final Sunken entity, final SunkenRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.variant = entity.getVariant();
    }

    @Override
    public Identifier getTextureLocation(final SunkenRenderState state) {
        return state.variant.modelAndTexture().asset().id();
    }
}
