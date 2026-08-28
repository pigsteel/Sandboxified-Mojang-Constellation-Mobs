package com.github.pigsteel.eum.advancements.triggers;

import com.github.pigsteel.eum.EUM;
//? >= 26.2 {
import net.minecraft.advancements.triggers.CriterionTrigger;
//?} < 26.2 {
/*import net.minecraft.advancements.CriterionTrigger;
*///?}
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

public class SMCMCriteriaTriggers {



	public static <T extends CriterionTrigger<?>> T register(String name, T criterion) {
		return Registry.register(BuiltInRegistries.TRIGGER_TYPES, EUM.id(name), criterion);
	}
}
