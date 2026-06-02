package com.github.pigsteel.smcm.entity.skeleton;

import com.github.pigsteel.smcm.client.renderer.entity.SunkenRenderer;
import com.github.pigsteel.smcm.registry.smcm$Registries;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.ClientAsset;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.RegistryFixedCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.variant.*;

import java.util.List;

public record SunkenVariant(
        ModelAndTexture<ModelType> modelAndTexture,
        ClientAsset.ResourceTexture deadCoralTexture,
        SpawnPrioritySelectors spawnConditions
)
        implements PriorityProvider<SpawnContext, SpawnCondition> {
    public static final Codec<SunkenVariant> DIRECT_CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    ModelAndTexture.codec(SunkenVariant.ModelType.CODEC, SunkenVariant.ModelType.NORMAL)
                            .forGetter(SunkenVariant::modelAndTexture),

                    ClientAsset.ResourceTexture.CODEC
                            .optionalFieldOf(
                                    "dead_coral_asset_id",
                                    SunkenRenderer.EMPTY_DEAD_CORAL_TEXTURE
                            )
                            .forGetter(SunkenVariant::deadCoralTexture),

                    SpawnPrioritySelectors.CODEC
                            .fieldOf("spawn_conditions")
                            .forGetter(SunkenVariant::spawnConditions)
            ).apply(instance, SunkenVariant::new)
    );

    public static final Codec<SunkenVariant> NETWORK_CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    ModelAndTexture.codec(SunkenVariant.ModelType.CODEC, SunkenVariant.ModelType.NORMAL)
                            .forGetter(SunkenVariant::modelAndTexture),

                    ClientAsset.ResourceTexture.CODEC
                            .optionalFieldOf(
                                    "dead_coral_asset_id",
                                    SunkenRenderer.EMPTY_DEAD_CORAL_TEXTURE
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
    public static final Codec<ResourceKey<SunkenVariant>> KEY_CODEC =
            ResourceKey.codec(smcm$Registries.SUNKEN_VARIANT);

    public static final StreamCodec<? super RegistryFriendlyByteBuf, ResourceKey<SunkenVariant>> KEY_STREAM_CODEC =
            ResourceKey.streamCodec(smcm$Registries.SUNKEN_VARIANT);

    private SunkenVariant(final ModelAndTexture<SunkenVariant.ModelType> primaryAssetInfo, ClientAsset.ResourceTexture secondaryAssetInfo) {
        this(primaryAssetInfo, secondaryAssetInfo, SpawnPrioritySelectors.EMPTY);
    }

    @Override
    public List<PriorityProvider.Selector<SpawnContext, SpawnCondition>> selectors() {
        return this.spawnConditions.selectors();
        // I LIKE MEN
    }

    static {
        CODEC = RegistryFixedCodec.create(smcm$Registries.SUNKEN_VARIANT);
        STREAM_CODEC = ByteBufCodecs.holderRegistry(smcm$Registries.SUNKEN_VARIANT);
    }

    public enum ModelType implements StringRepresentable {
        NORMAL("normal"),
        FROZEN("frozen"),
        BUBBLE_CORAL("bubble_coral"),
        FIRE_CORAL("fire_coral"),
        HORN_CORAL("horn_coral");

        public static final Codec<SunkenVariant.ModelType> CODEC = StringRepresentable.fromEnum(SunkenVariant.ModelType::values);
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
