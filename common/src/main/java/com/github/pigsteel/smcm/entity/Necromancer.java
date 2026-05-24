package com.github.pigsteel.smcm.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class Necromancer extends Monster {
    protected Necromancer(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }
}
