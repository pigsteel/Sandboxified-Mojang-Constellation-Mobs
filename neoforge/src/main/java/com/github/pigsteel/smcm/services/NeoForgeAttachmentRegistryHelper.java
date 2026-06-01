package com.github.pigsteel.smcm.services;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.services.util.DataAttachmentHandle;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public final class NeoForgeAttachmentRegistryHelper implements IAttachmentRegistryHelper {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
            DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, SMCM.MOD_ID);

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

        Supplier<AttachmentType<T>> type = ATTACHMENT_TYPES.register(
                name,
                () -> AttachmentType.builder(defaultValueSupplier).build()
        );

        handle.setPlatformTypeSupplier(type);
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

        Supplier<AttachmentType<T>> type = ATTACHMENT_TYPES.register(
                name,
                () -> AttachmentType.builder(defaultValueSupplier)
                        .serialize(codec)
                        .build()
        );

        handle.setPlatformTypeSupplier(type);
        return handle;
    }

    @Override
    public <T> T get(Entity entity, DataAttachmentHandle<T> handle) {
        return entity.getData(neoforgeType(handle));
    }

    @Override
    public <T> void set(Entity entity, DataAttachmentHandle<T> handle, T value) {
        entity.setData(neoforgeType(handle), value);
    }

    @Override
    public <T> boolean has(Entity entity, DataAttachmentHandle<T> handle) {
        return entity.hasData(neoforgeType(handle));
    }

    @Override
    public <T> void remove(Entity entity, DataAttachmentHandle<T> handle) {
        entity.removeData(neoforgeType(handle));
    }

    @SuppressWarnings("unchecked")
    private static <T> AttachmentType<T> neoforgeType(DataAttachmentHandle<T> handle) {
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

        Supplier<AttachmentType<T>> type = ATTACHMENT_TYPES.register(
                name,
                () -> AttachmentType.builder(defaultValueSupplier)
                        .sync((holder, player) -> true, streamCodec)
                        .build()
        );

        handle.setPlatformTypeSupplier(type);
        return handle;
    }

    @Override
    public <T> DataAttachmentHandle<T> registerPersistentSyncedEntityAttachment(
            String name,
            Supplier<T> defaultValueSupplier,
            MapCodec<T> mapCodec,
            StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec
    ) {
        DataAttachmentHandle<T> handle = new DataAttachmentHandle<>(
                name,
                defaultValueSupplier,
                true
        );

        Supplier<AttachmentType<T>> type = ATTACHMENT_TYPES.register(
                name,
                () -> AttachmentType.builder(defaultValueSupplier)
                        .serialize(mapCodec)
                        .sync((holder, player) -> true, streamCodec)
                        .build()
        );

        handle.setPlatformTypeSupplier(type);
        return handle;
    }
}
