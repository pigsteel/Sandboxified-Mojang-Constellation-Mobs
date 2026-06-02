package com.github.pigsteel.smcm.client.renderer.entity;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.client.model.monster.skeleton.AbstractSunkenModel;
import com.github.pigsteel.smcm.client.model.monster.skeleton.CoralSunkenModel;
import com.github.pigsteel.smcm.client.renderer.entity.state.SunkenRenderState;
import com.github.pigsteel.smcm.entity.skeleton.SunkenVariant;
import com.github.pigsteel.smcm.registry.smcm$ModelLayers;
import com.github.pigsteel.smcm.entity.skeleton.Sunken;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.monster.skeleton.SkeletonModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.AbstractSkeletonRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.core.ClientAsset;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.Map;

public class SunkenRenderer extends AbstractSkeletonRenderer<Sunken, SunkenRenderState> {
    private final Map<SunkenVariant.ModelType, AbstractSunkenModel> models;
    public static final ClientAsset.ResourceTexture EMPTY_DEAD_CORAL_TEXTURE =
            new ClientAsset.ResourceTexture(
                    Identifier.fromNamespaceAndPath(SMCM.MOD_ID, "entity/skeleton/sunken/empty")
            );


    public SunkenRenderer(EntityRendererProvider.Context context) {
        super(
                context,
                smcm$ModelLayers.SUNKEN,
                smcm$ModelLayers.SUNKEN_ARMOR
        );

        this.models = bakeModels(context);
    }

    private static Map<SunkenVariant.ModelType, AbstractSunkenModel> bakeModels(EntityRendererProvider.Context context) {
        AbstractSunkenModel normal = new AbstractSunkenModel(context.bakeLayer(smcm$ModelLayers.SUNKEN));
        AbstractSunkenModel frozen = new AbstractSunkenModel(context.bakeLayer(smcm$ModelLayers.SUNKEN_FROZEN));
        CoralSunkenModel warm = new CoralSunkenModel(context.bakeLayer(smcm$ModelLayers.SUNKEN_CORAL));

        return Maps.newEnumMap(Map.of(
                SunkenVariant.ModelType.NORMAL, normal,
                SunkenVariant.ModelType.FROZEN, frozen,
                SunkenVariant.ModelType.BUBBLE_CORAL, warm,
                SunkenVariant.ModelType.FIRE_CORAL, warm,
                SunkenVariant.ModelType.HORN_CORAL, warm
        ));
    }

    private AbstractSunkenModel smcm$selectedModel;

    public AbstractSunkenModel smcm$getSelectedModel() {
        return this.smcm$selectedModel;
    }

    @Override
    public void submit(
            SunkenRenderState state,
            PoseStack poseStack,
            SubmitNodeCollector submitNodeCollector,
            CameraRenderState camera
    ) {
        SunkenVariant.ModelType modelType = SunkenVariant.ModelType.NORMAL;

        if (state.variant != null) {
            modelType = state.variant.modelAndTexture().model();
        }

        this.smcm$selectedModel = this.models.getOrDefault(
                modelType,
                this.models.get(SunkenVariant.ModelType.NORMAL)
        );

        this.model = this.smcm$selectedModel;

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
        state.isSheared = entity.isSheared();

        SunkenVariant.ModelType modelType = state.variant == null
                ? SunkenVariant.ModelType.NORMAL
                : state.variant.modelAndTexture().model();

        state.isCoralDead = modelType.isCoral() && entity.isCoralDead();
    }

    @Override
    public Identifier getTextureLocation(final SunkenRenderState state) {
        if(!state.isCoralDead) {
            return state.variant.modelAndTexture().asset().texturePath();
        } else {
            return state.variant.deadCoralTexture().texturePath();
        }
    }
}
