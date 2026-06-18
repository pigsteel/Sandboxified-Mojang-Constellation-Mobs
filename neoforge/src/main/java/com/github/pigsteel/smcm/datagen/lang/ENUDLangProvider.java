package com.github.pigsteel.smcm.datagen.lang;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.registry.smcm$EntityTypes;
import com.github.pigsteel.smcm.registry.smcm$Items;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ENUDLangProvider extends LanguageProvider implements SMCMLangProviderVariables {
    // Translator: https://www.flipyourtext.com/

    public ENUDLangProvider(PackOutput output) {
        super(output, SMCM.MOD_ID, "en_ud");
    }

    @Override
    protected void addTranslations() {
        addEntityType(smcm$EntityTypes.BRUISER, TankIllagerName());
        addEntityType(smcm$EntityTypes.ENCHANTER, EnchanterName());
        addEntityType(smcm$EntityTypes.FROSTBITTEN, FrozenZombieName());
        addEntityType(smcm$EntityTypes.RECLAIMED, JungleZombieName());
        addEntityType(smcm$EntityTypes.SUNKEN, SunkenSkeletonName());

        addItem(smcm$Items.BRUISER_SPAWN_EGG, eggName(TankIllagerName()));
        addItem(smcm$Items.ENCHANTER_SPAWN_EGG, eggName(EnchanterName()));
        addItem(smcm$Items.FROSTBITTEN_SPAWN_EGG, eggName(FrozenZombieName()));
        addItem(smcm$Items.RECLAIMED_SPAWN_EGG, eggName(JungleZombieName()));
        add(smcm$Items.SUNKEN_SPAWN_EGG.get(), eggName(SunkenSkeletonName()));

        add("subtitles.smcm.entity.frostbitten.ambient", FrozenZombieAmbientVerb() + FrozenZombieName());
        add("subtitles.smcm.entity.frostbitten.hurt", HurtsVerb() + FrozenZombieName());
        add("subtitles.smcm.entity.frostbitten.death", DiesVerb() + FrozenZombieName());

        add("subtitles.smcm.entity.reclaimed.ambient", JungleZombieAmbientVerb() + JungleZombieName());
        add("subtitles.smcm.entity.reclaimed.hurt", HurtsVerb() + JungleZombieName());
        add("subtitles.smcm.entity.reclaimed.death", DiesVerb() + JungleZombieName());

        add("subtitles.smcm.entity.enchanter.ambient", EnchanterAmbientVerb() + EnchanterName());
        add("subtitles.smcm.entity.enchanter.hurt", HurtsVerb() + EnchanterName());
        add("subtitles.smcm.entity.enchanter.death", DiesVerb() + EnchanterName());

        add("subtitles.smcm.entity.parrot.imitate.frostbitten", FrozenZombieAmbientVerb() + ParrotImitates());
        add("subtitles.smcm.entity.parrot.imitate.reclaimed", JungleZombieAmbientVerb() + ParrotImitates());
        add("subtitles.smcm.entity.parrot.imitate.enchanter", EnchanterAmbientVerb() + ParrotImitates());
    }

    private String eggName(String name) {
        return "ɓɓƎ uʍɐdS " + name;
    }

    @Override
    public String TankIllagerName() {
        return "ɹǝsᴉnɹᗺ";
    }

    @Override
    public String EnchanterName() {
        return "ɹǝʇuɐɥɔuƎ";
    }

    @Override
    public String FrozenZombieName() {
        return "uǝʇʇᴉqʇsoɹℲ";
    }

    @Override
    public String JungleZombieName() {
        return "pǝɯᴉɐlɔǝᴚ";
    }

    @Override
    public String SunkenSkeletonName() {
        return "uǝʞunS";
    }

    @Override
    public String MossySkeletonName() {
        return "ʇsoꓶ";
    }

    @Override
    public String ParrotImitates() {
        return "ʇoɹɹɐԀ";
    }

    @Override
    public String FrozenZombieAmbientVerb() {
        return "suɐoɯ ";
    }

    @Override
    public String EnchanterAmbientVerb() {
        return "sǝlqɹnq ";
    }

    @Override
    public String JungleZombieAmbientVerb() {
        return "suɐoɹɓ ";
    }

    @Override
    public String BruiserAmbientVerb() {
        return "";
    }

    @Override
    public String SunkenSkeletonAmbientVerb() {
        return "sǝʅʇʇɐɹ ";
    }

    @Override
    public String MossySkeletonAmbientVerb() {
        return "sǝʅʇʇɐɹ ";
    }

    @Override
    public String DiesVerb() {
        return "sǝᴉp ";
    }

    @Override
    public String HurtsVerb() {
        return "sʇɹnɥ ";
    }
}
