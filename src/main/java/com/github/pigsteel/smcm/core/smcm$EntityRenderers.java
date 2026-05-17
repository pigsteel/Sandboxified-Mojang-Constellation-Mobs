package com.github.pigsteel.smcm.core;

import com.github.pigsteel.smcm.client.model.geom.smcm$ModelLayers;
import com.github.pigsteel.smcm.client.renderer.entity.BruiserRenderer;
import com.github.pigsteel.smcm.client.renderer.entity.EnchanterRenderer;
import com.github.pigsteel.smcm.client.renderer.entity.FrostbittenRenderer;
import com.github.pigsteel.smcm.client.renderer.entity.GeomancerRenderer;
import com.github.pigsteel.smcm.client.renderer.entity.IceologerRenderer;
import com.github.pigsteel.smcm.client.renderer.entity.LostRenderer;
import com.github.pigsteel.smcm.client.renderer.entity.MountaineerRenderer;
import com.github.pigsteel.smcm.client.renderer.entity.NecromancerRenderer;
import com.github.pigsteel.smcm.client.renderer.entity.ReclaimedPukeRenderer;
import com.github.pigsteel.smcm.client.renderer.entity.ReclaimedRenderer;
import com.github.pigsteel.smcm.client.renderer.entity.RedstoneGolemRenderer;
import com.github.pigsteel.smcm.client.renderer.entity.SunkenRenderer;
import com.github.pigsteel.smcm.client.renderer.entity.VilerWitchRenderer;
import com.github.pigsteel.smcm.client.renderer.entity.WindcallerRenderer;
import com.github.pigsteel.smcm.client.renderer.entity.ZombifiedPiglinBruteRenderer;
//? neoforge {
/*import com.github.pigsteel.smcm.platform.neoforge.NeoforgeVariables;
*///?}
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.PiglinRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

import java.util.function.Supplier;

public class smcm$EntityRenderers {
    public static void register() {}

	static {
		registerEntityRenderer(smcm$EntityTypes.BRUISER, BruiserRenderer::new);
		registerEntityRenderer(smcm$EntityTypes.ENCHANTER, EnchanterRenderer::new);
		registerEntityRenderer(smcm$EntityTypes.FROSTBITTEN, FrostbittenRenderer::new);
		registerEntityRenderer(smcm$EntityTypes.RECLAIMED, ReclaimedRenderer::new);
		registerEntityRenderer(smcm$EntityTypes.SUNKEN, SunkenRenderer::new);
		registerEntityRenderer(smcm$EntityTypes.LOST, LostRenderer::new);
		registerEntityRenderer(smcm$EntityTypes.NECROMANCER, NecromancerRenderer::new);
		registerEntityRenderer(smcm$EntityTypes.ZOMBIFIED_PIGLIN_BRUTE, ZombifiedPiglinBruteRenderer::new);
		registerEntityRenderer(smcm$EntityTypes.RECLAIMED_PUKE, ReclaimedPukeRenderer::new);
		registerEntityRenderer(smcm$EntityTypes.MOUNTAINEER, MountaineerRenderer::new);
		registerEntityRenderer(smcm$EntityTypes.GEOMANCER, GeomancerRenderer::new);
		registerEntityRenderer(smcm$EntityTypes.ICEOLOGER, IceologerRenderer::new);
		registerEntityRenderer(smcm$EntityTypes.WINDCALLER, WindcallerRenderer::new);
		registerEntityRenderer(
				smcm$EntityTypes.PIGLIN_FARMER,
				context -> new PiglinRenderer(context, smcm$ModelLayers.PIGLIN_FARMER, smcm$ModelLayers.PIGLIN_FARMER, smcm$ModelLayers.PIGLIN_FARMER_ARMOR, smcm$ModelLayers.PIGLIN_FARMER_ARMOR)
		);
		registerEntityRenderer(smcm$EntityTypes.VILER_WITCH, VilerWitchRenderer::new);
		registerEntityRenderer(smcm$EntityTypes.REDSTONE_GOLEM, RedstoneGolemRenderer::new);
		registerEntityRenderer(smcm$EntityTypes.FROSTBITTEN_SNOWBALL, ThrownItemRenderer::new);
	}

    public static <T extends Entity> void registerEntityRenderer(Supplier<? extends EntityType<? extends T>> type, EntityRendererProvider<T> provider) {
        //? fabric {
		EntityRenderers.register(type.get(), provider);
		//?} neoforge {
		/*NeoforgeVariables.ENTITY_RENDERERS.add(new NeoforgeVariables.EntityRendererDeferred<>(type, provider));
		*///?}
    }
}
