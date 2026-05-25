package com.github.pigsteel.smcm.datagen.lang;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.registry.smcm$EntityType;
import com.github.pigsteel.smcm.registry.smcm$Items;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

import static com.github.pigsteel.smcm.datagen.lang.LangUtils.s;

public class FRCALangProvider extends LanguageProvider {
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

    public FRCALangProvider(PackOutput output) {
        super(output, SMCM.MOD_ID, "fr_ca");
    }

    @Override
    protected void addTranslations() {
        addEntityType(smcm$EntityType.BRUISER, TankIllagerName);
        addEntityType(smcm$EntityType.ENCHANTER, EnchanterName);
        addEntityType(smcm$EntityType.FROSTBITTEN, FrozenZombieName);
        addEntityType(smcm$EntityType.RECLAIMED, JungleZombieName);

        addItem(smcm$Items.BRUISER_SPAWN_EGG, eggName("de " + TankIllagerNameS));
        addItem(smcm$Items.ENCHANTER_SPAWN_EGG, eggName("d'" + EnchanterNameS));
        addItem(smcm$Items.FROSTBITTEN_SPAWN_EGG, eggName("de " + FrozenZombieNameS));
        addItem(smcm$Items.RECLAIMED_SPAWN_EGG, eggName("de " + JungleZombieNameS));

        add("subtitles.smcm.entity.frostbitten.ambient", "Gémissement de " + FrozenZombieNameS);
        add("subtitles.smcm.entity.frostbitten.hurt", FrozenZombieName + HurtsVerb);
        add("subtitles.smcm.entity.frostbitten.death", FrozenZombieName + DiesVerb);

        add("subtitles.smcm.entity.reclaimed.ambient", "Grognement de réclamé");
        add("subtitles.smcm.entity.reclaimed.hurt", JungleZombieName + HurtsVerb);
        add("subtitles.smcm.entity.reclaimed.death", JungleZombieName + DiesVerb);

        add("subtitles.smcm.entity.enchanter.ambient", "Marmonnement d'" + EnchanterNameS);
        add("subtitles.smcm.entity.enchanter.hurt", EnchanterName + HurtsVerb);
        add("subtitles.smcm.entity.enchanter.death", EnchanterName + DiesVerb);
    }

    private String eggName(String name) {
        return "Oeuf spawneur " + name;
    }
}
