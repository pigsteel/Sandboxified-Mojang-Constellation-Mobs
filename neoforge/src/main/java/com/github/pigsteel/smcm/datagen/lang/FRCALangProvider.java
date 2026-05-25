package com.github.pigsteel.smcm.datagen.lang;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.registry.smcm$EntityType;
import com.github.pigsteel.smcm.registry.smcm$Items;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

import static com.github.pigsteel.smcm.datagen.lang.LangUtils.FrenchMasculineOrShort;
import static com.github.pigsteel.smcm.datagen.lang.LangUtils.s;

public class FRCALangProvider extends LanguageProvider implements SMCMLangProviderVariables, SMCMOptionalUncapitalizedLangProviderVariables, SMCMFrenchHelper {
    public FRCALangProvider(PackOutput output) {
        super(output, SMCM.MOD_ID, "fr_ca");
    }

    @Override
    protected void addTranslations() {
        addEntityType(smcm$EntityType.BRUISER, TankIllagerName());
        addEntityType(smcm$EntityType.ENCHANTER, EnchanterName());
        addEntityType(smcm$EntityType.FROSTBITTEN, FrozenZombieName());
        addEntityType(smcm$EntityType.RECLAIMED, JungleZombieName());

        addItem(smcm$Items.BRUISER_SPAWN_EGG, eggName("de " + TankIllagerNameS()));
        addItem(smcm$Items.ENCHANTER_SPAWN_EGG, eggName("d'" + EnchanterNameS()));
        addItem(smcm$Items.FROSTBITTEN_SPAWN_EGG, eggName("de " + FrozenZombieNameS()));
        addItem(smcm$Items.RECLAIMED_SPAWN_EGG, eggName("de " + JungleZombieNameS()));

        add("subtitles.smcm.entity.frostbitten.ambient", FrozenZombieAmbientVerb() + FrozenZombieNameS());
        add("subtitles.smcm.entity.frostbitten.hurt", FrozenZombieName() + HurtsVerb());
        add("subtitles.smcm.entity.frostbitten.death", FrozenZombieName() + DiesVerb());

        add("subtitles.smcm.entity.reclaimed.ambient", JungleZombieAmbientVerb());
        add("subtitles.smcm.entity.reclaimed.hurt", JungleZombieName() + HurtsVerb());
        add("subtitles.smcm.entity.reclaimed.death", JungleZombieName() + DiesVerb());

        add("subtitles.smcm.entity.enchanter.ambient", EnchanterAmbientVerb() + EnchanterNameS());
        add("subtitles.smcm.entity.enchanter.hurt", EnchanterName() + HurtsVerb());
        add("subtitles.smcm.entity.enchanter.death", EnchanterName() + DiesVerb());

        add("subtitles.smcm.entity.parrot.imitate.frostbitten", ParrotName() + FrozenZombieDefinite());
        add("subtitles.smcm.entity.parrot.imitate.reclaimed", ParrotName() + JungleZombieDefinite());
        add("subtitles.smcm.entity.parrot.imitate.enchanter", ParrotName() + EnchanterDefinite());
    }

    private String eggName(String name) {
        return "Oeuf spawneur " + name;
    }

    @Override
    public String TankIllagerName() {
        return "Brutalisateur";
    }

    @Override
    public String EnchanterName() {
        return "Enchanteur";
    }

    @Override
    public String FrozenZombieName() {
        return "Gelé";
    }

    @Override
    public String JungleZombieName() {
        return "Réclamé";
    }

    @Override
    public String SunkenSkeletonName() {
        return "Englouti";
    }

    @Override
    public String MossySkeletonName() {
        return "Perdu";
    }

    @Override
    public String ParrotName() {
        return "Perroquet imitant ";
    }

    @Override
    public String FrozenZombieAmbientVerb() {
        return "Gémissement de ";
    }

    @Override
    public String EnchanterAmbientVerb() {
        return "Marmonnement d'";
    }

    @Override
    public String JungleZombieAmbientVerb() {
        return "Grognement de réclamé";
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
        return " qui meurt";
    }

    @Override
    public String HurtsVerb() {
        return " blessé";
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
    public String TankIllagerDefinite() {
        return FrenchMasculineOrShort(TankIllagerNameS());
    }

    @Override
    public String EnchanterDefinite() {
        return FrenchMasculineOrShort(EnchanterNameS());
    }

    @Override
    public String FrozenZombieDefinite() {
        return FrenchMasculineOrShort(FrozenZombieNameS());
    }

    @Override
    public String JungleZombieDefinite() {
        return FrenchMasculineOrShort(JungleZombieNameS());
    }

    @Override
    public String SunkenSkeletonDefinite() {
        return FrenchMasculineOrShort(SunkenSkeletonName()); // idk man TODO
    }

    @Override
    public String MossySkeletonDefinite() {
        return FrenchMasculineOrShort(MossySkeletonName()); // perhaps? TODO
    }
}
