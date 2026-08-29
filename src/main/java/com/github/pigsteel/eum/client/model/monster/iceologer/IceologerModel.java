package com.github.pigsteel.eum.client.model.monster.iceologer;

//? >= 1.21.2 {
import com.github.pigsteel.eum.client.renderer.entity.state.IceologerRenderState;
import com.github.pigsteel.eum.util.ModelUtil;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.monster.illager.IllagerModel;

public class IceologerModel extends IllagerModel<IceologerRenderState> {
    public IceologerModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = ModelUtil.createIllagerMesh(CubeDeformation.NONE);
        return LayerDefinition.create(mesh, 128, 64);
    }
}
//?}
