package com.github.pigsteel.eum.core;

import com.github.pigsteel.eum.EUM;
import com.github.pigsteel.eum.world.entity.monster.necromancer.Necromancer;
import com.github.pigsteel.eum.world.entity.monster.skeleton.SunkenVariant;
import net.minecraft.core.Holder;
import net.minecraft.network.syncher.EntityDataSerializer;

public class EUMEntityDataSerializers {
	private EUMEntityDataSerializers() {}

    public static final EntityDataSerializer<Holder<SunkenVariant>> SUNKEN_VARIANT = EntityDataSerializer.forValueType(SunkenVariant.STREAM_CODEC);
	public static final EntityDataSerializer<Necromancer.NecromancerSpell> NECROMANCER_SPELL = EntityDataSerializer.forValueType(Necromancer.NecromancerSpell.STREAM_CODEC);

    public static void registerSerializer(EntityDataSerializer<?> serializer, String name) {
        EUM.xplat().register(serializer, name);
    }

    public static void load() {
        registerSerializer(SUNKEN_VARIANT, "sunken/variant");
		registerSerializer(NECROMANCER_SPELL, "necromancer_spell");
    }
}
