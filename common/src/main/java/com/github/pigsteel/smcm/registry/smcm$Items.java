package com.github.pigsteel.smcm.registry;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.services.IRegistryHelper;
import com.github.pigsteel.smcm.services.Services;
import com.github.pigsteel.smcm.services.util.RegistryHandle;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;

import java.util.function.Function;

public class smcm$Items {
    public static final RegistryHandle<Item> BRUISER_SPAWN_EGG = Services.REGISTRY.registerItem(
            "bruiser_spawn_egg",
            properties ->
                    new SpawnEggItem(properties.spawnEgg(smcm$EntityType.BRUISER.get()))
    );
    public static final RegistryHandle<Item> FROSTBITTEN_SPAWN_EGG = Services.REGISTRY.registerItem(
            "frostbitten_spawn_egg",
            properties ->
                    new SpawnEggItem(properties.spawnEgg(smcm$EntityType.FROSTBITTEN.get()))
    );
    public static final RegistryHandle<Item> RECLAIMED_SPAWN_EGG = Services.REGISTRY.registerItem(
            "reclaimed_spawn_egg",
            properties ->
                    new SpawnEggItem(properties.spawnEgg(smcm$EntityType.RECLAIMED.get()))
    );
    public static final RegistryHandle<Item> ENCHANTER_SPAWN_EGG = Services.REGISTRY.registerItem(
            "enchanter_spawn_egg",
            properties ->
                    new SpawnEggItem(properties.spawnEgg(smcm$EntityType.ENCHANTER.get()))
    );
    public static final RegistryHandle<Item> SUNKEN_SPAWN_EGG = Services.REGISTRY.registerItem(
            "sunken_spawn_egg",
            properties ->
                    new SpawnEggItem(properties.spawnEgg(smcm$EntityType.SUNKEN.get()))
    );

    public static <T extends Item> T register(String name, Function<Item.Properties, T> itemFactory, Item.Properties settings) {
        // Create the item key.
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(SMCM.MOD_ID, name));

        // Create the item instance.
        T item = itemFactory.apply(settings.setId(itemKey));

        // Register the item.
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }

    public static void load() {

    }
}
