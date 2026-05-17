package com.github.pigsteel.smcm.client.renderer.entity.layers;

import com.github.pigsteel.smcm.client.model.monster.enchanter.EnchanterModel;
import com.github.pigsteel.smcm.client.renderer.entity.EnchanterRenderer;
import com.github.pigsteel.smcm.client.renderer.entity.state.EnchanterRenderState;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.object.book.BookModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

public class EnchanterBookLayer extends RenderLayer<EnchanterRenderState, EnchanterModel<EnchanterRenderState>> {
	public static final Identifier BOOK_TEXTURE = Identifier.withDefaultNamespace("textures/entity/enchantment/enchanting_table_book.png");
	private final BookModel model;

	public EnchanterBookLayer(EnchanterRenderer renderer, EntityModelSet modelSet) {
		super(renderer);
		this.model = new BookModel(
				modelSet.bakeLayer(ModelLayers.BOOK)
		);
	}

	@Override
	public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, EnchanterRenderState state, float yRot, float xRot) {
		if(!state.isBookInvisible) {
			poseStack.pushPose();
			EnchanterModel<EnchanterRenderState> parentModel = this.getParentModel();
			parentModel.translateBook(state, poseStack);

			float open = Mth.clamp(0.0F, 0.001F, 1.0F); // to fix the Z-fighting

			poseStack.mulPose(Axis.XP.rotationDegrees(-75.0F));
			poseStack.mulPose(Axis.YP.rotationDegrees(open * 90.0F));
			poseStack.translate(-3 / 16.0F, 4 / 16.0F, 2.5 / 16.0F - 0.15625 * open);

			BookModel.State bookState = BookModel.State.forAnimation(0, Mth.clamp(0, 0.0F, 1.0F), Mth.clamp(0, 0.0F, 1.0F), open);

			submitNodeCollector.submitModel(
					this.model,
					bookState,
					poseStack,
					RenderTypes.entityCutout(BOOK_TEXTURE),
					lightCoords,
					OverlayTexture.NO_OVERLAY,
					state.outlineColor,
					null
			);

			poseStack.popPose();
		}
	}
}
