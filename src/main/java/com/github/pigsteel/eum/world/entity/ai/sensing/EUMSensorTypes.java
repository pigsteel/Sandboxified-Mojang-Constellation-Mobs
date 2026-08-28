package com.github.pigsteel.eum.world.entity.ai.sensing;

import com.github.pigsteel.eum.EUM;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;

import java.util.function.Supplier;

public class EUMSensorTypes {
	public static final Supplier<SensorType<NecromancerAttackEntitySensor>> NECROMANCER_ATTACK_ENTITY_SENSOR;

	private static <U extends Sensor<?>> Supplier<SensorType<U>> register(String name, Supplier<U> factory) {
		return EUM.xplat().register(name, factory);
	}

	static {
		NECROMANCER_ATTACK_ENTITY_SENSOR = register("necromancer_attack_entity_sensor", NecromancerAttackEntitySensor::new);
	}

	public static void load() {}
}
