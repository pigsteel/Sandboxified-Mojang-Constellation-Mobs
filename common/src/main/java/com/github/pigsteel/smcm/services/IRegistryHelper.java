package com.github.pigsteel.smcm.services;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.entity.skeleton.SunkenVariant;
import com.github.pigsteel.smcm.services.util.RegistryHandle;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.particle.Particle;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public interface IRegistryHelper {
    <T extends Block> RegistryHandle<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> block);

    <T extends BlockItem> RegistryHandle<T> registerBlockItem(String name, RegistryHandle<? extends Block> block, BiFunction<Block, Item.Properties, T> item);

    <T extends Item> RegistryHandle<T> registerItem(String name, Function<Item.Properties, T> item);

    <T extends Entity> RegistryHandle<EntityType<T>> registerEntityType(String name, EntityType.Builder<T> builder);

    //<T extends Particle> RegistryHandle<T> registerParticle(String name)

    RegistryHandle<SoundEvent> registerSoundEvent(String name);

    RegistryHandle<CreativeModeTab> registerCreativeTab(String name, Supplier<ItemStack> icon, Consumer<CreativeTabOutput> entries);

    <T extends ConsumeEffect> RegistryHandle<ConsumeEffect.Type<T>> registerConsumeEffectType(String name, MapCodec<T> codec, StreamCodec<RegistryFriendlyByteBuf, T> streamCodec);

    <T extends AbstractContainerMenu> RegistryHandle<MenuType<T>> registerMenuType(String name, Class<T> menuClass);

    static ResourceKey<Block> blockKey(String name) {
        return ResourceKey.create(Registries.BLOCK, SMCM.id(name));
    }

    static ResourceKey<Item> itemKey(String name) {
        return ResourceKey.create(Registries.ITEM, SMCM.id(name));
    }

    static ResourceKey<EntityType<?>> entityTypeKey(String name) {
        return ResourceKey.create(Registries.ENTITY_TYPE, SMCM.id(name));
    }

    static ResourceKey<SoundEvent> soundEventKey(String name) {
        return ResourceKey.create(Registries.SOUND_EVENT, SMCM.id(name));
    }

    @FunctionalInterface
    interface CreativeTabOutput {
        void accept(ItemLike itemLike);
    }
}
