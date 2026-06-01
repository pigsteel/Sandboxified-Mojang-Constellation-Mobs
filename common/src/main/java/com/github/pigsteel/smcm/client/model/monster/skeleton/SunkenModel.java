package com.github.pigsteel.smcm.client.model.monster.skeleton;

import com.github.pigsteel.smcm.client.renderer.entity.state.SunkenRenderState;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.monster.skeleton.SkeletonModel;

public class SunkenModel extends SkeletonModel<SunkenRenderState> {
    public SunkenModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        return SkeletonModel.createBodyLayer();
    }
}
