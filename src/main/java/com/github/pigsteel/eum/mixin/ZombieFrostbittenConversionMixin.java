package com.github.pigsteel.eum.mixin;

import com.github.pigsteel.eum.core.EUMEntityTypes;
import com.github.pigsteel.eum.network.EUMLevelEventPacketPayload;
import com.github.pigsteel.eum.util.EntityTypesUtil;
import com.github.pigsteel.eum.world.entity.ZombieFrostbittenConversion;
import com.google.common.annotations.VisibleForTesting;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ConversionParams;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.github.pigsteel.eum.core.EUMDataAttachments.DATA_FROSTBITTEN_CONVERSION_ID;

//? fabric {
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
//?} neoforge {
/*import net.neoforged.neoforge.network.PacketDistributor;
*///?}

@Mixin(Zombie.class)
public abstract class ZombieFrostbittenConversionMixin implements ZombieFrostbittenConversion {
    @Unique
    private static final int eum$FROSTBITTEN_TOTAL_CONVERSION_TIME = 300;

    @Unique
    private int eum$inPowderSnowTime;

    @Unique
    private int eum$freezingConversionTime;

	@Unique
    public boolean eum$canFreezeConvert(Zombie zombie) {
        return zombie.getType() == EntityTypesUtil.ZOMBIE;
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void eum$tick(CallbackInfo ci) {
        Zombie zombie = (Zombie) (Object) this;

        if (!this.eum$canFreezeConvert(zombie)) {
            return;
        }

        if (!zombie.level().isClientSide() && zombie.isAlive() && !zombie.isNoAi()) {
            if (zombie.isInPowderSnow) {
                if (this.eum$isFreezeConverting()) {
                    --this.eum$freezingConversionTime;
                    if (this.eum$freezingConversionTime < 0) {
                        this.eum$doFreezeConversion();
                    }
                } else {
                    ++this.eum$inPowderSnowTime;
                    if (this.eum$inPowderSnowTime >= 140) {
                        this.eum$startFreezeConversion(300);
                    }
                }
            } else {
                this.eum$inPowderSnowTime = -1;
                this.eum$setFreezeConverting(false);
            }
        }
    }

    @Unique
    public void eum$setFreezeConverting(boolean isConverting) {
        Zombie zombie = (Zombie) (Object) this;

		DATA_FROSTBITTEN_CONVERSION_ID.setAttached(zombie, isConverting);
    }

    @VisibleForTesting
    @Unique
    public void eum$startFreezeConversion(int time) {
        this.eum$freezingConversionTime = time;
        this.eum$setFreezeConverting(true);
    }

    @Unique
    public void eum$doFreezeConversion() {
        Zombie zombie = (Zombie) (Object) this;

        zombie.convertTo(EUMEntityTypes.FROSTBITTEN.get(), ConversionParams.single(zombie, true, true), (frostbitten) -> {
            if (!zombie.isSilent()) {
				EUMLevelEventPacketPayload payload = new EUMLevelEventPacketPayload(1001, zombie.blockPosition());

				//? fabric {
				for (ServerPlayer player : PlayerLookup.level((ServerLevel) zombie.level())) {
					ServerPlayNetworking.send(player, payload);
				}
				//?} neoforge {
				/*PacketDistributor.sendToPlayersTrackingChunk((ServerLevel) zombie.level(), zombie.chunkPosition(), payload);
				*///?}
            }
        });
    }

    @Unique
    public boolean eum$isFreezeConverting() {
        Zombie zombie = (Zombie) (Object) this;

        return DATA_FROSTBITTEN_CONVERSION_ID.getAttachedOrSet(zombie, false);
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void eum$saveFreezingConversionTime(ValueOutput output, CallbackInfo ci) {
        output.putInt("FrostbittenConversionTime", this.eum$isFreezeConverting() ? this.eum$freezingConversionTime : -1);
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void eum$readFreezingConversionTime(ValueInput input, CallbackInfo ci) {
        this.eum$freezingConversionTime = input.getIntOr("FrostbittenConversionTime", -1);
        if (this.eum$freezingConversionTime != -1) {
            this.eum$startFreezeConversion(this.eum$freezingConversionTime);
        } else {
            this.eum$setFreezeConverting(false);
        }
    }
}
