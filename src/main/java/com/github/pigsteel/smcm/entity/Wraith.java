package com.github.pigsteel.smcm.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class Wraith extends Monster {
    protected Wraith(EntityType<? extends Wraith> type, Level level) {
        super(type, level);
    }
}
