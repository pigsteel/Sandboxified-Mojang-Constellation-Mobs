package com.github.pigsteel.smcm.entity;

import net.minecraft.world.entity.monster.zombie.Zombie;

public interface ZombieFrostbittenConversion {
    boolean smcm$isFreezeConverting();
    boolean smcm$canFreezeConvert(Zombie zombie);
}