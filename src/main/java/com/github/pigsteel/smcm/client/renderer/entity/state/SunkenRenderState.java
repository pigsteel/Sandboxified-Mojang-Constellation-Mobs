package com.github.pigsteel.smcm.client.renderer.entity.state;


import com.github.pigsteel.smcm.world.entity.monster.skeleton.SunkenVariant;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;

//? fabric {
/*//@OnlyIn(Dist.CLIENT)
*///?}
public class SunkenRenderState extends SkeletonRenderState {
    public SunkenVariant variant;
    public boolean isSheared;
    public boolean isCoralDead;
}
