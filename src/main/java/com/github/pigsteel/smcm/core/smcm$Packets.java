package com.github.pigsteel.smcm.core;

import com.github.pigsteel.smcm.network.SMCMLevelEventPacketPayload;
//? fabric {
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
//?}

public class smcm$Packets {
	static {
		//? fabric {
		PayloadTypeRegistry.clientboundPlay().register(SMCMLevelEventPacketPayload.TYPE, SMCMLevelEventPacketPayload.STREAM_CODEC);
		//?}
	}

	public static void load() {}

	public static void clientLoad() {
		//? fabric {
		ClientPlayNetworking.registerGlobalReceiver(SMCMLevelEventPacketPayload.TYPE, SMCMLevelEventPacketPayload::handle);
		//?}
	}
}
