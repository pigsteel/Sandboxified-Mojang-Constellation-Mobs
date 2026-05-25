package com.github.pigsteel.smcm.datagen.lang;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.registry.smcm$EntityType;
import com.github.pigsteel.smcm.registry.smcm$Items;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class DEDELangProvider extends LanguageProvider {
    private final String TankIllagerName = "Hart-Schläger";
    private final String EnchanterName = "Zauberer";
    private final String FrozenZombieName = "Erfroren";
    private final String JungleZombieName = "Begrünten";
    private final String DiesVerb = " stirbt";
    private final String HurtsVerb = " nimmt Schaden";

    public DEDELangProvider(PackOutput output) {
        super(output, SMCM.MOD_ID, "de_de");
    }

    @Override
    protected void addTranslations() {
        add(smcm$EntityType.BRUISER.get(), TankIllagerName);
        add(smcm$EntityType.ENCHANTER.get(), EnchanterName);
        add(smcm$EntityType.FROSTBITTEN.get(), FrozenZombieName);
        add(smcm$EntityType.RECLAIMED.get(), JungleZombieName);

        add(smcm$Items.BRUISER_SPAWN_EGG.get(), eggName("Hart-Schläger"));
        add(smcm$Items.ENCHANTER_SPAWN_EGG.get(), eggName("Zauberer"));
        add(smcm$Items.FROSTBITTEN_SPAWN_EGG.get(), eggName("Erfrorenen"));
        add(smcm$Items.RECLAIMED_SPAWN_EGG.get(), eggName("Begrünten"));

        add("subtitles.smcm.entity.frostbitten.ambient", FrozenZombieName + " stöhnt");
        add("subtitles.smcm.entity.frostbitten.hurt", FrozenZombieName + HurtsVerb);
        add("subtitles.smcm.entity.frostbitten.death", FrozenZombieName + DiesVerb);

        add("subtitles.smcm.entity.reclaimed.ambient", JungleZombieName + " stöhnt");
        add("subtitles.smcm.entity.reclaimed.hurt", JungleZombieName + HurtsVerb);
        add("subtitles.smcm.entity.reclaimed.death", JungleZombieName + DiesVerb);

        add("subtitles.smcm.entity.enchanter.ambient", EnchanterName + " plappert");
        add("subtitles.smcm.entity.enchanter.hurt", EnchanterName + HurtsVerb);
        add("subtitles.smcm.entity.enchanter.death", EnchanterName + DiesVerb);
    }

    private String eggName(String name) {
        return name + "-Spawn-Ei";
    }
}