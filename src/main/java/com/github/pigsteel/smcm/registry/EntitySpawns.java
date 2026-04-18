package com.github.pigsteel.smcm.registry;

import com.github.pigsteel.smcm.SMCM;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;

public class EntitySpawns {
    public static void AddSpawns() {
        // Reweighting zombies and frostbitten to function like Strays
        BiomeModifications.create(Identifier.fromNamespaceAndPath(SMCM.MOD_ID,"adjust_winter_zombie_spawns"))
                .add(ModificationPhase.REPLACEMENTS,
                        BiomeSelectors.includeByKey(Biomes.ICE_SPIKES, Biomes.SNOWY_PLAINS, Biomes.JAGGED_PEAKS, Biomes.FROZEN_PEAKS, Biomes.SNOWY_TAIGA),
                        context -> {
                            var spawnSettings = context.getMobSpawnSettings();

                            spawnSettings.removeSpawnsOfEntityType(EntityType.ZOMBIE);

                            spawnSettings.addSpawn(
                                    MobCategory.MONSTER,
                                    new MobSpawnSettings.SpawnerData(EntityType.ZOMBIE, 4, 4),
                                    15
                            );

                            spawnSettings.addSpawn(
                                    MobCategory.MONSTER,
                                    new MobSpawnSettings.SpawnerData(EntityTypeRegistry.FROSTBITTEN, 4, 4),
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

                            spawnSettings.removeSpawnsOfEntityType(EntityType.ZOMBIE);

                            spawnSettings.addSpawn(
                                    MobCategory.MONSTER,
                                    new MobSpawnSettings.SpawnerData(EntityType.ZOMBIE, 4, 4),
                                    10
                            );

                            spawnSettings.addSpawn(
                                    MobCategory.MONSTER,
                                    new MobSpawnSettings.SpawnerData(EntityTypeRegistry.RECLAIMED, 4, 4),
                                    85
                            );
                        }
                );
    }
}
