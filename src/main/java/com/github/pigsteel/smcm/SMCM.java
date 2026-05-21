package com.github.pigsteel.smcm;

import com.github.pigsteel.smcm.registry.*;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SMCM implements ModInitializer {
    public static final String MOD_ID = "smcm";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        Sounds.init();
        EntityTypeRegistry.registerModEntityTypes();
        EntityTypeRegistry.registerAttributes();
        EntityTypeRegistry.registerSpawnRules();
        DataComponents.initialize();
        EntitySpawns.AddSpawns();
        ItemRegistry.initialize();
        LootTables.initialize();
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
