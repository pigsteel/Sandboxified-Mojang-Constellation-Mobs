package com.github.pigsteel.smcm.mixin;

import com.github.pigsteel.smcm.core.SMCMEntityTypes;
import com.github.pigsteel.smcm.network.SMCMLevelEventPacketPayload;
import com.github.pigsteel.smcm.util.EntityTypesUtil;
import com.github.pigsteel.smcm.world.entity.ZombieFrostbittenConversion;
import com.google.common.annotations.VisibleForTesting;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ConversionParams;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.github.pigsteel.smcm.core.SMCMDataAttachments.DATA_FROSTBITTEN_CONVERSION_ID;

//? fabric {
/*import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
*///?} neoforge {
import net.neoforged.neoforge.network.PacketDistributor;
//?}

@Mixin(Zombie.class)
public abstract class ZombieFrostbittenConversionMixin implements ZombieFrostbittenConversion {
    @Unique
    private static final int smcm$FROSTBITTEN_TOTAL_CONVERSION_TIME = 300;

    @Unique
    private int smcm$inPowderSnowTime;

    @Unique
    private int smcm$freezingConversionTime;

	@Unique
    public boolean smcm$canFreezeConvert(Zombie zombie) {
        return zombie.getType() == EntityTypesUtil.ZOMBIE;
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void smcm$tick(CallbackInfo ci) {
        Zombie zombie = (Zombie) (Object) this;

        if (!this.smcm$canFreezeConvert(zombie)) {
            return;
        }

        if (!zombie.level().isClientSide() && zombie.isAlive() && !zombie.isNoAi()) {
            if (zombie.isInPowderSnow) {
                if (this.smcm$isFreezeConverting()) {
                    --this.smcm$freezingConversionTime;
                    if (this.smcm$freezingConversionTime < 0) {
                        this.smcm$doFreezeConversion();
                    }
                } else {
                    ++this.smcm$inPowderSnowTime;
                    if (this.smcm$inPowderSnowTime >= 140) {
                        this.smcm$startFreezeConversion(300);
                    }
                }
            } else {
                this.smcm$inPowderSnowTime = -1;
                this.smcm$setFreezeConverting(false);
            }
        }
    }

    @Unique
    public void smcm$setFreezeConverting(boolean isConverting) {
        Zombie zombie = (Zombie) (Object) this;

		DATA_FROSTBITTEN_CONVERSION_ID.setAttached(zombie, isConverting);
    }

    @VisibleForTesting
    @Unique
    public void smcm$startFreezeConversion(int time) {
        this.smcm$freezingConversionTime = time;
        this.smcm$setFreezeConverting(true);
    }

    @Unique
    public void smcm$doFreezeConversion() {
        Zombie zombie = (Zombie) (Object) this;

        zombie.convertTo(SMCMEntityTypes.FROSTBITTEN.get(), ConversionParams.single(zombie, true, true), (frostbitten) -> {
            if (!zombie.isSilent()) {
				SMCMLevelEventPacketPayload payload = new SMCMLevelEventPacketPayload(1001, zombie.blockPosition());

				//? fabric {
				/*for (ServerPlayer player : PlayerLookup.level((ServerLevel) zombie.level())) {
					ServerPlayNetworking.send(player, payload);
				}
				*///?} neoforge {
				PacketDistributor.sendToPlayersTrackingChunk((ServerLevel) zombie.level(), zombie.chunkPosition(), payload);
				//?}
            }
        });
    }

    @Unique
    public boolean smcm$isFreezeConverting() {
        Zombie zombie = (Zombie) (Object) this;

        return DATA_FROSTBITTEN_CONVERSION_ID.getAttachedOrSet(zombie, false);
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void smcm$saveFreezingConversionTime(ValueOutput output, CallbackInfo ci) {
        output.putInt("FrostbittenConversionTime", this.smcm$isFreezeConverting() ? this.smcm$freezingConversionTime : -1);
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void smcm$readFreezingConversionTime(ValueInput input, CallbackInfo ci) {
        this.smcm$freezingConversionTime = input.getIntOr("FrostbittenConversionTime", -1);
        if (this.smcm$freezingConversionTime != -1) {
            this.smcm$startFreezeConversion(this.smcm$freezingConversionTime);
        } else {
            this.smcm$setFreezeConverting(false);
        }
    }
}
