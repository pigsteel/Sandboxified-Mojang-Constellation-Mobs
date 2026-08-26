package com.github.pigsteel.smcm.client.renderer.entity;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.minecraft.client.model.monster.illager.IllagerModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.state.ArmedEntityRenderState;
import net.minecraft.client.renderer.entity.state.IllagerRenderState;
import net.minecraft.world.entity.monster.illager.AbstractIllager;
import net.minecraft.world.item.CrossbowItem;

//@OnlyIn(Dist.CLIENT)
public abstract class ExtendableIllagerRenderer<T extends AbstractIllager, S extends IllagerRenderState, M extends IllagerModel<S>> extends MobRenderer<T, S, M> {
	protected ExtendableIllagerRenderer(final EntityRendererProvider.Context context, final M model, final float shadow) {
		super(context, model, shadow);
		this.addLayer(new CustomHeadLayer(this, context.getModelSet(), context.getPlayerSkinRenderCache()));
	}

	public void extractRenderState(final T entity, final S state, final float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		ArmedEntityRenderState.extractArmedEntityRenderState(entity, state, this.itemModelResolver, partialTicks);
		state.isRiding = entity.isPassenger();
		state.mainArm = entity.getMainArm();
		state.armPose = entity.getArmPose();
		state.maxCrossbowChargeDuration = state.armPose == AbstractIllager.IllagerArmPose.CROSSBOW_CHARGE ? CrossbowItem.getChargeDuration(entity.getUseItem(), entity) : 0;
		state.ticksUsingItem = entity.getTicksUsingItem(partialTicks);
		state.attackAnim = entity.getAttackAnim(partialTicks);
		state.isAggressive = entity.isAggressive();
	}
}
