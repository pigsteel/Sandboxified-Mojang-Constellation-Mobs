package com.github.pigsteel.smcm.util;

import com.github.pigsteel.smcm.core.SMCMEntityTypes;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.level.pathfinder.PathType;

//? neoforge {
/*import net.neoforged.fml.common.asm.enumextension.EnumProxy;
*///?}

public class EnumExtensions {
	//? neoforge {
	/*public static EnumProxy<Raid.RaiderType> VILER_WITCH_ENUM_PROXY = new EnumProxy<>(
			Raid.RaiderType.class, SMCMEntityTypes.VILER_WITCH, new int[]{0, 0, 0, 0, 0, 0, 1, 1}
	);
	*///?}


	public static PathType CLIMBABLE;
	public static final Raid.RaiderType VILER_WITCH;

	static {
		//CLIMBABLE = PathType.SMCM_CLIMBABLE;
		VILER_WITCH = Enum.valueOf(Raid.RaiderType.class, "SMCM_VILER_WITCH");
	}

	public static void load() {}
}
