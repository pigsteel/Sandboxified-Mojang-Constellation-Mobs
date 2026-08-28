package com.github.pigsteel.eum.world.entity;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;

public class ProjectileUtil {
    // ASSUMING THAT THE MOB ALWAYS HAS A WEAPON IN THIS SCENARIO!
    public static InteractionHand getWeaponHoldingHand(final LivingEntity mob, final Item[] weaponItems) {
        boolean i = false;
        for(Item weaponitem : weaponItems) {
            if (net.minecraft.world.entity.projectile.ProjectileUtil.getWeaponHoldingHand(mob, weaponitem) == InteractionHand.MAIN_HAND) i = true;
        }
        if (i) return InteractionHand.MAIN_HAND;
        return InteractionHand.OFF_HAND;
    }
}
