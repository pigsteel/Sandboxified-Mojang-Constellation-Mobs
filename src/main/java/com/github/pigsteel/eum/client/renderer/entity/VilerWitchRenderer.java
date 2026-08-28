package com.github.pigsteel.eum.client.renderer.entity;

import com.github.pigsteel.eum.EUM;
import com.github.pigsteel.eum.client.model.geom.EUMModelLayers;
import com.github.pigsteel.eum.client.model.monster.witch.VilerWitchModel;
import com.github.pigsteel.eum.client.renderer.entity.layers.VilerWitchItemLayer;
import com.github.pigsteel.eum.client.renderer.entity.state.VilerWitchRenderState;
import com.github.pigsteel.eum.world.entity.monster.VilerWitch;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.HoldingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class VilerWitchRenderer extends MobRenderer<VilerWitch, VilerWitchRenderState, VilerWitchModel> {
    private static final ResourceLocation VILER_WITCH_LOCATION = EUM.id("textures/entity/witch/viler_witch.png");

    public VilerWitchRenderer(EntityRendererProvider.Context context) {
        super(context, new VilerWitchModel(context.bakeLayer(EUMModelLayers.VILER_WITCH)), 0.5F);
        this.addLayer(new VilerWitchItemLayer(this)); // doesn't work due to non-extendibility
    }


    @Override
    public VilerWitchRenderState createRenderState() {
        return new VilerWitchRenderState();
    }

    @Override
    public ResourceLocation getTextureLocation(VilerWitchRenderState state) {
        return VILER_WITCH_LOCATION;
    }

	public void extractRenderState(VilerWitch entity, VilerWitchRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		HoldingEntityRenderState.extractHoldingEntityRenderState(entity, state, this.itemModelResolver);
		state.entityId = entity.getId();
		ItemStack mainHandItem = entity.getMainHandItem();
		state.isHoldingItem = !mainHandItem.isEmpty();
		state.isHoldingPotion = mainHandItem.is(Items.POTION);
	}
}
