package com.github.pigsteel.smcm;

import com.github.pigsteel.smcm.registry.FabricEntitySpawns;
import com.github.pigsteel.smcm.registry.FabricItemGroups;
import com.github.pigsteel.smcm.registry.FabricRegistries;
import com.github.pigsteel.smcm.services.Services;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;

public class SMCMFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        
        // This method is invoked by the Fabric mod loader when it is ready
        // to load your mod. You can access Fabric and Common code in this
        // project.

        // Use Fabric to bootstrap the Common mod.
        SMCM.LOGGER.info("Hello Fabric world!");
        SMCM.init();
        FabricItemGroups.init();
        FabricRegistries.init();
        FabricEntitySpawns.registerSpawnRules();
        FabricEntitySpawns.AddSpawns();

        Services.ATTRIBUTES.applyEntityAttributeRegistrations(FabricDefaultAttributeRegistry::register);
    }
}
