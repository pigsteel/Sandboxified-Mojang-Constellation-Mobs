package com.github.pigsteel.smcm;

import com.github.pigsteel.smcm.datagen.lang.*;
import com.github.pigsteel.smcm.datagen.model.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public final class SMCMNeoForgeDatagen {
    private SMCMNeoForgeDatagen() {}

    public static void onGatherClientData(GatherDataEvent.Client event) {
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
    }

    public static void onGatherServerData(GatherDataEvent.Server event) {

    }
}
