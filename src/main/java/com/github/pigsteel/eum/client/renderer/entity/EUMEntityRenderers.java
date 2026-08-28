package com.github.pigsteel.eum.client.renderer.entity;

import com.github.pigsteel.eum.EUM;
import com.github.pigsteel.eum.client.model.geom.EUMModelLayers;
import com.github.pigsteel.eum.core.EUMEntityTypes;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.PiglinRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

import java.util.function.Supplier;

public class EUMEntityRenderers {
    public static void load() {}

	static {
		register(EUMEntityTypes.BRUISER, BruiserRenderer::new);
		register(EUMEntityTypes.ENCHANTER, EnchanterRenderer::new);
		register(EUMEntityTypes.FROSTBITTEN, FrostbittenRenderer::new);
		register(EUMEntityTypes.RECLAIMED, ReclaimedRenderer::new);
		register(EUMEntityTypes.SUNKEN, SunkenRenderer::new);
		register(EUMEntityTypes.LOST, LostRenderer::new);
		register(EUMEntityTypes.NECROMANCER, NecromancerRenderer::new);
		register(EUMEntityTypes.ZOMBIFIED_PIGLIN_BRUTE, ZombifiedPiglinBruteRenderer::new);
		register(EUMEntityTypes.RECLAIMED_PUKE, ReclaimedPukeRenderer::new);
		register(EUMEntityTypes.MOUNTAINEER, MountaineerRenderer::new);
		register(EUMEntityTypes.GEOMANCER, GeomancerRenderer::new);
		register(EUMEntityTypes.ICEOLOGER, IceologerRenderer::new);
		register(EUMEntityTypes.WINDCALLER, WindcallerRenderer::new);
		register(
				EUMEntityTypes.PIGLIN_FARMER,
				context -> new PiglinRenderer(context, EUMModelLayers.PIGLIN_FARMER, EUMModelLayers.PIGLIN_FARMER, EUMModelLayers.PIGLIN_FARMER_ARMOR, EUMModelLayers.PIGLIN_FARMER_ARMOR)
		);
		register(EUMEntityTypes.VILER_WITCH, VilerWitchRenderer::new);
		register(EUMEntityTypes.REDSTONE_GOLEM, RedstoneGolemRenderer::new);
		register(EUMEntityTypes.FROSTBITTEN_SNOWBALL, ThrownItemRenderer::new);
		register(EUMEntityTypes.REDSTONE_MONSTROSITY, RedstoneMonstrosityRenderer::new);
		register(EUMEntityTypes.WILDFIRE, WildfireRenderer::new);
		register(EUMEntityTypes.WRAITH, WraithRenderer::new);
		register(EUMEntityTypes.NECROMANCER_BALL, NecromancerBallRenderer::new);
	}

    public static <T extends Entity> void register(Supplier<? extends EntityType<? extends T>> type, EntityRendererProvider<T> provider) {
		EUM.xplat().register(type, provider);
    }
}
