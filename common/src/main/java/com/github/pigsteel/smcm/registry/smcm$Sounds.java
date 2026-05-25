package com.github.pigsteel.smcm.registry;

import com.github.pigsteel.smcm.SMCM;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

public class smcm$Sounds {
    public static final SoundEvent FROSTBITTEN_AMBIENT = register("entity.frostbitten.ambient");
    public static final SoundEvent FROSTBITTEN_HURT = register("entity.frostbitten.hurt");
    public static final SoundEvent FROSTBITTEN_DEATH = register("entity.frostbitten.death");
    public static final SoundEvent RECLAIMED_AMBIENT = register("entity.reclaimed.ambient");
    public static final SoundEvent RECLAIMED_DEATH = register("entity.reclaimed.death");
    public static final SoundEvent RECLAIMED_HURT = register("entity.reclaimed.hurt");
    public static final SoundEvent RECLAIMED_STEP = register("entity.reclaimed.step");
    public static final SoundEvent RECLAIMED_SHEAR = register("entity.reclaimed.shear");
    public static final SoundEvent RECLAIMED_BONEMEAL = register("entity.reclaimed.bone_meal");
    public static final SoundEvent RECLAIMED_SPIT = register("entity.reclaimed.spit");
    public static final SoundEvent ENCHANTER_AMBIENT = register("entity.enchanter.ambient");
    public static final SoundEvent ENCHANTER_DEATH = register("entity.enchanter.death");
    public static final SoundEvent ENCHANTER_HURT = register("entity.enchanter.hurt");
    public static final SoundEvent PARROT_IMITATE_FROSTBITTEN = register("entity.parrot.imitate.frostbitten");
    public static final SoundEvent PARROT_IMITATE_RECLAIMED = register("entity.parrot.imitate.reclaimed");

    private static SoundEvent register(String id) {
        SoundEvent event = SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath(SMCM.MOD_ID, id));
        Registry.register(BuiltInRegistries.SOUND_EVENT, event.location(), event);
        return event;
    }

    public static void init() {
        SMCM.LOGGER.debug("Sounds for SMCM!");
    }
}
