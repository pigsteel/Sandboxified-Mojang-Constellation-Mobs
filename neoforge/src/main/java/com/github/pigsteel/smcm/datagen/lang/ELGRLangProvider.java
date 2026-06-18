package com.github.pigsteel.smcm.datagen.lang;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.registry.smcm$EntityTypes;
import com.github.pigsteel.smcm.registry.smcm$Items;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ELGRLangProvider extends LanguageProvider implements SMCMLangProviderVariables {
    public ELGRLangProvider(PackOutput output) {
        super(output, SMCM.MOD_ID, "el_gr");
    }

    @Override
    protected void addTranslations() {
        addEntityType(smcm$EntityTypes.BRUISER, TankIllagerName());
        addEntityType(smcm$EntityTypes.ENCHANTER, EnchanterName());
        addEntityType(smcm$EntityTypes.FROSTBITTEN, FrozenZombieName());
        addEntityType(smcm$EntityTypes.RECLAIMED, JungleZombieName());
        addEntityType(smcm$EntityTypes.SUNKEN, SunkenSkeletonName());

        add(smcm$Items.BRUISER_SPAWN_EGG.get(), eggName(TankIllagerName()));
        add(smcm$Items.ENCHANTER_SPAWN_EGG.get(), eggName(EnchanterName()));
        add(smcm$Items.FROSTBITTEN_SPAWN_EGG.get(), eggName(FrozenZombieName()));
        add(smcm$Items.RECLAIMED_SPAWN_EGG.get(), eggName(JungleZombieName()));
        add(smcm$Items.SUNKEN_SPAWN_EGG.get(), eggName(SunkenSkeletonName()));

        add("subtitles.smcm.entity.frostbitten.ambient", FrozenZombieName() + FrozenZombieAmbientVerb());
        add("subtitles.smcm.entity.frostbitten.hurt", FrozenZombieName() + HurtsVerb());
        add("subtitles.smcm.entity.frostbitten.death", FrozenZombieName() + DiesVerb());

        add("subtitles.smcm.entity.reclaimed.ambient", JungleZombieName() + JungleZombieAmbientVerb());
        add("subtitles.smcm.entity.reclaimed.hurt", JungleZombieName() + HurtsVerb());
        add("subtitles.smcm.entity.reclaimed.death", JungleZombieName() + DiesVerb());

        add("subtitles.smcm.entity.enchanter.ambient", EnchanterName() + EnchanterAmbientVerb());
        add("subtitles.smcm.entity.enchanter.hurt", EnchanterName() + HurtsVerb());
        add("subtitles.smcm.entity.enchanter.death", EnchanterName() + DiesVerb());

        add("subtitles.smcm.entity.parrot.imitate.frostbitten", ParrotImitates() + FrozenZombieAmbientVerb());
        add("subtitles.smcm.entity.parrot.imitate.reclaimed", ParrotImitates() + JungleZombieAmbientVerb());
        add("subtitles.smcm.entity.parrot.imitate.enchanter", ParrotImitates() + EnchanterAmbientVerb());
    }

    private String eggName(String name) {
        return "Αβγό Γέννησης " + name;
    }

    @Override
    public String TankIllagerName() {
        return "Γομάρι";
    }

    @Override
    public String EnchanterName() {
        return "Γητευτής";
    }

    @Override
    public String FrozenZombieName() {
        return "Παγωμένος";
    }

    @Override
    public String JungleZombieName() {
        return "Ανακτημένος";
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
        return "Παπαγάλος";
    }

    @Override
    public String FrozenZombieAmbientVerb() {
        return " βογκάει";
    }

    @Override
    public String EnchanterAmbientVerb() {
        return " μουρμουρίζει";
    }

    @Override
    public String JungleZombieAmbientVerb() {
        return " βογκάει";
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
        return " πεθαίνει";
    }

    @Override
    public String HurtsVerb() {
        return " τραυματίζεται";
    }
}
