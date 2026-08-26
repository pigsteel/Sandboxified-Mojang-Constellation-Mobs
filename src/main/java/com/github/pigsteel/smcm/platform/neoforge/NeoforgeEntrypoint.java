package com.github.pigsteel.smcm.platform.neoforge;

//? neoforge {

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.network.SMCMLevelEventPacketPayload;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handlers.ServerPayloadHandler;
import net.neoforged.neoforge.network.registration.HandlerThread;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@Mod(SMCM.MOD_ID)
public class NeoforgeEntrypoint {
	public NeoforgeEntrypoint(IEventBus modBus) {
		SMCM.onInitialize();
		modBus.addListener(NeoforgeEntrypoint::register);
		NeoforgeVariables.registerAll(modBus);
	}

	public static void register(RegisterPayloadHandlersEvent event) {
		final PayloadRegistrar registrar = event.registrar("1")
				.executesOn(HandlerThread.NETWORK); // All subsequent payloads will register on the network thread
		registrar.playToClient(
				SMCMLevelEventPacketPayload.TYPE,
				SMCMLevelEventPacketPayload.STREAM_CODEC
		);
	}
}
//?}
