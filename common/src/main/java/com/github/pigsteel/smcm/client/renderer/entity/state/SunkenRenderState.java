package com.github.pigsteel.smcm.client.renderer.entity.state;

import com.github.pigsteel.smcm.entity.skeleton.Sunken;
import com.github.pigsteel.smcm.entity.skeleton.SunkenVariant;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.monster.illager.AbstractIllager;

public class SunkenRenderState extends SkeletonRenderState {
    public SunkenVariant variant;
    public boolean isSheared;
    public boolean isCoralDead;
}
