package com.github.pigsteel.eum.mixin;

import com.github.pigsteel.eum.core.EUMEntityTypes;
import com.github.pigsteel.eum.util.EntityTypesUtil;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ConversionParams;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import net.minecraft.world.level.Level;
//? neoforge {
import net.neoforged.neoforge.event.EventHooks;
//?}
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractPiglin.class)
public class AbstractPiglinMixin extends Monster {

	protected AbstractPiglinMixin(EntityType<? extends Monster> type, Level level) {
		super(type, level);
	}

	@Inject(
			method = "finishConversion(Lnet/minecraft/server/level/ServerLevel;)V",
			at = @At("HEAD"),
			cancellable = true
	)
	protected void eum$finishConversion(ServerLevel level, CallbackInfo ci) {
		if (this.is(
				EntityTypesUtil.PIGLIN_BRUTE
		)) {
			this.convertTo(EUMEntityTypes.ZOMBIFIED_PIGLIN_BRUTE.get(), ConversionParams.single(this, true, true), (zombified) -> {
				zombified.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 200, 0));
				//? neoforge {
				EventHooks.onLivingConvert(this, zombified);
				//?}
			});
			ci.cancel();
		}
	}
}
