package com.github.pigsteel.smcm.core;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.world.effect.CustomMobEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.function.Supplier;

public class smcm$MobEffects {
	public static final Supplier<Holder<MobEffect>> CORRUPTION;

	static {
		CORRUPTION = register("corruption", new CustomMobEffect(MobEffectCategory.HARMFUL, 5309090).addAttributeModifier(Attributes.MAX_HEALTH, SMCM.id("effect.corruption"), (double)-2.0F, AttributeModifier.Operation.ADD_VALUE));
	}

	private static Supplier<Holder<MobEffect>> register(final String name, final MobEffect mobEffect) {
		Holder<MobEffect> var10000 = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, SMCM.id(name), mobEffect);
		return () -> var10000;
	}

	public static void init() {}
}
