package com.github.pigsteel.smcm.datagen.model;

//? fabric {
import com.github.pigsteel.smcm.core.smcm$Items;
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
    public void generateBlockStateModels(BlockModelGenerators blockModels) {
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generateFlatItem(smcm$Items.BRUISER_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(smcm$Items.FROSTBITTEN_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(smcm$Items.RECLAIMED_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(smcm$Items.ENCHANTER_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(smcm$Items.SUNKEN_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(smcm$Items.LOST_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(smcm$Items.NECROMANCER_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(smcm$Items.ICEOLOGER_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(smcm$Items.ZOMBIFIED_PIGLIN_BRUTE_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(smcm$Items.PIGLIN_FARMER_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(smcm$Items.WINDCALLER_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(smcm$Items.MOUNTAINEER_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(smcm$Items.GEOMANCER_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(smcm$Items.REDSTONE_GOLEM_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(smcm$Items.VILER_WITCH_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
    }
}
//?}
