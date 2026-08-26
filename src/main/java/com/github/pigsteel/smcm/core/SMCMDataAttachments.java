package com.github.pigsteel.smcm.core;

import com.github.pigsteel.smcm.SMCM;
//? fabric {
/*import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
*///?} neoforge {
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
//?}
import com.mojang.serialization.Codec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.Entity;
import org.jspecify.annotations.Nullable;

import javax.xml.crypto.Data;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

//? neoforge {
import static com.github.pigsteel.smcm.platform.neoforge.NeoforgeVariables.ATTACHMENT_TYPES;
import net.minecraft.resources.Identifier;
import java.util.Optional;
//?}

public class SMCMDataAttachments {
	// for reference: use these when we want to attach arbitrary data (the same way we would with an EntityDataAccessor) for an entity we do not own; like the zombie

    public static final DataAttachmentHandle<Boolean> DATA_FROSTBITTEN_CONVERSION_ID = register(
            "data_frostbitten_conversion_id",
            builder -> builder
                    .initializer(() -> false)
                    .syncWith(
                            ByteBufCodecs.BOOL
                    ).persistent(Codec.BOOL)
    );

	public static final DataAttachmentHandle<Boolean> DATA_SUNKEN_CONVERSION_ID = register(
			"data_sunken_conversion_id",
			builder -> builder
					.initializer(() -> false)
					.syncWith(
							ByteBufCodecs.BOOL
					).persistent(Codec.BOOL)
	);

    private SMCMDataAttachments() {
    }

	private static <A> DataAttachmentHandle<A> register(String id, Consumer<AgnosticBuilder<A>> consumer) {
		AgnosticBuilder<A> builder = builder();

		consumer.accept(builder);

		//? fabric {
		/*AttachmentType<A> attachment = AttachmentRegistry.create(SMCM.id(id), builder::fabricImpl);
		*///?} neoforge {
		Supplier<AttachmentType<A>> attachment = ATTACHMENT_TYPES.register(id, () -> builder.neoforgeImpl().build());
		//?}

		return new DataAttachmentHandle<A>() {
			@Override
			public boolean hasAttached(Entity entity) {
				//? fabric {
				/*return entity.hasAttached(attachment);
				*///?} neoforge {
				return entity.hasData(attachment);
				//?}
			}

			@Override
			public A getAttached(Entity entity) {
				//? fabric {
				/*return entity.getAttached(attachment);
				*///?} neoforge {
				return entity.getExistingData(attachment).get();
				//?}
			}

			@Override
			public A getAttachedOrElse(Entity entity, A defaultValue) {
				//? fabric {
				/*return entity.getAttachedOrElse(attachment, defaultValue);
				*///?} neoforge {
				return entity.getExistingData(attachment).orElse(defaultValue);
				//?}
			}

			@Override
			public void setAttached(Entity entity, A value) {
				//? fabric {
				/*entity.setAttached(attachment, value);
				*///?} neoforge {
				entity.setData(attachment, value);
				//?}
			}

			@Override
			public A getAttachedOrSet(Entity entity, A defaultValue) {
				//? fabric {
				/*return entity.getAttachedOrSet(attachment, defaultValue);
				*///?} neoforge {
				Optional<A> optional = entity.getExistingData(attachment);
				if (optional.isPresent()) {
					return optional.get();
				} else {
					this.setAttached(entity, defaultValue);
					return defaultValue;
				}
				//?}
			}
		};
	}

    public static void load() {}

	public interface DataAttachmentHandle<T> {
		boolean hasAttached(Entity entity);

		T getAttached(Entity entity);

		T getAttachedOrElse(Entity entity, T defaultValue);

		void setAttached(Entity entity, T value);

		T getAttachedOrSet(Entity entity, T defaultValue);
	}

	public static <A> AgnosticBuilder<A> builder() {
		return new AgnosticBuilder<>();
	}

	public static class AgnosticBuilder<A> {
		@Nullable
		private Supplier<A> defaultInitializer = null;
		@Nullable
		private Codec<A> persistenceCodec = null;
		@Nullable
		private StreamCodec<? super RegistryFriendlyByteBuf, A> streamCodec = null;
		private boolean copyOnDeath = false;
		private int maxSyncSize = -1;

		public AgnosticBuilder<A> persistent(Codec<A> codec) {
			Objects.requireNonNull(codec, "codec cannot be null");

			this.persistenceCodec = codec;
			return this;
		}

		public AgnosticBuilder<A> copyOnDeath() {
			this.copyOnDeath = true;
			return this;
		}

		public AgnosticBuilder<A> initializer(Supplier<A> initializer) {
			Objects.requireNonNull(initializer, "initializer cannot be null");

			this.defaultInitializer = initializer;
			return this;
		}

		public AgnosticBuilder<A> syncWith(StreamCodec<? super RegistryFriendlyByteBuf, A> streamCodec) {
			Objects.requireNonNull(streamCodec, "stream codec cannot be null");

			this.streamCodec = streamCodec;
			return this;
		}

		//? fabric {
		/*public void fabricImpl(AttachmentRegistry.Builder<A> builder) {
			if(this.defaultInitializer != null) {
				builder.initializer(this.defaultInitializer);
			}

			if(this.streamCodec != null) {
				builder.syncWith(streamCodec, AttachmentSyncPredicate.all());
			}

			if(this.persistenceCodec != null) {
				builder.persistent(this.persistenceCodec);
			}

			if(this.copyOnDeath) {
				builder.copyOnDeath();
			}
		}
		*///?}

		//? neoforge {
		public AttachmentType.Builder<A> neoforgeImpl() {
			Objects.requireNonNull(defaultInitializer, "defaultInitializer cannot be null");

			AttachmentType.Builder<A> builder = AttachmentType.builder(defaultInitializer);

			if(this.streamCodec != null) {
				builder.sync(this.streamCodec);
			}

			if(this.persistenceCodec != null) {
				builder.serialize(persistenceCodec.fieldOf("persistent"));
			}

			if(this.copyOnDeath) {
				builder.copyOnDeath();
			}

			return builder;
		}
		//?}
	}
}
