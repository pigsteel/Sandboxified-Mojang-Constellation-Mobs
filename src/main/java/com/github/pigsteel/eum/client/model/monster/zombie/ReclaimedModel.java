package com.github.pigsteel.eum.client.model.monster.zombie;

//? >= 1.21.2 {
import com.github.pigsteel.eum.client.renderer.entity.state.ReclaimedRenderState;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.monster.zombie.ZombieModel;

public class ReclaimedModel extends ZombieModel<ReclaimedRenderState> {

    public ReclaimedModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer(final CubeDeformation g) {
        MeshDefinition mesh = HumanoidModel.createMesh(g, 0.0F);
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild(
                "left_arm", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, g), PartPose.offset(5.0F, 2.0F, 0.0F)
        );
        root.addOrReplaceChild(
                "left_leg", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, g), PartPose.offset(1.9F, 12.0F, 0.0F)
        );
        return LayerDefinition.create(mesh, 64, 64);
    }
}
//?}
