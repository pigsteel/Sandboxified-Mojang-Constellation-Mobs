package com.github.pigsteel.smcm.entity;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Shearable;
import net.minecraft.world.entity.animal.cow.AbstractCow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.Nullable;

import java.util.function.IntFunction;

public class FlowerCow extends AbstractCow implements Shearable {

    public FlowerCow(EntityType<? extends AbstractCow> type, Level level) {
        super(type, level);
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel level, AgeableMob partner) {
        return null;
    }

    @Override
    public void shear(ServerLevel level, SoundSource soundSource, ItemStack tool) {

    }

    @Override
    public boolean readyForShearing() {
        return false;
    }

    public static enum Variant implements StringRepresentable {
        BUTTERCUP("buttercup", 0, Blocks.RED_MUSHROOM.defaultBlockState()), // replace these with flowers
        TULIP("tulip", 1, Blocks.BROWN_MUSHROOM.defaultBlockState());

        public static final Variant DEFAULT = BUTTERCUP;
        public static final Codec<Variant> CODEC = StringRepresentable.fromEnum(Variant::values);
        private static final IntFunction<Variant> BY_ID = ByIdMap.continuous(Variant::id, values(), ByIdMap.OutOfBoundsStrategy.CLAMP);
        public static final StreamCodec<ByteBuf, Variant> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, Variant::id);
        private final String type;
        private final int id;
        private final BlockState blockState;

        private Variant(final String type, final int id, final BlockState blockState) {
            this.type = type;
            this.id = id;
            this.blockState = blockState;
        }

        public BlockState getBlockState() {
            return this.blockState;
        }

        @Override
        public String getSerializedName() {
            return this.type;
        }

        private int id() {
            return this.id;
        }

        private static Variant byId(final int id) {
            return (Variant)BY_ID.apply(id);
        }
    }
}
