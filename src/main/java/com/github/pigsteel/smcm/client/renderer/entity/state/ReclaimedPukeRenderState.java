package com.github.pigsteel.smcm.client.renderer.entity.state;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.state.EntityRenderState;

//? fabric {
@Environment(EnvType.CLIENT)
//?}
public class ReclaimedPukeRenderState extends EntityRenderState {
    public float xRot;
    public float yRot;
}
