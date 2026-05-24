package com.github.pigsteel.smcm.registry;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.entity.illager.Bruiser;
import com.github.pigsteel.smcm.entity.illager.Enchanter;
import com.github.pigsteel.smcm.entity.skeleton.Sunken;
import com.github.pigsteel.smcm.entity.zombie.Frostbitten;
import com.github.pigsteel.smcm.entity.zombie.Reclaimed;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public final class NeoForgeEntityType {
    private static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(Registries.ENTITY_TYPE, SMCM.MOD_ID);

    private NeoForgeEntityType() {
    }

    public static void registerModEntityTypes(final IEventBus modBus) {
        smcm$EntityType.registerModEntityTypes(NeoForgeEntityType::register);
        ENTITY_TYPES.register(modBus);
    }

    private static <T extends Entity> Supplier<EntityType<T>> register(
            final String name,
            final EntityType.Builder<T> builder
    ) {
        return ENTITY_TYPES.register(name, () -> builder.build(smcm$EntityType.key(name)));
    }
}
