package com.github.pigsteel.smcm.entity.skeleton;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;

public class smcm$ProjectileUtil {
    public static InteractionHand getWeaponHoldingHand(final LivingEntity mob, final Item[] weaponItems) {
        for(Item weaponitem : weaponItems) {
            if (mob.getMainHandItem().is(weaponitem)) {
                return InteractionHand.MAIN_HAND;
            }
        }
        return InteractionHand.OFF_HAND;
    }
}
