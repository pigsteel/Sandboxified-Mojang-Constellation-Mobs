package com.github.pigsteel.smcm.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

import java.util.function.Function;

public class TintedEmissiveLayer<S extends LivingEntityRenderState, M extends EntityModel<S>> extends RenderLayer<S, M> {
    private final Function<S, Identifier> textureProvider;
    private final AlphaFunction<S> alphaFunction;
    private final ColorFunction<S> colorFunction;
    private final M model;
    private final Function<Identifier, RenderType> bufferProvider;
    private final boolean alwaysVisible;

    public TintedEmissiveLayer(
            RenderLayerParent<S, M> renderer,
            Function<S, Identifier> textureProvider,
            AlphaFunction<S> alphaFunction,
            ColorFunction<S> colorFunction,
            M model,
            Function<Identifier, RenderType> bufferProvider,
            boolean alwaysVisible
    ) {
        super(renderer);
        this.textureProvider = textureProvider;
        this.alphaFunction = alphaFunction;
        this.colorFunction = colorFunction;
        this.model = model;
        this.bufferProvider = bufferProvider;
        this.alwaysVisible = alwaysVisible;
    }

    @Override
    public void submit(
            PoseStack poseStack,
            SubmitNodeCollector submitNodeCollector,
            int lightCoords,
            S state,
            float yRot,
            float xRot
    ) {
        if (!state.isInvisible || this.alwaysVisible) {
            float alpha = this.alphaFunction.apply(state, state.ageInTicks);

            if (alpha > 1.0E-5F) {
                int color = this.colorFunction.apply(state, state.ageInTicks, alpha);
                RenderType renderType = this.bufferProvider.apply(this.textureProvider.apply(state));

                submitNodeCollector.order(1).submitModel(
                        this.model,
                        state,
                        poseStack,
                        renderType,
                        lightCoords,
                        LivingEntityRenderer.getOverlayCoords(state, 0.0F),
                        color,
                        null,
                        state.outlineColor,
                        null
                );
            }
        }
    }

    private static int argb(float alpha, float red, float green, float blue) {
        int a = (int) (Mth.clamp(alpha, 0.0F, 1.0F) * 255.0F);
        int r = (int) (Mth.clamp(red, 0.0F, 1.0F) * 255.0F);
        int g = (int) (Mth.clamp(green, 0.0F, 1.0F) * 255.0F);
        int b = (int) (Mth.clamp(blue, 0.0F, 1.0F) * 255.0F);

        return a << 24 | r << 16 | g << 8 | b;
    }

    public static int redGlow(float alpha) {
        return argb(alpha, 1.0F, 0.25F, 0.08F);
    }

    @FunctionalInterface
    public interface AlphaFunction<S extends LivingEntityRenderState> {
        float apply(S state, float ageInTicks);
    }

    @FunctionalInterface
    public interface ColorFunction<S extends LivingEntityRenderState> {
        int apply(S state, float ageInTicks, float alpha);
    }
}