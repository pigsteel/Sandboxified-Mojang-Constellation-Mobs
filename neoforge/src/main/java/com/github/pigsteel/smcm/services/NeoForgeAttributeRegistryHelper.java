package com.github.pigsteel.smcm.services;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class NeoForgeAttributeRegistryHelper implements IAttributeRegistryHelper {
    private final List<AttributeEntry<?>> attributes = new ArrayList<>();

    @Override
    public <T extends LivingEntity> void registerEntityAttributes(EntityType<T> entityType, Supplier<AttributeSupplier.Builder> builderSupplier) {
        this.attributes.add(new AttributeEntry<> (entityType, builderSupplier));
    }

    @Override
    public void applyEntityAttributeRegistrations(EntityAttributeRegistrar registrar) {
        for (AttributeEntry<?> attribute : this.attributes) {
            attribute.register(registrar);
        }
    }

    private record AttributeEntry<T extends LivingEntity>(EntityType<T> entityType, Supplier<AttributeSupplier.Builder> builderSupplier) {
        private void register(EntityAttributeRegistrar registrar) {
            registrar.register(this.entityType, this.builderSupplier.get());
        }
    }
}
