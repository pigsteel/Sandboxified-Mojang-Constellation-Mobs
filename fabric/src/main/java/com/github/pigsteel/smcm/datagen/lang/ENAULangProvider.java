package com.github.pigsteel.smcm.datagen.lang;

import com.github.pigsteel.smcm.registry.smcm$EntityType;
import com.github.pigsteel.smcm.registry.smcm$Items;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class ENAULangProvider extends FabricLanguageProvider {
    private final String TankIllagerName = "Bruiser";
    private final String EnchanterName = "Enchanter";
    private final String FrozenZombieName = "Frostbitten";
    private final String JungleZombieName = "Reclaimed";
    private final String DiesVerb = " dies";
    private final String HurtsVerb = " hurts";

    public ENAULangProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(packOutput, "en_au", registryLookup);
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

        translationBuilder.add("subtitles.smcm.entity.frostbitten.ambient", FrozenZombieName + " moans");
        translationBuilder.add("subtitles.smcm.entity.frostbitten.hurt", FrozenZombieName + HurtsVerb);
        translationBuilder.add("subtitles.smcm.entity.frostbitten.death", FrozenZombieName + DiesVerb);

        translationBuilder.add("subtitles.smcm.entity.reclaimed.ambient", JungleZombieName + " groans");
        translationBuilder.add("subtitles.smcm.entity.reclaimed.hurt", JungleZombieName + HurtsVerb);
        translationBuilder.add("subtitles.smcm.entity.reclaimed.death", JungleZombieName + DiesVerb);

        translationBuilder.add("subtitles.smcm.entity.enchanter.ambient", EnchanterName + " burbles");
        translationBuilder.add("subtitles.smcm.entity.enchanter.hurt", EnchanterName + HurtsVerb);
        translationBuilder.add("subtitles.smcm.entity.enchanter.death", EnchanterName + DiesVerb);
    }

    private String eggName(String name) { // GENITIVE!
        return name + " Spawn Egg";
    }
}