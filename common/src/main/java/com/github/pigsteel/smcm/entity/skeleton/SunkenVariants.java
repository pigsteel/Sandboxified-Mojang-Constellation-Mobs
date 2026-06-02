package com.github.pigsteel.smcm.entity.skeleton;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.registry.smcm$Registries;
import net.minecraft.core.ClientAsset;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.variant.BiomeCheck;
import net.minecraft.world.entity.variant.ModelAndTexture;
import net.minecraft.world.entity.variant.SpawnPrioritySelectors;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

public class SunkenVariants {
    public static final ResourceKey<SunkenVariant> NORMAL = createKey(Identifier.fromNamespaceAndPath(SMCM.MOD_ID, "normal"));
    public static final ResourceKey<SunkenVariant> WARM_FIRE_CORAL = createKey(Identifier.fromNamespaceAndPath(SMCM.MOD_ID, "fire_coral"));
    public static final ResourceKey<SunkenVariant> WARM_BUBBLE_CORAL = createKey(Identifier.fromNamespaceAndPath(SMCM.MOD_ID, "bubble_coral"));
    public static final ResourceKey<SunkenVariant> WARM_HORN_CORAL = createKey(Identifier.fromNamespaceAndPath(SMCM.MOD_ID, "horn_coral"));
    public static final ResourceKey<SunkenVariant> FROZEN = createKey(Identifier.fromNamespaceAndPath(SMCM.MOD_ID, "frozen"));

    private static ResourceKey<SunkenVariant> createKey(Identifier id) {
        return ResourceKey.create(smcm$Registries.SUNKEN_VARIANT, id);
    }

    public static void bootstrap(BootstrapContext<SunkenVariant> context) {
        register(
                context,
                NORMAL,
                SunkenVariant.ModelType.NORMAL,
                "sunken",
                null,
                SpawnPrioritySelectors.fallback(0)
        );

        register(
                context,
                WARM_FIRE_CORAL,
                SunkenVariant.ModelType.FIRE_CORAL,
                "sunken_fire_coral",
                "sunken_fire_coral_dead",
                BiomeTags.PRODUCES_CORALS_FROM_BONEMEAL
        );

        register(
                context,
                WARM_BUBBLE_CORAL,
                SunkenVariant.ModelType.BUBBLE_CORAL,
                "sunken_bubble_coral",
                "sunken_bubble_coral_dead",
                BiomeTags.PRODUCES_CORALS_FROM_BONEMEAL
        );

        register(
                context,
                WARM_HORN_CORAL,
                SunkenVariant.ModelType.HORN_CORAL,
                "sunken_horn_coral",
                "sunken_horn_coral_dead",
                BiomeTags.PRODUCES_CORALS_FROM_BONEMEAL
        );

        register(
                context,
                FROZEN,
                SunkenVariant.ModelType.FROZEN,
                "sunken_frozen",
                null,
                SpawnPrioritySelectors.EMPTY
        );
    }

    private static void register( // stage 1
            BootstrapContext<SunkenVariant> context,
            ResourceKey<SunkenVariant> name,
            SunkenVariant.ModelType modelType,
            String textureName,
            String deadCoralTextureName,
            TagKey<Biome> spawnBiome
    ) {
        HolderSet<Biome> biomes = context.lookup(Registries.BIOME).getOrThrow(spawnBiome);
        register(context, name, modelType, textureName, deadCoralTextureName, SpawnPrioritySelectors.single(new BiomeCheck(biomes), 1));
    }

    private static void register( // stage 2
            BootstrapContext<SunkenVariant> context,
            ResourceKey<SunkenVariant> name,
            SunkenVariant.ModelType modelType,
            String textureName,
            String deadCoralTextureName,
            SpawnPrioritySelectors selectors
    ) {
        Identifier textureId = Identifier.fromNamespaceAndPath(SMCM.MOD_ID, "entity/skeleton/sunken/" + textureName);
        Identifier deadCoralTextureId = Identifier.fromNamespaceAndPath(SMCM.MOD_ID, "entity/skeleton/sunken/" + deadCoralTextureName);
        context.register(name, new SunkenVariant(new ModelAndTexture<>(modelType, textureId), new ClientAsset.ResourceTexture(deadCoralTextureId), selectors));
    }
}
