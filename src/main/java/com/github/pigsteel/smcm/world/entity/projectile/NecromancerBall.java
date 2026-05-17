package com.github.pigsteel.smcm.world.entity.projectile;

import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;

public class NecromancerBall extends Projectile {
	public NecromancerBall(EntityType<? extends Projectile> type, Level level) {
		super(type, level);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder entityData) {

	}
}
