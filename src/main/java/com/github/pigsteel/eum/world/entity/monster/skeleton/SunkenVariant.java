package com.github.pigsteel.eum.world.entity.monster.skeleton;

import com.github.pigsteel.eum.EUM;
import com.github.pigsteel.eum.core.EUMCustomRegistries;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.ClientAsset;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.RegistryFixedCodec;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.variant.ModelAndTexture;
import net.minecraft.world.entity.variant.PriorityProvider;
import net.minecraft.world.entity.variant.SpawnCondition;
import net.minecraft.world.entity.variant.SpawnContext;
import net.minecraft.world.entity.variant.SpawnPrioritySelectors;

import java.util.List;

public record SunkenVariant(
        ModelAndTexture<ModelType> modelAndTexture,
        ClientAsset.ResourceTexture deadCoralTexture,
        SpawnPrioritySelectors spawnConditions
)
        implements PriorityProvider<SpawnContext, SpawnCondition> {
	public static final ClientAsset.ResourceTexture EMPTY_DEAD_CORAL_TEXTURE =
			new ClientAsset.ResourceTexture(
					Identifier.fromNamespaceAndPath(EUM.MOD_ID, "entity/skeleton/sunken/empty")
			);

    public static final Codec<SunkenVariant> DIRECT_CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    ModelAndTexture.codec(ModelType.CODEC, ModelType.NORMAL)
                            .forGetter(SunkenVariant::modelAndTexture),

                    ClientAsset.ResourceTexture.CODEC
                            .optionalFieldOf(
                                    "dead_coral_asset_id",
                                    null
                            )
                            .forGetter(SunkenVariant::deadCoralTexture),

                    SpawnPrioritySelectors.CODEC
                            .fieldOf("spawn_conditions")
                            .forGetter(SunkenVariant::spawnConditions)
            ).apply(instance, SunkenVariant::new)
    );

    public static final Codec<SunkenVariant> NETWORK_CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    ModelAndTexture.codec(ModelType.CODEC, ModelType.NORMAL)
                            .forGetter(SunkenVariant::modelAndTexture),

                    ClientAsset.ResourceTexture.CODEC
                            .optionalFieldOf(
                                    "dead_coral_asset_id",
                                    null
                            )
                            .forGetter(SunkenVariant::deadCoralTexture)
            ).apply(instance, (modelAndTexture, deadCoralTexture) ->
                    new SunkenVariant(
                            modelAndTexture,
                            deadCoralTexture,
                            SpawnPrioritySelectors.EMPTY
                    )
            )
    );
    public static final Codec<Holder<SunkenVariant>> CODEC;
    public static final StreamCodec<RegistryFriendlyByteBuf, Holder<SunkenVariant>> STREAM_CODEC;

    private SunkenVariant(final ModelAndTexture<ModelType> primaryAssetInfo, ClientAsset.ResourceTexture secondaryAssetInfo) {
        this(primaryAssetInfo, secondaryAssetInfo, SpawnPrioritySelectors.EMPTY);
    }

    @Override
    public List<Selector<SpawnContext, SpawnCondition>> selectors() {
        return this.spawnConditions.selectors();
    }

    static {
        CODEC = RegistryFixedCodec.create(EUMCustomRegistries.SUNKEN_VARIANT);
        STREAM_CODEC = ByteBufCodecs.holderRegistry(EUMCustomRegistries.SUNKEN_VARIANT);
    }

    public enum ModelType implements StringRepresentable {
        NORMAL("normal"),
        FROZEN("frozen"),
        BUBBLE_CORAL("bubble_coral"),
        FIRE_CORAL("fire_coral"),
        HORN_CORAL("horn_coral");

        public static final Codec<ModelType> CODEC = StringRepresentable.fromEnum(ModelType::values);
        private final String name;

        private ModelType(final String name) {
            this.name = name;
        }

        public String getSerializedName() {
            return this.name;
        }

        public boolean isCoral() {
            return this == BUBBLE_CORAL
                    || this == FIRE_CORAL
                    || this == HORN_CORAL;
        }
    }
}
