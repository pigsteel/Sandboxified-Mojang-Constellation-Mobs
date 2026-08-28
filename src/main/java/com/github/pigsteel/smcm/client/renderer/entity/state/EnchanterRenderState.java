package com.github.pigsteel.smcm.client.renderer.entity.state;

//? fabric {
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
//?}
import net.minecraft.client.renderer.entity.state.IllagerRenderState;

//? fabric {
@Environment(EnvType.CLIENT)
//?}
public class EnchanterRenderState extends IllagerRenderState {
	public boolean isBookInvisible;
    public boolean isHitting;
    public boolean isCastingSpell;
}
