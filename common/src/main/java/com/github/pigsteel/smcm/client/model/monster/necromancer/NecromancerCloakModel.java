package com.github.pigsteel.smcm.client.model.monster.necromancer;

import com.github.pigsteel.smcm.client.renderer.entity.state.NecromancerRenderState;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import org.joml.Quaternionf;

public class NecromancerCloakModel extends NecromancerModel<NecromancerRenderState> {
    public final ModelPart cloak;

    public NecromancerCloakModel(ModelPart root) {
        super(root);
        this.cloak = this.body.getChild("cloak");
    }

    public void setupAnim(NecromancerRenderState state) {
        float backSway = state.capeLean + state.capeFlap;
        float sideSway = state.capeLean2;

        this.cloak.xRot = Mth.clamp(backSway, -4.0F, 90.0F) * Mth.DEG_TO_RAD;
        this.cloak.yRot = 0.0F;
        this.cloak.zRot = Mth.clamp(sideSway, -16.0F, 16.0F) * Mth.DEG_TO_RAD;
    }

    public static LayerDefinition createCloakLayer() {
        MeshDefinition mesh = NecromancerModel.createMesh(CubeDeformation.NONE, 0.0F);
        PartDefinition root = mesh.getRoot().clearRecursively();
        PartDefinition body = root.getChild("body");

        body.addOrReplaceChild(
                "cloak",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-8.0F, 0.0F, -2.5F, 16.0F, 24.0F, 5.0F),
                PartPose.ZERO
        );

        return LayerDefinition.create(mesh, 64, 32);
    }
}
