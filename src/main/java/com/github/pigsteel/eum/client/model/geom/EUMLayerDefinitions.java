package com.github.pigsteel.eum.client.model.geom;

import com.github.pigsteel.eum.EUM;
import com.github.pigsteel.eum.client.model.monster.enchanter.EnchanterModel;
import com.github.pigsteel.eum.client.model.monster.iceologer.IceologerModel;
import com.github.pigsteel.eum.client.model.monster.necromancer.NecromancerBallModel;
import com.github.pigsteel.eum.client.model.monster.necromancer.NecromancerModel;
import com.github.pigsteel.eum.client.model.monster.redstonegolem.RedstoneGolemModel;
import com.github.pigsteel.eum.client.model.monster.redstonemonstrosity.RedstoneMonstrosityModel;
import com.github.pigsteel.eum.client.model.monster.skeleton.AbstractSunkenModel;
import com.github.pigsteel.eum.client.model.monster.skeleton.CoralSunkenModel;
import com.github.pigsteel.eum.client.model.monster.wildfire.WildfireModel;
import com.github.pigsteel.eum.client.model.monster.windcaller.WindcallerModel;
import com.github.pigsteel.eum.client.model.monster.witch.VilerWitchModel;
import com.github.pigsteel.eum.client.model.monster.wraith.WraithModel;
import com.github.pigsteel.eum.client.model.monster.zombie.BabyFrostbittenModel;
import com.github.pigsteel.eum.client.model.monster.zombie.BabyReclaimedModel;
import com.github.pigsteel.eum.client.model.monster.zombie.FrostbittenModel;
import com.github.pigsteel.eum.client.model.monster.zombie.ReclaimedModel;
import com.github.pigsteel.eum.client.model.monster.zombie.ReclaimedPukeModel;
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

import java.util.function.Supplier;

public class EUMLayerDefinitions {
    private static final CubeDeformation OUTER_ARMOR_DEFORMATION = new CubeDeformation(1.0F);
    private static final CubeDeformation INNER_ARMOR_DEFORMATION = new CubeDeformation(0.5F);
    private static final CubeDeformation BABY_OUTER_ARMOR_DEFORMATION = new CubeDeformation(-0.1F, 0.5F, 0.3F);
    private static final CubeDeformation BABY_INNER_ARMOR_DEFORMATION = new CubeDeformation(-0.1F, 0.3F, 0.3F);

    public static void load() {
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

        registerModelLayer(EUMModelLayers.RECLAIMED, () -> ReclaimedModel.createBodyLayer(CubeDeformation.NONE).apply(reclaimedScale));
        registerModelLayer(EUMModelLayers.RECLAIMED_BABY, () -> BabyReclaimedModel.createBodyLayer(CubeDeformation.NONE));
        registerArmorLayers(EUMModelLayers.RECLAIMED_ARMOR, humanoidArmor.map(layer -> layer.apply(reclaimedScale)));
        registerArmorLayers(EUMModelLayers.RECLAIMED_BABY_ARMOR, humanoidBabyArmor);
        registerModelLayer(EUMModelLayers.RECLAIMED_OUTER_LAYER, () -> ReclaimedModel.createBodyLayer(new CubeDeformation(0.25F)).apply(reclaimedScale));
        registerModelLayer(EUMModelLayers.RECLAIMED_BABY_OUTER_LAYER, () -> BabyReclaimedModel.createBodyLayer(new CubeDeformation(0.25F)));
        registerModelLayer(EUMModelLayers.FROSTBITTEN, () -> FrostbittenModel.createBodyLayer(CubeDeformation.NONE));
        registerModelLayer(EUMModelLayers.FROSTBITTEN_BABY, () -> BabyFrostbittenModel.createBodyLayer(CubeDeformation.NONE));
        registerArmorLayers(EUMModelLayers.FROSTBITTEN_ARMOR, humanoidArmor);
        registerArmorLayers(EUMModelLayers.FROSTBITTEN_BABY_ARMOR, humanoidBabyArmor);
        registerModelLayer(EUMModelLayers.FROSTBITTEN_OUTER_LAYER, () -> FrostbittenModel.createBodyLayer(new CubeDeformation(0.25F)));
        registerModelLayer(EUMModelLayers.FROSTBITTEN_BABY_OUTER_LAYER, () -> BabyFrostbittenModel.createBodyLayer(new CubeDeformation(0.25F)));
        registerModelLayer(EUMModelLayers.BRUISER, () -> IllagerModel.createBodyLayer().apply(MeshTransformer.scaling(1.0625F)));
        registerModelLayer(EUMModelLayers.ENCHANTER, () -> EnchanterModel.createBodyLayer().apply(villagerLikeScale));
        registerModelLayer(EUMModelLayers.SUNKEN, AbstractSunkenModel::createBodyLayer);
        registerModelLayer(EUMModelLayers.SUNKEN_FROZEN, AbstractSunkenModel::createBodyLayer);
        registerModelLayer(EUMModelLayers.SUNKEN_CORAL, CoralSunkenModel::createBodyLayer);
        registerArmorLayers(EUMModelLayers.SUNKEN_ARMOR, humanoidArmor);
        registerModelLayer(EUMModelLayers.LOST, SkeletonModel::createBodyLayer);
        registerArmorLayers(EUMModelLayers.LOST_ARMOR, humanoidArmor);
        registerModelLayer(EUMModelLayers.NECROMANCER, () -> NecromancerModel.createBodyLayer().apply(MeshTransformer.scaling(1.2F)));
        registerModelLayer(EUMModelLayers.ZOMBIFIED_PIGLIN_BRUTE, AdultZombifiedPiglinModel::createBodyLayer);
        registerArmorLayers(EUMModelLayers.ZOMBIFIED_PIGLIN_BRUTE_ARMOR, piglinArmor);
        registerModelLayer(EUMModelLayers.RECLAIMED_PUKE, ReclaimedPukeModel::createBodyLayer);
        registerModelLayer(EUMModelLayers.VILER_WITCH, () -> VilerWitchModel.createBodyLayer().apply(villagerLikeScale));
        registerModelLayer(EUMModelLayers.GEOMANCER, () -> IllagerModel.createBodyLayer().apply(villagerLikeScale));
        registerModelLayer(EUMModelLayers.MOUNTAINEER, () -> IllagerModel.createBodyLayer().apply(villagerLikeScale));
        registerModelLayer(EUMModelLayers.WINDCALLER, () -> WindcallerModel.createBodyLayer().apply(villagerLikeScale));
        registerModelLayer(EUMModelLayers.ICEOLOGER, () -> IceologerModel.createBodyLayer().apply(villagerLikeScale));
        registerModelLayer(EUMModelLayers.REDSTONE_GOLEM, RedstoneGolemModel::createBodyLayer);
        registerModelLayer(EUMModelLayers.REDSTONE_GOLEM_GLOW, RedstoneGolemModel::createGlowLayer);
        registerModelLayer(EUMModelLayers.REDSTONE_GOLEM_EYES, RedstoneGolemModel::createEyesLayer);
		registerModelLayer(EUMModelLayers.REDSTONE_MONSTROSITY, RedstoneMonstrosityModel::createBodyLayer);
        registerModelLayer(EUMModelLayers.PIGLIN_FARMER, AdultPiglinModel::createBodyLayer);
        registerArmorLayers(EUMModelLayers.PIGLIN_FARMER_ARMOR, piglinArmor);
		registerModelLayer(EUMModelLayers.WILDFIRE, WildfireModel::createBodyLayer);
		registerModelLayer(EUMModelLayers.WRAITH, WraithModel::createBodyLayer);
		registerModelLayer(EUMModelLayers.NECROMANCER_BALL, NecromancerBallModel::createBodyLayer);
    }

	public static void registerModelLayer(ModelLayerLocation modelLayerLocation, Supplier<LayerDefinition> consumer) {
		EUM.xplat().register(modelLayerLocation, consumer);
	}

    public static void registerArmorLayers(final ArmorModelSet<ModelLayerLocation> location, ArmorModelSet<LayerDefinition> modelSet) {
        registerModelLayer(location.head(), modelSet::head);
        registerModelLayer(location.chest(), modelSet::chest);
        registerModelLayer(location.legs(), modelSet::legs);
        registerModelLayer(location.feet(), modelSet::feet);
    }
}

