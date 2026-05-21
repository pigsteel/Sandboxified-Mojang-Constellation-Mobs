package com.github.pigsteel.smcm.registry;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.entity.zombie.Reclaimed;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Function;

public class ItemRegistry {
    public static final Item FLAIL = register("flail", Item::new, new Item.Properties());
    public static final Item HARPOON = register("harpoon", Item::new, new Item.Properties());
    public static final Item BRUISER_SPAWN_EGG = register(
            "bruiser_spawn_egg",
            SpawnEggItem::new,
            new Item.Properties().spawnEgg(EntityTypeRegistry.BRUISER)
    );
    public static final Item FROSTBITTEN_SPAWN_EGG = register(
            "frostbitten_spawn_egg",
            SpawnEggItem::new,
            new Item.Properties().spawnEgg(EntityTypeRegistry.FROSTBITTEN)
    );
    public static final Item RECLAIMED_SPAWN_EGG = register(
            "reclaimed_spawn_egg",
            SpawnEggItem::new,
            new Item.Properties().spawnEgg(EntityTypeRegistry.RECLAIMED)
    );
    public static final Item ENCHANTER_SPAWN_EGG = register(
            "enchanter_spawn_egg",
            SpawnEggItem::new,
            new Item.Properties().spawnEgg(EntityTypeRegistry.ENCHANTER)
    );

    public static final TagKey<Item> REPAIRS_FLAIL = TagKey.create(BuiltInRegistries.ITEM.key(), Identifier.fromNamespaceAndPath(SMCM.MOD_ID, "repairs_flail"));

    public static <T extends Item> T register(String name, Function<Item.Properties, T> itemFactory, Item.Properties settings) {
        // Create the item key.
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(SMCM.MOD_ID, name));

        // Create the item instance.
        T item = itemFactory.apply(settings.setId(itemKey));

        // Register the item.
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }

    public static void initialize() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.SPAWN_EGGS).register(output -> {
            output.insertAfter(Items.CAMEL_HUSK_SPAWN_EGG, new ItemStack(FROSTBITTEN_SPAWN_EGG));
            output.insertAfter(Items.HUSK_SPAWN_EGG, new ItemStack(RECLAIMED_SPAWN_EGG));
        }); // enchanter egg will be removed for now as entity is functionless
    }
}
