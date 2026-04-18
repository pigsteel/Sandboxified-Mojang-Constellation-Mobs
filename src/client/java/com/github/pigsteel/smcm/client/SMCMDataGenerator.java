package com.github.pigsteel.smcm.client;

import com.github.pigsteel.smcm.client.datagen.lang.ENUSLangProvider;
import com.github.pigsteel.smcm.client.datagen.model.SMCMModelProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class SMCMDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(ENUSLangProvider::new);
        pack.addProvider(SMCMModelProvider::new);
    }
}
