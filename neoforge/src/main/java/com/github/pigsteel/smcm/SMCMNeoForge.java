package com.github.pigsteel.smcm;

import com.github.pigsteel.smcm.registry.SMCMNeoForgeItemGroups;
import com.github.pigsteel.smcm.registry.SMCMNeoForgeSpawnPlacements;
import com.github.pigsteel.smcm.services.IAttributeRegistryHelper;
import com.github.pigsteel.smcm.services.NeoForgeAttachmentRegistryHelper;
import com.github.pigsteel.smcm.services.NeoForgeRegistryHelper;
import com.github.pigsteel.smcm.services.Services;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@Mod(SMCM.MOD_ID)
public class SMCMNeoForge {

    public SMCMNeoForge(IEventBus eventBus) {

        // This method is invoked by the NeoForge mod loader when it is ready
        // to load your mod. You can access NeoForge and Common code in this
        // project.

        // Use NeoForge to bootstrap the Common mod.
        SMCM.LOGGER.info("Hello NeoForge world!");
        SMCM.init();

        eventBus.addListener(SMCMNeoForgeDatagen::onGatherClientData);
        eventBus.addListener(SMCMNeoForge::onEntityAttributeCreation);
        eventBus.addListener(SMCMNeoForgeSpawnPlacements::registerSpawnPlacements);
        eventBus.addListener(SMCMNeoForgeItemGroups::modifyCreativeTabs);

        NeoForgeAttachmentRegistryHelper.ATTACHMENT_TYPES.register(eventBus);
        NeoForgeRegistryHelper.register(eventBus);
    }

    private static void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
        Services.ATTRIBUTES.applyEntityAttributeRegistrations(new IAttributeRegistryHelper.EntityAttributeRegistrar() {
            @Override
            public <T extends LivingEntity> void register(EntityType<T> entityType, AttributeSupplier.Builder builder) {
                event.put(entityType, builder.build());
            }
        });
    }
}