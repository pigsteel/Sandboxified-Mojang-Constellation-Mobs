package com.github.pigsteel.smcm.datagen.lang;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.registry.smcm$EntityType;
import com.github.pigsteel.smcm.registry.smcm$Items;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

import static com.github.pigsteel.smcm.datagen.lang.LangUtils.s;

public class ESMXLangProvider extends LanguageProvider {
    private final String TankIllagerName = "Brutalizador";
    private final String EnchanterName = "Heichicero";
    private final String FrozenZombieName = "Escarchado";
    private final String JungleZombieName = "Devorado";
    private final String TankIllagerNameS = s(TankIllagerName);
    private final String EnchanterNameS = s(EnchanterName);
    private final String FrozenZombieNameS = s(FrozenZombieName);
    private final String JungleZombieNameS = s(JungleZombieName);
    private final String DiesVerb = " muerto";
    private final String HurtsVerb = " herido";

    public ESMXLangProvider(PackOutput output) {
        super(output, SMCM.MOD_ID, "es_mx");
    }

    @Override
    protected void addTranslations() {
        addEntityType(smcm$EntityType.BRUISER, TankIllagerName);
        addEntityType(smcm$EntityType.ENCHANTER, EnchanterName);
        addEntityType(smcm$EntityType.FROSTBITTEN, FrozenZombieName);
        addEntityType(smcm$EntityType.RECLAIMED, JungleZombieName);

        addItem(smcm$Items.BRUISER_SPAWN_EGG, eggName(TankIllagerNameS));
        addItem(smcm$Items.ENCHANTER_SPAWN_EGG, eggName(EnchanterNameS));
        addItem(smcm$Items.FROSTBITTEN_SPAWN_EGG, eggName(FrozenZombieNameS));
        addItem(smcm$Items.RECLAIMED_SPAWN_EGG, eggName(JungleZombieNameS));

        add("subtitles.smcm.entity.frostbitten.ambient", "Gruñido de " + FrozenZombieNameS);
        add("subtitles.smcm.entity.frostbitten.hurt", FrozenZombieName + HurtsVerb);
        add("subtitles.smcm.entity.frostbitten.death", FrozenZombieName + DiesVerb);

        add("subtitles.smcm.entity.reclaimed.ambient", "Gruñido de " + JungleZombieNameS);
        add("subtitles.smcm.entity.reclaimed.hurt", JungleZombieName + HurtsVerb);
        add("subtitles.smcm.entity.reclaimed.death", JungleZombieName + DiesVerb);

        add("subtitles.smcm.entity.enchanter.ambient", "Murmuro de " + EnchanterNameS);
        add("subtitles.smcm.entity.enchanter.hurt", EnchanterName + HurtsVerb);
        add("subtitles.smcm.entity.enchanter.death", EnchanterName + DiesVerb);
    }

    private String eggName(String name) {
        return "Generar " + name;
    }
}
