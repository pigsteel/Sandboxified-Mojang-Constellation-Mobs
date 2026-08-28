package com.github.pigsteel.eum.core;

import com.github.pigsteel.eum.EUM;
import com.github.pigsteel.eum.world.effect.CustomMobEffect;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class EUMMobEffects {
	public static final Holder<MobEffect> CORRUPTION;

	static {
		CORRUPTION = register("corruption", new CustomMobEffect(MobEffectCategory.HARMFUL, 5309090).addAttributeModifier(Attributes.MAX_HEALTH, EUM.id("effect.corruption"), (double)-2.0F, AttributeModifier.Operation.ADD_VALUE));
	}

	private static Holder<MobEffect> register(final String name, final MobEffect mobEffect) {
		return EUM.xplat().register(name, mobEffect);
	}

	public static void load() {}
}
