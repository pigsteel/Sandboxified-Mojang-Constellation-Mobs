package com.github.pigsteel.smcm.client.datagen.lang;

import com.github.pigsteel.smcm.registry.EntityTypeRegistry;
import com.github.pigsteel.smcm.registry.ItemRegistry;
import com.github.pigsteel.smcm.registry.Sounds;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;

import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

public class ENUSLangProvider extends FabricLanguageProvider {
    public ENUSLangProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(packOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider provider, TranslationBuilder translationBuilder) {
        translationBuilder.add(EntityTypeRegistry.BRUISER, "Bruiser");
        translationBuilder.add(EntityTypeRegistry.ENCHANTER, "Enchanter");
        translationBuilder.add(EntityTypeRegistry.FROSTBITTEN, "Frostbitten");
        translationBuilder.add(EntityTypeRegistry.RECLAIMED, "Reclaimed");

        translationBuilder.add(ItemRegistry.BRUISER_SPAWN_EGG, "Bruiser Spawn Egg");
        translationBuilder.add(ItemRegistry.ENCHANTER_SPAWN_EGG, "Enchanter Spawn Egg");
        translationBuilder.add(ItemRegistry.FROSTBITTEN_SPAWN_EGG, "Frostbitten Spawn Egg");
        translationBuilder.add(ItemRegistry.RECLAIMED_SPAWN_EGG, "Reclaimed Spawn Egg");

        translationBuilder.add("subtitles.smcm.entity.frostbitten.ambient", "Frostbitten groans");
        translationBuilder.add("subtitles.smcm.entity.frostbitten.hurt", "Frostbitten hurts");
        translationBuilder.add("subtitles.smcm.entity.frostbitten.death", "Frostbitten dies");

        translationBuilder.add("subtitles.smcm.entity.reclaimed.ambient", "Reclaimed groans");
        translationBuilder.add("subtitles.smcm.entity.reclaimed.hurt", "Reclaimed hurts");
        translationBuilder.add("subtitles.smcm.entity.reclaimed.death", "Reclaimed dies");

        translationBuilder.add("subtitles.smcm.entity.enchanter.ambient", "Enchanter burbles");
        translationBuilder.add("subtitles.smcm.entity.enchanter.hurt", "Enchanter hurts");
        translationBuilder.add("subtitles.smcm.entity.enchanter.death", "Enchanter dies");
    }
}
