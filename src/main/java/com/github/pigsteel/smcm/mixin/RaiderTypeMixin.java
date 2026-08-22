package com.github.pigsteel.smcm.mixin;

import com.github.pigsteel.smcm.core.SMCMEntityTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.entity.raid.Raider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Raid.RaiderType.class)
public enum RaiderTypeMixin {
	SMCM_VILER_WITCH(SMCMEntityTypes.VILER_WITCH.get(), new int[]{0, 0, 0, 0, 0, 0, 1, 1});

	@Shadow
	RaiderTypeMixin(final EntityType<? extends Raider> entityType, final int[] spawnsPerWaveBeforeBonus) {
	}
}
