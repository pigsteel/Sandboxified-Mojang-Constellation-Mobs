package com.github.pigsteel.smcm.registry;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.entity.illager.Bruiser;
import com.github.pigsteel.smcm.entity.illager.Enchanter;
import com.github.pigsteel.smcm.entity.zombie.Frostbitten;
import com.github.pigsteel.smcm.entity.zombie.Reclaimed;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;

public class EntityTypeRegistry {
    public static EntityType<Bruiser> BRUISER;
    public static EntityType<Enchanter> ENCHANTER;
    public static EntityType<Frostbitten> FROSTBITTEN;
    public static EntityType<Reclaimed> RECLAIMED;

    private static <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> builder) {
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(SMCM.MOD_ID, name));
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, builder.build(key));
    }

    public static void registerModEntityTypes() {
        SMCM.LOGGER.info("Registering EntityTypes for " + SMCM.MOD_ID);

        BRUISER = register("bruiser", EntityType.Builder.of(Bruiser::new, MobCategory.MONSTER)
                .sized(0.6F, 1.95F).passengerAttachments(2.0F)
                .ridingOffset(-0.6F).clientTrackingRange(8)
                .notInPeaceful());
        ENCHANTER = register("enchanter", EntityType.Builder.of(Enchanter::new, MobCategory.MONSTER)
                .sized(0.6F, 1.95F)
                .passengerAttachments(2.0F)
                .ridingOffset(-0.6F)
                .clientTrackingRange(8)
                .notInPeaceful());
        FROSTBITTEN = register("frostbitten", EntityType.Builder.of(Frostbitten::new, MobCategory.MONSTER)
                .sized(0.6F, 1.95F).eyeHeight(1.74F).passengerAttachments(2.0125F)
                .ridingOffset(-0.7F)
                .clientTrackingRange(8)
                .notInPeaceful());
        RECLAIMED = register("reclaimed", EntityType.Builder.of(Reclaimed::new, MobCategory.MONSTER).sized(0.6F, 1.95F)
                .eyeHeight(1.74F)
                .passengerAttachments(2.075F)
                .ridingOffset(-0.7F).clientTrackingRange(8)
                .notInPeaceful()
        );
    }

    public static void registerAttributes() {
        FabricDefaultAttributeRegistry.register(BRUISER, Bruiser.createAttributes());
        FabricDefaultAttributeRegistry.register(FROSTBITTEN, Frostbitten.createAttributes());
        FabricDefaultAttributeRegistry.register(RECLAIMED, Reclaimed.createAttributes());
        FabricDefaultAttributeRegistry.register(ENCHANTER, Enchanter.createAttributes());
    }

    public static void registerSpawnRules() {
        SpawnPlacements.register(
                FROSTBITTEN,
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Frostbitten::checkFrostbittenSpawnRules
        );
        SpawnPlacements.register(
                RECLAIMED,
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkSurfaceMonstersSpawnRules
        );
    }
}
