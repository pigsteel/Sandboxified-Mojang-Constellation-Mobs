package com.github.pigsteel.smcm.platform.neoforge;

//? neoforge {

/*import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.core.SMCMParticleTypes;
import com.github.pigsteel.smcm.network.SMCMLevelEventPacketPayload;
import net.minecraft.client.particle.FlameParticle;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.client.network.event.RegisterClientPayloadHandlersEvent;

import static com.github.pigsteel.smcm.platform.neoforge.NeoforgeVariables.ENTITY_RENDERERS;
import static com.github.pigsteel.smcm.platform.neoforge.NeoforgeVariables.MODEL_LAYERS;

@EventBusSubscriber(modid = SMCM.MOD_ID, value = EnvType.CLIENT)
public class NeoforgeClientEventSubscriber {
	@SubscribeEvent
	public static void onClientSetup(final FMLClientSetupEvent event) {
		SMCM.onInitializeClient();
	}

	@SubscribeEvent // on the mod event bus only on the physical client
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		SMCM.onInitializeClient();

		MODEL_LAYERS.forEach(deferred -> {
			deferred.register(event);
		});
	}

	@SubscribeEvent // on the mod event bus only on the physical client
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		SMCM.onInitializeClient();

		ENTITY_RENDERERS.forEach(deferred -> {
			SMCM.LOGGER.info("Registering renderer for {}", deferred.type().get());
			deferred.register(event);
		});
	}

	@SubscribeEvent // on the mod event bus only on the physical client
	public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(SMCMParticleTypes.NECROMANCER_MAGIC.get(), FlameParticle.Provider::new);
	}

	@SubscribeEvent // on the mod event bus only on the physical client
	public static void register(RegisterClientPayloadHandlersEvent event) {
		event.register(
				SMCMLevelEventPacketPayload.TYPE,
				SMCMLevelEventPacketPayload::handle
		);
	}
}
*///?}
