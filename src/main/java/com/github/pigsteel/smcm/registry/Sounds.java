package com.github.pigsteel.smcm.registry;

import com.github.pigsteel.smcm.SMCM;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricSoundsProvider;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

public class Sounds {
    public static final SoundEvent FROSTBITTEN_AMBIENT = register("entity.frostbitten.ambient");
    public static final SoundEvent FROSTBITTEN_HURT = register("entity.frostbitten.hurt");
    public static final SoundEvent FROSTBITTEN_DEATH = register("entity.frostbitten.death");
    public static final SoundEvent RECLAIMED_AMBIENT = register("entity.reclaimed.ambient");
    public static final SoundEvent RECLAIMED_DEATH = register("entity.reclaimed.death");
    public static final SoundEvent RECLAIMED_HURT = register("entity.reclaimed.hurt");
    public static final SoundEvent RECLAIMED_STEP = register("entity.reclaimed.step");
    public static final SoundEvent ENCHANTER_AMBIENT = register("entity.enchanter.ambient");
    public static final SoundEvent ENCHANTER_DEATH = register("entity.enchanter.death");
    public static final SoundEvent ENCHANTER_HURT = register("entity.enchanter.hurt");

    private static SoundEvent register(String id) {
        SoundEvent event = SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath(SMCM.MOD_ID, id));
        Registry.register(BuiltInRegistries.SOUND_EVENT, event.location(), event);
        return event;
    }

    public static void init() {
        SMCM.LOGGER.debug("Sounds for SMCM!");
    }
}
