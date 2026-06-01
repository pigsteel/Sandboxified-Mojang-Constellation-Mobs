package com.github.pigsteel.smcm.registry;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.client.model.monster.illager.EnchanterModel;
import com.github.pigsteel.smcm.client.model.monster.skeleton.SunkenModel;
import com.github.pigsteel.smcm.client.model.monster.zombie.BabyFrostbittenModel;
import com.github.pigsteel.smcm.client.model.monster.zombie.BabyReclaimedModel;
import com.github.pigsteel.smcm.client.model.monster.zombie.FrostbittenModel;
import com.github.pigsteel.smcm.client.model.monster.zombie.ReclaimedModel;
import com.github.pigsteel.smcm.services.client.IClientRegistryHelper;
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

    public static void registerModelLayers(IClientRegistryHelper registrar) {
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
        registrar.registerModelLayer(smcm$ModelLayers.RECLAIMED, () -> ReclaimedModel.createBodyLayer(CubeDeformation.NONE));
        registrar.registerModelLayer(smcm$ModelLayers.RECLAIMED_BABY, () -> BabyReclaimedModel.createBodyLayer(CubeDeformation.NONE));
        registerArmorLayers(smcm$ModelLayers.RECLAIMED_ARMOR, humanoidArmor, registrar);
        registerArmorLayers(smcm$ModelLayers.RECLAIMED_BABY_ARMOR, humanoidBabyArmor, registrar);
        registrar.registerModelLayer(smcm$ModelLayers.RECLAIMED_OUTER_LAYER, () -> ReclaimedModel.createBodyLayer(new CubeDeformation(0.25F)));
        registrar.registerModelLayer(smcm$ModelLayers.RECLAIMED_BABY_OUTER_LAYER, () -> BabyReclaimedModel.createBodyLayer(new CubeDeformation(0.25F)));
        registrar.registerModelLayer(smcm$ModelLayers.FROSTBITTEN, () -> FrostbittenModel.createBodyLayer(CubeDeformation.NONE));
        registrar.registerModelLayer(smcm$ModelLayers.FROSTBITTEN_BABY, () -> BabyFrostbittenModel.createBodyLayer(CubeDeformation.NONE));
        registerArmorLayers(smcm$ModelLayers.FROSTBITTEN_ARMOR, humanoidArmor, registrar);
        registerArmorLayers(smcm$ModelLayers.FROSTBITTEN_BABY_ARMOR, humanoidBabyArmor, registrar);
        registrar.registerModelLayer(smcm$ModelLayers.FROSTBITTEN_OUTER_LAYER, () -> FrostbittenModel.createBodyLayer(new CubeDeformation(0.25F)));
        registrar.registerModelLayer(smcm$ModelLayers.FROSTBITTEN_BABY_OUTER_LAYER, () -> BabyFrostbittenModel.createBodyLayer(new CubeDeformation(0.25F)));
        registrar.registerModelLayer(smcm$ModelLayers.BRUISER, () -> IllagerModel.createBodyLayer().apply(MeshTransformer.scaling(1.0625F)));
        registrar.registerModelLayer(smcm$ModelLayers.ENCHANTER, () -> EnchanterModel.createBodyLayer().apply(MeshTransformer.scaling(0.9375F)));
        registrar.registerModelLayer(smcm$ModelLayers.SUNKEN, SunkenModel::createBodyLayer);
        registrar.registerModelLayer(smcm$ModelLayers.SUNKEN_COLD, SunkenModel::createBodyLayer);
        registrar.registerModelLayer(smcm$ModelLayers.SUNKEN_WARM, SunkenModel::createBodyLayer);
        registerArmorLayers(smcm$ModelLayers.SUNKEN_ARMOR, humanoidArmor, registrar);
        registrar.registerModelLayer(smcm$ModelLayers.LOST, SkeletonModel::createBodyLayer);
        registerArmorLayers(smcm$ModelLayers.LOST_ARMOR, humanoidArmor, registrar);
    }

    public static void registerArmorLayers(final ArmorModelSet<ModelLayerLocation> location, ArmorModelSet<LayerDefinition> modelSet, final IClientRegistryHelper registrar) {
        registrar.registerModelLayer(location.head(), modelSet::head);
        registrar.registerModelLayer(location.chest(), modelSet::chest);
        registrar.registerModelLayer(location.legs(), modelSet::legs);
        registrar.registerModelLayer(location.feet(), modelSet::feet);
    }
}

