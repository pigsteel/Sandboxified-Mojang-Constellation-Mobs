package com.github.pigsteel.smcm.entity.skeleton;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.skeleton.AbstractSkeleton;
import net.minecraft.world.level.Level;

public abstract class Overgrown extends AbstractSkeleton {
    public Overgrown(EntityType<? extends AbstractSkeleton> type, Level level) {
        super(type, level);
    }
}
