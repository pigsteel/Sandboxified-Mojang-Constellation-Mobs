package com.github.pigsteel.smcm.registry;

import com.github.pigsteel.smcm.client.renderer.entity.*;
import com.github.pigsteel.smcm.services.ServicesClient;
import com.github.pigsteel.smcm.services.client.IClientRegistryHelper;

public class smcm$Renderers {
    public static void load(IClientRegistryHelper registrar) {
        registrar.registerEntityRenderer(smcm$EntityType.BRUISER.get(), BruiserRenderer::new);
        registrar.registerEntityRenderer(smcm$EntityType.ENCHANTER.get(), EnchanterRenderer::new);
        registrar.registerEntityRenderer(smcm$EntityType.FROSTBITTEN.get(), FrostbittenRenderer::new);
        registrar.registerEntityRenderer(smcm$EntityType.RECLAIMED.get(), ReclaimedRenderer::new);
        registrar.registerEntityRenderer(smcm$EntityType.SUNKEN.get(), SunkenRenderer::new);
        registrar.registerEntityRenderer(smcm$EntityType.LOST.get(), LostRenderer::new);
        registrar.registerEntityRenderer(smcm$EntityType.NECROMANCER.get(), NecromancerRenderer::new);
    }
}
