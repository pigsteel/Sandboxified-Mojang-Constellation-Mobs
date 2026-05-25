package com.github.pigsteel.smcm.registry;

import com.github.pigsteel.smcm.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.EntityRenderers;

public class FabricEntityRenderers {
    public static void initialize() {
        EntityRenderers.register(smcm$EntityType.RECLAIMED, ReclaimedRenderer::new);
        EntityRenderers.register(smcm$EntityType.FROSTBITTEN, FrostbittenRenderer::new);
        EntityRenderers.register(smcm$EntityType.BRUISER, BruiserRenderer::new);
        EntityRenderers.register(smcm$EntityType.ENCHANTER, EnchanterRenderer::new);
        EntityRenderers.register(smcm$EntityType.SUNKEN, SunkenRenderer::new);
    }
}
