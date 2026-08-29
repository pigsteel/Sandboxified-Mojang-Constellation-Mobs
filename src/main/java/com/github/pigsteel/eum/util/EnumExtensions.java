package com.github.pigsteel.eum.util;

import com.github.pigsteel.eum.core.EUMEntityTypes;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.level.pathfinder.PathType;

//? neoforge {
/*import net.neoforged.fml.common.asm.enumextension.EnumProxy;
*///?}

public class EnumExtensions {
	//? neoforge {
	/*public static EnumProxy<Raid.RaiderType> VILER_WITCH_ENUM_PROXY = new EnumProxy<>(
			Raid.RaiderType.class, EUMEntityTypes.VILER_WITCH, new int[]{0, 0, 0, 0, 0, 0, 1, 1}
	);
	*///?}


	public static PathType CLIMBABLE;
	public static final Raid.RaiderType VILER_WITCH;

	static {
		//CLIMBABLE = PathType.SMCM_CLIMBABLE;
		VILER_WITCH = Enum.valueOf(Raid.RaiderType.class, "EUM_VILER_WITCH");
	}

	public static void load() {}
}
