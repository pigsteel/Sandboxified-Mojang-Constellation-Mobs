package com.github.pigsteel.smcm.registry;

import com.github.pigsteel.smcm.entity.Necromancer;
import com.github.pigsteel.smcm.entity.illager.Bruiser;
import com.github.pigsteel.smcm.entity.illager.Enchanter;
import com.github.pigsteel.smcm.entity.skeleton.Lost;
import com.github.pigsteel.smcm.entity.skeleton.Sunken;
import com.github.pigsteel.smcm.entity.zombie.Frostbitten;
import com.github.pigsteel.smcm.entity.zombie.Reclaimed;
import com.github.pigsteel.smcm.services.Services;

public final class Attributes {
    private Attributes() {}

    public static void load() {
        Services.ATTRIBUTES.registerEntityAttributes(smcm$EntityTypes.BRUISER, Bruiser::createAttributes);
        Services.ATTRIBUTES.registerEntityAttributes(smcm$EntityTypes.ENCHANTER, Enchanter::createAttributes);
        Services.ATTRIBUTES.registerEntityAttributes(smcm$EntityTypes.FROSTBITTEN, Frostbitten::createAttributes);
        Services.ATTRIBUTES.registerEntityAttributes(smcm$EntityTypes.RECLAIMED, Reclaimed::createAttributes);
        Services.ATTRIBUTES.registerEntityAttributes(smcm$EntityTypes.SUNKEN, Sunken::createAttributes);
        Services.ATTRIBUTES.registerEntityAttributes(smcm$EntityTypes.LOST, Lost::createAttributes);
        Services.ATTRIBUTES.registerEntityAttributes(smcm$EntityTypes.NECROMANCER, Necromancer::createAttributes);
    }
}
