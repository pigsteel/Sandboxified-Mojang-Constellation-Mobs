package com.github.pigsteel.smcm.registry;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.entity.illager.Bruiser;
import com.github.pigsteel.smcm.entity.illager.Enchanter;
import com.github.pigsteel.smcm.entity.skeleton.Sunken;
import com.github.pigsteel.smcm.entity.zombie.Frostbitten;
import com.github.pigsteel.smcm.entity.zombie.Reclaimed;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;

public class smcm$EntityType {
    public static EntityType<Bruiser> BRUISER;
    public static EntityType<Enchanter> ENCHANTER;
    public static EntityType<Frostbitten> FROSTBITTEN;
    public static EntityType<Reclaimed> RECLAIMED;
    public static EntityType<Sunken> SUNKEN;

    private static <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> builder) {
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(SMCM.MOD_ID, name));
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, builder.build(key));
    }

    public static void registerModEntityTypes(final EntityTypeRegistrar registrar) {
        SMCM.LOGGER.info("Registering EntityTypes for " + SMCM.MOD_ID);

        BRUISER = registrar.register("bruiser", EntityType.Builder.of(Bruiser::new, MobCategory.MONSTER)
                .sized(0.6F, 1.95F)
                .passengerAttachments(2.0F)
                .ridingOffset(-0.6F)
                .clientTrackingRange(8)
                .notInPeaceful());

        ENCHANTER = registrar.register("enchanter", EntityType.Builder.of(Enchanter::new, MobCategory.MONSTER)
                .sized(0.6F, 1.95F)
                .passengerAttachments(2.0F)
                .ridingOffset(-0.6F)
                .clientTrackingRange(8)
                .notInPeaceful());

        FROSTBITTEN = registrar.register("frostbitten", EntityType.Builder.of(Frostbitten::new, MobCategory.MONSTER)
                .sized(0.6F, 1.95F)
                .eyeHeight(1.74F)
                .passengerAttachments(2.0125F)
                .ridingOffset(-0.7F)
                .immuneTo(Blocks.POWDER_SNOW)
                .clientTrackingRange(8)
                .notInPeaceful());

        RECLAIMED = registrar.register("reclaimed", EntityType.Builder.of(Reclaimed::new, MobCategory.MONSTER)
                .sized(0.6F, 1.95F)
                .eyeHeight(1.74F)
                .passengerAttachments(2.075F)
                .ridingOffset(-0.7F)
                .clientTrackingRange(8)
                .notInPeaceful());

        SUNKEN = registrar.register("sunken", EntityType.Builder.of(Sunken::new, MobCategory.MONSTER)
                .sized(0.6F, 1.99F)
                .eyeHeight(1.74F)
                .ridingOffset(-0.7F)
                .clientTrackingRange(8)
                .notInPeaceful());
    }

    public static ResourceKey<EntityType<?>> key(final String name) {
        return ResourceKey.create(
                Registries.ENTITY_TYPE,
                Identifier.fromNamespaceAndPath(SMCM.MOD_ID, name)
        );
    }

    @FunctionalInterface
    public interface EntityTypeRegistrar {
        <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> builder);
    }
}
