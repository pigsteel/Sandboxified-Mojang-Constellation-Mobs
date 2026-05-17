package com.github.pigsteel.smcm.world.entity;

import net.minecraft.world.entity.Entity;
import org.jspecify.annotations.Nullable;

import java.util.UUID;

public interface Enchantable {


	final class EntityEnchantmentData {
		//public static final Codec<EntityEnchantmentData> CODEC = Codec.of(UUIDUtil.CODEC.fieldOf("UUID").codec());

		public @Nullable Entity enchanterHolder;
		public @Nullable UUID delayedEnchanterInfo;
	}
}
