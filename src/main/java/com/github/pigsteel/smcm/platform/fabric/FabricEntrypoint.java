package com.github.pigsteel.smcm.platform.fabric;

//? fabric {

/*import com.github.pigsteel.smcm.SMCM;
import dev.kikugie.fletching_table.annotation.fabric.Entrypoint;
import net.fabricmc.api.ModInitializer;

@Entrypoint("main")
public class FabricEntrypoint implements ModInitializer {

	@Override
	public void onInitialize() {
		SMCM.onInitialize();
		FabricEventSubscriber.registerEvents();

		FabricItemGroups.init();
		FabricRegistries.init();
		FabricEntitySpawns.registerSpawnRules();
		FabricEntitySpawns.AddSpawns();
	}
}
*///?}
