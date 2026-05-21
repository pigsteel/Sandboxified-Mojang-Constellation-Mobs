package com.github.pigsteel.smcm.client.registry;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.client.model.monster.zombie.ReclaimedModel;
import com.github.pigsteel.smcm.client.renderer.entity.ReclaimedRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.resources.Identifier;

public class ModelLayers {
    public static final ModelLayerLocation RECLAIMED = register("reclaimed");
    public static final ModelLayerLocation RECLAIMED_BABY = register("reclaimed_baby");
    public static final ModelLayerLocation RECLAIMED_OUTER_LAYER = register("reclaimed", "outer");
    public static final ModelLayerLocation RECLAIMED_BABY_OUTER_LAYER = register("reclaimed_baby", "outer");
    public static final ArmorModelSet<ModelLayerLocation> RECLAIMED_ARMOR = registerArmorSet("reclaimed");
    public static final ArmorModelSet<ModelLayerLocation> RECLAIMED_BABY_ARMOR = registerArmorSet("reclaimed_baby");
    public static final ModelLayerLocation FROSTBITTEN = register("frostbitten");
    public static final ModelLayerLocation FROSTBITTEN_BABY = register("frostbitten_baby");
    public static final ModelLayerLocation FROSTBITTEN_OUTER_LAYER = register("frostbitten", "outer");
    public static final ModelLayerLocation FROSTBITTEN_BABY_OUTER_LAYER = register("frostbitten_baby", "outer");
    public static final ArmorModelSet<ModelLayerLocation> FROSTBITTEN_ARMOR = registerArmorSet("frostbitten");
    public static final ArmorModelSet<ModelLayerLocation> FROSTBITTEN_BABY_ARMOR = registerArmorSet("frostbitten_baby");
    public static final ModelLayerLocation BRUISER = register("bruiser");
    public static final ModelLayerLocation ENCHANTER = register("enchanter");
    public static final ModelLayerLocation SUNKEN = register("sunken");
    public static final ArmorModelSet<ModelLayerLocation> SUNKEN_ARMOR = registerArmorSet("sunken");

    private static ModelLayerLocation register(String name) {
        return register(name, "main");
    }

    private static ModelLayerLocation register(String name, String layer) {
        return new ModelLayerLocation(Identifier.fromNamespaceAndPath(SMCM.MOD_ID, name), layer);
    }

    private static ArmorModelSet<ModelLayerLocation> registerArmorSet(final String modelId) {
        return new ArmorModelSet<>(register(modelId, "helmet"), register(modelId, "chestplate"), register(modelId, "leggings"), register(modelId, "boots"));
    }
}
