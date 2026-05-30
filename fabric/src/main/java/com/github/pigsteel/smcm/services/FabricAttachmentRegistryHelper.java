package com.github.pigsteel.smcm.services;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.services.util.DataAttachmentHandle;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentTarget;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;

import java.util.function.Supplier;

public final class FabricAttachmentRegistryHelper implements IAttachmentRegistryHelper {
    @Override
    public <T> DataAttachmentHandle<T> registerEntityAttachment(
            String name,
            Supplier<T> defaultValueSupplier
    ) {
        DataAttachmentHandle<T> handle = new DataAttachmentHandle<>(
                name,
                defaultValueSupplier,
                false
        );

        AttachmentType<T> type = AttachmentRegistry.createDefaulted(
                Identifier.fromNamespaceAndPath(SMCM.MOD_ID, name),
                defaultValueSupplier
        );

        handle.setPlatformTypeSupplier(() -> type);
        return handle;
    }

    @Override
    public <T> DataAttachmentHandle<T> registerPersistentEntityAttachment(
            String name,
            Supplier<T> defaultValueSupplier,
            MapCodec<T> codec
    ) {
        DataAttachmentHandle<T> handle = new DataAttachmentHandle<>(
                name,
                defaultValueSupplier,
                true
        );

        AttachmentType<T> type = AttachmentRegistry.createPersistent(
                Identifier.fromNamespaceAndPath(SMCM.MOD_ID, name),
                codec.codec()
        );

        handle.setPlatformTypeSupplier(() -> type);
        return handle;
    }

    @Override
    public <T> T get(Entity entity, DataAttachmentHandle<T> handle) {
        AttachmentTarget target = (AttachmentTarget) entity;

        T value = target.getAttached(fabricType(handle));
        if (value != null) {
            return value;
        }

        T defaultValue = handle.defaultValueSupplier().get();
        target.setAttached(fabricType(handle), defaultValue);
        return defaultValue;
    }

    @Override
    public <T> void set(Entity entity, DataAttachmentHandle<T> handle, T value) {
        ((AttachmentTarget) entity).setAttached(fabricType(handle), value);
    }

    @Override
    public <T> boolean has(Entity entity, DataAttachmentHandle<T> handle) {
        return ((AttachmentTarget) entity).hasAttached(fabricType(handle));
    }

    @Override
    public <T> void remove(Entity entity, DataAttachmentHandle<T> handle) {
        ((AttachmentTarget) entity).removeAttached(fabricType(handle));
    }

    @SuppressWarnings("unchecked")
    private static <T> AttachmentType<T> fabricType(DataAttachmentHandle<T> handle) {
        return (AttachmentType<T>) handle.platformType();
    }

    @Override
    public <T> DataAttachmentHandle<T> registerSyncedEntityAttachment(
            String name,
            Supplier<T> defaultValueSupplier,
            StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec
    ) {
        DataAttachmentHandle<T> handle = new DataAttachmentHandle<>(
                name,
                defaultValueSupplier,
                false
        );

        AttachmentType<T> type = AttachmentRegistry.create(
                Identifier.fromNamespaceAndPath(SMCM.MOD_ID, name),
                builder -> builder
                        .initializer(defaultValueSupplier)
                        .syncWith(streamCodec, AttachmentSyncPredicate.all())
        );

        handle.setPlatformTypeSupplier(() -> type);
        return handle;
    }
}