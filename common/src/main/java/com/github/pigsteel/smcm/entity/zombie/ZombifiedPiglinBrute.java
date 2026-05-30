package com.github.pigsteel.smcm.entity.zombie;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.entity.monster.zombie.ZombifiedPiglin;
import net.minecraft.world.level.Level;

public class ZombifiedPiglinBrute extends Zombie {
    public ZombifiedPiglinBrute(EntityType<? extends ZombifiedPiglinBrute> type, Level level) {
        super(type, level);
    }
}
