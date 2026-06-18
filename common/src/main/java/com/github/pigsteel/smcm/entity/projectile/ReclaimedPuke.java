package com.github.pigsteel.smcm.entity.projectile;

import com.github.pigsteel.smcm.entity.zombie.Reclaimed;
import com.github.pigsteel.smcm.registry.smcm$EntityTypes;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;

public class ReclaimedPuke extends Projectile {


    protected ReclaimedPuke(EntityType<? extends Projectile> type, Level level) {
        super(type, level);
    }

    public ReclaimedPuke (Level level, Reclaimed owner) {
        this(smcm$EntityTypes.RECLAIMED_PUKE.get(), level);
        this.setOwner(owner);
    }

    protected void defineSynchedData(SynchedEntityData.Builder builder) {
    }

    public void tick() {
        super.tick();
        if (this.level() != null && this.level().isClientSide()) {
            //this.level().addParticle(smcm$EntityType.RECLAIMED_PUKE_PARTICLE.get(), this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
        }
    }
}
