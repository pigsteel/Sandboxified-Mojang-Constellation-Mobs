package com.github.pigsteel.smcm.datagen.lang;

import com.github.pigsteel.smcm.registry.smcm$EntityType;
import com.github.pigsteel.smcm.registry.smcm$Items;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

import static com.github.pigsteel.smcm.datagen.lang.LangUtils.*;

public class ESESLangProvider extends FabricLanguageProvider {
    private final String TankIllagerName = "Brutalizador";
    private final String EnchanterName = "Heichicero";
    private final String FrozenZombieName = "Escarchado";
    private final String JungleZombieName = "Devorado";
    private final String TankIllagerNameS = s(TankIllagerName);
    private final String EnchanterNameS = s(EnchanterName);
    private final String FrozenZombieNameS = s(FrozenZombieName);
    private final String JungleZombieNameS = s(JungleZombieName);
    private final String DiesVerb = " muere";
    private final String HurtsVerb = " herido";

    public ESESLangProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(packOutput, "es_es", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider provider, TranslationBuilder translationBuilder) {
        translationBuilder.add(smcm$EntityType.BRUISER, TankIllagerName);
        translationBuilder.add(smcm$EntityType.ENCHANTER, EnchanterName);
        translationBuilder.add(smcm$EntityType.FROSTBITTEN, FrozenZombieName);
        translationBuilder.add(smcm$EntityType.RECLAIMED, JungleZombieName);

        translationBuilder.add(smcm$Items.BRUISER_SPAWN_EGG, eggName(TankIllagerNameS));
        translationBuilder.add(smcm$Items.ENCHANTER_SPAWN_EGG, eggName(EnchanterNameS));
        translationBuilder.add(smcm$Items.FROSTBITTEN_SPAWN_EGG, eggName(FrozenZombieNameS));
        translationBuilder.add(smcm$Items.RECLAIMED_SPAWN_EGG, eggName(JungleZombieNameS));

        translationBuilder.add("subtitles.smcm.entity.frostbitten.ambient", FrozenZombieName + " gime");
        translationBuilder.add("subtitles.smcm.entity.frostbitten.hurt", FrozenZombieName + HurtsVerb);
        translationBuilder.add("subtitles.smcm.entity.frostbitten.death", FrozenZombieName + DiesVerb);

        translationBuilder.add("subtitles.smcm.entity.reclaimed.ambient", JungleZombieName + " gime");
        translationBuilder.add("subtitles.smcm.entity.reclaimed.hurt", JungleZombieName + HurtsVerb);
        translationBuilder.add("subtitles.smcm.entity.reclaimed.death", JungleZombieName + DiesVerb);

        translationBuilder.add("subtitles.smcm.entity.enchanter.ambient", EnchanterName + " murmura");
        translationBuilder.add("subtitles.smcm.entity.enchanter.hurt", EnchanterName + HurtsVerb);
        translationBuilder.add("subtitles.smcm.entity.enchanter.death", EnchanterName + DiesVerb);
    }

    private String eggName(String name) { // GENITIVE!
        return "Generar " + name;
    }
}
