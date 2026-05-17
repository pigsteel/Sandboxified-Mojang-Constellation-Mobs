package com.github.pigsteel.smcm.world.entity.monster.redstonegolem;

import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.Nullable;

public class RedstoneGolemPart extends Entity {
    public final RedstoneGolem parentMob;
    public final String name;
    private final EntityDimensions size;

    public RedstoneGolemPart(final RedstoneGolem parentMob, final String name, final float w, final float h) {
        super(parentMob.getType(), parentMob.level());
        this.size = EntityDimensions.scalable(w, h);
        this.refreshDimensions();
        this.parentMob = parentMob;
        this.name = name;
    }

    @Override
    public boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
        return !this.isInvulnerableToBase(source) && this.parentMob.hurt(level, this, source, damage);
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
    }

    @Override
    public boolean isPickable() {
        return true;
    }

    @Override
    public @Nullable ItemStack getPickResult() {
        return this.parentMob.getPickResult();
    }

    @Override
    public boolean is(final Entity other) {
        return this == other || this.parentMob == other;
    }

    @Override
    public EntityDimensions getDimensions(final Pose pose) {
        return this.size;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
    }
}
