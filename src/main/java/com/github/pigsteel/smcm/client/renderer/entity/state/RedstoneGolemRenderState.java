package com.github.pigsteel.smcm.client.renderer.entity.state;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

//? fabric {
@Environment(EnvType.CLIENT)
//?}
public class RedstoneGolemRenderState extends LivingEntityRenderState {
    public float glowAnimation;
}
