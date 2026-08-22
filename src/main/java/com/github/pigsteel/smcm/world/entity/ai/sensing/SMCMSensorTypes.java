package com.github.pigsteel.smcm.world.entity.ai.sensing;

import com.github.pigsteel.smcm.SMCM;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;

import java.util.function.Supplier;

//? neoforge {
/*import static com.github.pigsteel.smcm.platform.neoforge.NeoforgeVariables.SENSOR_TYPES;
*///?}

public class SMCMSensorTypes {
	public static final Supplier<SensorType<NecromancerAttackEntitySensor>> NECROMANCER_ATTACK_ENTITY_SENSOR;

	private static <U extends Sensor<?>> Supplier<SensorType<U>> register(String name, Supplier<U> factory) {
		//? fabric {
		var var10000 = Registry.register(BuiltInRegistries.SENSOR_TYPE, SMCM.id(name), new SensorType<>(factory));
		return () -> var10000;
		//?} neoforge {
		/*return SENSOR_TYPES.register(name, () -> new SensorType<>(factory));
		*///?}
	}

	static {
		NECROMANCER_ATTACK_ENTITY_SENSOR = register("necromancer_attack_entity_sensor", NecromancerAttackEntitySensor::new);
	}

	public static void load() {}
}
