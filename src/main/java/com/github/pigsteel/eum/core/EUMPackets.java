package com.github.pigsteel.eum.core;

import com.github.pigsteel.eum.network.EUMLevelEventPacketPayload;
//? fabric {
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
//?}

public class EUMPackets {
	static {
		//? fabric {
		PayloadTypeRegistry.clientboundPlay().register(EUMLevelEventPacketPayload.TYPE, EUMLevelEventPacketPayload.STREAM_CODEC);
		 //?}
	}

	public static void load() {}

	public static void clientLoad() {
		//? fabric {
		ClientPlayNetworking.registerGlobalReceiver(EUMLevelEventPacketPayload.TYPE, EUMLevelEventPacketPayload::handle);
		//?}
	}
}
