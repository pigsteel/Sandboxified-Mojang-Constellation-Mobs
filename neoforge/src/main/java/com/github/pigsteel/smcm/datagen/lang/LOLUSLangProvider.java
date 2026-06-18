package com.github.pigsteel.smcm.datagen.lang;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.registry.smcm$EntityTypes;
import com.github.pigsteel.smcm.registry.smcm$Items;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class LOLUSLangProvider extends LanguageProvider {
    private final String TankIllagerName = "Brewsur";
    private final String EnchanterName = "Enchentor";
    private final String FrozenZombieName = "Frosbidin";
    private final String JungleZombieName = "Recleimd";
    private final String SunkenSkeletonName = "Sucken";
    private final String DiesVerb = " ded";
    private final String HurtsVerb = " hurz";

    public LOLUSLangProvider(PackOutput output) {
        super(output, SMCM.MOD_ID, "lol_us");
    }

    @Override
    protected void addTranslations() {
        addEntityType(smcm$EntityTypes.BRUISER, TankIllagerName);
        addEntityType(smcm$EntityTypes.ENCHANTER, EnchanterName);
        addEntityType(smcm$EntityTypes.FROSTBITTEN, FrozenZombieName);
        addEntityType(smcm$EntityTypes.RECLAIMED, JungleZombieName);
        addEntityType(smcm$EntityTypes.SUNKEN, SunkenSkeletonName);

        addItem(smcm$Items.BRUISER_SPAWN_EGG, eggName(TankIllagerName));
        addItem(smcm$Items.ENCHANTER_SPAWN_EGG, eggName(EnchanterName));
        addItem(smcm$Items.FROSTBITTEN_SPAWN_EGG, eggName(FrozenZombieName));
        addItem(smcm$Items.RECLAIMED_SPAWN_EGG, eggName(JungleZombieName));
        addItem(smcm$Items.SUNKEN_SPAWN_EGG, eggName(SunkenSkeletonName));

        add("subtitles.smcm.entity.frostbitten.ambient", "Cold Hooman moonz");
        add("subtitles.smcm.entity.frostbitten.hurt", "Cold Hooman" + HurtsVerb);
        add("subtitles.smcm.entity.frostbitten.death", "Cold Hooman" + DiesVerb);

        add("subtitles.smcm.entity.reclaimed.ambient", "plant thang groonz");
        add("subtitles.smcm.entity.reclaimed.hurt", "plant thang" + HurtsVerb);
        add("subtitles.smcm.entity.reclaimed.death", "plant thang" + DiesVerb);

        add("subtitles.smcm.entity.enchanter.ambient", "megic dood mumblz");
        add("subtitles.smcm.entity.enchanter.hurt", "megic dood" + HurtsVerb);
        add("subtitles.smcm.entity.enchanter.death", "megic dood gets rekt");

        add("subtitles.smcm.entity.parrot.imitate.frostbitten", "Rainbow Bird moonz");
        add("subtitles.smcm.entity.parrot.imitate.reclaimed", "Rainbow Bird groonz");
        add("subtitles.smcm.entity.parrot.imitate.enchanter", "Purrot boorblez");
    }

    private String eggName(String name) {
        return name + " spon ec";
    }
}
