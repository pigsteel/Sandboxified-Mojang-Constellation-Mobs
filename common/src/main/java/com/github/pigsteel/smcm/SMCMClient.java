package com.github.pigsteel.smcm;

import com.github.pigsteel.smcm.registry.ModelLayers;
import com.github.pigsteel.smcm.services.ServicesClient;

public final class SMCMClient {
    private static boolean initialized;

    private SMCMClient() {}

    public static void init() {
        if(initialized)
            return;

        initialized = true;

        ServicesClient.CLIENT_REGISTRY.registerModelLayer(ModelLayers.BRUISER, );
    }
}
