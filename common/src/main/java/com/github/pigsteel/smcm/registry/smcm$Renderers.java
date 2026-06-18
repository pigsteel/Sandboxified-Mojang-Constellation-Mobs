package com.github.pigsteel.smcm.registry;

import com.github.pigsteel.smcm.client.renderer.entity.*;
import com.github.pigsteel.smcm.services.client.IClientRegistryHelper;

public class smcm$Renderers {
    public static void load(IClientRegistryHelper registrar) {
        registrar.registerEntityRenderer(smcm$EntityTypes.BRUISER.get(), BruiserRenderer::new);
        registrar.registerEntityRenderer(smcm$EntityTypes.ENCHANTER.get(), EnchanterRenderer::new);
        registrar.registerEntityRenderer(smcm$EntityTypes.FROSTBITTEN.get(), FrostbittenRenderer::new);
        registrar.registerEntityRenderer(smcm$EntityTypes.RECLAIMED.get(), ReclaimedRenderer::new);
        registrar.registerEntityRenderer(smcm$EntityTypes.SUNKEN.get(), SunkenRenderer::new);
        registrar.registerEntityRenderer(smcm$EntityTypes.LOST.get(), LostRenderer::new);
        registrar.registerEntityRenderer(smcm$EntityTypes.NECROMANCER.get(), NecromancerRenderer::new);
    }
}
