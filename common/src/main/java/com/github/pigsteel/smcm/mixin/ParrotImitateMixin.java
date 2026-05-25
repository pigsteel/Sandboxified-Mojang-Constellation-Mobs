package com.github.pigsteel.smcm.mixin;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.registry.smcm$EntityType;
import com.github.pigsteel.smcm.registry.smcm$Sounds;
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
        if (!MOB_SOUND_MAP.containsKey(smcm$EntityType.FROSTBITTEN)) {
            Map<EntityType<?>, SoundEvent> map = new HashMap<>(MOB_SOUND_MAP);

            map.put(smcm$EntityType.FROSTBITTEN.get(), smcm$Sounds.PARROT_IMITATE_FROSTBITTEN);
            map.put(smcm$EntityType.RECLAIMED.get(), smcm$Sounds.PARROT_IMITATE_RECLAIMED);

            MOB_SOUND_MAP = map;

            SMCM.LOGGER.info("Registered custom parrot mimics");
        }
    }
}
