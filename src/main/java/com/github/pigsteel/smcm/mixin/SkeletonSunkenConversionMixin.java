package com.github.pigsteel.smcm.mixin;


import com.github.pigsteel.smcm.core.SMCMEntityTypes;
import com.github.pigsteel.smcm.network.SMCMLevelEventPacketPayload;
import com.github.pigsteel.smcm.util.EntityTypesUtil;
import com.github.pigsteel.smcm.world.entity.SkeletonSunkenConversion;
import com.google.common.annotations.VisibleForTesting;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ConversionParams;
import net.minecraft.world.entity.monster.skeleton.Skeleton;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.github.pigsteel.smcm.core.SMCMDataAttachments.DATA_SUNKEN_CONVERSION_ID;

@Mixin(Skeleton.class)
public abstract class SkeletonSunkenConversionMixin implements SkeletonSunkenConversion {
	@Unique
	private static final int smcm$SUNKEN_TOTAL_CONVERSION_TIME = 300;

	@Unique
	private int smcm$InWaterTime;

	@Unique
	private int smcm$waterConversionTime;

	@Unique
	public boolean smcm$canWaterConvert(Skeleton skeleton) {
		return skeleton.getType() == EntityTypesUtil.SKELETON;
	}

	@Inject(method = "tick", at = @At("TAIL"))
	private void smcm$tick(CallbackInfo ci) {
		Skeleton skeleton = (Skeleton) (Object) this;

		if (!this.smcm$canWaterConvert(skeleton)) {
			return;
		}

		if (!skeleton.level().isClientSide() && skeleton.isAlive() && !skeleton.isNoAi()) {
			if (skeleton.isInWater()) {
				if (this.smcm$isWaterConverting()) {
					--this.smcm$waterConversionTime;
					if (this.smcm$waterConversionTime < 0) {
						this.smcm$doWaterConversion();
					}
				} else {
					++this.smcm$InWaterTime;
					if (this.smcm$InWaterTime >= 140) {
						this.smcm$startWaterConversion(300);
					}
				}
			} else {
				this.smcm$InWaterTime = -1;
				this.smcm$setWaterConverting(false);
			}
		}
	}

	@Unique
	public void smcm$setWaterConverting(boolean isConverting) {
		Skeleton skeleton = (Skeleton) (Object) this;

		DATA_SUNKEN_CONVERSION_ID.setAttached(skeleton, isConverting);
	}

	@VisibleForTesting
	@Unique
	public void smcm$startWaterConversion(int time) {
		this.smcm$waterConversionTime = time;
		this.smcm$setWaterConverting(true);
	}

	@Unique
	public void smcm$doWaterConversion() {
		Skeleton skeleton = (Skeleton) (Object) this;

		skeleton.convertTo(SMCMEntityTypes.SUNKEN.get(), ConversionParams.single(skeleton, true, true), (sunken) -> {
			if (!skeleton.isSilent()) {
				SMCMLevelEventPacketPayload payload = new SMCMLevelEventPacketPayload(1003, skeleton.blockPosition());

				//? fabric {
				for (ServerPlayer player : PlayerLookup.level((ServerLevel) skeleton.level())) {
					ServerPlayNetworking.send(player, payload);
				}
				//?} neoforge {

				//?}

				sunken.selectVariant();
			}
		});
	}

	@Unique
	public boolean smcm$isWaterConverting() {
		Skeleton skeleton = (Skeleton) (Object) this;

		return DATA_SUNKEN_CONVERSION_ID.getAttachedOrSet(skeleton, false);
	}

	@Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
	private void smcm$saveWaterConversionTime(ValueOutput output, CallbackInfo ci) {
		output.putInt("SunkenConversionTime", this.smcm$isWaterConverting() ? this.smcm$waterConversionTime : -1);
	}

	@Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
	private void smcm$readWaterConversionTime(ValueInput input, CallbackInfo ci) {
		this.smcm$waterConversionTime = input.getIntOr("SunkenConversionTime", -1);
		if (this.smcm$waterConversionTime != -1) {
			this.smcm$startWaterConversion(this.smcm$waterConversionTime);
		} else {
			this.smcm$setWaterConverting(false);
		}
	}

	@Inject(method = "isShaking", at = @At("HEAD"), cancellable = true)
	private void smcm$isShaking(CallbackInfoReturnable<Boolean> cir) {
		if(this.smcm$isWaterConverting()) cir.setReturnValue(true);
	}
}
