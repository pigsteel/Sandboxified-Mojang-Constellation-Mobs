package com.github.pigsteel.smcm.entity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;

public interface Bonemealable {
    void bonemeal(ServerLevel level, SoundSource soundSource, ItemStack bonemealItem);

    boolean canBonemeal();
}
