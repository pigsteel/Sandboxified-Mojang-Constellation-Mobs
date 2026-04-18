package com.github.pigsteel.smcm.client.model.monster.zombie;

import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.monster.zombie.BabyZombieModel;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.world.entity.HumanoidArm;

public class BabyFrostbittenModel extends FrostbittenModel  {
    public BabyFrostbittenModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer(final CubeDeformation g) {
        return BabyZombieModel.createBodyLayer(g);
    }
}
