package com.github.pigsteel.smcm.datagen.model;

import com.github.pigsteel.smcm.registry.smcm$Items;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;

public class SMCMModelProvider extends FabricModelProvider {
    public SMCMModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generateFlatItem(smcm$Items.BRUISER_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(smcm$Items.FROSTBITTEN_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(smcm$Items.RECLAIMED_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(smcm$Items.ENCHANTER_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
    }

    @Override
    public String getName() {
        return "SMCMModelProvider";
    }
}
