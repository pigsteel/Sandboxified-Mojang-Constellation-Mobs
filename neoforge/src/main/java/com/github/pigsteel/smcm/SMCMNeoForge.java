package com.github.pigsteel.smcm;

import com.github.pigsteel.smcm.registry.NeoForgeEntityType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.RegisterEvent;

@Mod(SMCM.MOD_ID)
public class SMCMNeoForge {

    public SMCMNeoForge(IEventBus eventBus) {

        // This method is invoked by the NeoForge mod loader when it is ready
        // to load your mod. You can access NeoForge and Common code in this
        // project.

        // Use NeoForge to bootstrap the Common mod.
        SMCM.LOGGER.info("Hello NeoForge world!");
        SMCM.init();

        eventBus.addListener((final RegisterEvent event) -> {
            if (event.getRegistryKey().equals(Registries.ENTITY_TYPE)) {
                env(NeoForgeEntityType::bindCommonFields);
            }
        });
    }


}