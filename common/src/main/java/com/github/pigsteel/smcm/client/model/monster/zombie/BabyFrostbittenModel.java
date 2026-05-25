package com.github.pigsteel.smcm.client.model.monster.zombie;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.monster.zombie.BabyZombieModel;

public class BabyFrostbittenModel extends FrostbittenModel  {
    public BabyFrostbittenModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer(final CubeDeformation g) {
        return BabyZombieModel.createBodyLayer(g);
    }
}
