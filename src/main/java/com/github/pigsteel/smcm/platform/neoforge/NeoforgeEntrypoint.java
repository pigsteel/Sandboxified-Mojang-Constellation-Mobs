package com.github.pigsteel.smcm.platform.neoforge;

//? neoforge {

/*import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.core.smcm$Registries;
import com.github.pigsteel.smcm.platform.neoforge.subscriber.NeoforgeModelLayers;
import com.github.pigsteel.smcm.world.entity.monster.skeleton.SunkenVariant;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;

import static com.github.pigsteel.smcm.platform.neoforge.NeoforgeVariables.*;

@Mod(SMCM.MOD_ID)
public class NeoforgeEntrypoint {
	public NeoforgeEntrypoint(IEventBus modBus) {
		SMCM.onInitialize();
		NeoforgeVariables.registerAll(modBus);
	}
}
*///?}
