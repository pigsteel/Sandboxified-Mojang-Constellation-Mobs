package com.github.pigsteel.smcm.client.renderer.entity.state;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.entity.AnimationState;
import org.joml.Vector3f;

//? fabric {
@Environment(EnvType.CLIENT)
//?}
public class NecromancerRenderState extends HumanoidRenderState {
    public float capeLean;
    public float capeLean2;
    public float capeFlap;
    public float stripLean;
	public boolean isLeftHanded = false;
	public Vector3f staffBallPosition = new Vector3f(0.0F);

    public AnimationState summonAnimationState = new AnimationState();
    public AnimationState shootingAnimationState = new AnimationState();
}
