package com.github.pigsteel.smcm.client;

import com.github.pigsteel.smcm.client.registry.LayerDefinitions;
import com.github.pigsteel.smcm.client.registry.ModelLayers;
import com.github.pigsteel.smcm.client.registry.Renderers;
import net.fabricmc.api.ClientModInitializer;

public class SMCMClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        LayerDefinitions.registerModelLayers();
        Renderers.initialize();
    }
}
