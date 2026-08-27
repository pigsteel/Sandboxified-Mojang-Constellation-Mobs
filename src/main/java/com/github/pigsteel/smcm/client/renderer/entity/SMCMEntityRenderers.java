package com.github.pigsteel.smcm.client.renderer.entity;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.client.model.geom.SMCMModelLayers;
import com.github.pigsteel.smcm.core.SMCMEntityTypes;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.PiglinRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

import java.util.function.Supplier;

public class SMCMEntityRenderers {
    public static void load() {}

	static {
		register(SMCMEntityTypes.BRUISER, BruiserRenderer::new);
		register(SMCMEntityTypes.ENCHANTER, EnchanterRenderer::new);
		register(SMCMEntityTypes.FROSTBITTEN, FrostbittenRenderer::new);
		register(SMCMEntityTypes.RECLAIMED, ReclaimedRenderer::new);
		register(SMCMEntityTypes.SUNKEN, SunkenRenderer::new);
		register(SMCMEntityTypes.LOST, LostRenderer::new);
		register(SMCMEntityTypes.NECROMANCER, NecromancerRenderer::new);
		register(SMCMEntityTypes.ZOMBIFIED_PIGLIN_BRUTE, ZombifiedPiglinBruteRenderer::new);
		register(SMCMEntityTypes.RECLAIMED_PUKE, ReclaimedPukeRenderer::new);
		register(SMCMEntityTypes.MOUNTAINEER, MountaineerRenderer::new);
		register(SMCMEntityTypes.GEOMANCER, GeomancerRenderer::new);
		register(SMCMEntityTypes.ICEOLOGER, IceologerRenderer::new);
		register(SMCMEntityTypes.WINDCALLER, WindcallerRenderer::new);
		register(
				SMCMEntityTypes.PIGLIN_FARMER,
				context -> new PiglinRenderer(context, SMCMModelLayers.PIGLIN_FARMER, SMCMModelLayers.PIGLIN_FARMER, SMCMModelLayers.PIGLIN_FARMER_ARMOR, SMCMModelLayers.PIGLIN_FARMER_ARMOR)
		);
		register(SMCMEntityTypes.VILER_WITCH, VilerWitchRenderer::new);
		register(SMCMEntityTypes.REDSTONE_GOLEM, RedstoneGolemRenderer::new);
		register(SMCMEntityTypes.FROSTBITTEN_SNOWBALL, ThrownItemRenderer::new);
		register(SMCMEntityTypes.REDSTONE_MONSTROSITY, RedstoneMonstrosityRenderer::new);
		register(SMCMEntityTypes.WILDFIRE, WildfireRenderer::new);
		register(SMCMEntityTypes.WRAITH, WraithRenderer::new);
		register(SMCMEntityTypes.NECROMANCER_BALL, NecromancerBallRenderer::new);
	}

    public static <T extends Entity> void register(Supplier<? extends EntityType<? extends T>> type, EntityRendererProvider<T> provider) {
		SMCM.xplat().register(type, provider);
    }
}
