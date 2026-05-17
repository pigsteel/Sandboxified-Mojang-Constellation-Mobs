package com.github.pigsteel.smcm.platform.neoforge.subscriber;

//? neoforge {
/*import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.core.smcm$EntityTypes;
import com.github.pigsteel.smcm.world.entity.monster.zombie.Frostbitten;
import com.github.pigsteel.smcm.world.entity.monster.zombie.Reclaimed;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

@EventBusSubscriber(modid = SMCM.MOD_ID)
public final class NeoforgeSpawnPlacements {
    private NeoforgeSpawnPlacements() {}

    @SubscribeEvent
    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(
                smcm$EntityTypes.FROSTBITTEN.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Frostbitten::checkFrostbittenSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.REPLACE
        );

        event.register(
                smcm$EntityTypes.RECLAIMED.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING,
                Reclaimed::checkSurfaceMonstersSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.REPLACE
        );
    }
}
*///?}
