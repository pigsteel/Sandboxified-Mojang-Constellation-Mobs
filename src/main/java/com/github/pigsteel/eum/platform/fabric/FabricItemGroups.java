package com.github.pigsteel.eum.platform.fabric;

//? fabric  {

/*import com.github.pigsteel.eum.EUM;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import static com.github.pigsteel.eum.core.EUMItems.BRUISER_SPAWN_EGG;
import static com.github.pigsteel.eum.core.EUMItems.ENCHANTER_SPAWN_EGG;
import static com.github.pigsteel.eum.core.EUMItems.FROSTBITTEN_SPAWN_EGG;
import static com.github.pigsteel.eum.core.EUMItems.GEOMANCER_SPAWN_EGG;
import static com.github.pigsteel.eum.core.EUMItems.ICEOLOGER_SPAWN_EGG;
import static com.github.pigsteel.eum.core.EUMItems.LOST_SPAWN_EGG;
import static com.github.pigsteel.eum.core.EUMItems.MOUNTAINEER_SPAWN_EGG;
import static com.github.pigsteel.eum.core.EUMItems.NECROMANCER_SPAWN_EGG;
import static com.github.pigsteel.eum.core.EUMItems.PIGLIN_FARMER_SPAWN_EGG;
import static com.github.pigsteel.eum.core.EUMItems.RECLAIMED_SPAWN_EGG;
import static com.github.pigsteel.eum.core.EUMItems.REDSTONE_GOLEM_SPAWN_EGG;
import static com.github.pigsteel.eum.core.EUMItems.SUNKEN_SPAWN_EGG;
import static com.github.pigsteel.eum.core.EUMItems.VILER_WITCH_SPAWN_EGG;
import static com.github.pigsteel.eum.core.EUMItems.WINDCALLER_SPAWN_EGG;
import static com.github.pigsteel.eum.core.EUMItems.ZOMBIFIED_PIGLIN_BRUTE_SPAWN_EGG;

public class FabricItemGroups {

    public static void init() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.SPAWN_EGGS).register(output -> {
            /^
             * Release-visible eggs.
             *
             * Undead strict alphabetical placement:
             * Drowned
             * Frostbitten
             * Husk
             * ...
             * Parched
             * Reclaimed
             * Skeleton
             ^/
            output.insertAfter(Items.DROWNED_SPAWN_EGG, new ItemStack(FROSTBITTEN_SPAWN_EGG.get()));
            output.insertAfter(Items.PARCHED_SPAWN_EGG, new ItemStack(RECLAIMED_SPAWN_EGG.get()));

            if (EUM.xplat().isDevelopmentEnvironment()) {
                /^
                 * Undead:
                 * Frostbitten
                 * Husk
                 * Lost
                 * Necromancer
                 * Parched
                 * Reclaimed
                 * ...
                 * Stray
                 * Sunken
                 * Zombie
                 ^/
                output.insertAfter(Items.HUSK_SPAWN_EGG, new ItemStack(LOST_SPAWN_EGG.get()));
                output.insertAfter(LOST_SPAWN_EGG.get(), new ItemStack(NECROMANCER_SPAWN_EGG.get()));
                output.insertAfter(Items.STRAY_SPAWN_EGG, new ItemStack(SUNKEN_SPAWN_EGG.get()));

                /^
                 * Monsters:
                 * Slime
                 * Viler Witch
                 * Warden
                 * Witch
                 *
                 * This is strict alphabetical placement. Even though Viler Witch is a witch type,
                 * "Viler Witch" alphabetically comes before "Warden" and "Witch".
                 ^/
                output.insertAfter(Items.SLIME_SPAWN_EGG, new ItemStack(VILER_WITCH_SPAWN_EGG.get()));

                /^
                 * Illagers:
                 * Bruiser
                 * Enchanter
                 * Evoker
                 * Geomancer
                 * Iceologer
                 * Mountaineer
                 * Pillager
                 * Ravager
                 * Redstone Golem
                 * Redstone Monstrosity
                 * Vex
                 * Vindicator
                 * Windcaller
                 ^/

                output.insertBefore(Items.EVOKER_SPAWN_EGG, new ItemStack(BRUISER_SPAWN_EGG.get()));
                output.insertAfter(BRUISER_SPAWN_EGG.get(), new ItemStack(ENCHANTER_SPAWN_EGG.get()));

                output.insertAfter(Items.EVOKER_SPAWN_EGG, new ItemStack(GEOMANCER_SPAWN_EGG.get()));
                output.insertAfter(GEOMANCER_SPAWN_EGG.get(), new ItemStack(ICEOLOGER_SPAWN_EGG.get()));
                output.insertAfter(ICEOLOGER_SPAWN_EGG.get(), new ItemStack(MOUNTAINEER_SPAWN_EGG.get()));

                output.insertAfter(Items.RAVAGER_SPAWN_EGG, new ItemStack(REDSTONE_GOLEM_SPAWN_EGG.get()));
                output.insertAfter(Items.VINDICATOR_SPAWN_EGG, new ItemStack(WINDCALLER_SPAWN_EGG.get()));

                /^
                 * The Nether:
                 * Piglin
                 * Piglin Brute
                 * Piglin Farmer
                 * Strider
                 * ...
                 * Zombified Piglin
                 * Zombified Piglin Brute
                 ^/
                output.insertAfter(Items.PIGLIN_BRUTE_SPAWN_EGG, new ItemStack(PIGLIN_FARMER_SPAWN_EGG.get()));
                output.insertAfter(Items.ZOMBIFIED_PIGLIN_SPAWN_EGG, new ItemStack(ZOMBIFIED_PIGLIN_BRUTE_SPAWN_EGG.get()));
            }
        });
    }
}
*///?}
