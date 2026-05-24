package com.github.pigsteel.smcm.entity.illager;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.illager.Vindicator;
import net.minecraft.world.level.Level;

public class VindicatorChef extends Vindicator {
    public VindicatorChef(EntityType<? extends VindicatorChef> type, Level level) {
        super(type, level);
    }
}
