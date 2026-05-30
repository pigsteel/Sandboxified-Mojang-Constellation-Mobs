package com.github.pigsteel.smcm.client.renderer.entity;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.client.renderer.entity.state.SunkenRenderState;
import com.github.pigsteel.smcm.registry.smcm$ModelLayers;
import com.github.pigsteel.smcm.entity.skeleton.Sunken;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.AbstractSkeletonRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class SunkenRenderer extends AbstractSkeletonRenderer<Sunken, SunkenRenderState> {
    private static final Identifier SUNKEN_LOCATION = Identifier.fromNamespaceAndPath(SMCM.MOD_ID,"textures/entity/skeleton/sunken.png");

    public SunkenRenderer(EntityRendererProvider.Context context) {
        super(context, smcm$ModelLayers.SUNKEN, smcm$ModelLayers.SUNKEN_ARMOR);

        // add layers ..................................................fard
    }

    @Override
    public Identifier getTextureLocation(final SunkenRenderState state) {
        return SUNKEN_LOCATION;
    }

    @Override
    public void extractRenderState(Sunken entity, SunkenRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);

        // TODO: implement logic for variants (hello Tobin)
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
}
