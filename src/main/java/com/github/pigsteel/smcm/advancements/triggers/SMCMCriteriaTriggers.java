package com.github.pigsteel.smcm.advancements.triggers;

import com.github.pigsteel.smcm.SMCM;
//? >= 26.2 {
import net.minecraft.advancements.triggers.CriterionTrigger;
//?} < 26.2 {
/*import net.minecraft.advancements.CriterionTrigger;
*///?}
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

public class SMCMCriteriaTriggers {



	public static <T extends CriterionTrigger<?>> T register(String name, T criterion) {
		return Registry.register(BuiltInRegistries.TRIGGER_TYPES, SMCM.id(name), criterion);
	}
}
