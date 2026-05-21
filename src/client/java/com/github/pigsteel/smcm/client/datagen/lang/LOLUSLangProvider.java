package com.github.pigsteel.smcm.client.datagen.lang;

import com.github.pigsteel.smcm.registry.EntityTypeRegistry;
import com.github.pigsteel.smcm.registry.ItemRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class LOLUSLangProvider extends FabricLanguageProvider {
    private final String TankIllagerName = "Brewsur";
    private final String EnchanterName = "Enchentor";
    private final String FrozenZombieName = "Frosbidin";
    private final String JungleZombieName = "Recleimd";
    private final String DiesVerb = " ded";
    private final String HurtsVerb = " hurz";

    public LOLUSLangProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(packOutput, "lol_us", registryLookup);
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

        translationBuilder.add("subtitles.smcm.entity.frostbitten.ambient", "Cold Hooman moonz");
        translationBuilder.add("subtitles.smcm.entity.frostbitten.hurt", "Cold Hooman" + HurtsVerb);
        translationBuilder.add("subtitles.smcm.entity.frostbitten.death", "Cold Hooman" + DiesVerb);

        translationBuilder.add("subtitles.smcm.entity.reclaimed.ambient", "plant thang groonz");
        translationBuilder.add("subtitles.smcm.entity.reclaimed.hurt", "plant thang" + HurtsVerb);
        translationBuilder.add("subtitles.smcm.entity.reclaimed.death", "plant thang" + DiesVerb);

        translationBuilder.add("subtitles.smcm.entity.enchanter.ambient",  "megic dood mumblz");
        translationBuilder.add("subtitles.smcm.entity.enchanter.hurt", "megic dood" + HurtsVerb);
        translationBuilder.add("subtitles.smcm.entity.enchanter.death", "megic dood gets rekt");
    }

    private String eggName(String name) { // GENITIVE!
        return name + " spon ec";
    }
}
