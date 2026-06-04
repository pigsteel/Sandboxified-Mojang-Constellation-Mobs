package com.github.pigsteel.smcm.datagen.model;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.registry.smcm$Items;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;

public class SMCMModelProvider extends ModelProvider {
    public SMCMModelProvider(PackOutput output) {
        super(output, SMCM.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        generateItemModels(itemModels);
    }

    public void generateBlockStateModels(BlockModelGenerators blockModels) {
    }

    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generateFlatItem(smcm$Items.BRUISER_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(smcm$Items.FROSTBITTEN_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(smcm$Items.RECLAIMED_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(smcm$Items.ENCHANTER_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(smcm$Items.SUNKEN_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(smcm$Items.LOST_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(smcm$Items.NECROMANCER_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);   }
}
