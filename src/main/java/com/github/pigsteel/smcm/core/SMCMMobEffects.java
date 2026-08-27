package com.github.pigsteel.smcm.core;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.world.effect.CustomMobEffect;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class SMCMMobEffects {
	public static final Holder<MobEffect> CORRUPTION;

	static {
		CORRUPTION = register("corruption", new CustomMobEffect(MobEffectCategory.HARMFUL, 5309090).addAttributeModifier(Attributes.MAX_HEALTH, SMCM.id("effect.corruption"), (double)-2.0F, AttributeModifier.Operation.ADD_VALUE));
	}

	private static Holder<MobEffect> register(final String name, final MobEffect mobEffect) {
		return SMCM.xplat().register(name, mobEffect);
	}

	public static void load() {}
}
