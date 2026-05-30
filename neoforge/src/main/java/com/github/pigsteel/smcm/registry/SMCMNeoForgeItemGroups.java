package com.github.pigsteel.smcm.registry;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

public final class SMCMNeoForgeItemGroups {
    private SMCMNeoForgeItemGroups() {}

    @SubscribeEvent
    public static void modifyCreativeTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() != CreativeModeTabs.SPAWN_EGGS) {
            return;
        }

        event.insertAfter(
                new ItemStack(Items.CAMEL_HUSK_SPAWN_EGG),
                new ItemStack(smcm$Items.FROSTBITTEN_SPAWN_EGG.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );

        event.insertAfter(
                new ItemStack(Items.HUSK_SPAWN_EGG),
                new ItemStack(smcm$Items.RECLAIMED_SPAWN_EGG.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
    }
}