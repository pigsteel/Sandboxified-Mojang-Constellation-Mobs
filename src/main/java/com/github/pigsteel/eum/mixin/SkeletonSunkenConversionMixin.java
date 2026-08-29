package com.github.pigsteel.eum.mixin;


import com.github.pigsteel.eum.core.EUMEntityTypes;
import com.github.pigsteel.eum.network.EUMLevelEventPacketPayload;
import com.github.pigsteel.eum.util.EntityTypesUtil;
import com.github.pigsteel.eum.world.entity.SkeletonSunkenConversion;
import com.google.common.annotations.VisibleForTesting;
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

import static com.github.pigsteel.eum.core.EUMDataAttachments.DATA_SUNKEN_CONVERSION_ID;

//? fabric {
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
//?} neoforge {
/*import net.neoforged.neoforge.network.PacketDistributor;
*///?}

@Mixin(Skeleton.class)
public abstract class SkeletonSunkenConversionMixin implements SkeletonSunkenConversion {
	@Unique
	private static final int eum$SUNKEN_TOTAL_CONVERSION_TIME = 300;

	@Unique
	private int eum$InWaterTime;

	@Unique
	private int eum$waterConversionTime;

	@Unique
	public boolean eum$canWaterConvert(Skeleton skeleton) {
		return skeleton.getType() == EntityTypesUtil.SKELETON;
	}

	@Inject(method = "tick", at = @At("TAIL"))
	private void eum$tick(CallbackInfo ci) {
		Skeleton skeleton = (Skeleton) (Object) this;

		if (!this.eum$canWaterConvert(skeleton)) {
			return;
		}

		if (!skeleton.level().isClientSide() && skeleton.isAlive() && !skeleton.isNoAi()) {
			if (skeleton.isInWater()) {
				if (this.eum$isWaterConverting()) {
					--this.eum$waterConversionTime;
					if (this.eum$waterConversionTime < 0) {
						this.eum$doWaterConversion();
					}
				} else {
					++this.eum$InWaterTime;
					if (this.eum$InWaterTime >= 140) {
						this.eum$startWaterConversion(300);
					}
				}
			} else {
				this.eum$InWaterTime = -1;
				this.eum$setWaterConverting(false);
			}
		}
	}

	@Unique
	public void eum$setWaterConverting(boolean isConverting) {
		Skeleton skeleton = (Skeleton) (Object) this;

		DATA_SUNKEN_CONVERSION_ID.setAttached(skeleton, isConverting);
	}

	@VisibleForTesting
	@Unique
	public void eum$startWaterConversion(int time) {
		this.eum$waterConversionTime = time;
		this.eum$setWaterConverting(true);
	}

	@Unique
	public void eum$doWaterConversion() {
		Skeleton skeleton = (Skeleton) (Object) this;

		skeleton.convertTo(EUMEntityTypes.SUNKEN.get(), ConversionParams.single(skeleton, true, true), (sunken) -> {
			if (!skeleton.isSilent()) {
				EUMLevelEventPacketPayload payload = new EUMLevelEventPacketPayload(1003, skeleton.blockPosition());

				//? fabric {
				for (ServerPlayer player : PlayerLookup.level((ServerLevel) skeleton.level())) {
					ServerPlayNetworking.send(player, payload);
				}
				//?} neoforge {
				/*PacketDistributor.sendToPlayersTrackingChunk((ServerLevel) skeleton.level(), skeleton.chunkPosition(), payload);
				*///?}

				sunken.selectVariant();
			}
		});
	}

	@Unique
	public boolean eum$isWaterConverting() {
		Skeleton skeleton = (Skeleton) (Object) this;

		return DATA_SUNKEN_CONVERSION_ID.getAttachedOrSet(skeleton, false);
	}

	@Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
	private void eum$saveWaterConversionTime(ValueOutput output, CallbackInfo ci) {
		output.putInt("SunkenConversionTime", this.eum$isWaterConverting() ? this.eum$waterConversionTime : -1);
	}

	@Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
	private void eum$readWaterConversionTime(ValueInput input, CallbackInfo ci) {
		this.eum$waterConversionTime = input.getIntOr("SunkenConversionTime", -1);
		if (this.eum$waterConversionTime != -1) {
			this.eum$startWaterConversion(this.eum$waterConversionTime);
		} else {
			this.eum$setWaterConverting(false);
		}
	}

	@Inject(method = "isShaking", at = @At("HEAD"), cancellable = true)
	private void eum$isShaking(CallbackInfoReturnable<Boolean> cir) {
		if(this.eum$isWaterConverting()) cir.setReturnValue(true);
	}
}
