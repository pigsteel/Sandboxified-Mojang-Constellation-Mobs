package com.github.pigsteel.smcm.registry;

import com.github.pigsteel.smcm.entity.illager.Bruiser;
import com.github.pigsteel.smcm.entity.illager.Enchanter;
import com.github.pigsteel.smcm.entity.skeleton.Sunken;
import com.github.pigsteel.smcm.entity.zombie.Frostbitten;
import com.github.pigsteel.smcm.entity.zombie.Reclaimed;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;

import static com.github.pigsteel.smcm.registry.smcm$EntityType.*;

public class FabricEntityEvents {

    public static void registerAttributes() {
        FabricDefaultAttributeRegistry.register(BRUISER, Bruiser.createAttributes());
        FabricDefaultAttributeRegistry.register(FROSTBITTEN, Frostbitten.createAttributes());
        FabricDefaultAttributeRegistry.register(RECLAIMED, Reclaimed.createAttributes());
        FabricDefaultAttributeRegistry.register(ENCHANTER, Enchanter.createAttributes());
        FabricDefaultAttributeRegistry.register(SUNKEN, Sunken.createAttributes());
    }
}
