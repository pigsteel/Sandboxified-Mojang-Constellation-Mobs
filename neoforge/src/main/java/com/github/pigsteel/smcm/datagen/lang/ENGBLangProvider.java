package com.github.pigsteel.smcm.datagen.lang;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.registry.smcm$EntityType;
import com.github.pigsteel.smcm.registry.smcm$Items;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ENGBLangProvider extends LanguageProvider {
    private final String TankIllagerName = "Bruiser";
    private final String EnchanterName = "Enchanter";
    private final String FrozenZombieName = "Frostbitten";
    private final String JungleZombieName = "Reclaimed";
    private final String DiesVerb = " dies";
    private final String HurtsVerb = " hurts";

    public ENGBLangProvider(PackOutput output) {
        super(output, SMCM.MOD_ID, "en_gb");
    }

    @Override
    protected void addTranslations() {
        addEntityType(smcm$EntityType.BRUISER, TankIllagerName);
        addEntityType(smcm$EntityType.ENCHANTER, EnchanterName);
        addEntityType(smcm$EntityType.FROSTBITTEN, FrozenZombieName);
        addEntityType(smcm$EntityType.RECLAIMED, JungleZombieName);

        addItem(smcm$Items.BRUISER_SPAWN_EGG, eggName(TankIllagerName));
        addItem(smcm$Items.ENCHANTER_SPAWN_EGG, eggName(EnchanterName));
        addItem(smcm$Items.FROSTBITTEN_SPAWN_EGG, eggName(FrozenZombieName));
        addItem(smcm$Items.RECLAIMED_SPAWN_EGG, eggName(JungleZombieName));

        add("subtitles.smcm.entity.frostbitten.ambient", FrozenZombieName + " moans");
        add("subtitles.smcm.entity.frostbitten.hurt", FrozenZombieName + HurtsVerb);
        add("subtitles.smcm.entity.frostbitten.death", FrozenZombieName + DiesVerb);

        add("subtitles.smcm.entity.reclaimed.ambient", JungleZombieName + " groans");
        add("subtitles.smcm.entity.reclaimed.hurt", JungleZombieName + HurtsVerb);
        add("subtitles.smcm.entity.reclaimed.death", JungleZombieName + DiesVerb);

        add("subtitles.smcm.entity.enchanter.ambient", EnchanterName + " burbles");
        add("subtitles.smcm.entity.enchanter.hurt", EnchanterName + HurtsVerb);
        add("subtitles.smcm.entity.enchanter.death", EnchanterName + DiesVerb);
    }

    private String eggName(String name) {
        return name + " Spawn Egg";
    }
}