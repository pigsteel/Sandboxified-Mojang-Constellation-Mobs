package com.github.pigsteel.eum.client.renderer.entity.state;

//? >= 1.21.2 {
import com.github.pigsteel.eum.world.entity.animal.FlowerCow.Variant;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

public class FlowerCowRenderState extends LivingEntityRenderState {
    public Variant variant = Variant.BUTTERCUP;
    public final BlockModelRenderState flowerModel = new BlockModelRenderState();
}
//?}
