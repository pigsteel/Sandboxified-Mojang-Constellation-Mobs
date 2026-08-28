package com.github.pigsteel.eum.core;

import com.github.pigsteel.eum.EUM;
import com.github.pigsteel.eum.world.entity.monster.VilerWitch;
import com.github.pigsteel.eum.world.entity.monster.Wildfire;
import com.github.pigsteel.eum.world.entity.monster.Wraith;
import com.github.pigsteel.eum.world.entity.monster.illager.Bruiser;
import com.github.pigsteel.eum.world.entity.monster.illager.Enchanter;
import com.github.pigsteel.eum.world.entity.monster.illager.Geomancer;
import com.github.pigsteel.eum.world.entity.monster.illager.Iceologer;
import com.github.pigsteel.eum.world.entity.monster.illager.Mountaineer;
import com.github.pigsteel.eum.world.entity.monster.illager.Windcaller;
import com.github.pigsteel.eum.world.entity.monster.necromancer.Necromancer;
import com.github.pigsteel.eum.world.entity.monster.piglin.PiglinFarmer;
import com.github.pigsteel.eum.world.entity.monster.redstonegolem.RedstoneGolem;
import com.github.pigsteel.eum.world.entity.monster.redstonemonstrosity.RedstoneMonstrosity;
import com.github.pigsteel.eum.world.entity.monster.skeleton.Lost;
import com.github.pigsteel.eum.world.entity.monster.skeleton.Sunken;
import com.github.pigsteel.eum.world.entity.monster.zombie.Frostbitten;
import com.github.pigsteel.eum.world.entity.monster.zombie.Reclaimed;
import com.github.pigsteel.eum.world.entity.monster.zombie.ZombifiedPiglinBrute;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;

import java.util.function.Supplier;

public final class EUMDefaultAttributes {
    private EUMDefaultAttributes() {}

    public static void load() {
        register(EUMEntityTypes.BRUISER, Bruiser::createAttributes);
        register(EUMEntityTypes.ENCHANTER, Enchanter::createAttributes);
        register(EUMEntityTypes.FROSTBITTEN, Frostbitten::createAttributes);
        register(EUMEntityTypes.RECLAIMED, Reclaimed::createAttributes);
        register(EUMEntityTypes.SUNKEN, Sunken::createAttributes);
        register(EUMEntityTypes.LOST, Lost::createAttributes);
        register(EUMEntityTypes.NECROMANCER, Necromancer::createAttributes);
        register(EUMEntityTypes.ZOMBIFIED_PIGLIN_BRUTE, ZombifiedPiglinBrute::createAttributes);
        register(EUMEntityTypes.REDSTONE_GOLEM, RedstoneGolem::createAttributes);
        register(EUMEntityTypes.ICEOLOGER, Iceologer::createMonsterAttributes);
        register(EUMEntityTypes.WINDCALLER, Windcaller::createMonsterAttributes);
        register(EUMEntityTypes.GEOMANCER, Geomancer::createMonsterAttributes);
        register(EUMEntityTypes.PIGLIN_FARMER, PiglinFarmer::createMonsterAttributes);
        register(EUMEntityTypes.VILER_WITCH, VilerWitch::createAttributes);
        register(EUMEntityTypes.MOUNTAINEER, Mountaineer::createAttributes);
		register(EUMEntityTypes.REDSTONE_MONSTROSITY, RedstoneMonstrosity::createMonsterAttributes);
		register(EUMEntityTypes.WILDFIRE, Wildfire::createMonsterAttributes);
		register(EUMEntityTypes.WRAITH, Wraith::createAttributes);
    }

    public static <T extends LivingEntity> void register(Supplier<EntityType<T>> entityType, Supplier<AttributeSupplier.Builder> supplier) {
		EUM.xplat().register(entityType, supplier);
    }
}
