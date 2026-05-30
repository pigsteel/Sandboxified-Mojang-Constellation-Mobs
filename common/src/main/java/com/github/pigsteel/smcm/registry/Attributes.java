package com.github.pigsteel.smcm.registry;

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
        Services.ATTRIBUTES.registerEntityAttributes(smcm$EntityType.BRUISER, Bruiser::createAttributes);
        Services.ATTRIBUTES.registerEntityAttributes(smcm$EntityType.ENCHANTER, Enchanter::createAttributes);
        Services.ATTRIBUTES.registerEntityAttributes(smcm$EntityType.FROSTBITTEN, Frostbitten::createAttributes);
        Services.ATTRIBUTES.registerEntityAttributes(smcm$EntityType.RECLAIMED, Reclaimed::createAttributes);
        Services.ATTRIBUTES.registerEntityAttributes(smcm$EntityType.SUNKEN, Sunken::createAttributes);
        Services.ATTRIBUTES.registerEntityAttributes(smcm$EntityType.LOST, Lost::createAttributes);
    }
}
