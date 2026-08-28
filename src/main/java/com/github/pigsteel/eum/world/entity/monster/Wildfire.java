package com.github.pigsteel.eum.world.entity.monster;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class Wildfire extends Monster {
    public Wildfire(EntityType<? extends Wildfire> type, Level level) {
        super(type, level);
    }
}
