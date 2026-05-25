package com.github.pigsteel.smcm.services.client;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

import java.util.function.Supplier;

public interface IClientRegistryHelper {
    <T extends Entity> void registerEntityRenderer(EntityType<T> entityType, EntityRendererProvider<T> provider);
    void registerModelLayer(ModelLayerLocation location, Supplier<LayerDefinition> supplier);

    void registerArmorSetModelLayer(ArmorModelSet<ModelLayerLocation> location, Supplier<LayerDefinition> supplier);

    void applyEntityRendererRegistrations(EntityRendererRegistrar registrar);

    void applyModelLayerRegistrations(ModelLayerRegistrar registrar);

    void applyArmorSetModelLayerRegistrations(ModelLayerRegistrar registrar);

    interface EntityRendererRegistrar {
        <T extends Entity> void register(EntityType<T> entityType, EntityRendererProvider<T> provider);
    }

    interface ModelLayerRegistrar {
        void register(ModelLayerLocation location, Supplier<LayerDefinition> supplier);

        void registerArmorSet(ArmorModelSet<ModelLayerLocation> location, Supplier<LayerDefinition> supplier);
    }

    /*
    private static ArmorModelSet<ModelLayerLocation> registerArmorSet(final String modelId) {
        return new ArmorModelSet<>(register(modelId, "helmet"), register(modelId, "chestplate"), register(modelId, "leggings"), register(modelId, "boots"));
    }

     */
}
