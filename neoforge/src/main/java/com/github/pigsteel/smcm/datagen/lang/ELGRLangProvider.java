package com.github.pigsteel.smcm.datagen.lang;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.registry.smcm$EntityType;
import com.github.pigsteel.smcm.registry.smcm$Items;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ELGRLangProvider extends LanguageProvider {
    private final String TankIllagerName = "Γομάρι";
    private final String EnchanterName = "Γητευτής";
    private final String FrozenZombieName = "Παγωμένος";
    private final String JungleZombieName = "Ανακτημένος";
    private final String DiesVerb = " πεθαίνει";
    private final String HurtsVerb = " τραυματίζεται";

    public ELGRLangProvider(PackOutput output) {
        super(output, SMCM.MOD_ID, "el_gr");
    }

    @Override
    protected void addTranslations() {
        add(smcm$EntityType.BRUISER.get(), TankIllagerName);
        add(smcm$EntityType.ENCHANTER.get(), EnchanterName);
        add(smcm$EntityType.FROSTBITTEN.get(), FrozenZombieName);
        add(smcm$EntityType.RECLAIMED.get(), JungleZombieName);

        add(smcm$Items.BRUISER_SPAWN_EGG.get(), eggName(TankIllagerName));
        add(smcm$Items.ENCHANTER_SPAWN_EGG.get(), eggName(EnchanterName));
        add(smcm$Items.FROSTBITTEN_SPAWN_EGG.get(), eggName(FrozenZombieName));
        add(smcm$Items.RECLAIMED_SPAWN_EGG.get(), eggName(JungleZombieName));

        add("subtitles.smcm.entity.frostbitten.ambient", FrozenZombieName + " βογκάει");
        add("subtitles.smcm.entity.frostbitten.hurt", FrozenZombieName + HurtsVerb);
        add("subtitles.smcm.entity.frostbitten.death", FrozenZombieName + DiesVerb);

        add("subtitles.smcm.entity.reclaimed.ambient", JungleZombieName + " βογκάει");
        add("subtitles.smcm.entity.reclaimed.hurt", JungleZombieName + HurtsVerb);
        add("subtitles.smcm.entity.reclaimed.death", JungleZombieName + DiesVerb);

        add("subtitles.smcm.entity.enchanter.ambient", EnchanterName + " μουρμουρίζει");
        add("subtitles.smcm.entity.enchanter.hurt", EnchanterName + HurtsVerb);
        add("subtitles.smcm.entity.enchanter.death", EnchanterName + DiesVerb);
    }

    private String eggName(String name) {
        return "Αβγό Γέννησης " + name;
    }
}
