package com.github.pigsteel.smcm.platform.neoforge;

//? neoforge {

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.core.particles.CustomSimpleParticleType;
import com.github.pigsteel.smcm.platform.Platform;

import com.mojang.serialization.Codec;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.syncher.EntityDataSerializer;
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
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;


import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import static com.github.pigsteel.smcm.platform.neoforge.NeoforgeVariables.*;

public class NeoforgePlatform implements Platform {

	@Override
	public boolean isModLoaded(String modId) {
		return ModList.get().isLoaded(modId);
	}

	@Override
	public ModLoader loader() {
		return ModLoader.NEOFORGE;
	}

	@Override
	public String mcVersion() {
		return "";
	}

	@Override
	public boolean isDevelopmentEnvironment() {
		return !FMLLoader/*? if > 1.21.7 {*/.getCurrent()/*?}*/.isProduction();
	}

	@Override
	public <T> Supplier<DataComponentType<T>> register(String id, UnaryOperator<DataComponentType.Builder<T>> builder) {
		return DATA_COMPONENTS.registerComponentType(id, builder);
	}

	@Override
	public <T extends LivingEntity> void register(Supplier<EntityType<T>> entityType, Supplier<AttributeSupplier.Builder> supplier) {
		DEFAULT_ATTRIBUTES.add(new NeoforgeVariables.DefaultAttributesDeferred<>(entityType, supplier));
	}

	@Override
	public void register(EntityDataSerializer<?> serializer, String name) {
		ENTITY_DATA_SERIALIZERS.register(name, () -> serializer);
	}

	@Override
	public <T extends Entity> Supplier<EntityType<T>> register(String id, EntityType.Builder<T> builder) {
		ResourceKey<EntityType<?>> key = SMCM.key(Registries.ENTITY_TYPE, id);
		return ENTITIES.register(id, () -> builder.build(key));
	}

	@Override
	public <T extends Item> Supplier<T> register(String name, Function<Item.Properties, T> itemFactory) {
		return ITEMS.registerItem(name, itemFactory);
	}

	@Override
	public Holder<MobEffect> register(String name, MobEffect mobEffect) {
		return MOB_EFFECTS.register(name, () -> mobEffect);
	}

	@Override
	public Supplier<SimpleParticleType> register(String name, boolean overrideLimiter) {
		return PARTICLE_TYPES.register(name, () -> new CustomSimpleParticleType(overrideLimiter));
	}

	@Override
	public Supplier<SoundEvent> registerSoundEvent(String name) {
		return SOUND_EVENTS.register(name, SoundEvent::createVariableRangeEvent);
	}

	@Override
	public void register(ModelLayerLocation modelLayerLocation, Supplier<LayerDefinition> consumer) {
		MODEL_LAYERS.add(new NeoforgeVariables.ModelLayerDeferred(modelLayerLocation, consumer));
	}

	@Override
	public <U extends Sensor<?>> Supplier<SensorType<U>> register(String name, Supplier<U> factory) {
		return SENSOR_TYPES.register(name, () -> new SensorType<>(factory));
	}

	@Override
	public <U> Supplier<MemoryModuleType<U>> registerMemoryModuleType(String name, Optional<Codec<U>> codec) {
		return MEMORY_MODULE_TYPES.register(name, () -> new MemoryModuleType<>(codec));
	}

	@Override
	public <T extends Entity> void register(Supplier<? extends EntityType<? extends T>> type, EntityRendererProvider<T> provider) {
		NeoforgeVariables.ENTITY_RENDERERS.add(new NeoforgeVariables.EntityRendererDeferred<>(type, provider));
	}
}
//?}
