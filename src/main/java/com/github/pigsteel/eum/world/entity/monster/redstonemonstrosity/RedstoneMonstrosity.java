package com.github.pigsteel.eum.world.entity.monster.redstonemonstrosity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.level.Level;

public class RedstoneMonstrosity extends Raider {
	public RedstoneMonstrosity(EntityType<? extends Raider> type, Level level) {
		super(type, level);
	}

	@Override
	public void applyRaidBuffs(ServerLevel level, int wave, boolean isCaptain) {

	}

	@Override
	public SoundEvent getCelebrateSound() {
		return null;
	}
}
