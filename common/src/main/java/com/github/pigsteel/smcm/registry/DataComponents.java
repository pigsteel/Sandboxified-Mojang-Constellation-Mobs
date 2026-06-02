package com.github.pigsteel.smcm.registry;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.entity.zombie.Reclaimed;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;

public class DataComponents {
    public static DataComponentType<Reclaimed.HeadFlower> RECLAIMED_HEAD_FLOWER;

    public static void load() {
        RECLAIMED_HEAD_FLOWER = Registry.register(
                BuiltInRegistries.DATA_COMPONENT_TYPE,
                SMCM.id("reclaimed/head_flower"),
                DataComponentType.<Reclaimed.HeadFlower>builder()
                        .persistent(Reclaimed.HeadFlower.CODEC)
                        .networkSynchronized(Reclaimed.HeadFlower.STREAM_CODEC)
                        .build()
        );
    }
}
