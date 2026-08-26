package com.github.pigsteel.smcm.core;

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
//? fabric {
/*import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
*///?} neoforge {
import com.github.pigsteel.smcm.platform.neoforge.NeoforgeVariables;
import static com.github.pigsteel.smcm.platform.neoforge.NeoforgeVariables.DEFAULT_ATTRIBUTES;
//?}

public final class SMCMDefaultAttributes {
    private SMCMDefaultAttributes() {}

    public static void load() {
        registerAttributes(SMCMEntityTypes.BRUISER, Bruiser::createAttributes);
        registerAttributes(SMCMEntityTypes.ENCHANTER, Enchanter::createAttributes);
        registerAttributes(SMCMEntityTypes.FROSTBITTEN, Frostbitten::createAttributes);
        registerAttributes(SMCMEntityTypes.RECLAIMED, Reclaimed::createAttributes);
        registerAttributes(SMCMEntityTypes.SUNKEN, Sunken::createAttributes);
        registerAttributes(SMCMEntityTypes.LOST, Lost::createAttributes);
        registerAttributes(SMCMEntityTypes.NECROMANCER, Necromancer::createAttributes);
        registerAttributes(SMCMEntityTypes.ZOMBIFIED_PIGLIN_BRUTE, ZombifiedPiglinBrute::createAttributes);
        registerAttributes(SMCMEntityTypes.REDSTONE_GOLEM, RedstoneGolem::createAttributes);
        registerAttributes(SMCMEntityTypes.ICEOLOGER, Iceologer::createMonsterAttributes);
        registerAttributes(SMCMEntityTypes.WINDCALLER, Windcaller::createMonsterAttributes);
        registerAttributes(SMCMEntityTypes.GEOMANCER, Geomancer::createMonsterAttributes);
        registerAttributes(SMCMEntityTypes.PIGLIN_FARMER, PiglinFarmer::createMonsterAttributes);
        registerAttributes(SMCMEntityTypes.VILER_WITCH, VilerWitch::createAttributes);
        registerAttributes(SMCMEntityTypes.MOUNTAINEER, Mountaineer::createAttributes);
		registerAttributes(SMCMEntityTypes.REDSTONE_MONSTROSITY, RedstoneMonstrosity::createMonsterAttributes);
		registerAttributes(SMCMEntityTypes.WILDFIRE, Wildfire::createMonsterAttributes);
		registerAttributes(SMCMEntityTypes.WRAITH, Wraith::createMonsterAttributes);
    }

    public static <T extends LivingEntity> void registerAttributes(Supplier<EntityType<T>> entityType, Supplier<AttributeSupplier.Builder> supplier) {
        //? fabric {
        /*FabricDefaultAttributeRegistry.register(entityType.get(), supplier.get());
        *///?}
        //? neoforge {
		DEFAULT_ATTRIBUTES.add(new NeoforgeVariables.DefaultAttributesDeferred<>(entityType, supplier));
        //?}
    }
}
