package com.github.pigsteel.smcm;

import com.github.pigsteel.smcm.registry.smcm$LayerDefinitions;
import com.github.pigsteel.smcm.registry.smcm$Renderers;
import com.github.pigsteel.smcm.services.ServicesClient;

public final class SMCMClient {
    private static boolean initialized;

    private SMCMClient() {}

    public static void init() {
        if(initialized)
            return;

        initialized = true;

        smcm$LayerDefinitions.registerModelLayers(ServicesClient.CLIENT_REGISTRY);
        smcm$Renderers.load(ServicesClient.CLIENT_REGISTRY);
    }
}
