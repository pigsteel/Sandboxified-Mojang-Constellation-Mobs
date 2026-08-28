package com.github.pigsteel.eum.client.renderer.entity.state;


import com.github.pigsteel.eum.world.entity.monster.skeleton.SunkenVariant;
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
