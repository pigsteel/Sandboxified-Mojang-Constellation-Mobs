package com.github.pigsteel.smcm.services;

import com.github.pigsteel.smcm.services.util.DataAttachmentHandle;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.Entity;

import java.util.function.Supplier;

public interface IAttachmentRegistryHelper {
    <T> DataAttachmentHandle<T> registerEntityAttachment(
            String name,
            Supplier<T> defaultValueSupplier
    );

    <T> T get(Entity entity, DataAttachmentHandle<T> handle);

    <T> void set(Entity entity, DataAttachmentHandle<T> handle, T value);

    <T> boolean has(Entity entity, DataAttachmentHandle<T> handle);

    <T> void remove(Entity entity, DataAttachmentHandle<T> handle);

    <T> DataAttachmentHandle<T> registerPersistentEntityAttachment(
            String name,
            Supplier<T> defaultValueSupplier,
            MapCodec<T> codec
    );

    <T> DataAttachmentHandle<T> registerSyncedEntityAttachment(
            String name,
            Supplier<T> defaultValueSupplier,
            StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec
    );

    <T> DataAttachmentHandle<T> registerPersistentSyncedEntityAttachment(
            String name,
            Supplier<T> defaultValueSupplier,
            MapCodec<T> codec,
            StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec
    );
}
