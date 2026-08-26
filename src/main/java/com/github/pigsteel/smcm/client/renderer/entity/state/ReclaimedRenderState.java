package com.github.pigsteel.smcm.client.renderer.entity.state;


import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;

//? fabric {
/*//@OnlyIn(Dist.CLIENT)
*///?}
public class ReclaimedRenderState extends ZombieRenderState {
    public final BlockModelRenderState flowerModel = new BlockModelRenderState();
}
