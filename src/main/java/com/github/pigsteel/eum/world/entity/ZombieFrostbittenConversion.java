package com.github.pigsteel.eum.world.entity;

import net.minecraft.world.entity.monster.zombie.Zombie;

public interface ZombieFrostbittenConversion {
    boolean eum$isFreezeConverting();
    boolean eum$canFreezeConvert(Zombie zombie);
}
