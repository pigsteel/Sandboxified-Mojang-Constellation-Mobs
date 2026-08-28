package com.github.pigsteel.eum.platform.neoforge;

//? neoforge {

import com.github.pigsteel.eum.EUM;
import com.github.pigsteel.eum.core.EUMParticleTypes;
import com.github.pigsteel.eum.network.EUMLevelEventPacketPayload;
import net.minecraft.client.particle.FlameParticle;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.client.network.event.RegisterClientPayloadHandlersEvent;

import static com.github.pigsteel.eum.platform.neoforge.NeoforgeVariables.ENTITY_RENDERERS;
import static com.github.pigsteel.eum.platform.neoforge.NeoforgeVariables.MODEL_LAYERS;

@EventBusSubscriber(modid = EUM.MOD_ID, value = Dist.CLIENT)
public class NeoforgeClientEventSubscriber {
	@SubscribeEvent
	public static void onClientSetup(final FMLClientSetupEvent event) {
		EUM.onInitializeClient();
	}

	@SubscribeEvent // on the mod event bus only on the physical client
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		EUM.onInitializeClient();

		MODEL_LAYERS.forEach(deferred -> {
			deferred.register(event);
		});
	}

	@SubscribeEvent // on the mod event bus only on the physical client
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		EUM.onInitializeClient();

		ENTITY_RENDERERS.forEach(deferred -> {
			EUM.LOGGER.info("Registering renderer for {}", deferred.type().get());
			deferred.register(event);
		});
	}

	@SubscribeEvent // on the mod event bus only on the physical client
	public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(EUMParticleTypes.NECROMANCER_MAGIC.get(), FlameParticle.Provider::new);
	}

	@SubscribeEvent // on the mod event bus only on the physical client
	public static void register(RegisterClientPayloadHandlersEvent event) {
		event.register(
				EUMLevelEventPacketPayload.TYPE,
				EUMLevelEventPacketPayload::handle
		);
	}
}
//?}
