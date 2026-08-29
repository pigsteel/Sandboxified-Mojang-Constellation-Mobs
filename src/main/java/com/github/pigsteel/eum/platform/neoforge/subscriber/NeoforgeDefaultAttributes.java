package com.github.pigsteel.eum.platform.neoforge.subscriber;

//? neoforge {
/*import com.github.pigsteel.eum.EUM;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

import static com.github.pigsteel.eum.platform.neoforge.NeoforgeVariables.DEFAULT_ATTRIBUTES;

@EventBusSubscriber(modid = EUM.MOD_ID)
public class NeoforgeDefaultAttributes {
	@SubscribeEvent
	public static void createDefaultAttributes(EntityAttributeCreationEvent event) {
		DEFAULT_ATTRIBUTES.forEach(deferred -> deferred.register(event));
	}
}
*///?}
