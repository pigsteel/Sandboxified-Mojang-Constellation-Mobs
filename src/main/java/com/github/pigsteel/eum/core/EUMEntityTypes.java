package com.github.pigsteel.eum.core;

import com.github.pigsteel.eum.EUM;
import com.github.pigsteel.eum.world.entity.monster.VilerWitch;
import com.github.pigsteel.eum.world.entity.monster.Wildfire;
import com.github.pigsteel.eum.world.entity.monster.Wraith;
import com.github.pigsteel.eum.world.entity.monster.illager.Bruiser;
import com.github.pigsteel.eum.world.entity.monster.illager.Enchanter;
import com.github.pigsteel.eum.world.entity.monster.illager.Geomancer;
import com.github.pigsteel.eum.world.entity.monster.illager.Iceologer;
import com.github.pigsteel.eum.world.entity.monster.illager.Mountaineer;
import com.github.pigsteel.eum.world.entity.monster.illager.Windcaller;
import com.github.pigsteel.eum.world.entity.monster.necromancer.Necromancer;
import com.github.pigsteel.eum.world.entity.monster.piglin.PiglinFarmer;
import com.github.pigsteel.eum.world.entity.monster.redstonegolem.RedstoneGolem;
import com.github.pigsteel.eum.world.entity.monster.redstonemonstrosity.RedstoneMonstrosity;
import com.github.pigsteel.eum.world.entity.monster.skeleton.Lost;
import com.github.pigsteel.eum.world.entity.monster.skeleton.Sunken;
import com.github.pigsteel.eum.world.entity.monster.zombie.Frostbitten;
import com.github.pigsteel.eum.world.entity.monster.zombie.Reclaimed;
import com.github.pigsteel.eum.world.entity.monster.zombie.ZombifiedPiglinBrute;
import com.github.pigsteel.eum.world.entity.projectile.FrostbittenSnowball;
import com.github.pigsteel.eum.world.entity.projectile.GeomancerWall;
import com.github.pigsteel.eum.world.entity.projectile.NecromancerBall;
import com.github.pigsteel.eum.world.entity.projectile.ReclaimedPuke;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityType.Builder;
import net.minecraft.world.entity.MobCategory;

import java.util.function.Supplier;

public class EUMEntityTypes {
	private EUMEntityTypes() {}

	public static final Supplier<EntityType<Bruiser>> BRUISER;
	public static final Supplier<EntityType<Enchanter>> ENCHANTER;
	public static final Supplier<EntityType<Frostbitten>> FROSTBITTEN;
	public static final Supplier<EntityType<FrostbittenSnowball>> FROSTBITTEN_SNOWBALL;
	public static final Supplier<EntityType<Geomancer>> GEOMANCER;
	public static final Supplier<EntityType<GeomancerWall>> GEOMANCER_WALL;
	public static final Supplier<EntityType<Iceologer>> ICEOLOGER;
	public static final Supplier<EntityType<Lost>> LOST;
	public static final Supplier<EntityType<Mountaineer>> MOUNTAINEER;
	public static final Supplier<EntityType<Necromancer>> NECROMANCER;
	public static final Supplier<EntityType<NecromancerBall>> NECROMANCER_BALL;
	public static final Supplier<EntityType<PiglinFarmer>> PIGLIN_FARMER;
	public static final Supplier<EntityType<Reclaimed>> RECLAIMED;
	public static final Supplier<EntityType<ReclaimedPuke>> RECLAIMED_PUKE;
	public static final Supplier<EntityType<RedstoneGolem>> REDSTONE_GOLEM;
	public static final Supplier<EntityType<RedstoneMonstrosity>> REDSTONE_MONSTROSITY;
	public static final Supplier<EntityType<Sunken>> SUNKEN;
	public static final Supplier<EntityType<VilerWitch>> VILER_WITCH;
	public static final Supplier<EntityType<Wildfire>> WILDFIRE;
	public static final Supplier<EntityType<Windcaller>> WINDCALLER;
	public static final Supplier<EntityType<Wraith>> WRAITH;
	public static final Supplier<EntityType<ZombifiedPiglinBrute>> ZOMBIFIED_PIGLIN_BRUTE;

	static {
		BRUISER = register(
				"bruiser",
				Builder.of(Bruiser::new, MobCategory.MONSTER)
						.sized(0.6F, 1.95F)
						.passengerAttachments(2.0F)
						.ridingOffset(-0.6F)
						.clientTrackingRange(8)
						.notInPeaceful()
		);

		ENCHANTER = register("enchanter", Builder.of(Enchanter::new, MobCategory.MONSTER)
				.sized(0.6F, 1.95F)
				.passengerAttachments(2.0F)
				.ridingOffset(-0.6F)
				.clientTrackingRange(8)
				.notInPeaceful()
		);

		FROSTBITTEN = register("frostbitten", Builder.of(Frostbitten::new, MobCategory.MONSTER)
				.sized(0.6F, 1.95F)
				.eyeHeight(1.74F)
				.passengerAttachments(2.075F)
				.ridingOffset(-0.7F)
				//? >= 26.2 {
				/*.immuneTo(EUMBlockTags.FROSTBITTEN_IMMUNE_TO)
				*///?}
				.clientTrackingRange(8)
				.notInPeaceful()
		);

		RECLAIMED = register("reclaimed", Builder.of(Reclaimed::new, MobCategory.MONSTER)
				.sized(0.6F, 1.95F)
				.eyeHeight(1.74F)
				.passengerAttachments(2.075F)
				.ridingOffset(-0.7F)
				.clientTrackingRange(8)
				.notInPeaceful()
		);

		RECLAIMED_PUKE = register("reclaimed_puke", Builder.<ReclaimedPuke>of(ReclaimedPuke::new, MobCategory.MISC)
				.sized(0.25F, 0.25F)
				.clientTrackingRange(4)
				.noLootTable()
				.updateInterval(20)
		);

		SUNKEN = register("sunken", Builder.of(Sunken::new, MobCategory.MONSTER)
				.sized(0.6F, 1.99F)
				.eyeHeight(1.74F)
				.ridingOffset(-0.7F)
				.clientTrackingRange(8)
				.notInPeaceful()
		);

		LOST = register("lost", Builder.of(Lost::new, MobCategory.MONSTER)
				.sized(0.6F, 1.95F)
				.eyeHeight(1.74F)
				.ridingOffset(-0.7F)
				.clientTrackingRange(8)
				.notInPeaceful()
		);

		NECROMANCER = register("necromancer", Builder.of(Necromancer::new, MobCategory.MONSTER)
				.sized(0.7F, 2.4F)
				.eyeHeight(2.1F)
				.ridingOffset(-0.875F)
				.clientTrackingRange(16)
				.notInPeaceful()
		);

		ZOMBIFIED_PIGLIN_BRUTE = register("zombified_piglin_brute", Builder.of(ZombifiedPiglinBrute::new, MobCategory.MONSTER)
				.fireImmune()
				.sized(0.6F, 1.95F)
				.eyeHeight(1.79F)
				.passengerAttachments(2.0F)
				.ridingOffset(-0.7F)
				.clientTrackingRange(8)
				.notInPeaceful()
		);

		GEOMANCER = register("geomancer", Builder.of(Geomancer::new, MobCategory.MONSTER)
				.canSpawnFarFromPlayer()
				.sized(0.6F, 1.95F)
				.passengerAttachments(2.0F)
				.ridingOffset(-0.6F)
				.clientTrackingRange(8)
				.notInPeaceful()
		);

		ICEOLOGER = register("iceologer", Builder.of(Iceologer::new, MobCategory.MONSTER)
				.canSpawnFarFromPlayer()
				.sized(0.6F, 1.95F)
				.passengerAttachments(2.0F)
				.ridingOffset(-0.6F)
				.clientTrackingRange(8)
				.notInPeaceful()
		);

		MOUNTAINEER = register("mountaineer", Builder.of(Mountaineer::new, MobCategory.MONSTER)
				.canSpawnFarFromPlayer()
				.sized(0.6F, 1.95F)
				.passengerAttachments(2.0F)
				.ridingOffset(-0.6F)
				.clientTrackingRange(8)
				.notInPeaceful()
		);

		WINDCALLER = register("windcaller", Builder.of(Windcaller::new, MobCategory.MONSTER)
				.canSpawnFarFromPlayer()
				.sized(0.6F, 1.95F)
				.passengerAttachments(2.0F)
				.ridingOffset(-0.6F)
				.clientTrackingRange(8)
				.notInPeaceful()
		);

		PIGLIN_FARMER = register("piglin_farmer", Builder.of(PiglinFarmer::new, MobCategory.MONSTER)
				.sized(0.6F, 1.95F)
				.eyeHeight(1.79F)
				.passengerAttachments(2.0125F)
				.ridingOffset(-0.7F)
				.clientTrackingRange(8)
		);

		VILER_WITCH = register("viler_witch", Builder.of(VilerWitch::new, MobCategory.MONSTER)
				.sized(0.6F, 1.95F)
				.eyeHeight(1.62F)
				.passengerAttachments(2.2625F)
				.clientTrackingRange(8)
				.notInPeaceful()
		);

		REDSTONE_GOLEM = register("redstone_golem", Builder.of(RedstoneGolem::new, MobCategory.MONSTER)
				.sized(3.5F, 4.0F)
				.eyeHeight(3.25F)
		);

		FROSTBITTEN_SNOWBALL = register("frostbitten_snowball", Builder.<FrostbittenSnowball>of(FrostbittenSnowball::new, MobCategory.MISC)
				.noLootTable()
				.sized(0.25F, 0.25F)
				.clientTrackingRange(4)
				.updateInterval(10)
		);

		WILDFIRE = register("wildfire", Builder.of(Wildfire::new, MobCategory.MONSTER)
				.sized(2.0F, 2.0F)
				.notInPeaceful()
		);

		WRAITH = register("wraith", Builder.of(Wraith::new, MobCategory.MONSTER)
				.sized(0.6F, 1.95F)
				.notInPeaceful()
		);

		GEOMANCER_WALL = register("geomancer_wall", Builder.of(GeomancerWall::new, MobCategory.MISC)
				.sized(0.5F, 0.0F));

		NECROMANCER_BALL = register("necromancer_ball", Builder.<NecromancerBall>of(NecromancerBall::new, MobCategory.MISC)
				.noLootTable()
				.sized(0.375F, 0.375F)
				.eyeHeight(0.0F)
				.clientTrackingRange(4)
				.updateInterval(10)
		);

		REDSTONE_MONSTROSITY = register("redstone_monstrosity", Builder.of(RedstoneMonstrosity::new, MobCategory.MONSTER));
	}

	public static <T extends Entity> Supplier<EntityType<T>> register(String id, Builder<T> builder) {
		return EUM.xplat().register(id, builder);
	}

	public static void load() {}
}
