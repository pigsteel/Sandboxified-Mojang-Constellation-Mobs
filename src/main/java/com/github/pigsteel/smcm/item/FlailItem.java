package com.github.pigsteel.smcm.item;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.List;

// A reverse-kinetic weapon with a low movement-threshold
public class FlailItem extends Item {
    public FlailItem(Properties properties) {
        super(properties);
    }

    public boolean ReleaseUsing() {
        return true;
    }
}
