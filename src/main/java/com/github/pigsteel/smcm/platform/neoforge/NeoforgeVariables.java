package com.github.pigsteel.smcm.platform.neoforge;

//? neoforge {
import com.github.pigsteel.smcm.SMCM;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.ArrayList;
import java.util.function.Supplier;

public class NeoforgeVariables {
	public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, SMCM.MOD_ID);
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(SMCM.MOD_ID);
	public static final DeferredRegister.DataComponents DATA_COMPONENTS = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, SMCM.MOD_ID);
	public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(Registries.SOUND_EVENT, SMCM.MOD_ID);
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, SMCM.MOD_ID);
	public static final DeferredRegister<EntityDataSerializer<?>> ENTITY_DATA_SERIALIZERS = DeferredRegister.create(NeoForgeRegistries.ENTITY_DATA_SERIALIZERS, SMCM.MOD_ID);
	public static final DeferredRegister<MemoryModuleType<?>> MEMORY_MODULE_TYPES = DeferredRegister.create(Registries.MEMORY_MODULE_TYPE, SMCM.MOD_ID);
	public static final DeferredRegister<SensorType<?>> SENSOR_TYPES = DeferredRegister.create(Registries.SENSOR_TYPE, SMCM.MOD_ID);
	public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, SMCM.MOD_ID);
	public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, SMCM.MOD_ID);
	public static ArrayList<ModelLayerDeferred> MODEL_LAYERS = new ArrayList<>();
	public static ArrayList<DefaultAttributesDeferred<?>> DEFAULT_ATTRIBUTES = new ArrayList<>();
	public static ArrayList<EntityRendererDeferred<?>> ENTITY_RENDERERS = new ArrayList<>();

	public static void registerAll(IEventBus modBus) {
		ATTACHMENT_TYPES.register(modBus);
		ITEMS.register(modBus);
		DATA_COMPONENTS.register(modBus);
		SOUND_EVENTS.register(modBus);
		ENTITIES.register(modBus);
		ENTITY_DATA_SERIALIZERS.register(modBus);
		MEMORY_MODULE_TYPES.register(modBus);
		SENSOR_TYPES.register(modBus);
		PARTICLE_TYPES.register(modBus);
		MOB_EFFECTS.register(modBus);
	}

	public record DefaultAttributesDeferred<T extends LivingEntity>(Supplier<EntityType<T>> type, Supplier<AttributeSupplier.Builder> supplier) {
		public void register(EntityAttributeCreationEvent event) {
			event.put(this.type().get(), supplier.get().build());
		}
	}
	public record ModelLayerDeferred(ModelLayerLocation layer, Supplier<LayerDefinition> supplier) {
		public void register(EntityRenderersEvent.RegisterLayerDefinitions event) {
			event.registerLayerDefinition(this.layer(), this.supplier());
		}
	}
	public record EntityRendererDeferred<T extends Entity>(Supplier<? extends EntityType<? extends T>> type, EntityRendererProvider<T> provider) {
		public void register(EntityRenderersEvent.RegisterRenderers event) {
			event.registerEntityRenderer(type.get(), provider);
		}
	}
}
//?}
