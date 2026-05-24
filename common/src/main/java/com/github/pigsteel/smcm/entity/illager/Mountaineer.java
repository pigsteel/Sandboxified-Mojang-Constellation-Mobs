package com.github.pigsteel.smcm.entity.illager;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.illager.Vindicator;
import net.minecraft.world.level.Level;

public class Mountaineer extends Vindicator {
    public Mountaineer(EntityType<? extends Mountaineer> type, Level level) {
        super(type, level);
    }
}
