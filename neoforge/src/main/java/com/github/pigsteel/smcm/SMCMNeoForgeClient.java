package com.github.pigsteel.smcm;

import com.github.pigsteel.smcm.services.ServicesClient;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = SMCM.MOD_ID, value = Dist.CLIENT)
public final class SMCMNeoForgeClient {
    private SMCMNeoForgeClient() {}

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        SMCMClient.init();
        ServicesClient.CLIENT_REGISTRY.applyEntityRendererRegistrations(event::registerEntityRenderer);
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        SMCMClient.init();
        ServicesClient.CLIENT_REGISTRY.applyModelLayerRegistrations(event::registerLayerDefinition);
    }
}
