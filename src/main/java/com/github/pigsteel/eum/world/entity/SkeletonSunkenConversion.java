package com.github.pigsteel.eum.world.entity;

import net.minecraft.world.entity.monster.skeleton.Skeleton;

public interface SkeletonSunkenConversion {
	boolean eum$isWaterConverting();
	boolean eum$canWaterConvert(Skeleton skeleton);
}
