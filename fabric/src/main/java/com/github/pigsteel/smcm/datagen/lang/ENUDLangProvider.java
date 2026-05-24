package com.github.pigsteel.smcm.datagen.lang;

import com.github.pigsteel.smcm.registry.smcm$EntityType;
import com.github.pigsteel.smcm.registry.smcm$Items;
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
        translationBuilder.add(smcm$EntityType.BRUISER, TankIllagerName);
        translationBuilder.add(smcm$EntityType.ENCHANTER, EnchanterName);
        translationBuilder.add(smcm$EntityType.FROSTBITTEN, FrozenZombieName);
        translationBuilder.add(smcm$EntityType.RECLAIMED, JungleZombieName);

        translationBuilder.add(smcm$Items.BRUISER_SPAWN_EGG, eggName(TankIllagerName));
        translationBuilder.add(smcm$Items.ENCHANTER_SPAWN_EGG, eggName(EnchanterName));
        translationBuilder.add(smcm$Items.FROSTBITTEN_SPAWN_EGG, eggName(FrozenZombieName));
        translationBuilder.add(smcm$Items.RECLAIMED_SPAWN_EGG, eggName(JungleZombieName));

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
