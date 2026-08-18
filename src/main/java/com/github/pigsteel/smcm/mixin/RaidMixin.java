package com.github.pigsteel.smcm.mixin;

import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.raid.Raid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.github.pigsteel.smcm.util.EnumExtensions.*;

@Mixin(Raid.class)
public class RaidMixin {

	@Inject(
			method = "getPotentialBonusSpawns",
			at = @At("HEAD"),
			cancellable = true
	)
	private void getPotentialSMCMBonusSpawns(Raid.RaiderType type, RandomSource random, int wav, DifficultyInstance difficultyInstance, boolean isBonusWave, CallbackInfoReturnable<Integer> cir) {
		if(type == VILER_WITCH) {
			Difficulty difficulty = difficultyInstance.getDifficulty();
			boolean isEasy = difficulty == Difficulty.EASY;
			boolean isNormal = difficulty == Difficulty.NORMAL;
			int bonusSpawns;

			if (isEasy || isNormal || wav <= 2 || wav == 4) {
				cir.setReturnValue(0);
				return;
			}

			bonusSpawns = 1;

			cir.setReturnValue(random.nextInt(bonusSpawns + 1));
		}
	}
}
