package com.github.pigsteel.smcm.datagen.lang;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.registry.smcm$EntityType;
import com.github.pigsteel.smcm.registry.smcm$Items;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ENUDLangProvider extends LanguageProvider {
    private final String TankIllagerName = "ɹǝsᴉnɹᗺ";
    private final String EnchanterName = "ɹǝʇuɐɥɔuƎ";
    private final String FrozenZombieName = "uǝʇʇᴉqʇsoɹℲ";
    private final String JungleZombieName = "pǝɯᴉɐlɔǝᴚ";
    private final String DiesVerb = "sǝᴉp ";
    private final String HurtsVerb = "sʇɹnɥ ";

    public ENUDLangProvider(PackOutput output) {
        super(output, SMCM.MOD_ID, "en_ud");
    }

    @Override
    protected void addTranslations() {
        addEntityType(smcm$EntityType.BRUISER, TankIllagerName);
        addEntityType(smcm$EntityType.ENCHANTER, EnchanterName);
        addEntityType(smcm$EntityType.FROSTBITTEN, FrozenZombieName);
        addEntityType(smcm$EntityType.RECLAIMED, JungleZombieName);

        addItem(smcm$Items.BRUISER_SPAWN_EGG, eggName(TankIllagerName));
        addItem(smcm$Items.ENCHANTER_SPAWN_EGG, eggName(EnchanterName));
        addItem(smcm$Items.FROSTBITTEN_SPAWN_EGG, eggName(FrozenZombieName));
        addItem(smcm$Items.RECLAIMED_SPAWN_EGG, eggName(JungleZombieName));

        add("subtitles.smcm.entity.frostbitten.ambient", "suɐoɯ " + FrozenZombieName);
        add("subtitles.smcm.entity.frostbitten.hurt", HurtsVerb + FrozenZombieName);
        add("subtitles.smcm.entity.frostbitten.death", DiesVerb + FrozenZombieName);

        add("subtitles.smcm.entity.reclaimed.ambient", "suɐoɹɓ " + JungleZombieName);
        add("subtitles.smcm.entity.reclaimed.hurt", HurtsVerb + JungleZombieName);
        add("subtitles.smcm.entity.reclaimed.death", DiesVerb + JungleZombieName);

        add("subtitles.smcm.entity.enchanter.ambient", "sǝlqɹnq " + EnchanterName);
        add("subtitles.smcm.entity.enchanter.hurt", HurtsVerb + EnchanterName);
        add("subtitles.smcm.entity.enchanter.death", DiesVerb + EnchanterName);
    }

    private String eggName(String name) {
        return "ɓɓƎ uʍɐdS " + name;
    }
}
