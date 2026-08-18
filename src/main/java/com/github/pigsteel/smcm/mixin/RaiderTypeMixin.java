package com.github.pigsteel.smcm.mixin;

import com.github.pigsteel.smcm.core.smcm$EntityTypes;
import com.github.pigsteel.smcm.world.entity.monster.VilerWitch;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.entity.raid.Raider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.function.Supplier;

@Mixin(Raid.RaiderType.class)
public enum RaiderTypeMixin {
	SMCM_VILER_WITCH(smcm$EntityTypes.VILER_WITCH.get(), new int[]{0, 0, 0, 0, 0, 0, 1, 1});

	@Shadow
	RaiderTypeMixin(final EntityType<? extends Raider> entityType, final int[] spawnsPerWaveBeforeBonus) {
	}
}
