package com.github.pigsteel.smcm.client.renderer.entity.state;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;

//? fabric {
@Environment(EnvType.CLIENT)
//?}
public class ReclaimedRenderState extends ZombieRenderState {
    public final BlockModelRenderState flowerModel = new BlockModelRenderState();
}
