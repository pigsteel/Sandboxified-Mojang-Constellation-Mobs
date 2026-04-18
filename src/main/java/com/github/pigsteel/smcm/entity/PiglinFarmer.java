package com.github.pigsteel.smcm.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import net.minecraft.world.entity.monster.piglin.PiglinArmPose;
import net.minecraft.world.level.Level;

public class PiglinFarmer extends AbstractPiglin {
    public PiglinFarmer(EntityType<? extends PiglinFarmer> type, Level level) {
        super(type, level);
    }

    @Override
    protected boolean canHunt() {
        return false;
    }

    @Override
    public PiglinArmPose getArmPose() {
        return null;
    }

    @Override
    protected void playConvertedSound() {

    }
}
