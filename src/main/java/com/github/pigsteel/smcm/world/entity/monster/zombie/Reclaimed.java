package com.github.pigsteel.smcm.world.entity.monster.zombie;

import com.github.pigsteel.smcm.core.smcm$DataComponents;
import com.github.pigsteel.smcm.core.smcm$LootTables;
import com.github.pigsteel.smcm.core.smcm$SoundEvents;
import com.github.pigsteel.smcm.world.entity.Bonemealable;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Shearable;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.function.IntFunction;

// Just a poison zombie lol // WRONG!!!!!!!!!!!!!!!!!!!!
public class Reclaimed extends Zombie implements Bonemealable, Shearable, RangedAttackMob {
    private static final EntityDataAccessor<Optional<BlockState>> DATA_HEAD_FLOWER_STATE = SynchedEntityData.defineId(Reclaimed.class, EntityDataSerializers.OPTIONAL_BLOCK_STATE);
	private int projectilesToVomit;

    public Reclaimed(EntityType<? extends Reclaimed> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Zombie.createAttributes();
    }

    @Override
    public boolean doHurtTarget(final ServerLevel level, final Entity target) {
        boolean result = super.doHurtTarget(level, target);
        if (result && this.getMainHandItem().isEmpty() && target instanceof LivingEntity) {
            float difficulty = level.getCurrentDifficultyAt(this.blockPosition()).getEffectiveDifficulty();
            ((LivingEntity)target).addEffect(new MobEffectInstance(MobEffects.POISON, 140 * (int)difficulty), this);
        }

        return result;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_HEAD_FLOWER_STATE, Optional.empty());
    }

    @Override
    protected void addAdditionalSaveData(final ValueOutput output) {
        super.addAdditionalSaveData(output);
        BlockState blockState = this.getHeadFlower();
        if (blockState != null) {
            output.store("HeadFlower", BlockState.CODEC, blockState);
        }
    }

    @Override
    protected void readAdditionalSaveData(final ValueInput input) {
        super.readAdditionalSaveData(input);
        this.setHeadFlower((BlockState)input.read("HeadFlower", BlockState.CODEC).orElse(null));
    }

    @Override
    public <T> @Nullable T get(final DataComponentType<? extends T> type) {
        if (type == smcm$DataComponents.RECLAIMED_HEAD_FLOWER.get()) {
            HeadFlower flower = this.getHeadFlowerType();

            return flower == null
                    ? null
                    : (T) castComponentValue(smcm$DataComponents.RECLAIMED_HEAD_FLOWER.get(), flower);
        }

        return super.get(type);
    }

    @Nullable
    public HeadFlower getHeadFlowerType() {
        return HeadFlower.byBlockState(this.getHeadFlower());
    }

    @Override
    public InteractionResult mobInteract(final Player player, final InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (itemStack.is(Items.BONE_MEAL) && this.canBonemeal()) {
            if (this.level() instanceof ServerLevel level) {
                this.bonemeal(level, SoundSource.PLAYERS, itemStack);
                this.gameEvent(GameEvent.ITEM_INTERACT_FINISH, player);
                itemStack.consume(1, player);
            }

            return InteractionResult.SUCCESS;
        } else if (itemStack.is(Items.SHEARS) && this.readyForShearing()){
            if (this.level() instanceof ServerLevel level) {
                this.shear(level, SoundSource.PLAYERS, itemStack);
                this.gameEvent(GameEvent.SHEAR, player);
                itemStack.hurtAndBreak(1, player, hand.asEquipmentSlot());
            }

            return InteractionResult.SUCCESS;
        } else {
            return super.mobInteract(player, hand);
        }
    }

    public boolean isSheared() {
        return this.entityData.get(DATA_HEAD_FLOWER_STATE).isEmpty();
    }

    @Nullable
    public BlockState getHeadFlower() {
        return this.entityData.get(DATA_HEAD_FLOWER_STATE).orElse(null);
    }

    public void setHeadFlower(@Nullable final BlockState flower) {
        this.entityData.set(DATA_HEAD_FLOWER_STATE, Optional.ofNullable(flower));
        if(flower != null && !this.isPersistenceRequired()) this.setPersistenceRequired();
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return smcm$SoundEvents.RECLAIMED_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(final DamageSource source) {
        return smcm$SoundEvents.RECLAIMED_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return smcm$SoundEvents.RECLAIMED_DEATH.get();
    }

    @Override
    protected SoundEvent getStepSound() {
        return smcm$SoundEvents.RECLAIMED_STEP.get();
    }

    private static final BlockState[] HEAD_FLOWERS = new BlockState[] {
            Blocks.CORNFLOWER.defaultBlockState(),
            Blocks.DANDELION.defaultBlockState(),
            Blocks.POPPY.defaultBlockState(),
            Blocks.BLUE_ORCHID.defaultBlockState(),
            Blocks.ALLIUM.defaultBlockState(),
            Blocks.AZURE_BLUET.defaultBlockState(),
            Blocks.OXEYE_DAISY.defaultBlockState(),
            Blocks.LILY_OF_THE_VALLEY.defaultBlockState(),
            Blocks.RED_TULIP.defaultBlockState(),
            Blocks.WHITE_TULIP.defaultBlockState(),
            Blocks.PINK_TULIP.defaultBlockState()
    }; //temp

    @Override
    public void bonemeal(ServerLevel level, SoundSource soundSource, ItemStack bonemealItem) {
        level.playSound(null, this, smcm$SoundEvents.RECLAIMED_BONEMEAL.get(), soundSource, 1.0F, 1.0F);
        this.setHeadFlower(
                HEAD_FLOWERS[this.random.nextInt(HEAD_FLOWERS.length)]
        );
        // scuffed temporarily
    }

    @Override
    public boolean canBonemeal() {
        return this.isSheared() && this.isAlive() && !this.isBaby();
    }

    @Override
    public void shear(ServerLevel level, SoundSource soundSource, ItemStack tool) {
        level.playSound(null, this, smcm$SoundEvents.RECLAIMED_SHEAR.get(), soundSource, 1.0F, 1.0F);
        // check the flower and then take it off ()
        this.dropFromShearingLootTable(
                level,
                smcm$LootTables.SHEAR_RECLAIMED,
                tool,
                (l, drop) -> this.spawnAtLocation(l, drop, this.getEyeHeight())
        );
        this.setHeadFlower(null);
    }

    @Override
    public boolean readyForShearing() {
        return !this.isSheared() && this.isAlive() && !this.isBaby();
    }

    @Override
    protected void applyImplicitComponents(final DataComponentGetter components) {
        this.applyImplicitComponentIfPresent(components, smcm$DataComponents.RECLAIMED_HEAD_FLOWER.get());
        super.applyImplicitComponents(components);
    }

    @Override
    protected <T> boolean applyImplicitComponent(final DataComponentType<T> type, final T value) {
        if (type == smcm$DataComponents.RECLAIMED_HEAD_FLOWER.get()) {
            this.setHeadFlower(castComponentValue(smcm$DataComponents.RECLAIMED_HEAD_FLOWER.get(), value).defaultBlockState());
            return true;
        } else {
            return super.applyImplicitComponent(type, value);
        }
    }

	@Override
	protected void customServerAiStep(final ServerLevel level) {
		LivingEntity target = this.getTarget();
		if (target == null)

		super.customServerAiStep(level);
	}

	@Override
	public void performRangedAttack(LivingEntity target, float power) {
		if(!this.getTarget().is(target)) this.setTarget(target);

		this.setProjectilesToVomit(5);
	}

	public void setProjectilesToVomit(int amount) {
		this.projectilesToVomit = amount;
	}

	public enum HeadFlower implements StringRepresentable {
        CORNFLOWER(0, "cornflower", Blocks.CORNFLOWER),
        DANDELION(1, "dandelion", Blocks.DANDELION),
        POPPY(2, "poppy", Blocks.POPPY),
        BLUE_ORCHID(3, "blue_orchid", Blocks.BLUE_ORCHID),
        ALLIUM(4, "allium", Blocks.ALLIUM),
        AZURE_BLUET(5, "azure_bluet", Blocks.AZURE_BLUET),
        OXEYE_DAISY(6, "oxeye_daisy", Blocks.OXEYE_DAISY),
        LILY_OF_THE_VALLEY(7, "lily_of_the_valley", Blocks.LILY_OF_THE_VALLEY),
        RED_TULIP(8, "red_tulip", Blocks.RED_TULIP),
        ORANGE_TULIP(9, "orange_tulip", Blocks.ORANGE_TULIP),
        WHITE_TULIP(10, "white_tulip", Blocks.WHITE_TULIP),
        PINK_TULIP(11, "pink_tulip", Blocks.PINK_TULIP);

        public static final List<HeadFlower> VALUES = List.of(values());

        private static final IntFunction<HeadFlower> BY_ID = ByIdMap.continuous(
                HeadFlower::getId,
                VALUES.toArray(HeadFlower[]::new),
                ByIdMap.OutOfBoundsStrategy.ZERO
        );

        public static final EnumCodec<HeadFlower> CODEC =
                StringRepresentable.fromEnum(HeadFlower::values);

        public static final StreamCodec<ByteBuf, HeadFlower> STREAM_CODEC =
                ByteBufCodecs.idMapper(BY_ID, HeadFlower::getId);

        private final int id;
        private final String name;
        private final Block block;

        HeadFlower(final int id, final String name, final Block block) {
            this.id = id;
            this.name = name;
            this.block = block;
        }

        public int getId() {
            return this.id;
        }

        public String getName() {
            return this.name;
        }

        public BlockState defaultBlockState() {
            return this.block.defaultBlockState();
        }

        @Nullable
        public static HeadFlower byBlockState(@Nullable final BlockState state) {
            if (state == null) {
                return null;
            }

            Block block = state.getBlock();

            for (HeadFlower value : VALUES) {
                if (value.block == block) {
                    return value;
                }
            }

            return null;
        }

        @Nullable
        public static HeadFlower byName(final String name, @Nullable final HeadFlower def) {
            for (HeadFlower value : VALUES) {
                if (value.name.equals(name)) {
                    return value;
                }
            }

            return def;
        }

        @Override
        public String getSerializedName() {
            return this.name;
        }
    }
}
