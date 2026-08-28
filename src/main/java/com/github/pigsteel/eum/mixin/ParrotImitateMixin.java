package com.github.pigsteel.eum.mixin;

import com.github.pigsteel.eum.core.EUMEntityTypes;
import com.github.pigsteel.eum.core.EUMSoundEvents;
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
    private static void eum$injectMimics(EntityType<?> id, CallbackInfoReturnable<SoundEvent> cir) {
        if (!MOB_SOUND_MAP.containsKey(EUMEntityTypes.FROSTBITTEN.get())) {
            Map<EntityType<?>, SoundEvent> map = new HashMap<>(MOB_SOUND_MAP);

            map.put(EUMEntityTypes.FROSTBITTEN.get(), EUMSoundEvents.PARROT_IMITATE_FROSTBITTEN.get());
            map.put(EUMEntityTypes.RECLAIMED.get(), EUMSoundEvents.PARROT_IMITATE_RECLAIMED.get());
            map.put(EUMEntityTypes.ENCHANTER.get(), EUMSoundEvents.PARROT_IMITATE_ENCHANTER.get());
            map.put(EUMEntityTypes.SUNKEN.get(), EUMSoundEvents.PARROT_IMITATE_SUNKEN.get());
            map.put(EUMEntityTypes.LOST.get(), EUMSoundEvents.PARROT_IMITATE_LOST.get());
            map.put(EUMEntityTypes.NECROMANCER.get(), EUMSoundEvents.PARROT_IMITATE_NECROMANCER.get());
			map.put(EUMEntityTypes.VILER_WITCH.get(), EUMSoundEvents.PARROT_IMITATE_VILER_WITCH.get());

            MOB_SOUND_MAP = map;
        }
    }
	*///?}
}
