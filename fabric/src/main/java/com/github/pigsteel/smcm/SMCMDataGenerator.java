package com.github.pigsteel.smcm;

import com.github.pigsteel.smcm.datagen.model.SMCMModelProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class SMCMDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(ENUSLangProvider::new);
        pack.addProvider(ENGBLangProvider::new);
        pack.addProvider(ENNZLangProvider::new);
        pack.addProvider(ENCALangProvider::new);
        pack.addProvider(ENAULangProvider::new);
        pack.addProvider(ITITLangProvider::new);
        pack.addProvider(DEDELangProvider::new);
        pack.addProvider(FRFRLangProvider::new);
        pack.addProvider(FRCALangProvider::new);
        pack.addProvider(ESESLangProvider::new);
        pack.addProvider(ESMXLangProvider::new);
        pack.addProvider(ROROLangProvider::new);
        pack.addProvider(ELGRLangProvider::new);
        pack.addProvider(LOLUSLangProvider::new);
        pack.addProvider(ENPTLangProvider::new);
        pack.addProvider(ENUDLangProvider::new);

        pack.addProvider(SMCMModelProvider::new);
    }
}
