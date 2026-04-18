package com.github.pigsteel.smcm.client.registry;

import com.github.pigsteel.smcm.client.renderer.entity.BruiserRenderer;
import com.github.pigsteel.smcm.client.renderer.entity.EnchanterRenderer;
import com.github.pigsteel.smcm.client.renderer.entity.FrostbittenRenderer;
import com.github.pigsteel.smcm.client.renderer.entity.ReclaimedRenderer;
import com.github.pigsteel.smcm.registry.EntityTypeRegistry;
import net.minecraft.client.renderer.entity.EntityRenderers;

public class Renderers {
    public static void initialize() {
        EntityRenderers.register(EntityTypeRegistry.RECLAIMED, ReclaimedRenderer::new);
        EntityRenderers.register(EntityTypeRegistry.FROSTBITTEN, FrostbittenRenderer::new);
        EntityRenderers.register(EntityTypeRegistry.BRUISER, BruiserRenderer::new);
        EntityRenderers.register(EntityTypeRegistry.ENCHANTER, EnchanterRenderer::new);
    }
}
