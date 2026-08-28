package com.github.pigsteel.eum.client.renderer.entity.state;

import com.github.pigsteel.eum.world.entity.animal.FlowerCow.Variant;
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
