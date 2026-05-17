package com.github.pigsteel.smcm.mixin;

import net.minecraft.world.entity.ConversionParams;
import net.minecraft.world.entity.EntityType;

public interface ZombieFrostbittenConversion {
    void doFreezeConversion();

    void setFreezeConverting(final boolean isConverting);
}