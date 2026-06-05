package com.github.pigsteel.smcm.registry;

import com.github.pigsteel.smcm.entity.skeleton.SunkenVariant;
import com.github.pigsteel.smcm.entity.skeleton.SunkenVariants;
import com.github.pigsteel.smcm.services.Services;
import com.github.pigsteel.smcm.services.util.DataAttachmentHandle;
import net.minecraft.core.UUIDUtil;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.ResourceKey;

import java.util.Optional;
import java.util.UUID;

public class smcm$DataAttachments {
    public static final DataAttachmentHandle<Boolean> DATA_FROSTBITTEN_CONVERSION_ID =
            Services.ATTACHMENTS.registerSyncedEntityAttachment(
                    "data_frostbitten_conversion_id",
                    () -> false,
                    ByteBufCodecs.BOOL
            );

    public static final DataAttachmentHandle<ResourceKey<SunkenVariant>> SUNKEN_VARIANT =
            Services.ATTACHMENTS.registerPersistentSyncedEntityAttachment(
                    "sunken_variant",
                    () -> SunkenVariants.NORMAL,
                    SunkenVariant.KEY_CODEC.fieldOf("value"),
                    SunkenVariant.KEY_STREAM_CODEC
            );

    private smcm$DataAttachments() {
    }

    public static void load() {}
}
