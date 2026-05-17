package com.github.pigsteel.smcm.mixin;

import com.github.pigsteel.smcm.client.renderer.entity.SunkenRenderer;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AgeableMobRenderer.class)
public abstract class AgeableMobRendererIgnoreSunkenMixin {
    @ModifyExpressionValue(
            method = "submit",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/client/renderer/entity/AgeableMobRenderer;adultModel:Lnet/minecraft/client/model/EntityModel;"
            )
    )
    private EntityModel<?> smcm$useSunkenSelectedModel(
            EntityModel<?> original
    ) {
        if ((Object) this instanceof SunkenRenderer renderer) {
            return renderer.smcm$getSelectedModel();
        }

        return original;
    }
}
