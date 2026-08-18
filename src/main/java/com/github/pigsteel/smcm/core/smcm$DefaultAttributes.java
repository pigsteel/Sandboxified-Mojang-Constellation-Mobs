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
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
//?} neoforge {
/*import com.github.pigsteel.smcm.platform.neoforge.NeoforgeVariables;
import static com.github.pigsteel.smcm.platform.neoforge.NeoforgeVariables.DEFAULT_ATTRIBUTES;
*///?}

public final class smcm$DefaultAttributes {
    private smcm$DefaultAttributes() {}

    public static void register() {
        registerAttributes(smcm$EntityTypes.BRUISER, Bruiser::createAttributes);
        registerAttributes(smcm$EntityTypes.ENCHANTER, Enchanter::createAttributes);
        registerAttributes(smcm$EntityTypes.FROSTBITTEN, Frostbitten::createAttributes);
        registerAttributes(smcm$EntityTypes.RECLAIMED, Reclaimed::createAttributes);
        registerAttributes(smcm$EntityTypes.SUNKEN, Sunken::createAttributes);
        registerAttributes(smcm$EntityTypes.LOST, Lost::createAttributes);
        registerAttributes(smcm$EntityTypes.NECROMANCER, Necromancer::createAttributes);
        registerAttributes(smcm$EntityTypes.ZOMBIFIED_PIGLIN_BRUTE, ZombifiedPiglinBrute::createAttributes);
        registerAttributes(smcm$EntityTypes.REDSTONE_GOLEM, RedstoneGolem::createAttributes);
        registerAttributes(smcm$EntityTypes.ICEOLOGER, Iceologer::createMonsterAttributes);
        registerAttributes(smcm$EntityTypes.WINDCALLER, Windcaller::createMonsterAttributes);
        registerAttributes(smcm$EntityTypes.GEOMANCER, Geomancer::createMonsterAttributes);
        registerAttributes(smcm$EntityTypes.PIGLIN_FARMER, PiglinFarmer::createMonsterAttributes);
        registerAttributes(smcm$EntityTypes.VILER_WITCH, VilerWitch::createAttributes);
        registerAttributes(smcm$EntityTypes.MOUNTAINEER, Mountaineer::createAttributes);
		registerAttributes(smcm$EntityTypes.REDSTONE_MONSTROSITY, RedstoneMonstrosity::createMonsterAttributes);
		registerAttributes(smcm$EntityTypes.WILDFIRE, Wildfire::createMonsterAttributes);
		registerAttributes(smcm$EntityTypes.WRAITH, Wraith::createMonsterAttributes);
    }

    public static <T extends LivingEntity> void registerAttributes(Supplier<EntityType<T>> entityType, Supplier<AttributeSupplier.Builder> supplier) {
        //? fabric {
        FabricDefaultAttributeRegistry.register(entityType.get(), supplier.get());
        //?}
        //? neoforge {
		/*DEFAULT_ATTRIBUTES.add(new NeoforgeVariables.DefaultAttributesDeferred<>(entityType, supplier));
        *///?}
    }
}
