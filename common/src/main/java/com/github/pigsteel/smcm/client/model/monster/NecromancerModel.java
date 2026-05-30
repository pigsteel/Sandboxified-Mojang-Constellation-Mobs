package com.github.pigsteel.smcm.client.model.monster;

import com.github.pigsteel.smcm.client.renderer.entity.state.NecromancerRenderState;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.monster.skeleton.SkeletonModel;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;

public class NecromancerModel<S extends HumanoidRenderState> extends SkeletonModel<NecromancerRenderState> {
    public NecromancerModel(ModelPart root) {
        super(root);
    }
}
