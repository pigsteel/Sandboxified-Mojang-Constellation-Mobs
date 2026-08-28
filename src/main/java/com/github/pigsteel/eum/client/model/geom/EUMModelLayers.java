package com.github.pigsteel.eum.client.model.geom;

import com.github.pigsteel.eum.EUM;
import net.minecraft.client.model.geom.ModelLayerLocation;
//? > 26.1 {
import net.minecraft.client.renderer.entity.ArmorModelSet;
//?}
import net.minecraft.resources.ResourceLocation;

public class EUMModelLayers {
	public static final ModelLayerLocation BRUISER = register("bruiser");
	public static final ModelLayerLocation ENCHANTER = register("enchanter");
	public static final ModelLayerLocation FROSTBITTEN = register("frostbitten");
	public static final ModelLayerLocation FROSTBITTEN_BABY = register("frostbitten_baby");
	public static final ModelLayerLocation FROSTBITTEN_BABY_OUTER_LAYER = register("frostbitten_baby", "outer");
	public static final ModelLayerLocation FROSTBITTEN_OUTER_LAYER = register("frostbitten", "outer");
	public static final ModelLayerLocation GEOMANCER = register("geomancer");
	public static final ModelLayerLocation ICEOLOGER = register("iceologer");
	public static final ModelLayerLocation LOST = register("lost");
	public static final ModelLayerLocation MOUNTAINEER = register("mountaineer");
	public static final ModelLayerLocation NECROMANCER = register("necromancer");
	public static final ModelLayerLocation NECROMANCER_BALL = register("necromancer_ball");
	public static final ModelLayerLocation PIGLIN_FARMER = register("piglin_farmer");
	public static final ModelLayerLocation RECLAIMED = register("reclaimed");
	public static final ModelLayerLocation RECLAIMED_BABY = register("reclaimed_baby");
	public static final ModelLayerLocation RECLAIMED_BABY_OUTER_LAYER = register("reclaimed_baby", "outer");
	public static final ModelLayerLocation RECLAIMED_OUTER_LAYER = register("reclaimed", "outer");
	public static final ModelLayerLocation RECLAIMED_PUKE = register("reclaimed_puke");
	public static final ModelLayerLocation REDSTONE_GOLEM = register("redstone_golem");
	public static final ModelLayerLocation REDSTONE_GOLEM_EYES = register("redstone_golem_eyes");
	public static final ModelLayerLocation REDSTONE_GOLEM_GLOW = register("redstone_golem_glow");
	public static final ModelLayerLocation REDSTONE_MONSTROSITY = register("redstone_monstrosity");
	public static final ModelLayerLocation SUNKEN = register("sunken");
	public static final ModelLayerLocation SUNKEN_CORAL = register("sunken_warm");
	public static final ModelLayerLocation SUNKEN_FROZEN = register("sunken_cold");
	public static final ModelLayerLocation VILER_WITCH = register("viler_witch");
	public static final ModelLayerLocation WILDFIRE = register("wildfire");
	public static final ModelLayerLocation WINDCALLER = register("windcaller");
	public static final ModelLayerLocation WRAITH = register("wraith");
	public static final ModelLayerLocation ZOMBIFIED_PIGLIN_BRUTE = register("zombified_piglin_brute");

	public static final ArmorModelSet<ModelLayerLocation> FROSTBITTEN_ARMOR = registerArmorSet("frostbitten");
	public static final ArmorModelSet<ModelLayerLocation> FROSTBITTEN_BABY_ARMOR = registerArmorSet("frostbitten_baby");
	public static final ArmorModelSet<ModelLayerLocation> PIGLIN_FARMER_ARMOR = registerArmorSet("piglin_farmer");
	public static final ArmorModelSet<ModelLayerLocation> RECLAIMED_ARMOR = registerArmorSet("reclaimed");
	public static final ArmorModelSet<ModelLayerLocation> LOST_ARMOR = registerArmorSet("lost");
	public static final ArmorModelSet<ModelLayerLocation> RECLAIMED_BABY_ARMOR = registerArmorSet("reclaimed_baby");
	public static final ArmorModelSet<ModelLayerLocation> SUNKEN_ARMOR = registerArmorSet("sunken");
	public static final ArmorModelSet<ModelLayerLocation> ZOMBIFIED_PIGLIN_BRUTE_ARMOR = registerArmorSet("zombified_piglin_brute");

	private static ModelLayerLocation register(String name) {
		return register(name, "main");
	}

	private static ModelLayerLocation register(String name, String layer) {
		return new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EUM.MOD_ID, name), layer);
	}

	private static ArmorModelSet<ModelLayerLocation> registerArmorSet(final String modelId) {
		return new ArmorModelSet<>(register(modelId, "helmet"), register(modelId, "chestplate"), register(modelId, "leggings"), register(modelId, "boots"));
	}

	public static void load() {
	}
}
