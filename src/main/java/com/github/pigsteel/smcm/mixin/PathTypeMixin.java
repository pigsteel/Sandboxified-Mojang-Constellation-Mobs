package com.github.pigsteel.smcm.mixin;

import net.minecraft.world.level.pathfinder.PathType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(PathType.class)
public enum PathTypeMixin {
	SMCM_CLIMBABLE(0.0F);

	@Shadow
	PathTypeMixin(float defaultCost) {}
}
