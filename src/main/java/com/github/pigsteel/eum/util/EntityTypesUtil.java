package com.github.pigsteel.eum.util;


import net.minecraft.world.entity.EntityType;
//? >= 1.21.11 {
import net.minecraft.world.entity.animal.equine.ZombieHorse;
import net.minecraft.world.entity.animal.golem.IronGolem;
import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.entity.monster.skeleton.Skeleton;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.entity.player.Player;
//?} < 1.21.11 {
/*import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.horse.ZombieHorse;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.entity.player.Player;
*///?}

//? >= 26.2 {
import net.minecraft.world.entity.EntityTypes;
//?}

public class EntityTypesUtil {
	private EntityTypesUtil() {
	}
	public static final EntityType<IronGolem> IRON_GOLEM;
	public static final EntityType<PiglinBrute> PIGLIN_BRUTE;
	public static final EntityType<Skeleton> SKELETON;
	public static final EntityType<Witch> WITCH;
	public static final EntityType<Zombie> ZOMBIE;
	public static final EntityType<ZombieHorse> ZOMBIE_HORSE;
	public static final EntityType<Player> PLAYER;

	static {
		//? <26.2 {
		/*IRON_GOLEM = EntityType.IRON_GOLEM;
		PIGLIN_BRUTE = EntityType.PIGLIN_BRUTE;
		SKELETON = EntityType.SKELETON;
		WITCH = EntityType.WITCH;
		ZOMBIE = EntityType.ZOMBIE;
		ZOMBIE_HORSE = EntityType.ZOMBIE_HORSE;
		PLAYER = EntityType.PLAYER;
		*///?} >=26.2 {
		IRON_GOLEM = EntityTypes.IRON_GOLEM;
		PIGLIN_BRUTE = EntityTypes.PIGLIN_BRUTE;
		SKELETON = EntityTypes.SKELETON;
		WITCH = EntityTypes.WITCH;
		ZOMBIE = EntityTypes.ZOMBIE;
		ZOMBIE_HORSE = EntityTypes.ZOMBIE_HORSE;
		PLAYER = EntityTypes.PLAYER;
		//?}
	}
}
