package com.github.pigsteel.smcm.registry;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import static com.github.pigsteel.smcm.registry.smcm$Items.*;

public class FabricItemGroups {

    public static void init() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.SPAWN_EGGS).register(output -> {
            output.insertAfter(Items.CAMEL_HUSK_SPAWN_EGG, new ItemStack(FROSTBITTEN_SPAWN_EGG.get()));
            output.insertAfter(Items.HUSK_SPAWN_EGG, new ItemStack(RECLAIMED_SPAWN_EGG.get()));
        }); // enchanter egg will be removed for now as entity is functionless
    }
}
