package com.github.pigsteel.smcm.core;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.world.entity.monster.necromancer.Necromancer;
import com.github.pigsteel.smcm.world.entity.monster.skeleton.SunkenVariant;
import net.minecraft.core.Holder;
import net.minecraft.network.syncher.EntityDataSerializer;

public class SMCMEntityDataSerializers {


    public static final EntityDataSerializer<Holder<SunkenVariant>> SUNKEN_VARIANT = EntityDataSerializer.forValueType(SunkenVariant.STREAM_CODEC);
	public static final EntityDataSerializer<Necromancer.NecromancerSpell> NECROMANCER_SPELL = EntityDataSerializer.forValueType(Necromancer.NecromancerSpell.STREAM_CODEC);

    public static void registerSerializer(EntityDataSerializer<?> serializer, String name) {
        SMCM.xplat().register(serializer, name);
    }

    public static void load() {
        registerSerializer(SUNKEN_VARIANT, "sunken/variant");
		registerSerializer(NECROMANCER_SPELL, "necromancer_spell");
    }
}
