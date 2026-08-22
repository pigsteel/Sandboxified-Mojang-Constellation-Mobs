package com.github.pigsteel.smcm.world.entity.projectile;

import com.github.pigsteel.smcm.core.SMCMEntityTypes;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;

public class ReclaimedPuke extends Projectile {

    public ReclaimedPuke(final EntityType<? extends ReclaimedPuke> type, final Level level) {
        super(type, level);
    }

	public ReclaimedPuke(double x, double y, double z, Level level) {
		this(SMCMEntityTypes.RECLAIMED_PUKE.get(), level);
		this.setPos(x, y, z);
	}

	public ReclaimedPuke(final LivingEntity owner, Level level) {
		this(owner.getX(), owner.getEyeY() - (double)0.1F, owner.getZ(), level);
		this.setOwner(owner);
	}

    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
    }

    public void tick() {
        super.tick();
        if (this.level().isClientSide()) {
            //this.level().addParticle(smcm$EntityType.RECLAIMED_PUKE_PARTICLE.get(), this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
        }
    }
}
