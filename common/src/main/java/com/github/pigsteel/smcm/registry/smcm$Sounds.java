package com.github.pigsteel.smcm.registry;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.services.Services;
import com.github.pigsteel.smcm.services.util.RegistryHandle;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

public class smcm$Sounds {
    public static final RegistryHandle<SoundEvent> FROSTBITTEN_AMBIENT = Services.REGISTRY.registerSoundEvent("entity.frostbitten.ambient");
    public static final RegistryHandle<SoundEvent> FROSTBITTEN_HURT = Services.REGISTRY.registerSoundEvent("entity.frostbitten.hurt");
    public static final RegistryHandle<SoundEvent> FROSTBITTEN_DEATH = Services.REGISTRY.registerSoundEvent("entity.frostbitten.death");
    public static final RegistryHandle<SoundEvent> RECLAIMED_AMBIENT = Services.REGISTRY.registerSoundEvent("entity.reclaimed.ambient");
    public static final RegistryHandle<SoundEvent> RECLAIMED_DEATH = Services.REGISTRY.registerSoundEvent("entity.reclaimed.death");
    public static final RegistryHandle<SoundEvent> RECLAIMED_HURT = Services.REGISTRY.registerSoundEvent("entity.reclaimed.hurt");
    public static final RegistryHandle<SoundEvent> RECLAIMED_STEP = Services.REGISTRY.registerSoundEvent("entity.reclaimed.step");
    public static final RegistryHandle<SoundEvent> RECLAIMED_SHEAR = Services.REGISTRY.registerSoundEvent("entity.reclaimed.shear");
    public static final RegistryHandle<SoundEvent> RECLAIMED_BONEMEAL = Services.REGISTRY.registerSoundEvent("entity.reclaimed.bone_meal");
    public static final RegistryHandle<SoundEvent> RECLAIMED_SPIT = Services.REGISTRY.registerSoundEvent("entity.reclaimed.spit");
    public static final RegistryHandle<SoundEvent> ENCHANTER_AMBIENT = Services.REGISTRY.registerSoundEvent("entity.enchanter.ambient");
    public static final RegistryHandle<SoundEvent> ENCHANTER_DEATH = Services.REGISTRY.registerSoundEvent("entity.enchanter.death");
    public static final RegistryHandle<SoundEvent> ENCHANTER_HURT = Services.REGISTRY.registerSoundEvent("entity.enchanter.hurt");
    public static final RegistryHandle<SoundEvent> PARROT_IMITATE_FROSTBITTEN = Services.REGISTRY.registerSoundEvent("entity.parrot.imitate.frostbitten");
    public static final RegistryHandle<SoundEvent> PARROT_IMITATE_RECLAIMED = Services.REGISTRY.registerSoundEvent("entity.parrot.imitate.reclaimed");
    public static final RegistryHandle<SoundEvent> PARROT_IMITATE_ENCHANTER = Services.REGISTRY.registerSoundEvent("entity.parrot.imitate.enchanter");

    private static SoundEvent register(String id) {
        SoundEvent event = SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath(SMCM.MOD_ID, id));
        Registry.register(BuiltInRegistries.SOUND_EVENT, event.location(), event);
        return event;
    }

    public static void load() {
        SMCM.LOGGER.debug("Sounds for SMCM!");
    }
}
