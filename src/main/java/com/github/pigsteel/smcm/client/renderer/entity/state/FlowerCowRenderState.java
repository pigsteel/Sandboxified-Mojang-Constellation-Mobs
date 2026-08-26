package com.github.pigsteel.smcm.client.renderer.entity.state;

import com.github.pigsteel.smcm.world.entity.animal.FlowerCow.Variant;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

//? fabric {
/*//@OnlyIn(Dist.CLIENT)
*///?}
public class FlowerCowRenderState extends LivingEntityRenderState {
    public Variant variant = Variant.BUTTERCUP;
    public final BlockModelRenderState flowerModel = new BlockModelRenderState();
}
