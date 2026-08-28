package com.github.pigsteel.eum.platform.neoforge;

//? neoforge {

import com.github.pigsteel.eum.EUM;
import com.github.pigsteel.eum.core.EUMDataAttachments;
import com.github.pigsteel.eum.core.particles.CustomSimpleParticleType;
import com.github.pigsteel.eum.platform.Platform;

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
import net.neoforged.neoforge.attachment.AttachmentType;


import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import static com.github.pigsteel.eum.platform.neoforge.NeoforgeVariables.*;

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
		return !FMLLoader/*? if > 1.21.7 {*//*.getCurrent()*//*?}*/.isProduction();
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
		ResourceKey<EntityType<?>> key = EUM.key(Registries.ENTITY_TYPE, id);
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

	@Override
	public <A> EUMDataAttachments.DataAttachmentHandle<A> register(String id, Consumer<EUMDataAttachments.AgnosticBuilder<A>> consumer) {
		EUMDataAttachments.AgnosticBuilder<A> builder = EUMDataAttachments.builder();

		consumer.accept(builder);

		Supplier<AttachmentType<A>> attachment = ATTACHMENT_TYPES.register(id, () -> builder.neoforgeImpl().build());

		return new EUMDataAttachments.DataAttachmentHandle<A>() {
			@Override
			public boolean hasAttached(Entity entity) {
				return entity.hasData(attachment);
			}

			@Override
			public A getAttached(Entity entity) {
				return entity.getExistingData(attachment).get();
			}

			@Override
			public A getAttachedOrElse(Entity entity, A defaultValue) {
				return entity.getExistingData(attachment).orElse(defaultValue);
			}

			@Override
			public void setAttached(Entity entity, A value) {
				entity.setData(attachment, value);
			}

			@Override
			public A getAttachedOrSet(Entity entity, A defaultValue) {
				Optional<A> optional = entity.getExistingData(attachment);
				if (optional.isPresent()) {
					return optional.get();
				} else {
					this.setAttached(entity, defaultValue);
					return defaultValue;
				}
			}
		};
	}
}
//?}
