package com.github.pigsteel.eum.client.renderer.entity.layers;

//? >= 1.21.2 {
import com.github.pigsteel.eum.client.renderer.entity.state.FlowerCowRenderState;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.animal.cow.CowModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;

public class FlowerCowFlowerLayer extends RenderLayer<FlowerCowRenderState, CowModel> {
    public FlowerCowFlowerLayer(RenderLayerParent<FlowerCowRenderState, CowModel> renderer) {
        super(renderer);
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, FlowerCowRenderState state, float yRot, float xRot) {

    }
}
//?}
