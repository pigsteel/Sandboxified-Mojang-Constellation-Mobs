package com.github.pigsteel.smcm;

import com.github.pigsteel.smcm.client.model.geom.smcm$LayerDefinitions;
import com.github.pigsteel.smcm.client.model.geom.smcm$ModelLayers;
import com.github.pigsteel.smcm.core.smcm$DataAttachments;
import com.github.pigsteel.smcm.core.smcm$DataComponents;
import com.github.pigsteel.smcm.core.smcm$DefaultAttributes;
import com.github.pigsteel.smcm.core.smcm$EntityDataSerializers;
import com.github.pigsteel.smcm.client.renderer.entity.smcm$EntityRenderers;
import com.github.pigsteel.smcm.core.smcm$EntityTypes;
import com.github.pigsteel.smcm.core.smcm$Items;
import com.github.pigsteel.smcm.core.smcm$LootTables;
import com.github.pigsteel.smcm.core.smcm$Packets;
import com.github.pigsteel.smcm.core.smcm$Registries;
import com.github.pigsteel.smcm.core.smcm$SoundEvents;
import com.github.pigsteel.smcm.mixson.advancements.AdvancementEvents;
import com.github.pigsteel.smcm.platform.Platform;
import com.github.pigsteel.smcm.world.entity.ai.memory.smcm$MemoryModuleTypes;
import com.github.pigsteel.smcm.world.entity.ai.sensing.smcm$SensorTypes;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//? fabric {
import com.github.pigsteel.smcm.platform.fabric.FabricPlatform;
//?} neoforge {
/*import com.github.pigsteel.smcm.platform.neoforge.NeoforgePlatform;
 *///?}

@SuppressWarnings("LoggingSimilarMessage")
public class SMCM {

	public static final String MOD_ID = /*$ mod_id*/ "smcm";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private static final Platform PLATFORM = createPlatformInstance();

	public static void onInitialize() {
		smcm$Registries.load();
		smcm$Packets.init();
		smcm$MemoryModuleTypes.init();
		smcm$SensorTypes.init();
		smcm$EntityDataSerializers.register();
		smcm$EntityTypes.register();
		smcm$Items.register();
		smcm$DefaultAttributes.register();
		smcm$DataComponents.init();
		smcm$LootTables.load();
		smcm$DataAttachments.load();
		smcm$SoundEvents.register();

		AdvancementEvents.init();
	}

	public static void onInitializeClient() {
		smcm$ModelLayers.init();
		smcm$LayerDefinitions.registerModelLayers();
		smcm$EntityRenderers.register();
		smcm$Packets.clinit();
	}

	public static Platform xplat() {
		return PLATFORM;
	}

	private static Platform createPlatformInstance() {
		//? fabric {
		return new FabricPlatform();
		//?} neoforge {
		/*return new NeoforgePlatform();
		 *///?}
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}

	public static Identifier id(String namespace, String path) {
		return Identifier.fromNamespaceAndPath(namespace, path);
	}

	public static <T> ResourceKey<T> key(ResourceKey<Registry<T>> registry, String path) {
		return ResourceKey.create(registry, id(path));
	}
}
