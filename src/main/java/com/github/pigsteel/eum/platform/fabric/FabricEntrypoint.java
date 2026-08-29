package com.github.pigsteel.eum.platform.fabric;

//? fabric {

import com.github.pigsteel.eum.EUM;
import dev.kikugie.fletching_table.annotation.fabric.Entrypoint;
import net.fabricmc.api.ModInitializer;

@Entrypoint("main")
public class FabricEntrypoint implements ModInitializer {

	@Override
	public void onInitialize() {
		EUM.onInitialize();
		FabricEventSubscriber.registerEvents();

		FabricItemGroups.init();
		FabricRegistries.init();
		FabricEntitySpawns.registerSpawnRules();
		FabricEntitySpawns.AddSpawns();
	}
}
//?}
