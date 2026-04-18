package com.github.pigsteel.smcm;

import com.github.pigsteel.smcm.registry.*;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.text.html.parser.Entity;

public class SMCM implements ModInitializer {
    public static final String MOD_ID = "smcm";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        Sounds.init();
        EntityTypeRegistry.registerModEntityTypes();
        EntityTypeRegistry.registerAttributes();
        EntityTypeRegistry.registerSpawnRules();
        EntitySpawns.AddSpawns();
        ItemRegistry.initialize();
        LootTables.initialize();
    }
}
