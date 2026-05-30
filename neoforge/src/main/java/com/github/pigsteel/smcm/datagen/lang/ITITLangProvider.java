package com.github.pigsteel.smcm.datagen.lang;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.registry.smcm$EntityType;
import com.github.pigsteel.smcm.registry.smcm$Items;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

import static com.github.pigsteel.smcm.datagen.lang.LangUtils.s;

public class ITITLangProvider extends LanguageProvider implements SMCMLangProviderVariables, SMCMOptionalUncapitalizedLangProviderVariables {
    public ITITLangProvider(PackOutput output) {
        super(output, SMCM.MOD_ID, "it_it");
    }

    @Override
    protected void addTranslations() {
        addEntityType(smcm$EntityType.BRUISER, TankIllagerName());
        addEntityType(smcm$EntityType.ENCHANTER, EnchanterName());
        addEntityType(smcm$EntityType.FROSTBITTEN, FrozenZombieName());
        addEntityType(smcm$EntityType.RECLAIMED, JungleZombieName());
        addEntityType(smcm$EntityType.SUNKEN, SunkenSkeletonName());

        addItem(smcm$Items.BRUISER_SPAWN_EGG, eggName(TankIllagerNameS()));
        addItem(smcm$Items.ENCHANTER_SPAWN_EGG, eggName(EnchanterNameS()));
        addItem(smcm$Items.FROSTBITTEN_SPAWN_EGG, eggName(FrozenZombieNameS()));
        addItem(smcm$Items.RECLAIMED_SPAWN_EGG, eggName(JungleZombieNameS()));
        addItem(smcm$Items.SUNKEN_SPAWN_EGG, eggName(SunkenSkeletonNameS()));


        add("subtitles.smcm.entity.frostbitten.ambient", FrozenZombieName() + FrozenZombieAmbientVerb());
        add("subtitles.smcm.entity.frostbitten.hurt", FrozenZombieName() + HurtsVerb());
        add("subtitles.smcm.entity.frostbitten.death", FrozenZombieName() + DiesVerb());

        add("subtitles.smcm.entity.reclaimed.ambient", JungleZombieName() + JungleZombieAmbientVerb());
        add("subtitles.smcm.entity.reclaimed.hurt", JungleZombieName() + HurtsVerb());
        add("subtitles.smcm.entity.reclaimed.death", JungleZombieName() + DiesVerb());

        add("subtitles.smcm.entity.enchanter.ambient", EnchanterName() + EnchanterAmbientVerb());
        add("subtitles.smcm.entity.enchanter.hurt", EnchanterName() + HurtsVerb());
        add("subtitles.smcm.entity.enchanter.death", EnchanterName() + DiesVerb());

        add("subtitles.smcm.entity.parrot.imitate.frostbitten", ParrotImitates() + FrozenZombieNameS());
        add("subtitles.smcm.entity.parrot.imitate.reclaimed", ParrotImitates() + JungleZombieNameS());
        add("subtitles.smcm.entity.parrot.imitate.enchanter", ParrotImitates() + EnchanterNameS());
    }

    private String eggName(String name) {
        return "Uovo generatore di " + name;
    }

    @Override
    public String TankIllagerName() {
        return "Picchiatore";
    }

    @Override
    public String EnchanterName() {
        return "Incantatore";
    }

    @Override
    public String FrozenZombieName() {
        return "Congelato";
    }

    @Override
    public String JungleZombieName() {
        return "Infestato";
    }

    @Override
    public String SunkenSkeletonName() {
        return "";
    }

    @Override
    public String MossySkeletonName() {
        return "";
    }

    @Override
    public String ParrotImitates() {
        return "Pappagallo imita ";
    }

    @Override
    public String FrozenZombieAmbientVerb() {
        return " geme";
    }

    @Override
    public String EnchanterAmbientVerb() {
        return " gorgoglia";
    }

    @Override
    public String JungleZombieAmbientVerb() {
        return " geme";
    }

    @Override
    public String BruiserAmbientVerb() {
        return "";
    }

    @Override
    public String SunkenSkeletonAmbientVerb() {
        return "";
    }

    @Override
    public String MossySkeletonAmbientVerb() {
        return "";
    }

    @Override
    public String DiesVerb() {
        return " muore";
    }

    @Override
    public String HurtsVerb() {
        return " ferito";
    }

    @Override
    public String TankIllagerNameS() {
        return s(TankIllagerName());
    }

    @Override
    public String EnchanterNameS() {
        return s(EnchanterName());
    }

    @Override
    public String FrozenZombieNameS() {
        return s(FrozenZombieName());
    }

    @Override
    public String JungleZombieNameS() {
        return s(JungleZombieName());
    }

    @Override
    public String SunkenSkeletonNameS() {
        return s(SunkenSkeletonName());
    }
}