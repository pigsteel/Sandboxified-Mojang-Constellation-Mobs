package com.github.pigsteel.smcm.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.golem.AbstractGolem;
import net.minecraft.world.level.Level;

public class TuffGolem extends AbstractGolem {
    protected TuffGolem(EntityType<? extends AbstractGolem> type, Level level) {
        super(type, level);
    }
}
