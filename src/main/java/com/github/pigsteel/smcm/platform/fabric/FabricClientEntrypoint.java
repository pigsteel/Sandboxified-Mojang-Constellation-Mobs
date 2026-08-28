package com.github.pigsteel.smcm.platform.fabric;

//? fabric {

import com.github.pigsteel.smcm.SMCM;
import dev.kikugie.fletching_table.annotation.fabric.Entrypoint;
import net.fabricmc.api.ClientModInitializer;

@Entrypoint("client")
public class FabricClientEntrypoint implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		SMCM.onInitializeClient();
	}

}
//?}
