package com.github.pigsteel.smcm.util;

import com.github.pigsteel.smcm.mixin.RaiderTypeMixin;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.level.pathfinder.PathType;

public class EnumExtensions {
	public static PathType CLIMBABLE;
	public static Raid.RaiderType VILER_WITCH = Raid.RaiderType.SMCM_VILER_WITCH;

	static {
		//CLIMBABLE = PathType.SMCM_CLIMBABLE;
	}
}
