package com.github.pigsteel.smcm.client.renderer.entity;

import com.github.pigsteel.smcm.client.model.geom.SMCMModelLayers;
//? neoforge {
/*import com.github.pigsteel.smcm.platform.neoforge.NeoforgeVariables;
*///?}
import com.github.pigsteel.smcm.core.SMCMEntityTypes;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.PiglinRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

import java.util.function.Supplier;

public class SMCMEntityRenderers {
    public static void load() {}

	static {
		registerEntityRenderer(SMCMEntityTypes.BRUISER, BruiserRenderer::new);
		registerEntityRenderer(SMCMEntityTypes.ENCHANTER, EnchanterRenderer::new);
		registerEntityRenderer(SMCMEntityTypes.FROSTBITTEN, FrostbittenRenderer::new);
		registerEntityRenderer(SMCMEntityTypes.RECLAIMED, ReclaimedRenderer::new);
		registerEntityRenderer(SMCMEntityTypes.SUNKEN, SunkenRenderer::new);
		registerEntityRenderer(SMCMEntityTypes.LOST, LostRenderer::new);
		registerEntityRenderer(SMCMEntityTypes.NECROMANCER, NecromancerRenderer::new);
		registerEntityRenderer(SMCMEntityTypes.ZOMBIFIED_PIGLIN_BRUTE, ZombifiedPiglinBruteRenderer::new);
		registerEntityRenderer(SMCMEntityTypes.RECLAIMED_PUKE, ReclaimedPukeRenderer::new);
		registerEntityRenderer(SMCMEntityTypes.MOUNTAINEER, MountaineerRenderer::new);
		registerEntityRenderer(SMCMEntityTypes.GEOMANCER, GeomancerRenderer::new);
		registerEntityRenderer(SMCMEntityTypes.ICEOLOGER, IceologerRenderer::new);
		registerEntityRenderer(SMCMEntityTypes.WINDCALLER, WindcallerRenderer::new);
		registerEntityRenderer(
				SMCMEntityTypes.PIGLIN_FARMER,
				context -> new PiglinRenderer(context, SMCMModelLayers.PIGLIN_FARMER, SMCMModelLayers.PIGLIN_FARMER, SMCMModelLayers.PIGLIN_FARMER_ARMOR, SMCMModelLayers.PIGLIN_FARMER_ARMOR)
		);
		registerEntityRenderer(SMCMEntityTypes.VILER_WITCH, VilerWitchRenderer::new);
		registerEntityRenderer(SMCMEntityTypes.REDSTONE_GOLEM, RedstoneGolemRenderer::new);
		registerEntityRenderer(SMCMEntityTypes.FROSTBITTEN_SNOWBALL, ThrownItemRenderer::new);
		registerEntityRenderer(SMCMEntityTypes.REDSTONE_MONSTROSITY, RedstoneMonstrosityRenderer::new);
		registerEntityRenderer(SMCMEntityTypes.WILDFIRE, WildfireRenderer::new);
		registerEntityRenderer(SMCMEntityTypes.WRAITH, WraithRenderer::new);
		registerEntityRenderer(SMCMEntityTypes.NECROMANCER_BALL, NecromancerBallRenderer::new);
	}

    public static <T extends Entity> void registerEntityRenderer(Supplier<? extends EntityType<? extends T>> type, EntityRendererProvider<T> provider) {
        //? fabric {
		EntityRenderers.register(type.get(), provider);
		//?} neoforge {
		/*NeoforgeVariables.ENTITY_RENDERERS.add(new NeoforgeVariables.EntityRendererDeferred<>(type, provider));
		*///?}
    }
}
