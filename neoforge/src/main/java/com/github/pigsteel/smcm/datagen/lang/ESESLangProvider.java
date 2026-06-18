package com.github.pigsteel.smcm.datagen.lang;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.registry.smcm$EntityTypes;
import com.github.pigsteel.smcm.registry.smcm$Items;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

import static com.github.pigsteel.smcm.datagen.lang.LangUtils.s;

public class ESESLangProvider extends LanguageProvider implements SMCMLangProviderVariables, SMCMOptionalUncapitalizedLangProviderVariables, SMCMSpanishHelper {
    public ESESLangProvider(PackOutput output) {
        super(output, SMCM.MOD_ID, "es_es");
    }

    @Override
    protected void addTranslations() {
        addEntityType(smcm$EntityTypes.BRUISER, TankIllagerName());
        addEntityType(smcm$EntityTypes.ENCHANTER, EnchanterName());
        addEntityType(smcm$EntityTypes.FROSTBITTEN, FrozenZombieName());
        addEntityType(smcm$EntityTypes.RECLAIMED, JungleZombieName());
        addEntityType(smcm$EntityTypes.SUNKEN, SunkenSkeletonName());

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

        add("subtitles.smcm.entity.parrot.imitate.frostbitten", ParrotImitates() + FrozenZombieIndefinite());
        add("subtitles.smcm.entity.parrot.imitate.reclaimed", ParrotImitates() + JungleZombieIndefinite());
        add("subtitles.smcm.entity.parrot.imitate.enchanter", ParrotImitates() + EnchanterIndefinite());
    }

    private String eggName(String name) {
        return "Generar " + name;
    }

    @Override
    public String TankIllagerName() {
        return "Brutalizador";
    }

    @Override
    public String EnchanterName() {
        return "Heichicero";
    }

    @Override
    public String FrozenZombieName() {
        return "Escarchado";
    }

    @Override
    public String JungleZombieName() {
        return "Devorado";
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
        return "Loro imita a ";
    }

    @Override
    public String FrozenZombieAmbientVerb() {
        return " gime";
    }

    @Override
    public String EnchanterAmbientVerb() {
        return " murmura";
    }

    @Override
    public String JungleZombieAmbientVerb() {
        return " gime";
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
        return " muere";
    }

    @Override
    public String HurtsVerb() {
        return " herido";
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

    @Override
    public String TankIllagerIndefinite() {
        return MasculineArticle + TankIllagerNameS();
    }

    @Override
    public String EnchanterIndefinite() {
        return MasculineArticle + EnchanterNameS();
    }

    @Override
    public String FrozenZombieIndefinite() {
        return MasculineArticle + FrozenZombieNameS();
    }

    @Override
    public String JungleZombieIndefinite() {
        return MasculineArticle + JungleZombieNameS();
    }

    @Override
    public String SunkenSkeletonIndefinite() {
        return "";
    }

    @Override
    public String MossySkeletonIndefinite() {
        return "";
    }
}
