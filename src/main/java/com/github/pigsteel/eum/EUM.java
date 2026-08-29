package com.github.pigsteel.eum;

import com.github.pigsteel.eum.client.model.geom.EUMLayerDefinitions;
import com.github.pigsteel.eum.client.model.geom.EUMModelLayers;
import com.github.pigsteel.eum.core.EUMDataAttachments;
import com.github.pigsteel.eum.core.EUMDataComponents;
import com.github.pigsteel.eum.core.EUMDefaultAttributes;
import com.github.pigsteel.eum.core.EUMEntityDataSerializers;
import com.github.pigsteel.eum.client.renderer.entity.EUMEntityRenderers;
import com.github.pigsteel.eum.core.EUMEntityTypes;
import com.github.pigsteel.eum.core.EUMItems;
import com.github.pigsteel.eum.core.EUMLootTables;
import com.github.pigsteel.eum.core.EUMMobEffects;
import com.github.pigsteel.eum.core.EUMPackets;
import com.github.pigsteel.eum.core.EUMParticleTypes;
import com.github.pigsteel.eum.core.EUMCustomRegistries;
import com.github.pigsteel.eum.core.EUMSoundEvents;
import com.github.pigsteel.eum.mixson.advancements.AdvancementEvents;
import com.github.pigsteel.eum.platform.Platform;
import com.github.pigsteel.eum.util.EnumExtensions;
import com.github.pigsteel.eum.world.entity.ai.memory.EUMMemoryModuleTypes;
import com.github.pigsteel.eum.world.entity.ai.sensing.EUMSensorTypes;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//? fabric {
import com.github.pigsteel.eum.platform.fabric.FabricPlatform;
//?} neoforge {
/*import com.github.pigsteel.eum.platform.neoforge.NeoforgePlatform;
 *///?}

@SuppressWarnings("LoggingSimilarMessage")
public class EUM {

	public static final String MOD_ID = /*$ mod_id*/ "eum";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private static final Platform PLATFORM = createPlatformInstance();

	public static void onInitialize() {
		EUMCustomRegistries.load();
		EUMPackets.load();
		EUMMemoryModuleTypes.load();
		EUMSensorTypes.load();
		EUMEntityDataSerializers.load();
		EUMEntityTypes.load();
		EUMItems.load();
		EUMDefaultAttributes.load();
		EUMDataComponents.load();
		EUMLootTables.load();
		EUMDataAttachments.load();
		EUMSoundEvents.load();
		EUMMobEffects.load();
		EUMParticleTypes.load();
		EnumExtensions.load();

		AdvancementEvents.load();
	}

	public static void onInitializeClient() {
		EUMModelLayers.load();
		EUMLayerDefinitions.load();
		EUMEntityRenderers.load();
		EUMPackets.clientLoad();
		EUMParticleTypes.clientLoad();
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
