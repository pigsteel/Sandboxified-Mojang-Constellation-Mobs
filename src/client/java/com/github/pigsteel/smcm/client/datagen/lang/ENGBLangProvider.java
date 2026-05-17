package com.github.pigsteel.smcm.client.datagen.lang;

import com.github.pigsteel.smcm.registry.EntityTypeRegistry;
import com.github.pigsteel.smcm.registry.ItemRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class ENGBLangProvider extends FabricLanguageProvider {
    private final String TankIllagerName = "Bruiser";
    private final String EnchanterName = "Enchanter";
    private final String FrozenZombieName = "Frostbitten";
    private final String JungleZombieName = "Reclaimed";
    private final String DiesVerb = " dies";
    private final String HurtsVerb = " hurts";

    public ENGBLangProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(packOutput, "en_gb", registryLookup);
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
