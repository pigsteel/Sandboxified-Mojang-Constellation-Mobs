package com.github.pigsteel.smcm.client.model.monster.zombie;

import com.github.pigsteel.smcm.client.renderer.entity.state.ReclaimedPukeRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ReclaimedPukeModel extends EntityModel<ReclaimedPukeRenderState> {
    public ReclaimedPukeModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild(
                "main",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(0.0F, 0.0F, 0.0F, 8.0F, 4.0F, 4.0F),
                PartPose.ZERO
        );
        return LayerDefinition.create(mesh, 16, 16);
    }
}
