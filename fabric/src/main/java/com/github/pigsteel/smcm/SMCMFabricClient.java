package com.github.pigsteel.smcm;

import com.github.pigsteel.smcm.services.ServicesClient;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.renderer.entity.EntityRenderers;

public class SMCMFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        SMCMClient.init();
        ServicesClient.CLIENT_REGISTRY.applyModelLayerRegistrations((location, supplier) -> {
            ModelLayerRegistry.registerModelLayer(location, supplier::get);
        });
        ServicesClient.CLIENT_REGISTRY.applyEntityRendererRegistrations(EntityRenderers::register);
    }
}
