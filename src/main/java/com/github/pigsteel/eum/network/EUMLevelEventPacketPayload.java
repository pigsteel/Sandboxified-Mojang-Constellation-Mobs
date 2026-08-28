package com.github.pigsteel.eum.network;

import com.github.pigsteel.eum.EUM;
import com.github.pigsteel.eum.core.EUMSoundEvents;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;

//? fabric {
/*import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
 *///?} neoforge {
import net.neoforged.neoforge.network.handling.IPayloadContext;
//?}

public record EUMLevelEventPacketPayload(int event, BlockPos pos) implements CustomPacketPayload {
	public static final ResourceLocation SMCM_EVENT_PAYLOAD_ID = EUM.id("level_event");

	public static final CustomPacketPayload.Type<EUMLevelEventPacketPayload> TYPE = new CustomPacketPayload.Type<>(SMCM_EVENT_PAYLOAD_ID);

	public static final StreamCodec<RegistryFriendlyByteBuf, EUMLevelEventPacketPayload> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.INT, EUMLevelEventPacketPayload::event, BlockPos.STREAM_CODEC, EUMLevelEventPacketPayload::pos, EUMLevelEventPacketPayload::new);

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	//? fabric {
	/*public static void handle(EUMLevelEventPacketPayload payload, ClientPlayNetworking.Context context) {
		ClientLevel level = context.client().level;
	*///?} neoforge {
	public static void handle(EUMLevelEventPacketPayload payload, final IPayloadContext context) {
		ClientLevel level = (ClientLevel)context.player().level();
	//?}

		if (level == null) {
			return;
		}

		//? neoforge {
		context.enqueueWork(() -> {
		//?}
		BlockPos pos = payload.pos();
		RandomSource random = level.getRandom();

		switch(payload.event()) {
			case(1001):
				level.playLocalSound(pos, EUMSoundEvents.ZOMBIE_CONVERTED_TO_FROSTBITTEN.get(), SoundSource.HOSTILE, 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
			break;
			case(1002):
				level.playLocalSound(pos, EUMSoundEvents.NECROMANCER_SUMMON.get(), SoundSource.HOSTILE, 1.0F, 1.0F, false);
				for(int i = 0; i < 20; ++i) {
					double xx = (double)pos.getX() + (double)0.5F + (random.nextDouble() - (double)0.5F) * (double)2.0F;
					double yx = (double)pos.getY() + (double)0.5F + (random.nextDouble() - (double)0.5F) * (double)2.0F;
					double zx = (double)pos.getZ() + (double)0.5F + (random.nextDouble() - (double)0.5F) * (double)2.0F;
					level.addParticle(ParticleTypes.SOUL, xx, yx, zx, (double)0.0F, (double)0.0F, (double)0.0F);
					level.addParticle(ParticleTypes.SOUL_FIRE_FLAME, xx, yx, zx, (double)0.0F, (double)0.0F, (double)0.0F);
				}
			break;
			case(1003):
				level.playLocalSound(pos, EUMSoundEvents.SKELETON_CONVERTED_TO_SUNKEN.get(), SoundSource.HOSTILE, 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
			break;
		}
		//? neoforge {
				})
				.exceptionally(e -> {
					// Handle exception
					context.disconnect(Component.translatable("eum.networking.failed", e.getMessage()));
					return null;
				});
		//?}
	}
}
