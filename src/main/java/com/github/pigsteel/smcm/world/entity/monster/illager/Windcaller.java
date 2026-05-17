package com.github.pigsteel.smcm.world.entity.monster.illager;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.illager.SpellcasterIllager;
import net.minecraft.world.level.Level;

public class Windcaller extends SpellcasterIllager {
    public Windcaller(EntityType<? extends Windcaller> type, Level level) {
        super(type, level);
    }

    @Override
    protected SoundEvent getCastingSoundEvent() {
        return null;
    }

    @Override
    public void applyRaidBuffs(ServerLevel level, int wave, boolean isCaptain) {

    }

    @Override
    public SoundEvent getCelebrateSound() {
        return null;
    }

	@Override
	public IllagerArmPose getArmPose() {
		return IllagerArmPose.NEUTRAL;
	}
}
