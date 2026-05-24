package com.github.pigsteel.smcm.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.golem.AbstractGolem;
import net.minecraft.world.level.Level;

// Since this guy is so big we'll have to use a complex hitbox similar to the Ender Dragon
// Could maybe have limb health
public class RedstoneGolem extends AbstractGolem {
    protected RedstoneGolem(EntityType<? extends RedstoneGolem> type, Level level) {
        super(type, level);
    }


}
