package com.github.pigsteel.smcm.model.monster.zombie;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.monster.zombie.BabyZombieModel;

public class BabyReclaimedModel extends ReclaimedModel {
    public BabyReclaimedModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer(final CubeDeformation g) {
        return BabyZombieModel.createBodyLayer(g); // remember to add little flower on baby reclaimed head
    }
}
