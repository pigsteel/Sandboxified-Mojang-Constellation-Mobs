package com.github.pigsteel.smcm.world.entity;

import net.minecraft.world.entity.monster.skeleton.Skeleton;

public interface SkeletonSunkenConversion {
	boolean smcm$isWaterConverting();
	boolean smcm$canWaterConvert(Skeleton skeleton);
}
