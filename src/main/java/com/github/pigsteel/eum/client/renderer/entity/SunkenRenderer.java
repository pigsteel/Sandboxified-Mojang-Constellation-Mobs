package com.github.pigsteel.eum.client.renderer.entity;

//? >= 1.21.2 {
import com.github.pigsteel.eum.client.model.geom.EUMModelLayers;
import com.github.pigsteel.eum.client.model.monster.skeleton.AbstractSunkenModel;
import com.github.pigsteel.eum.client.model.monster.skeleton.CoralSunkenModel;
import com.github.pigsteel.eum.client.renderer.entity.state.SunkenRenderState;
import com.github.pigsteel.eum.world.entity.monster.skeleton.Sunken;
import com.github.pigsteel.eum.world.entity.monster.skeleton.SunkenVariant;
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
    private final Map<SunkenVariant.ModelType, AbstractSunkenModel> models;

    public SunkenRenderer(EntityRendererProvider.Context context) {
        super(
                context,
                EUMModelLayers.SUNKEN,
                EUMModelLayers.SUNKEN_ARMOR
        );

        this.models = bakeModels(context);
    }

    private static Map<SunkenVariant.ModelType, AbstractSunkenModel> bakeModels(EntityRendererProvider.Context context) {
        AbstractSunkenModel normal = new AbstractSunkenModel(context.bakeLayer(EUMModelLayers.SUNKEN));
        AbstractSunkenModel frozen = new AbstractSunkenModel(context.bakeLayer(EUMModelLayers.SUNKEN_FROZEN));
        CoralSunkenModel warm = new CoralSunkenModel(context.bakeLayer(EUMModelLayers.SUNKEN_CORAL));

        return Maps.newEnumMap(Map.of(
                SunkenVariant.ModelType.NORMAL, normal,
                SunkenVariant.ModelType.FROZEN, frozen,
                SunkenVariant.ModelType.BUBBLE_CORAL, warm,
                SunkenVariant.ModelType.FIRE_CORAL, warm,
                SunkenVariant.ModelType.HORN_CORAL, warm
        ));
    }

    private AbstractSunkenModel eum$selectedModel;

    public AbstractSunkenModel eum$getSelectedModel() {
        return this.eum$selectedModel;
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

        this.eum$selectedModel = this.models.getOrDefault(
                modelType,
                this.models.get(SunkenVariant.ModelType.NORMAL)
        );

        this.model = this.eum$selectedModel;

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
        state.variant = entity.getVariant().value();
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
//?}
