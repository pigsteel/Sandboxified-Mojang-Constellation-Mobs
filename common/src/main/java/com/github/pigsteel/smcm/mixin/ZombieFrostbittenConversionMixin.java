package com.github.pigsteel.smcm.mixin;

import com.github.pigsteel.smcm.entity.ZombieFrostbittenConversion;
import com.github.pigsteel.smcm.registry.smcm$EntityType;
import com.google.common.annotations.VisibleForTesting;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Zombie.class)
public abstract class ZombieFrostbittenConversionMixin implements ZombieFrostbittenConversion {
    @Unique
    private static final int smcm$FROSTBITTEN_TOTAL_CONVERSION_TIME = 300;

    @Unique
    private static final EntityDataAccessor<Boolean> DATA_FROSTBITTEN_CONVERSION_ID;

    @Unique
    private int smcm$inPowderSnowTime;

    @Unique
    private int smcm$freezingConversionTime;

    @Inject(method = "defineSynchedData", at = @At("HEAD"))
    protected void smcm$defineSynchedData(SynchedEntityData.Builder entityData, CallbackInfo ci) {
        entityData.define(DATA_FROSTBITTEN_CONVERSION_ID, false);
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void smcm$tick(CallbackInfo ci) {
        Zombie zombie = (Zombie) (Object) this;

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
        zombie.getEntityData().set(DATA_FROSTBITTEN_CONVERSION_ID, isConverting);
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

        zombie.convertTo(smcm$EntityType.FROSTBITTEN.get(), ConversionParams.single(zombie, true, true), (stray) -> {
            if (!zombie.isSilent()) {
                zombie.level().levelEvent((Entity)null, 1048, zombie.blockPosition(), 0);
            }

        });
    }

    @Unique
    public boolean smcm$isFreezeConverting() {
        Zombie zombie = (Zombie) (Object) this;

        return (Boolean)zombie.getEntityData().get(DATA_FROSTBITTEN_CONVERSION_ID);
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


    static {
        DATA_FROSTBITTEN_CONVERSION_ID = SynchedEntityData.defineId(Zombie.class, EntityDataSerializers.BOOLEAN);
    }
}
