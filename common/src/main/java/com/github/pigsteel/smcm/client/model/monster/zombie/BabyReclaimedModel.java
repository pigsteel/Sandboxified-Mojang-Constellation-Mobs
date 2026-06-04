package com.github.pigsteel.smcm.client.model.monster.zombie;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.model.monster.zombie.BabyZombieModel;

public class BabyReclaimedModel extends ReclaimedModel {
    public BabyReclaimedModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer(final CubeDeformation g) { // have to fully recreate this so we can add a part i believe
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-2.0F, -2.5F, -1.0F, 4.0F, 5.0F, 2.0F, g), PartPose.offset(0.0F, 17.5F, 0.0F));
        PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(3, 3).addBox(-3.0F, -6.25F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(35, 3).addBox(-3.0F, -6.15F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 15.25F, 0.0F));
        head.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(36, 16).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 5.0F, 2.0F, g), PartPose.offset(-3.0F, 15.5F, 0.0F));
        root.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(28, 16).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 5.0F, 2.0F, g), PartPose.offset(3.0F, 15.5F, 0.0F));
        root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(8, 16).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, g), PartPose.offset(-1.0F, 20.0F, 0.0F));
        root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, g), PartPose.offset(1.0F, 20.0F, 0.0F));
        head.addOrReplaceChild(
                "baby_flowers",
                CubeListBuilder.create().texOffs(0, 24).addBox(-3.0F, -6.5F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.25F, 0.0F, 0.25F)),
                PartPose.ZERO
        );
        return LayerDefinition.create(mesh, 64, 64); // remember to add little flower on baby reclaimed head
    }
}
