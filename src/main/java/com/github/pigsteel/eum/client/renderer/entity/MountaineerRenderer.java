package com.github.pigsteel.eum.client.renderer.entity;

//? >= 1.21.2 {
import com.github.pigsteel.eum.EUM;
import com.github.pigsteel.eum.client.model.geom.EUMModelLayers;
import com.github.pigsteel.eum.world.entity.monster.illager.Mountaineer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.monster.illager.IllagerModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.state.IllagerRenderState;
import net.minecraft.resources.Identifier;

public class MountaineerRenderer extends IllagerRenderer<Mountaineer, IllagerRenderState> {
    private static final Identifier MOUNTAINEER_LOCATION = EUM.id("textures/entity/illager/mountaineer.png");

    public MountaineerRenderer(EntityRendererProvider.Context context) {
        super(context, new IllagerModel<>(context.bakeLayer(EUMModelLayers.MOUNTAINEER)), 0.5F);
        this.addLayer(new ItemInHandLayer<>(this) {
			public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, IllagerRenderState state, float yRot, float xRot) {
				super.submit(poseStack, submitNodeCollector, lightCoords, state, yRot, xRot);
			}
		});
    }

    @Override
    public Identifier getTextureLocation(IllagerRenderState state) {
        return MOUNTAINEER_LOCATION;
    }

    @Override
    public IllagerRenderState createRenderState() {
        return new IllagerRenderState();
    }
}
//?}
