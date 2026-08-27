package com.github.pigsteel.smcm.platform.fabric;

//? fabric {

/*import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.core.particles.CustomSimpleParticleType;
import com.github.pigsteel.smcm.platform.Platform;
import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityDataRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.item.Item;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class FabricPlatform implements Platform {

	@Override
	public boolean isModLoaded(String modId) {
		return FabricLoader.getInstance().isModLoaded(modId);
	}

	@Override
	public ModLoader loader() {
		return ModLoader.FABRIC;
	}

	@Override
	public String mcVersion() {
		return FabricLoader.getInstance().getRawGameVersion();
	}

	@Override
	public boolean isDevelopmentEnvironment() {
		return FabricLoader.getInstance().isDevelopmentEnvironment();
	}

	@Override
	public <T> Supplier<DataComponentType<T>> register(String id, UnaryOperator<DataComponentType.Builder<T>> builder) {
		DataComponentType<T> type = builder
				.apply(DataComponentType.<T>builder())
				.build();
		DataComponentType<T> dataComponentType = Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, SMCM.id(id), type);
		return () -> dataComponentType;
	}

	@Override
	public <T extends LivingEntity> void register(Supplier<EntityType<T>> entityType, Supplier<AttributeSupplier.Builder> supplier) {
		FabricDefaultAttributeRegistry.register(entityType.get(), supplier.get());
	}

	@Override
	public void register(EntityDataSerializer<?> serializer, String name) {
		FabricEntityDataRegistry.register(SMCM.id(name), serializer);
	}

	@Override
	public <T extends Entity> Supplier<EntityType<T>> register(String id, EntityType.Builder<T> builder) {
		ResourceKey<EntityType<?>> key = SMCM.key(Registries.ENTITY_TYPE, id);
		EntityType<T> entity = Registry.register(BuiltInRegistries.ENTITY_TYPE, key, builder.build(key));
		return () -> entity;
	}

	@Override
	public <T extends Item> Supplier<T> register(String name, Function<Item.Properties, T> itemFactory) {
		ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, SMCM.id(name));
		Identifier id = key.identifier();
		T registered = Registry.register(BuiltInRegistries.ITEM, id, itemFactory.apply(new Item.Properties().setId(key)));
		return () -> registered;
	}

	@Override
	public Holder<MobEffect> register(String name, MobEffect mobEffect) {
		return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, SMCM.id(name), mobEffect);
	}

	@Override
	public Supplier<SimpleParticleType> register(String name, boolean overrideLimiter) {
		SimpleParticleType particleType = Registry.register(BuiltInRegistries.PARTICLE_TYPE, SMCM.id(name), new CustomSimpleParticleType(overrideLimiter));
		return () -> particleType;
	}

	@Override
	public Supplier<SoundEvent> registerSoundEvent(String name) {
		Identifier id = SMCM.id(name);
		SoundEvent sound = Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
		return () -> sound;
	}

	@Override
	public void register(ModelLayerLocation modelLayerLocation, Supplier<LayerDefinition> consumer) {
		ModelLayerRegistry.registerModelLayer(modelLayerLocation, consumer::get);
	}

	@Override
	public <U extends Sensor<?>> Supplier<SensorType<U>> register(String name, Supplier<U> factory) {
		SensorType<U> sensorType = Registry.register(BuiltInRegistries.SENSOR_TYPE, SMCM.id(name), new SensorType<>(factory));
		return () -> sensorType;
	}

	@Override
	public <U> Supplier<MemoryModuleType<U>> registerMemoryModuleType(String name, Optional<Codec<U>> maybeCodec) {
		MemoryModuleType<U> memoryModuleType = Registry.register(BuiltInRegistries.MEMORY_MODULE_TYPE, SMCM.id(name), new MemoryModuleType<>(maybeCodec));
		return () -> memoryModuleType;
	}

	@Override
	public <T extends Entity> void register(Supplier<? extends EntityType<? extends T>> type, EntityRendererProvider<T> provider) {
		EntityRenderers.register(type.get(), provider);
	}
}
*///?}
