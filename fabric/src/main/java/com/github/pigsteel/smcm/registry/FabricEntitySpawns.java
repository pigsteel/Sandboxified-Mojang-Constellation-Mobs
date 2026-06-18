package com.github.pigsteel.smcm.registry;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.entity.zombie.Frostbitten;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.Heightmap;

import static com.github.pigsteel.smcm.registry.smcm$EntityTypes.FROSTBITTEN;
import static com.github.pigsteel.smcm.registry.smcm$EntityTypes.RECLAIMED;

public class FabricEntitySpawns {
    public static void AddSpawns() {
        // Reweighting zombies and frostbitten to function like Strays
        BiomeModifications.create(Identifier.fromNamespaceAndPath(SMCM.MOD_ID,"adjust_winter_zombie_spawns"))
                .add(ModificationPhase.REPLACEMENTS,
                        BiomeSelectors.includeByKey(Biomes.ICE_SPIKES, Biomes.SNOWY_PLAINS, Biomes.JAGGED_PEAKS, Biomes.FROZEN_PEAKS, Biomes.SNOWY_TAIGA),
                        context -> {
                            var spawnSettings = context.getMobSpawnSettings();

                            spawnSettings.removeSpawnsOfEntityType(EntityTypes.ZOMBIE);

                            spawnSettings.addSpawn(
                                    MobCategory.MONSTER,
                                    new MobSpawnSettings.SpawnerData(EntityTypes.ZOMBIE, 4, 4),
                                    15
                            );

                            spawnSettings.addSpawn(
                                    MobCategory.MONSTER,
                                    new MobSpawnSettings.SpawnerData(FROSTBITTEN.get(), 4, 4),
                                    80
                            );
                        }
                );

        // Ditto
        BiomeModifications.create(Identifier.fromNamespaceAndPath(SMCM.MOD_ID,"adjust_jungle_zombie_spawns"))
                .add(ModificationPhase.REPLACEMENTS,
                        BiomeSelectors.includeByKey(Biomes.JUNGLE, Biomes.BAMBOO_JUNGLE, Biomes.SPARSE_JUNGLE),
                        context -> {
                            var spawnSettings = context.getMobSpawnSettings();

                            spawnSettings.removeSpawnsOfEntityType(EntityTypes.ZOMBIE);

                            spawnSettings.addSpawn(
                                    MobCategory.MONSTER,
                                    new MobSpawnSettings.SpawnerData(EntityTypes.ZOMBIE, 4, 4),
                                    10
                            );

                            spawnSettings.addSpawn(
                                    MobCategory.MONSTER,
                                    new MobSpawnSettings.SpawnerData(RECLAIMED.get(), 4, 4),
                                    85
                            );
                        }
                );
    }

    public static void registerSpawnRules() {
        SpawnPlacements.register(
                FROSTBITTEN.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Frostbitten::checkFrostbittenSpawnRules
        );
        SpawnPlacements.register(
                RECLAIMED.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkSurfaceMonstersSpawnRules
        );
    }
}
