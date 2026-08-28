package com.github.pigsteel.eum.client.renderer.entity;

import com.github.pigsteel.eum.EUM;
import com.github.pigsteel.eum.client.model.geom.EUMModelLayers;
import com.github.pigsteel.eum.client.model.monster.necromancer.NecromancerModel;
import com.github.pigsteel.eum.client.renderer.entity.layers.NecromancerEyesLayer;
import com.github.pigsteel.eum.client.renderer.entity.state.NecromancerRenderState;
import com.github.pigsteel.eum.world.entity.monster.necromancer.Necromancer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class NecromancerRenderer extends MobRenderer<Necromancer, NecromancerRenderState, NecromancerModel<NecromancerRenderState>> {
    private static final ResourceLocation NECROMANCER_LOCATION = ResourceLocation.fromNamespaceAndPath(EUM.MOD_ID,"textures/entity/necromancer/necromancer.png");
	private final NecromancerModel<NecromancerRenderState> model;

    public NecromancerRenderer(EntityRendererProvider.Context context) {
        NecromancerModel<NecromancerRenderState> model = new NecromancerModel<>(context.bakeLayer(EUMModelLayers.NECROMANCER));
		super(context, model, 0.85f);
        this.addLayer(new NecromancerEyesLayer(this));
		this.model = model;
    }

    @Override
    public ResourceLocation getTextureLocation(NecromancerRenderState necromancerRenderState) {
        return NECROMANCER_LOCATION;
    }

    public void extractRenderState(final Necromancer entity, final NecromancerRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        extractCapeState(entity, state, partialTicks);

		entity.setOrbPosition(state.staffBallPosition);
		state.isLeftHanded = entity.isLeftHanded();
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
