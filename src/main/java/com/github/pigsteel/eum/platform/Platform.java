package com.github.pigsteel.eum.platform;

import com.github.pigsteel.eum.core.EUMDataAttachments;
import com.mojang.serialization.Codec;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.syncher.EntityDataSerializer;
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
import net.neoforged.neoforge.attachment.AttachmentType;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import static com.github.pigsteel.eum.platform.neoforge.NeoforgeVariables.ATTACHMENT_TYPES;

public interface Platform {
	boolean isModLoaded(String modId);

	ModLoader loader();

	String mcVersion();

	boolean isDevelopmentEnvironment();

	default boolean isDebug() {
		return isDevelopmentEnvironment();
	}

	<T> Supplier<DataComponentType<T>> register(
			final String id,
			final UnaryOperator<DataComponentType.Builder<T>> builder
	);

	<T extends LivingEntity> void register(Supplier<EntityType<T>> entityType, Supplier<AttributeSupplier.Builder> supplier);

	void register(EntityDataSerializer<?> serializer, String name);

	<T extends Entity> Supplier<EntityType<T>> register(String id, EntityType.Builder<T> builder);

	<T extends Item> Supplier<T> register(String name, Function<Item.Properties, T> itemFactory);

	Holder<MobEffect> register(final String name, final MobEffect mobEffect);

	Supplier<SimpleParticleType> register(final String name, final boolean overrideLimiter);

	Supplier<SoundEvent> registerSoundEvent(String name);

	void register(ModelLayerLocation modelLayerLocation, Supplier<LayerDefinition> consumer);

	<U extends Sensor<?>> Supplier<SensorType<U>> register(String name, Supplier<U> factory);

	<U> Supplier<MemoryModuleType<U>> registerMemoryModuleType(String name, Optional<Codec<U>> maybeCodec);

	<T extends Entity> void register(Supplier<? extends EntityType<? extends T>> type, EntityRendererProvider<T> provider);

	<A> EUMDataAttachments.DataAttachmentHandle<A> register(String id, Consumer<EUMDataAttachments.AgnosticBuilder<A>> consumer);

	enum ModLoader {
		FABRIC, NEOFORGE, FORGE, QUILT
	}
}
