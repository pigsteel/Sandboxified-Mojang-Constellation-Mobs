package com.github.pigsteel.smcm;

import com.github.pigsteel.smcm.datagen.lang.*;
import com.github.pigsteel.smcm.datagen.model.*;
import com.github.pigsteel.smcm.registry.smcm$RegistryData;
import net.minecraft.core.RegistrySetBuilder;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Set;

public final class SMCMNeoForgeDatagen {
    private SMCMNeoForgeDatagen() {}

    public static void onGatherClientData(GatherDataEvent.Client event) {
        SMCM.LOGGER.info("SMCM client datagen is running");

        event.createProvider(ENUSLangProvider::new);
        event.createProvider(ENGBLangProvider::new);
        event.createProvider(ENNZLangProvider::new);
        event.createProvider(ENCALangProvider::new);
        event.createProvider(ENAULangProvider::new);
        event.createProvider(ITITLangProvider::new);
        event.createProvider(DEDELangProvider::new);
        event.createProvider(FRFRLangProvider::new);
        event.createProvider(FRCALangProvider::new);
        event.createProvider(ESESLangProvider::new);
        event.createProvider(ESMXLangProvider::new);
        event.createProvider(ROROLangProvider::new);
        event.createProvider(ELGRLangProvider::new);
        event.createProvider(LOLUSLangProvider::new);
        event.createProvider(ENPTLangProvider::new);
        event.createProvider(ENUDLangProvider::new);

        event.createProvider(SMCMModelProvider::new);


        SMCM.LOGGER.info("Registering SMCM datapack registry objects provider");
        event.createDatapackRegistryObjects(
                smcm$RegistryData.createRegistrySetBuilder(),
                Set.of(SMCM.MOD_ID)
        );
    }

    public static void onGatherServerData(GatherDataEvent.Server event) {
        SMCM.LOGGER.info("SMCM server datagen is running");


    }
}
