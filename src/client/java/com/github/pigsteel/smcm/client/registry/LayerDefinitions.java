package com.github.pigsteel.smcm.client.registry;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.client.model.monster.illager.EnchanterModel;
import com.github.pigsteel.smcm.client.model.monster.zombie.BabyFrostbittenModel;
import com.github.pigsteel.smcm.client.model.monster.zombie.BabyReclaimedModel;
import com.github.pigsteel.smcm.client.model.monster.zombie.FrostbittenModel;
import com.github.pigsteel.smcm.client.model.monster.zombie.ReclaimedModel;
import com.github.pigsteel.smcm.client.renderer.entity.ReclaimedRenderer;
import com.github.pigsteel.smcm.entity.illager.Bruiser;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshTransformer;
import net.minecraft.client.model.monster.illager.IllagerModel;
import net.minecraft.client.model.monster.piglin.AbstractPiglinModel;
import net.minecraft.client.model.monster.piglin.PiglinModel;
import net.minecraft.client.model.monster.skeleton.SkeletonModel;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.renderer.entity.ArmorModelSet;

public class LayerDefinitions {
    private static final CubeDeformation OUTER_ARMOR_DEFORMATION = new CubeDeformation(1.0F);
    private static final CubeDeformation INNER_ARMOR_DEFORMATION = new CubeDeformation(0.5F);
    private static final CubeDeformation BABY_OUTER_ARMOR_DEFORMATION = new CubeDeformation(-0.1F, 0.5F, 0.3F);
    private static final CubeDeformation BABY_INNER_ARMOR_DEFORMATION = new CubeDeformation(-0.1F, 0.3F, 0.3F);
    private static final CubeDeformation BABY_PIGLIN_INNER_ARMOR_DEFORMATION = new CubeDeformation(0.7F);
    private static final CubeDeformation BABY_PIGLIN_OUTER_ARMOR_DEFORMATION = new CubeDeformation(0.7F);
    private static final PartPose BABY_PIGLIN_ARMOR_ARM_OFFSET = new PartPose(0.5F, -0.5F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);

    public static void registerModelLayers() {
        LayerDefinition illagerBodyLayer = IllagerModel.createBodyLayer().apply(MeshTransformer.scaling(0.9375F));
        //1.0625F
        ArmorModelSet<LayerDefinition> humanoidArmor = HumanoidModel.createArmorMeshSet(INNER_ARMOR_DEFORMATION, OUTER_ARMOR_DEFORMATION)
                .map(mesh -> LayerDefinition.create(mesh, 64, 32));
        ArmorModelSet<LayerDefinition> humanoidBabyArmor = HumanoidModel.createBabyArmorMeshSet(
                        BABY_INNER_ARMOR_DEFORMATION, BABY_OUTER_ARMOR_DEFORMATION, PartPose.ZERO
                )
                .map(mesh -> LayerDefinition.create(mesh, 64, 64));
        ArmorModelSet<LayerDefinition> playerArmor = PlayerModel.createArmorMeshSet(INNER_ARMOR_DEFORMATION, OUTER_ARMOR_DEFORMATION)
                .map(mesh -> LayerDefinition.create(mesh, 64, 32));
        ArmorModelSet<LayerDefinition> piglinArmor = PiglinModel.createArmorMeshSet(INNER_ARMOR_DEFORMATION, new CubeDeformation(1.02F))
                .map(mesh -> LayerDefinition.create(mesh, 64, 32));
        ArmorModelSet<LayerDefinition> piglinBabyArmor = AbstractPiglinModel.createBabyArmorMeshSet(
                        BABY_PIGLIN_INNER_ARMOR_DEFORMATION, BABY_PIGLIN_OUTER_ARMOR_DEFORMATION, BABY_PIGLIN_ARMOR_ARM_OFFSET
                )
                .map(mesh -> LayerDefinition.create(mesh, 64, 64));

        SMCM.LOGGER.debug("Registering model layers for SMCM");
        ModelLayerRegistry.registerModelLayer(ModelLayers.RECLAIMED, () -> ReclaimedModel.createBodyLayer(CubeDeformation.NONE));
        ModelLayerRegistry.registerModelLayer(ModelLayers.RECLAIMED_BABY, () -> BabyReclaimedModel.createBodyLayer(CubeDeformation.NONE));
        registerArmorLayers(ModelLayers.RECLAIMED_ARMOR, humanoidArmor);
        registerArmorLayers(ModelLayers.RECLAIMED_BABY_ARMOR, humanoidBabyArmor);
        ModelLayerRegistry.registerModelLayer(ModelLayers.RECLAIMED_OUTER_LAYER, () -> ReclaimedModel.createBodyLayer(new CubeDeformation(0.25F)));
        ModelLayerRegistry.registerModelLayer(ModelLayers.RECLAIMED_BABY_OUTER_LAYER, () -> BabyReclaimedModel.createBodyLayer(new CubeDeformation(0.25F)));
        ModelLayerRegistry.registerModelLayer(ModelLayers.FROSTBITTEN, () -> FrostbittenModel.createBodyLayer(CubeDeformation.NONE));
        ModelLayerRegistry.registerModelLayer(ModelLayers.FROSTBITTEN_BABY, () -> BabyFrostbittenModel.createBodyLayer(CubeDeformation.NONE));
        registerArmorLayers(ModelLayers.FROSTBITTEN_ARMOR, humanoidArmor);
        registerArmorLayers(ModelLayers.FROSTBITTEN_BABY_ARMOR, humanoidBabyArmor);
        ModelLayerRegistry.registerModelLayer(ModelLayers.FROSTBITTEN_OUTER_LAYER, () -> FrostbittenModel.createBodyLayer(new CubeDeformation(0.25F)));
        ModelLayerRegistry.registerModelLayer(ModelLayers.FROSTBITTEN_BABY_OUTER_LAYER, () -> BabyFrostbittenModel.createBodyLayer(new CubeDeformation(0.25F)));
        ModelLayerRegistry.registerModelLayer(ModelLayers.BRUISER, () -> IllagerModel.createBodyLayer().apply(MeshTransformer.scaling(1.0625F)));
        ModelLayerRegistry.registerModelLayer(ModelLayers.ENCHANTER, () -> EnchanterModel.createBodyLayer().apply(MeshTransformer.scaling(0.9375F)));
        ModelLayerRegistry.registerModelLayer(ModelLayers.SUNKEN, SkeletonModel::createBodyLayer);
        registerArmorLayers(ModelLayers.SUNKEN_ARMOR, humanoidArmor);
    }

    public static void registerArmorLayers(final ArmorModelSet<ModelLayerLocation> location, ArmorModelSet<LayerDefinition> modelSet) {
        ModelLayerRegistry.registerModelLayer(location.head(), modelSet::head);
        ModelLayerRegistry.registerModelLayer(location.chest(), modelSet::chest);
        ModelLayerRegistry.registerModelLayer(location.legs(), modelSet::legs);
        ModelLayerRegistry.registerModelLayer(location.feet(), modelSet::feet);
    }

}
