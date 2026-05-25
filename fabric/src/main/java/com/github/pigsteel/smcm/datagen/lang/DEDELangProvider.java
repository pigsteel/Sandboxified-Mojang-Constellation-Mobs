package com.github.pigsteel.smcm.datagen.lang;

import com.github.pigsteel.smcm.registry.smcm$EntityType;
import com.github.pigsteel.smcm.registry.smcm$Items;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class DEDELangProvider extends FabricLanguageProvider {
    private final String TankIllagerName = "Hart-Schläger";
    private final String EnchanterName = "Zauberer";
    private final String FrozenZombieName = "Erfroren";
    private final String JungleZombieName = "Begrünten";
    private final String DiesVerb = " stirbt";
    private final String HurtsVerb = " nimmt Schaden";

    public DEDELangProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(packOutput, "de_de", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider provider, TranslationBuilder translationBuilder) {
        translationBuilder.add(smcm$EntityType.BRUISER.get(), TankIllagerName);
        translationBuilder.add(smcm$EntityType.ENCHANTER.get(), EnchanterName);
        translationBuilder.add(smcm$EntityType.FROSTBITTEN.get(), FrozenZombieName);
        translationBuilder.add(smcm$EntityType.RECLAIMED.get(), JungleZombieName);

        translationBuilder.add(smcm$Items.BRUISER_SPAWN_EGG, eggName("Hart-Schläger")); // Special genitive!
        translationBuilder.add(smcm$Items.ENCHANTER_SPAWN_EGG, eggName("Zauberer"));
        translationBuilder.add(smcm$Items.FROSTBITTEN_SPAWN_EGG, eggName("Erfrorenen"));
        translationBuilder.add(smcm$Items.RECLAIMED_SPAWN_EGG, eggName("Begrünten"));

        translationBuilder.add("subtitles.smcm.entity.frostbitten.ambient", FrozenZombieName + " stöhnt");
        translationBuilder.add("subtitles.smcm.entity.frostbitten.hurt", FrozenZombieName + HurtsVerb);
        translationBuilder.add("subtitles.smcm.entity.frostbitten.death", FrozenZombieName + DiesVerb);

        translationBuilder.add("subtitles.smcm.entity.reclaimed.ambient", JungleZombieName + " stöhnt");
        translationBuilder.add("subtitles.smcm.entity.reclaimed.hurt", JungleZombieName + HurtsVerb);
        translationBuilder.add("subtitles.smcm.entity.reclaimed.death", JungleZombieName + DiesVerb);

        translationBuilder.add("subtitles.smcm.entity.enchanter.ambient", EnchanterName + " plappert");
        translationBuilder.add("subtitles.smcm.entity.enchanter.hurt", EnchanterName + HurtsVerb);
        translationBuilder.add("subtitles.smcm.entity.enchanter.death", EnchanterName + DiesVerb);
    }

    private String eggName(String name) { // GENITIVE!
        return name + "-Spawn-Ei";
    }
}