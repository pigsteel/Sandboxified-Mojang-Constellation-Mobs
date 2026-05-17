package com.github.pigsteel.smcm.core;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.world.entity.monster.skeleton.SunkenVariant;
import com.github.pigsteel.smcm.world.entity.monster.zombie.Reclaimed;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.Identifier;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

//? neoforge {
/*import static com.github.pigsteel.smcm.platform.neoforge.NeoforgeVariables.DATA_COMPONENTS;
*///?}

public class smcm$DataComponents {
    public static final Supplier<DataComponentType<Reclaimed.HeadFlower>> RECLAIMED_HEAD_FLOWER = register(
            "reclaimed/head_flower",
            builder -> builder
                    .persistent(Reclaimed.HeadFlower.CODEC)
                    .networkSynchronized(Reclaimed.HeadFlower.STREAM_CODEC)
    );

    public static final Supplier<DataComponentType<Holder<SunkenVariant>>> SUNKEN_VARIANT = register(
            "sunken/variant",
            builder -> builder
                    .persistent(SunkenVariant.CODEC)
                    .networkSynchronized(SunkenVariant.STREAM_CODEC)
    );

	public static final Supplier<DataComponentType<Boolean>> IS_CORAL_DEAD = register(
			"sunken/is_coral_dead",
			builder -> builder
					.persistent(Codec.BOOL)
					.networkSynchronized(ByteBufCodecs.BOOL)
	);

    public static void init() {
    }

    private static <T> Supplier<DataComponentType<T>> register(
            final String id,
            final UnaryOperator<DataComponentType.Builder<T>> builder
    ) {
		//? fabric {
		DataComponentType<T> type = builder
                .apply(DataComponentType.<T>builder())
                .build();
		DataComponentType<T> var10000 = Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, SMCM.id(id), type);
        return () -> var10000;
		//?} neoforge {
		/*return DATA_COMPONENTS.registerComponentType(id, builder);
		*///?}
    }
}
