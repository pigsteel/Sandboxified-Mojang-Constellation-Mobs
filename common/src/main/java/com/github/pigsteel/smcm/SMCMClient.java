package com.github.pigsteel.smcm;

import com.github.pigsteel.smcm.registry.LayerDefinitions;
import com.github.pigsteel.smcm.registry.ModelLayers;
import com.github.pigsteel.smcm.registry.smcm$Renderers;
import com.github.pigsteel.smcm.services.ServicesClient;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.ArmorModelSet;

public final class SMCMClient {
    private static boolean initialized;

    private SMCMClient() {}

    public static void init() {
        if(initialized)
            return;

        initialized = true;

        LayerDefinitions.registerModelLayers(ServicesClient.CLIENT_REGISTRY);
        smcm$Renderers.load(ServicesClient.CLIENT_REGISTRY);
    }
}
