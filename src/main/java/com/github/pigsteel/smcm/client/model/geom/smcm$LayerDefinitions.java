package com.github.pigsteel.smcm.client.model.geom;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.client.model.monster.enchanter.EnchanterModel;
import com.github.pigsteel.smcm.client.model.monster.iceologer.IceologerModel;
import com.github.pigsteel.smcm.client.model.monster.necromancer.NecromancerModel;
import com.github.pigsteel.smcm.client.model.monster.redstonegolem.RedstoneGolemModel;
import com.github.pigsteel.smcm.client.model.monster.skeleton.AbstractSunkenModel;
import com.github.pigsteel.smcm.client.model.monster.skeleton.CoralSunkenModel;
import com.github.pigsteel.smcm.client.model.monster.windcaller.WindcallerModel;
import com.github.pigsteel.smcm.client.model.monster.witch.VilerWitchModel;
import com.github.pigsteel.smcm.client.model.monster.zombie.BabyFrostbittenModel;
import com.github.pigsteel.smcm.client.model.monster.zombie.BabyReclaimedModel;
import com.github.pigsteel.smcm.client.model.monster.zombie.FrostbittenModel;
import com.github.pigsteel.smcm.client.model.monster.zombie.ReclaimedModel;
import com.github.pigsteel.smcm.client.model.monster.zombie.ReclaimedPukeModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshTransformer;
import net.minecraft.client.model.monster.illager.IllagerModel;
import net.minecraft.client.model.monster.piglin.AdultPiglinModel;
import net.minecraft.client.model.monster.piglin.AdultZombifiedPiglinModel;
import net.minecraft.client.model.monster.piglin.PiglinModel;
import net.minecraft.client.model.monster.skeleton.SkeletonModel;
import net.minecraft.client.renderer.entity.ArmorModelSet;

import java.util.function.Consumer;
import java.util.function.Supplier;

//? neoforge {
/*import com.github.pigsteel.smcm.platform.neoforge.NeoforgeVariables;
import static com.github.pigsteel.smcm.platform.neoforge.NeoforgeVariables.MODEL_LAYERS;
*///?}

public class smcm$LayerDefinitions {
    private static final CubeDeformation OUTER_ARMOR_DEFORMATION = new CubeDeformation(1.0F);
    private static final CubeDeformation INNER_ARMOR_DEFORMATION = new CubeDeformation(0.5F);
    private static final CubeDeformation BABY_OUTER_ARMOR_DEFORMATION = new CubeDeformation(-0.1F, 0.5F, 0.3F);
    private static final CubeDeformation BABY_INNER_ARMOR_DEFORMATION = new CubeDeformation(-0.1F, 0.3F, 0.3F);

    public static void registerModelLayers() {
        MeshTransformer villagerLikeScale = MeshTransformer.scaling(0.9375F);
        //1.0625F
        ArmorModelSet<LayerDefinition> humanoidArmor = HumanoidModel.createArmorMeshSet(INNER_ARMOR_DEFORMATION, OUTER_ARMOR_DEFORMATION)
                .map(mesh -> LayerDefinition.create(mesh, 64, 32));
        ArmorModelSet<LayerDefinition> humanoidBabyArmor = HumanoidModel.createBabyArmorMeshSet(
                        BABY_INNER_ARMOR_DEFORMATION, BABY_OUTER_ARMOR_DEFORMATION, PartPose.ZERO
                )
                .map(mesh -> LayerDefinition.create(mesh, 64, 64));
        ArmorModelSet<LayerDefinition> piglinArmor = PiglinModel.createArmorMeshSet(INNER_ARMOR_DEFORMATION, new CubeDeformation(1.02F))
                .map(mesh -> LayerDefinition.create(mesh, 64, 32));
        MeshTransformer reclaimedScale = MeshTransformer.scaling(1.025F);

        SMCM.LOGGER.debug("Registering model layers for SMCM");
        registerModelLayer(smcm$ModelLayers.RECLAIMED, () -> ReclaimedModel.createBodyLayer(CubeDeformation.NONE).apply(reclaimedScale));
        registerModelLayer(smcm$ModelLayers.RECLAIMED_BABY, () -> BabyReclaimedModel.createBodyLayer(CubeDeformation.NONE));
        registerArmorLayers(smcm$ModelLayers.RECLAIMED_ARMOR, humanoidArmor.map(layer -> layer.apply(reclaimedScale)));
        registerArmorLayers(smcm$ModelLayers.RECLAIMED_BABY_ARMOR, humanoidBabyArmor);
        registerModelLayer(smcm$ModelLayers.RECLAIMED_OUTER_LAYER, () -> ReclaimedModel.createBodyLayer(new CubeDeformation(0.25F)).apply(reclaimedScale));
        registerModelLayer(smcm$ModelLayers.RECLAIMED_BABY_OUTER_LAYER, () -> BabyReclaimedModel.createBodyLayer(new CubeDeformation(0.25F)));
        registerModelLayer(smcm$ModelLayers.FROSTBITTEN, () -> FrostbittenModel.createBodyLayer(CubeDeformation.NONE));
        registerModelLayer(smcm$ModelLayers.FROSTBITTEN_BABY, () -> BabyFrostbittenModel.createBodyLayer(CubeDeformation.NONE));
        registerArmorLayers(smcm$ModelLayers.FROSTBITTEN_ARMOR, humanoidArmor);
        registerArmorLayers(smcm$ModelLayers.FROSTBITTEN_BABY_ARMOR, humanoidBabyArmor);
        registerModelLayer(smcm$ModelLayers.FROSTBITTEN_OUTER_LAYER, () -> FrostbittenModel.createBodyLayer(new CubeDeformation(0.25F)));
        registerModelLayer(smcm$ModelLayers.FROSTBITTEN_BABY_OUTER_LAYER, () -> BabyFrostbittenModel.createBodyLayer(new CubeDeformation(0.25F)));
        registerModelLayer(smcm$ModelLayers.BRUISER, () -> IllagerModel.createBodyLayer().apply(MeshTransformer.scaling(1.0625F)));
        registerModelLayer(smcm$ModelLayers.ENCHANTER, () -> EnchanterModel.createBodyLayer().apply(villagerLikeScale));
        registerModelLayer(smcm$ModelLayers.SUNKEN, AbstractSunkenModel::createBodyLayer);
        registerModelLayer(smcm$ModelLayers.SUNKEN_FROZEN, AbstractSunkenModel::createBodyLayer);
        registerModelLayer(smcm$ModelLayers.SUNKEN_CORAL, CoralSunkenModel::createBodyLayer);
        registerArmorLayers(smcm$ModelLayers.SUNKEN_ARMOR, humanoidArmor);
        registerModelLayer(smcm$ModelLayers.LOST, SkeletonModel::createBodyLayer);
        registerArmorLayers(smcm$ModelLayers.LOST_ARMOR, humanoidArmor);
        registerModelLayer(smcm$ModelLayers.NECROMANCER, () -> NecromancerModel.createMainLayer().apply(MeshTransformer.scaling(1.2F)));
        registerModelLayer(smcm$ModelLayers.NECROMANCER_CLOAK, () -> NecromancerModel.createCloakLayer().apply(MeshTransformer.scaling(1.2F)));
        registerModelLayer(smcm$ModelLayers.ZOMBIFIED_PIGLIN_BRUTE, AdultZombifiedPiglinModel::createBodyLayer);
        registerArmorLayers(smcm$ModelLayers.ZOMBIFIED_PIGLIN_BRUTE_ARMOR, piglinArmor);
        registerModelLayer(smcm$ModelLayers.RECLAIMED_PUKE, ReclaimedPukeModel::createBodyLayer);
        registerModelLayer(smcm$ModelLayers.VILER_WITCH, () -> VilerWitchModel.createBodyLayer().apply(villagerLikeScale));
        registerModelLayer(smcm$ModelLayers.GEOMANCER, () -> IllagerModel.createBodyLayer().apply(villagerLikeScale));
        registerModelLayer(smcm$ModelLayers.MOUNTAINEER, () -> IllagerModel.createBodyLayer().apply(villagerLikeScale));
        registerModelLayer(smcm$ModelLayers.WINDCALLER, () -> WindcallerModel.createBodyLayer().apply(villagerLikeScale));
        registerModelLayer(smcm$ModelLayers.ICEOLOGER, () -> IceologerModel.createBodyLayer().apply(villagerLikeScale));
        registerModelLayer(smcm$ModelLayers.REDSTONE_GOLEM, RedstoneGolemModel::createBodyLayer);
        registerModelLayer(smcm$ModelLayers.REDSTONE_GOLEM_GLOW, RedstoneGolemModel::createGlowLayer);
        registerModelLayer(smcm$ModelLayers.REDSTONE_GOLEM_EYES, RedstoneGolemModel::createEyesLayer);
        registerModelLayer(smcm$ModelLayers.PIGLIN_FARMER, AdultPiglinModel::createBodyLayer);
        registerArmorLayers(smcm$ModelLayers.PIGLIN_FARMER_ARMOR, piglinArmor);
    }

	public static void registerModelLayer(ModelLayerLocation modelLayerLocation, Supplier<LayerDefinition> consumer) {
		//? fabric {
		net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry.registerModelLayer(modelLayerLocation, consumer::get);
		//?} neoforge {
		/*MODEL_LAYERS.add(new NeoforgeVariables.ModelLayerDeferred(modelLayerLocation, consumer));
		*///?}
	}

    public static void registerArmorLayers(final ArmorModelSet<ModelLayerLocation> location, ArmorModelSet<LayerDefinition> modelSet) {
        registerModelLayer(location.head(), modelSet::head);
        registerModelLayer(location.chest(), modelSet::chest);
        registerModelLayer(location.legs(), modelSet::legs);
        registerModelLayer(location.feet(), modelSet::feet);
    }
}

