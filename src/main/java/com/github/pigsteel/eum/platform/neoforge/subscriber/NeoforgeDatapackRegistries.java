package com.github.pigsteel.eum.platform.neoforge.subscriber;

//? neoforge {
import com.github.pigsteel.eum.EUM;
import com.github.pigsteel.eum.core.EUMCustomRegistries;
import com.github.pigsteel.eum.world.entity.monster.skeleton.SunkenVariant;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;

@EventBusSubscriber(modid = EUM.MOD_ID)
public class NeoforgeDatapackRegistries {
	@SubscribeEvent
	public static void registerDatapackRegistries(DataPackRegistryEvent.NewRegistry event) {
		event.dataPackRegistry(
				EUMCustomRegistries.SUNKEN_VARIANT,
				SunkenVariant.DIRECT_CODEC,
				SunkenVariant.NETWORK_CODEC,
				builder -> builder.maxId(256)
		);
	}
}
//?}
