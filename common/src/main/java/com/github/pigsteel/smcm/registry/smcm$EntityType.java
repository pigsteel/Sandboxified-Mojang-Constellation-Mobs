package com.github.pigsteel.smcm.registry;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.entity.Necromancer;
import com.github.pigsteel.smcm.entity.illager.Bruiser;
import com.github.pigsteel.smcm.entity.illager.Enchanter;
import com.github.pigsteel.smcm.entity.projectile.ReclaimedPuke;
import com.github.pigsteel.smcm.entity.skeleton.Lost;
import com.github.pigsteel.smcm.entity.skeleton.Sunken;
import com.github.pigsteel.smcm.entity.zombie.Frostbitten;
import com.github.pigsteel.smcm.entity.zombie.Reclaimed;
import com.github.pigsteel.smcm.services.Services;
import com.github.pigsteel.smcm.services.util.RegistryHandle;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;

public class smcm$EntityType {
    public static RegistryHandle<EntityType<Bruiser>> BRUISER;
    public static RegistryHandle<EntityType<Enchanter>> ENCHANTER;
    public static RegistryHandle<EntityType<Frostbitten>> FROSTBITTEN;
    public static RegistryHandle<EntityType<Reclaimed>> RECLAIMED;
    public static RegistryHandle<EntityType<ReclaimedPuke>> RECLAIMED_PUKE;
    public static RegistryHandle<EntityType<Sunken>> SUNKEN;
    public static RegistryHandle<EntityType<Lost>> LOST;
    public static RegistryHandle<EntityType<Necromancer>> NECROMANCER;

    static {

        BRUISER = Services.REGISTRY.registerEntityType(
                "bruiser",
                EntityType.Builder.of(Bruiser::new, MobCategory.MONSTER)
                        .sized(0.6F, 1.95F)
                        .passengerAttachments(2.0F)
                        .ridingOffset(-0.6F)
                        .clientTrackingRange(8)
                        .notInPeaceful());

        ENCHANTER = Services.REGISTRY.registerEntityType("enchanter", EntityType.Builder.of(Enchanter::new, MobCategory.MONSTER)
                .sized(0.6F, 1.95F)
                .passengerAttachments(2.0F)
                .ridingOffset(-0.6F)
                .clientTrackingRange(8)
                .notInPeaceful());

        FROSTBITTEN = Services.REGISTRY.registerEntityType("frostbitten", EntityType.Builder.of(Frostbitten::new, MobCategory.MONSTER)
                .sized(0.6F, 1.95F)
                .eyeHeight(1.74F)
                .passengerAttachments(2.075F)
                .ridingOffset(-0.7F)
                .immuneTo(Blocks.POWDER_SNOW)
                .clientTrackingRange(8)
                .notInPeaceful());

        RECLAIMED = Services.REGISTRY.registerEntityType("reclaimed", EntityType.Builder.of(Reclaimed::new, MobCategory.MONSTER)
                .sized(0.6F, 1.95F)
                .eyeHeight(1.74F)
                .passengerAttachments(2.075F)
                .ridingOffset(-0.7F)
                .clientTrackingRange(8)
                .notInPeaceful());

        /*
        RECLAIMED_PUKE = Services.REGISTRY.registerEntityType("reclaimed_puke", EntityType.Builder.of(ReclaimedPuke::new, MobCategory.MISC))
                .sized(0.25F, 0.25F)
                .clientTrackingRange(4)
                .updateInterval(10)
                .suppressSuffocationChecks()
                .suppressVehicleSaving();

         */

        SUNKEN = Services.REGISTRY.registerEntityType("sunken", EntityType.Builder.of(Sunken::new, MobCategory.MONSTER)
                .sized(0.6F, 1.99F)
                .eyeHeight(1.74F)
                .ridingOffset(-0.7F)
                .clientTrackingRange(8)
                .notInPeaceful());

        LOST = Services.REGISTRY.registerEntityType("lost", EntityType.Builder.of(Lost::new, MobCategory.MONSTER)
                .sized(0.6F, 1.95F)
                .eyeHeight(1.74F)
                .ridingOffset(-0.7F)
                .clientTrackingRange(8)
                .notInPeaceful());

        NECROMANCER = Services.REGISTRY.registerEntityType("necromancer", EntityType.Builder.of(Necromancer::new, MobCategory.MONSTER)
                .sized(0.7F, 2.4F)
                .eyeHeight(2.1F)
                .ridingOffset(-0.875F)
                .clientTrackingRange(16)
                .notInPeaceful());
    }

    public static void load() {} // does nothing, initializes the static block
}
