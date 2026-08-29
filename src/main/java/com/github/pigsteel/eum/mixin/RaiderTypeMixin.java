package com.github.pigsteel.eum.mixin;

import com.github.pigsteel.eum.EUM;
import com.github.pigsteel.eum.core.EUMEntityTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.entity.raid.Raider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

//? fabric {
@Mixin(Raid.RaiderType.class)
public enum RaiderTypeMixin {
	EUM_VILER_WITCH(EUMEntityTypes.VILER_WITCH.get(), new int[]{0, 0, 0, 0, 0, 0, 1, 1});

	@Shadow
	RaiderTypeMixin(final EntityType<? extends Raider> entityType, final int[] spawnsPerWaveBeforeBonus) {
	}
}
//?} neoforge {
/*@Mixin(EUM.class)
public class RaiderTypeMixin { // dud

}
*///?}
