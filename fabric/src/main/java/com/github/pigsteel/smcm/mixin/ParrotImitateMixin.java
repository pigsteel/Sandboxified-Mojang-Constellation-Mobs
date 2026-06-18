package com.github.pigsteel.smcm.mixin;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.registry.smcm$EntityTypes;
import com.github.pigsteel.smcm.registry.smcm$SoundEvents;
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
public class ParrotImitateMixin {

    @Shadow
    @Final
    @Mutable
    private static Map<EntityType<?>, SoundEvent> MOB_SOUND_MAP;

    @Inject(method = "getImitatedSound", at = @At("HEAD"))
    private static void smcm$injectMimics(EntityType<?> id, CallbackInfoReturnable<SoundEvent> cir) {
        if (!MOB_SOUND_MAP.containsKey(smcm$EntityTypes.FROSTBITTEN)) {
            Map<EntityType<?>, SoundEvent> map = new HashMap<>(MOB_SOUND_MAP);

            map.put(smcm$EntityTypes.FROSTBITTEN.get(), smcm$SoundEvents.PARROT_IMITATE_FROSTBITTEN.get());
            map.put(smcm$EntityTypes.RECLAIMED.get(), smcm$SoundEvents.PARROT_IMITATE_RECLAIMED.get());
            map.put(smcm$EntityTypes.ENCHANTER.get(), smcm$SoundEvents.PARROT_IMITATE_ENCHANTER.get());
            map.put(smcm$EntityTypes.SUNKEN.get(), smcm$SoundEvents.PARROT_IMITATE_SUNKEN.get());
            map.put(smcm$EntityTypes.LOST.get(), smcm$SoundEvents.PARROT_IMITATE_LOST.get());
            map.put(smcm$EntityTypes.NECROMANCER.get(), smcm$SoundEvents.PARROT_IMITATE_NECROMANCER.get());

            MOB_SOUND_MAP = map;

            SMCM.LOGGER.info("Registered custom parrot mimics");
        }
    }
}
