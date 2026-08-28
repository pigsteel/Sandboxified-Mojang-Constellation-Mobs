package com.github.pigsteel.eum.platform.neoforge.subscriber;

//? neoforge {

import com.github.pigsteel.eum.EUM;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

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
import static net.minecraft.world.item.Items.CAMEL_HUSK_SPAWN_EGG;
import static net.minecraft.world.item.Items.EVOKER_SPAWN_EGG;
import static net.minecraft.world.item.Items.HUSK_SPAWN_EGG;
import static net.minecraft.world.item.Items.PIGLIN_BRUTE_SPAWN_EGG;
import static net.minecraft.world.item.Items.RAVAGER_SPAWN_EGG;
import static net.minecraft.world.item.Items.SLIME_SPAWN_EGG;
import static net.minecraft.world.item.Items.STRAY_SPAWN_EGG;
import static net.minecraft.world.item.Items.VINDICATOR_SPAWN_EGG;
import static net.minecraft.world.item.Items.ZOMBIFIED_PIGLIN_SPAWN_EGG;

@EventBusSubscriber(modid = EUM.MOD_ID)
public final class NeoforgeItemGroups {
    private NeoforgeItemGroups() {}

    @SubscribeEvent
    public static void modifyCreativeTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() != CreativeModeTabs.SPAWN_EGGS) {
            return;
        }

        event.insertAfter(
                new ItemStack(CAMEL_HUSK_SPAWN_EGG),
                new ItemStack(FROSTBITTEN_SPAWN_EGG.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );

        event.insertAfter(
                new ItemStack(HUSK_SPAWN_EGG),
                new ItemStack(RECLAIMED_SPAWN_EGG.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );

		if (EUM.xplat().isDevelopmentEnvironment()) {
			event.insertAfter(HUSK_SPAWN_EGG.getDefaultInstance(), new ItemStack(LOST_SPAWN_EGG.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.insertAfter(LOST_SPAWN_EGG.get().getDefaultInstance(), new ItemStack(NECROMANCER_SPAWN_EGG.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.insertAfter(STRAY_SPAWN_EGG.getDefaultInstance(), new ItemStack(SUNKEN_SPAWN_EGG.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

			event.insertAfter(SLIME_SPAWN_EGG.getDefaultInstance(), new ItemStack(VILER_WITCH_SPAWN_EGG.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

			event.insertBefore(EVOKER_SPAWN_EGG.getDefaultInstance(), BRUISER_SPAWN_EGG.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.insertAfter(BRUISER_SPAWN_EGG.get().getDefaultInstance(), ENCHANTER_SPAWN_EGG.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

			event.insertAfter(EVOKER_SPAWN_EGG.getDefaultInstance(), new ItemStack(GEOMANCER_SPAWN_EGG.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.insertAfter(GEOMANCER_SPAWN_EGG.get().getDefaultInstance(), new ItemStack(ICEOLOGER_SPAWN_EGG.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.insertAfter(ICEOLOGER_SPAWN_EGG.get().getDefaultInstance(), new ItemStack(MOUNTAINEER_SPAWN_EGG.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

			event.insertAfter(RAVAGER_SPAWN_EGG.getDefaultInstance(), new ItemStack(REDSTONE_GOLEM_SPAWN_EGG.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.insertAfter(VINDICATOR_SPAWN_EGG.getDefaultInstance(), new ItemStack(WINDCALLER_SPAWN_EGG.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);


			event.insertAfter(PIGLIN_BRUTE_SPAWN_EGG.getDefaultInstance(), new ItemStack(PIGLIN_FARMER_SPAWN_EGG.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.insertAfter(ZOMBIFIED_PIGLIN_SPAWN_EGG.getDefaultInstance(), new ItemStack(ZOMBIFIED_PIGLIN_BRUTE_SPAWN_EGG.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
		}
    }
}
//?}
