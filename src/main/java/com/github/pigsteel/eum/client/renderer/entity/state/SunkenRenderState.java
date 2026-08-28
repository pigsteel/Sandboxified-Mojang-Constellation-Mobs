package com.github.pigsteel.eum.client.renderer.entity.state;

//? >= 1.21.2 {
import com.github.pigsteel.eum.world.entity.monster.skeleton.SunkenVariant;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;

public class SunkenRenderState extends SkeletonRenderState {
    public SunkenVariant variant;
    public boolean isSheared;
    public boolean isCoralDead;
}
//?}
