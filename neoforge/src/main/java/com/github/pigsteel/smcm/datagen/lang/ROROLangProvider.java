package com.github.pigsteel.smcm.datagen.lang;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.registry.smcm$EntityTypes;
import com.github.pigsteel.smcm.registry.smcm$Items;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

import static com.github.pigsteel.smcm.datagen.lang.LangUtils.s;

public class ROROLangProvider extends LanguageProvider implements SMCMLangProviderVariables, SMCMOptionalUncapitalizedLangProviderVariables, SMCMRomanianHelper {
    public ROROLangProvider(PackOutput output) {
        super(output, SMCM.MOD_ID, "ro_ro");
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
    }

    private String eggName(String name) {
        return "Ou generator de " + name;
    }

    @Override
    public String TankIllagerName() {
        return "Vânător";
    }

    @Override
    public String EnchanterName() {
        return "Vrăjitor";
    }

    @Override
    public String FrozenZombieName() {
        return "Degerat";
    }

    @Override
    public String JungleZombieName() {
        return "Revendicat";
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
        return "Papagalul imitâ";
    }

    @Override
    public String FrozenZombieAmbientVerb() {
        return "ul geme";
    }

    @Override
    public String EnchanterAmbientVerb() {
        return "ul gâlgâie";
    }

    @Override
    public String JungleZombieAmbientVerb() {
        return "ul geme";
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
        return "ul este rănit";
    }

    @Override
    public String HurtsVerb() {
        return "ul moare";
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
        return MasculineArticle + TankIllagerNameS();
    }

    @Override
    public String FrozenZombieIndefinite() {
        return MasculineArticle + TankIllagerNameS();
    }

    @Override
    public String JungleZombieIndefinite() {
        return MasculineArticle + TankIllagerNameS();
    }

    @Override
    public String SunkenSkeletonIndefinite() {
        return MasculineArticle + TankIllagerNameS();
    }

    @Override
    public String MossySkeletonIndefinite() {
        return MasculineArticle + TankIllagerNameS();
    }
}
