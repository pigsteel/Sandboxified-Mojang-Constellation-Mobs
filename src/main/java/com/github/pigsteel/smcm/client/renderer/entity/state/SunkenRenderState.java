package com.github.pigsteel.smcm.client.renderer.entity.state;


import com.github.pigsteel.smcm.world.entity.monster.skeleton.SunkenVariant;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;

//? fabric {
@Environment(EnvType.CLIENT)
//?}
public class SunkenRenderState extends SkeletonRenderState {
    public SunkenVariant variant;
    public boolean isSheared;
    public boolean isCoralDead;
}
