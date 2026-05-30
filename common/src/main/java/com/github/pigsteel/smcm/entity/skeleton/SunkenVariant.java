package com.github.pigsteel.smcm.entity.skeleton;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.ClientAsset;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.RegistryFixedCodec;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.animal.cow.CowVariant;
import net.minecraft.world.entity.variant.*;

import java.util.List;

public record SunkenVariant(ModelAndTexture<ModelType> modelAndTexture, SpawnPrioritySelectors spawnConditions) implements PriorityProvider<SpawnContext, SpawnCondition> {
    public static final Codec<SunkenVariant> DIRECT_CODEC = RecordCodecBuilder.create((i) -> i.group(ModelAndTexture.codec(SunkenVariant.ModelType.CODEC, SunkenVariant.ModelType.NORMAL).forGetter(SunkenVariant::modelAndTexture), SpawnPrioritySelectors.CODEC.fieldOf("spawn_conditions").forGetter(SunkenVariant::spawnConditions)).apply(i, SunkenVariant::new));
    public static final Codec<SunkenVariant> NETWORK_CODEC = RecordCodecBuilder.create((i) -> i.group(ModelAndTexture.codec(SunkenVariant.ModelType.CODEC, SunkenVariant.ModelType.NORMAL).forGetter(SunkenVariant::modelAndTexture)).apply(i, SunkenVariant::new));
    //public static final Codec<Holder<SunkenVariant>> CODEC;
    //public static final StreamCodec<RegistryFriendlyByteBuf, Holder<SunkenVariant>> STREAM_CODEC;

    private SunkenVariant(final ModelAndTexture<SunkenVariant.ModelType> assetInfo) {
        this(assetInfo, SpawnPrioritySelectors.EMPTY);
    }

    @Override
    public List<PriorityProvider.Selector<SpawnContext, SpawnCondition>> selectors() {
        return this.spawnConditions.selectors();
    }

    static {
        //CODEC = RegistryFixedCodec.create(null);
        //STREAM_CODEC = ByteBufCodecs.holderRegistry(null);
    }

    public enum ModelType implements StringRepresentable {
        NORMAL("normal"),
        COLD("cold"),
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
    }
}
