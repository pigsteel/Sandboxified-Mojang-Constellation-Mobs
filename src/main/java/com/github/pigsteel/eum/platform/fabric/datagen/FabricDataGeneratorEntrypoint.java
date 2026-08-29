package com.github.pigsteel.eum.platform.fabric.datagen;

//? fabric {
import com.github.pigsteel.eum.datagen.SMCMLanguageProvider;
import com.github.pigsteel.eum.datagen.SMCMModelProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class FabricDataGeneratorEntrypoint implements DataGeneratorEntrypoint {

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator generator) {
		final FabricDataGenerator.Pack pack = generator.createPack();
		pack.addProvider(SMCMModelProvider::new);
		pack.addProvider(SMCMLanguageProvider::new);
	}

}
//?}
