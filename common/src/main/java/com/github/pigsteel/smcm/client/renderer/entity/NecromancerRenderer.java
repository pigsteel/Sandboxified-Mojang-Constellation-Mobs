package com.github.pigsteel.smcm.client.renderer.entity;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.client.model.monster.necromancer.NecromancerModel;
import com.github.pigsteel.smcm.client.renderer.entity.layers.NecromancerCloakLayer;
import com.github.pigsteel.smcm.client.renderer.entity.layers.NecromancerEyesLayer;
import com.github.pigsteel.smcm.client.renderer.entity.state.NecromancerRenderState;
import com.github.pigsteel.smcm.entity.Necromancer;
import com.github.pigsteel.smcm.registry.smcm$ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

public class NecromancerRenderer extends HumanoidMobRenderer<Necromancer, NecromancerRenderState, NecromancerModel<NecromancerRenderState>> {
    private static final Identifier NECROMANCER_LOCATION = Identifier.fromNamespaceAndPath(SMCM.MOD_ID,"textures/entity/necromancer/necromancer.png");

    public NecromancerRenderer(EntityRendererProvider.Context context) {
        super(context, new NecromancerModel<>(context.bakeLayer(smcm$ModelLayers.NECROMANCER)), 1.0f);
        this.addLayer(new NecromancerCloakLayer(this, context.getModelSet()));
        this.addLayer(new NecromancerEyesLayer(this));
    }

    @Override
    public Identifier getTextureLocation(NecromancerRenderState necromancerRenderState) {
        return NECROMANCER_LOCATION;
    }

    public void extractRenderState(final Necromancer entity, final NecromancerRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        extractCapeState(entity, state, partialTicks);
        state.summonAnimationState.copyFrom(entity.summonAnimationState);
        state.shootingAnimationState.copyFrom(entity.shootingAnimationState);
    }

    private void extractCapeState(Necromancer entity, NecromancerRenderState state, float partialTicks) {
        double cloakX = Mth.lerp(partialTicks, entity.cloakXOld, entity.cloakX);
        double cloakY = Mth.lerp(partialTicks, entity.cloakYOld, entity.cloakY);
        double cloakZ = Mth.lerp(partialTicks, entity.cloakZOld, entity.cloakZ);

        double entityX = Mth.lerp(partialTicks, entity.xo, entity.getX());
        double entityY = Mth.lerp(partialTicks, entity.yo, entity.getY());
        double entityZ = Mth.lerp(partialTicks, entity.zo, entity.getZ());

        double deltaX = cloakX - entityX;
        double deltaY = cloakY - entityY;
        double deltaZ = cloakZ - entityZ;

        float yBodyRot = Mth.rotLerp(partialTicks, entity.yBodyRotO, entity.yBodyRot);

        double forwardX = Mth.sin(yBodyRot * Mth.DEG_TO_RAD);
        double forwardZ = -Mth.cos(yBodyRot * Mth.DEG_TO_RAD);

        state.capeFlap = (float) deltaY * 4.0F;
        state.capeFlap = Mth.clamp(state.capeFlap, -3.0F, 8.0F);

        state.capeLean = (float) (deltaX * forwardX + deltaZ * forwardZ) * 45.0F;
        state.capeLean = Mth.clamp(state.capeLean, -65.0F, 65.0F);

        state.capeLean2 = (float) (deltaX * forwardZ - deltaZ * forwardX) * 30.0F;
        state.capeLean2 = Mth.clamp(state.capeLean2, -10.0F, 10.0F);

        state.stripLean = (float) (deltaX * forwardX + deltaZ * forwardZ) * 50.0F;
        state.stripLean = Mth.clamp(state.stripLean, -75.0F, 75.0F);

        float walkSpeed = Mth.clamp(state.walkAnimationSpeed, 0.0F, 1.0F);
        float walkPos = state.walkAnimationPos;

        state.capeLean2 += Mth.sin(walkPos * 0.7F) * 0.5F * walkSpeed;
    }

    @Override
    public NecromancerRenderState createRenderState() {
        return new NecromancerRenderState();
    }
}
