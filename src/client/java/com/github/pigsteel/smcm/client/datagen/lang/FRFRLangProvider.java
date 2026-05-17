package com.github.pigsteel.smcm.client.datagen.lang;

import com.github.pigsteel.smcm.registry.EntityTypeRegistry;
import com.github.pigsteel.smcm.registry.ItemRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

import static com.github.pigsteel.smcm.client.datagen.lang.LangUtils.s;

public class FRFRLangProvider extends FabricLanguageProvider {
    private final String TankIllagerName = "Brutalisateur";
    private final String EnchanterName = "Enchanteur";
    private final String FrozenZombieName = "Gelé";
    private final String JungleZombieName = "Réclamé";
    private final String TankIllagerNameS = s(TankIllagerName);
    private final String EnchanterNameS = s(EnchanterName);
    private final String FrozenZombieNameS = s(FrozenZombieName);
    private final String JungleZombieNameS = s(JungleZombieName);
    private final String DiesVerb = " qui meurt";
    private final String HurtsVerb = " blessé";

    public FRFRLangProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(packOutput, "fr_fr", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider provider, TranslationBuilder translationBuilder) {
        translationBuilder.add(EntityTypeRegistry.BRUISER, TankIllagerName);
        translationBuilder.add(EntityTypeRegistry.ENCHANTER, EnchanterName);
        translationBuilder.add(EntityTypeRegistry.FROSTBITTEN, FrozenZombieName);
        translationBuilder.add(EntityTypeRegistry.RECLAIMED, JungleZombieName);

        translationBuilder.add(ItemRegistry.BRUISER_SPAWN_EGG, eggName("de " + TankIllagerNameS));
        translationBuilder.add(ItemRegistry.ENCHANTER_SPAWN_EGG, eggName("d'" + EnchanterNameS));
        translationBuilder.add(ItemRegistry.FROSTBITTEN_SPAWN_EGG, eggName("de " + FrozenZombieNameS));
        translationBuilder.add(ItemRegistry.RECLAIMED_SPAWN_EGG, eggName("de " + JungleZombieNameS));

        translationBuilder.add("subtitles.smcm.entity.frostbitten.ambient", "Gémissement de " + FrozenZombieNameS);
        translationBuilder.add("subtitles.smcm.entity.frostbitten.hurt", FrozenZombieName + HurtsVerb);
        translationBuilder.add("subtitles.smcm.entity.frostbitten.death", FrozenZombieName + DiesVerb);

        translationBuilder.add("subtitles.smcm.entity.reclaimed.ambient", "Grognement de " + JungleZombieNameS);
        translationBuilder.add("subtitles.smcm.entity.reclaimed.hurt", JungleZombieName + HurtsVerb);
        translationBuilder.add("subtitles.smcm.entity.reclaimed.death", JungleZombieName + DiesVerb);

        translationBuilder.add("subtitles.smcm.entity.enchanter.ambient", "Murmure d'" + EnchanterNameS);
        translationBuilder.add("subtitles.smcm.entity.enchanter.hurt", EnchanterName + HurtsVerb);
        translationBuilder.add("subtitles.smcm.entity.enchanter.death", EnchanterName + DiesVerb);
    }

    private String eggName(String name) { // HAVE TO CONJUGATE "DE"
        return "Oeuf d'apparition " + name;
    }
}