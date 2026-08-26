package com.github.pigsteel.smcm.platform.neoforge.subscriber;

//? neoforge {
import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.core.SMCMCustomRegistries;
import com.github.pigsteel.smcm.world.entity.monster.skeleton.SunkenVariant;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;

@EventBusSubscriber(modid = SMCM.MOD_ID)
public class NeoforgeDatapackRegistries {
	@SubscribeEvent
	public static void registerDatapackRegistries(DataPackRegistryEvent.NewRegistry event) {
		event.dataPackRegistry(
				SMCMCustomRegistries.SUNKEN_VARIANT,
				SunkenVariant.DIRECT_CODEC,
				SunkenVariant.NETWORK_CODEC,
				builder -> builder.maxId(256)
		);
	}
}
//?}
