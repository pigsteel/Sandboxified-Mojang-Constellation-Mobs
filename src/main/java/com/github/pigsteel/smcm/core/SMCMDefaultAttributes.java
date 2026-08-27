package com.github.pigsteel.smcm.core;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.world.entity.monster.VilerWitch;
import com.github.pigsteel.smcm.world.entity.monster.Wildfire;
import com.github.pigsteel.smcm.world.entity.monster.Wraith;
import com.github.pigsteel.smcm.world.entity.monster.illager.Bruiser;
import com.github.pigsteel.smcm.world.entity.monster.illager.Enchanter;
import com.github.pigsteel.smcm.world.entity.monster.illager.Geomancer;
import com.github.pigsteel.smcm.world.entity.monster.illager.Iceologer;
import com.github.pigsteel.smcm.world.entity.monster.illager.Mountaineer;
import com.github.pigsteel.smcm.world.entity.monster.illager.Windcaller;
import com.github.pigsteel.smcm.world.entity.monster.necromancer.Necromancer;
import com.github.pigsteel.smcm.world.entity.monster.piglin.PiglinFarmer;
import com.github.pigsteel.smcm.world.entity.monster.redstonegolem.RedstoneGolem;
import com.github.pigsteel.smcm.world.entity.monster.redstonemonstrosity.RedstoneMonstrosity;
import com.github.pigsteel.smcm.world.entity.monster.skeleton.Lost;
import com.github.pigsteel.smcm.world.entity.monster.skeleton.Sunken;
import com.github.pigsteel.smcm.world.entity.monster.zombie.Frostbitten;
import com.github.pigsteel.smcm.world.entity.monster.zombie.Reclaimed;
import com.github.pigsteel.smcm.world.entity.monster.zombie.ZombifiedPiglinBrute;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;

import java.util.function.Supplier;

public final class SMCMDefaultAttributes {
    private SMCMDefaultAttributes() {}

    public static void load() {
        register(SMCMEntityTypes.BRUISER, Bruiser::createAttributes);
        register(SMCMEntityTypes.ENCHANTER, Enchanter::createAttributes);
        register(SMCMEntityTypes.FROSTBITTEN, Frostbitten::createAttributes);
        register(SMCMEntityTypes.RECLAIMED, Reclaimed::createAttributes);
        register(SMCMEntityTypes.SUNKEN, Sunken::createAttributes);
        register(SMCMEntityTypes.LOST, Lost::createAttributes);
        register(SMCMEntityTypes.NECROMANCER, Necromancer::createAttributes);
        register(SMCMEntityTypes.ZOMBIFIED_PIGLIN_BRUTE, ZombifiedPiglinBrute::createAttributes);
        register(SMCMEntityTypes.REDSTONE_GOLEM, RedstoneGolem::createAttributes);
        register(SMCMEntityTypes.ICEOLOGER, Iceologer::createMonsterAttributes);
        register(SMCMEntityTypes.WINDCALLER, Windcaller::createMonsterAttributes);
        register(SMCMEntityTypes.GEOMANCER, Geomancer::createMonsterAttributes);
        register(SMCMEntityTypes.PIGLIN_FARMER, PiglinFarmer::createMonsterAttributes);
        register(SMCMEntityTypes.VILER_WITCH, VilerWitch::createAttributes);
        register(SMCMEntityTypes.MOUNTAINEER, Mountaineer::createAttributes);
		register(SMCMEntityTypes.REDSTONE_MONSTROSITY, RedstoneMonstrosity::createMonsterAttributes);
		register(SMCMEntityTypes.WILDFIRE, Wildfire::createMonsterAttributes);
		register(SMCMEntityTypes.WRAITH, Wraith::createMonsterAttributes);
    }

    public static <T extends LivingEntity> void register(Supplier<EntityType<T>> entityType, Supplier<AttributeSupplier.Builder> supplier) {
		SMCM.xplat().register(entityType, supplier);
    }
}
