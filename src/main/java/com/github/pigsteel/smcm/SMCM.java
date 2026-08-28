package com.github.pigsteel.smcm;

import com.github.pigsteel.smcm.client.model.geom.SMCMLayerDefinitions;
import com.github.pigsteel.smcm.client.model.geom.SMCMModelLayers;
import com.github.pigsteel.smcm.core.SMCMDataAttachments;
import com.github.pigsteel.smcm.core.SMCMDataComponents;
import com.github.pigsteel.smcm.core.SMCMDefaultAttributes;
import com.github.pigsteel.smcm.core.SMCMEntityDataSerializers;
import com.github.pigsteel.smcm.client.renderer.entity.SMCMEntityRenderers;
import com.github.pigsteel.smcm.core.SMCMEntityTypes;
import com.github.pigsteel.smcm.core.SMCMItems;
import com.github.pigsteel.smcm.core.SMCMLootTables;
import com.github.pigsteel.smcm.core.SMCMMobEffects;
import com.github.pigsteel.smcm.core.SMCMPackets;
import com.github.pigsteel.smcm.core.SMCMParticleTypes;
import com.github.pigsteel.smcm.core.SMCMCustomRegistries;
import com.github.pigsteel.smcm.core.SMCMSoundEvents;
import com.github.pigsteel.smcm.mixson.advancements.AdvancementEvents;
import com.github.pigsteel.smcm.platform.Platform;
import com.github.pigsteel.smcm.util.EnumExtensions;
import com.github.pigsteel.smcm.world.entity.ai.memory.SMCMMemoryModuleTypes;
import com.github.pigsteel.smcm.world.entity.ai.sensing.SMCMSensorTypes;
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
		SMCMCustomRegistries.load();
		SMCMPackets.load();
		SMCMMemoryModuleTypes.load();
		SMCMSensorTypes.load();
		SMCMEntityDataSerializers.load();
		SMCMEntityTypes.load();
		SMCMItems.load();
		SMCMDefaultAttributes.load();
		SMCMDataComponents.load();
		SMCMLootTables.load();
		SMCMDataAttachments.load();
		SMCMSoundEvents.load();
		SMCMMobEffects.load();
		SMCMParticleTypes.load();
		EnumExtensions.load();

		AdvancementEvents.load();
	}

	public static void onInitializeClient() {
		SMCMModelLayers.load();
		SMCMLayerDefinitions.load();
		SMCMEntityRenderers.load();
		SMCMPackets.clientLoad();
		SMCMParticleTypes.clientLoad();
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
