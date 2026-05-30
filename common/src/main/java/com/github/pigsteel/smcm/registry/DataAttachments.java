package com.github.pigsteel.smcm.registry;

import com.github.pigsteel.smcm.services.Services;
import com.github.pigsteel.smcm.services.util.DataAttachmentHandle;
import net.minecraft.network.codec.ByteBufCodecs;

public class DataAttachments {
    public static final DataAttachmentHandle<Boolean> DATA_FROSTBITTEN_CONVERSION_ID =
            Services.ATTACHMENTS.registerSyncedEntityAttachment(
                    "data_frostbitten_conversion_id",
                    () -> false,
                    ByteBufCodecs.BOOL
            );

    private DataAttachments() {
    }

    public static void load() {}
}
