package com.github.pigsteel.smcm.client.datagen.lang;

import com.github.pigsteel.smcm.registry.EntityTypeRegistry;
import com.github.pigsteel.smcm.registry.ItemRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class ENUDLangProvider extends FabricLanguageProvider {
    private final String TankIllagerName = "ɹǝsᴉnɹᗺ";
    private final String EnchanterName = "ɹǝʇuɐɥɔuƎ";
    private final String FrozenZombieName = "uǝʇʇᴉqʇsoɹℲ";
    private final String JungleZombieName = "pǝɯᴉɐlɔǝᴚ";
    private final String DiesVerb = "sǝᴉp ";
    private final String HurtsVerb = "sʇɹnɥ ";

    public ENUDLangProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(packOutput, "en_ud", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider provider, TranslationBuilder translationBuilder) {
        translationBuilder.add(EntityTypeRegistry.BRUISER, TankIllagerName);
        translationBuilder.add(EntityTypeRegistry.ENCHANTER, EnchanterName);
        translationBuilder.add(EntityTypeRegistry.FROSTBITTEN, FrozenZombieName);
        translationBuilder.add(EntityTypeRegistry.RECLAIMED, JungleZombieName);

        translationBuilder.add(ItemRegistry.BRUISER_SPAWN_EGG, eggName(TankIllagerName));
        translationBuilder.add(ItemRegistry.ENCHANTER_SPAWN_EGG, eggName(EnchanterName));
        translationBuilder.add(ItemRegistry.FROSTBITTEN_SPAWN_EGG, eggName(FrozenZombieName));
        translationBuilder.add(ItemRegistry.RECLAIMED_SPAWN_EGG, eggName(JungleZombieName));

        translationBuilder.add("subtitles.smcm.entity.frostbitten.ambient", "suɐoɯ " + FrozenZombieName);
        translationBuilder.add("subtitles.smcm.entity.frostbitten.hurt", HurtsVerb + FrozenZombieName);
        translationBuilder.add("subtitles.smcm.entity.frostbitten.death", DiesVerb + FrozenZombieName);

        translationBuilder.add("subtitles.smcm.entity.reclaimed.ambient", "suɐoɹɓ " + JungleZombieName);
        translationBuilder.add("subtitles.smcm.entity.reclaimed.hurt", HurtsVerb + JungleZombieName);
        translationBuilder.add("subtitles.smcm.entity.reclaimed.death", DiesVerb + JungleZombieName);

        translationBuilder.add("subtitles.smcm.entity.enchanter.ambient", "sǝlqɹnq " + EnchanterName);
        translationBuilder.add("subtitles.smcm.entity.enchanter.hurt", HurtsVerb + EnchanterName);
        translationBuilder.add("subtitles.smcm.entity.enchanter.death", DiesVerb + EnchanterName);
    }

    private String eggName(String name) {
        return "ɓɓƎ uʍɐdS " + name;
    }
}
