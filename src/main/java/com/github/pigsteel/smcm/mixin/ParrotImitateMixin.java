package com.github.pigsteel.smcm.mixin;

import com.github.pigsteel.smcm.core.SMCMEntityTypes;
import com.github.pigsteel.smcm.core.SMCMSoundEvents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.parrot.Parrot;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;
import java.util.Map;

@Mixin(Parrot.class)
public abstract class ParrotImitateMixin {
	//? fabric {
    /*@Shadow
    @Final
    @Mutable
    private static Map<EntityType<?>, SoundEvent> MOB_SOUND_MAP;

    @Inject(method = "getImitatedSound", at = @At("HEAD"))
    private static void smcm$injectMimics(EntityType<?> id, CallbackInfoReturnable<SoundEvent> cir) {
        if (!MOB_SOUND_MAP.containsKey(SMCMEntityTypes.FROSTBITTEN.get())) {
            Map<EntityType<?>, SoundEvent> map = new HashMap<>(MOB_SOUND_MAP);

            map.put(SMCMEntityTypes.FROSTBITTEN.get(), SMCMSoundEvents.PARROT_IMITATE_FROSTBITTEN.get());
            map.put(SMCMEntityTypes.RECLAIMED.get(), SMCMSoundEvents.PARROT_IMITATE_RECLAIMED.get());
            map.put(SMCMEntityTypes.ENCHANTER.get(), SMCMSoundEvents.PARROT_IMITATE_ENCHANTER.get());
            map.put(SMCMEntityTypes.SUNKEN.get(), SMCMSoundEvents.PARROT_IMITATE_SUNKEN.get());
            map.put(SMCMEntityTypes.LOST.get(), SMCMSoundEvents.PARROT_IMITATE_LOST.get());
            map.put(SMCMEntityTypes.NECROMANCER.get(), SMCMSoundEvents.PARROT_IMITATE_NECROMANCER.get());
			map.put(SMCMEntityTypes.VILER_WITCH.get(), SMCMSoundEvents.PARROT_IMITATE_VILER_WITCH.get());

            MOB_SOUND_MAP = map;
        }
    }
	*///?}
}
