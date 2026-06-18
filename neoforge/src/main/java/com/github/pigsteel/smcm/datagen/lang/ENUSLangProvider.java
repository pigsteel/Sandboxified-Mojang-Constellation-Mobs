package com.github.pigsteel.smcm.datagen.lang;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.registry.smcm$EntityTypes;
import com.github.pigsteel.smcm.registry.smcm$Items;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ENUSLangProvider extends LanguageProvider implements SMCMLangProviderVariables {

    public ENUSLangProvider(PackOutput output) {
        super(output, SMCM.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        addEntityType(smcm$EntityTypes.BRUISER, TankIllagerName());
        addEntityType(smcm$EntityTypes.ENCHANTER, EnchanterName());
        addEntityType(smcm$EntityTypes.FROSTBITTEN, FrozenZombieName());
        addEntityType(smcm$EntityTypes.RECLAIMED, JungleZombieName());
        addEntityType(smcm$EntityTypes.SUNKEN, SunkenSkeletonName());
        addEntityType(smcm$EntityTypes.LOST, MossySkeletonName());
        addEntityType(smcm$EntityTypes.NECROMANCER, NecromancerName());

        addItem(smcm$Items.BRUISER_SPAWN_EGG, eggName(TankIllagerName()));
        addItem(smcm$Items.ENCHANTER_SPAWN_EGG, eggName(EnchanterName()));
        addItem(smcm$Items.FROSTBITTEN_SPAWN_EGG, eggName(FrozenZombieName()));
        addItem(smcm$Items.RECLAIMED_SPAWN_EGG, eggName(JungleZombieName()));
        addItem(smcm$Items.SUNKEN_SPAWN_EGG, eggName(SunkenSkeletonName()));
        addItem(smcm$Items.LOST_SPAWN_EGG, eggName(MossySkeletonName()));
        addItem(smcm$Items.NECROMANCER_SPAWN_EGG, eggName(NecromancerName()));

        add("subtitles.smcm.entity.bruiser.ambient", BruiserAmbientVerb());
        add("subtitles.smcm.entity.bruiser.hurt", HurtsVerb());
        add("subtitles.smcm.entity.bruiser.death", DiesVerb());

        add("subtitles.smcm.entity.enchanter.ambient", EnchanterName() + EnchanterAmbientVerb());
        add("subtitles.smcm.entity.enchanter.hurt", EnchanterName() + HurtsVerb());
        add("subtitles.smcm.entity.enchanter.death", EnchanterName() + DiesVerb());

        add("subtitles.smcm.entity.frostbitten.ambient", FrozenZombieName() + FrozenZombieAmbientVerb());
        add("subtitles.smcm.entity.frostbitten.hurt", FrozenZombieName() + HurtsVerb());
        add("subtitles.smcm.entity.frostbitten.death", FrozenZombieName() + DiesVerb());
        add("subtitles.smcm.entity.zombie.converted_to_frostbitten", FrozenZombieName() + " forms");

        add("subtitles.smcm.entity.reclaimed.ambient", JungleZombieName() + JungleZombieAmbientVerb());
        add("subtitles.smcm.entity.reclaimed.hurt", JungleZombieName() + HurtsVerb());
        add("subtitles.smcm.entity.reclaimed.death", JungleZombieName() + DiesVerb());
        add("subtitles.smcm.entity.reclaimed.spit", JungleZombieName() + " spits");

        add("subtitles.smcm.entity.sunken.ambient", SunkenSkeletonName() + SunkenSkeletonAmbientVerb());
        add("subtitles.smcm.entity.sunken.hurt", SunkenSkeletonName() + HurtsVerb());
        add("subtitles.smcm.entity.sunken.death", SunkenSkeletonName() + DiesVerb());
        add("subtitles.smcm.entity.skeleton.converted_to_sunken", SunkenSkeletonName() + " forms");

        add("subtitles.smcm.entity.lost.ambient", MossySkeletonName() + MossySkeletonAmbientVerb());
        add("subtitles.smcm.entity.lost.hurt", MossySkeletonName() + HurtsVerb());
        add("subtitles.smcm.entity.lost.death", MossySkeletonName() + DiesVerb());

        add("subtitles.smcm.entity.necromancer.ambient", NecromancerName() + NecromancerAmbientVerb());
        add("subtitles.smcm.entity.necromancer.hurt", NecromancerName() + HurtsVerb());
        add("subtitles.smcm.entity.necromancer.death", NecromancerName() + DiesVerb());
        add("subtitles.smcm.entity.necromancer.laugh", NecromancerName() + NecromancerLaughsVerb());

        add("subtitles.smcm.entity.parrot.imitate.frostbitten", ParrotImitates() + FrozenZombieAmbientVerb());
        add("subtitles.smcm.entity.parrot.imitate.reclaimed", ParrotImitates() + JungleZombieAmbientVerb());
        add("subtitles.smcm.entity.parrot.imitate.enchanter", ParrotImitates() + EnchanterAmbientVerb());
        add("subtitles.smcm.entity.parrot.imitate.necromancer", ParrotImitates() + NecromancerLaughsVerb());
        add("subtitles.smcm.entity.parrot.imitate.lost", ParrotImitates() + MossySkeletonAmbientVerb());
        add("subtitles.smcm.entity.parrot.imitate.sunken", ParrotImitates() + SunkenSkeletonAmbientVerb());
    }

    private String eggName(String name) {
        return name + " Spawn Egg";
    }

    @Override
    public String TankIllagerName() {
        return "Bruiser";
    }

    @Override
    public String EnchanterName() {
        return "Enchanter";
    }

    @Override
    public String FrozenZombieName() {
        return "Frostbitten";
    }

    @Override
    public String JungleZombieName() {
        return "Reclaimed";
    }

    @Override
    public String SunkenSkeletonName() {
        return "Sunken";
    }

    @Override
    public String MossySkeletonName() {
        return "Lost";
    }

    public String NecromancerName() {
        return "Necromancer";
    }

    @Override
    public String ParrotImitates() {
        return "Parrot";
    }

    @Override
    public String FrozenZombieAmbientVerb() {
        return " moans";
    }

    @Override
    public String EnchanterAmbientVerb() {
        return " burbles";
    }

    @Override
    public String JungleZombieAmbientVerb() {
        return " groans";
    }

    @Override
    public String BruiserAmbientVerb() {
        return " grunts";
    }

    @Override
    public String SunkenSkeletonAmbientVerb() {
        return " rattles";
    }

    @Override
    public String MossySkeletonAmbientVerb() {
        return " shifts";
    }

    public String NecromancerLaughsVerb() { return " cackles"; }

    public String NecromancerAmbientVerb() { return " clatters"; }

    @Override
    public String DiesVerb() {
        return " dies";
    }

    @Override
    public String HurtsVerb() {
        return " hurts";
    }
}
