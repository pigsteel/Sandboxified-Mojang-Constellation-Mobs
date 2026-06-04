package com.github.pigsteel.smcm.registry;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.services.Services;
import com.github.pigsteel.smcm.services.util.RegistryHandle;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

public class smcm$SoundEvents {
    public static final RegistryHandle<SoundEvent> FROSTBITTEN_AMBIENT = Services.REGISTRY.registerSoundEvent("entity.frostbitten.ambient");
    public static final RegistryHandle<SoundEvent> FROSTBITTEN_HURT = Services.REGISTRY.registerSoundEvent("entity.frostbitten.hurt");
    public static final RegistryHandle<SoundEvent> FROSTBITTEN_DEATH = Services.REGISTRY.registerSoundEvent("entity.frostbitten.death");
    public static final RegistryHandle<SoundEvent> FROSTBITTEN_SHOOT = Services.REGISTRY.registerSoundEvent("entity.frostbitten.shoot");
    public static final RegistryHandle<SoundEvent> ZOMBIE_CONVERTED_TO_FROSTBITTEN = Services.REGISTRY.registerSoundEvent("entity.zombie.converted_to_frostbitten");
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
    public static final RegistryHandle<SoundEvent> NECROMANCER_AMBIENT = Services.REGISTRY.registerSoundEvent("entity.necromancer.ambient");
    public static final RegistryHandle<SoundEvent> NECROMANCER_DEATH = Services.REGISTRY.registerSoundEvent("entity.necromancer.death");
    public static final RegistryHandle<SoundEvent> NECROMANCER_HURT = Services.REGISTRY.registerSoundEvent("entity.necromancer.hurt");
    public static final RegistryHandle<SoundEvent> NECROMANCER_STEP = Services.REGISTRY.registerSoundEvent("entity.necromancer.step");
    public static final RegistryHandle<SoundEvent> NECROMANCER_LAUGH = Services.REGISTRY.registerSoundEvent("entity.necromancer.laugh");
    public static final RegistryHandle<SoundEvent> PARROT_IMITATE_FROSTBITTEN = Services.REGISTRY.registerSoundEvent("entity.parrot.imitate.frostbitten");
    public static final RegistryHandle<SoundEvent> PARROT_IMITATE_RECLAIMED = Services.REGISTRY.registerSoundEvent("entity.parrot.imitate.reclaimed");
    public static final RegistryHandle<SoundEvent> PARROT_IMITATE_ENCHANTER = Services.REGISTRY.registerSoundEvent("entity.parrot.imitate.enchanter");
    public static final RegistryHandle<SoundEvent> PARROT_IMITATE_NECROMANCER = Services.REGISTRY.registerSoundEvent("entity.parrot.imitate.necromancer");
    public static final RegistryHandle<SoundEvent> PARROT_IMITATE_LOST = Services.REGISTRY.registerSoundEvent("entity.parrot.imitate.lost");
    public static final RegistryHandle<SoundEvent> PARROT_IMITATE_SUNKEN = Services.REGISTRY.registerSoundEvent("entity.parrot.imitate.sunken");
    public static final RegistryHandle<SoundEvent> LOST_AMBIENT =
            Services.REGISTRY.registerSoundEvent("entity.lost.ambient");
    public static final RegistryHandle<SoundEvent> LOST_DEATH =
            Services.REGISTRY.registerSoundEvent("entity.lost.death");
    public static final RegistryHandle<SoundEvent> LOST_HURT =
            Services.REGISTRY.registerSoundEvent("entity.lost.hurt");
    public static final RegistryHandle<SoundEvent> LOST_STEP =
            Services.REGISTRY.registerSoundEvent("entity.lost.step");

    public static final RegistryHandle<SoundEvent> SUNKEN_AMBIENT =
            Services.REGISTRY.registerSoundEvent("entity.sunken.ambient");
    public static final RegistryHandle<SoundEvent> SUNKEN_DEATH =
            Services.REGISTRY.registerSoundEvent("entity.sunken.death");
    public static final RegistryHandle<SoundEvent> SUNKEN_HURT =
            Services.REGISTRY.registerSoundEvent("entity.sunken.hurt");
    public static final RegistryHandle<SoundEvent> SUNKEN_STEP =
            Services.REGISTRY.registerSoundEvent("entity.sunken.step");

    public static final RegistryHandle<SoundEvent> SKELETON_CONVERTED_TO_SUNKEN =
            Services.REGISTRY.registerSoundEvent("entity.skeleton.converted_to_sunken");

    public static void load() {
        SMCM.LOGGER.debug("Sounds for SMCM!");
    }
}
