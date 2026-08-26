package com.github.pigsteel.smcm.core;

import com.github.pigsteel.smcm.SMCM;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

import java.util.function.Supplier;

//? neoforge {
import static com.github.pigsteel.smcm.platform.neoforge.NeoforgeVariables.SOUND_EVENTS;
//?}

public class SMCMSoundEvents {
	public static final Supplier<SoundEvent> BRUISER_AMBIENT = register("entity.bruiser.ambient");
	public static final Supplier<SoundEvent> BRUISER_CELEBRATE = register("entity.bruiser.celebrate");
	public static final Supplier<SoundEvent> BRUISER_DEATH = register("entity.bruiser.death");
	public static final Supplier<SoundEvent> BRUISER_HURT = register("entity.bruiser.hurt");
	public static final Supplier<SoundEvent> ENCHANTER_AMBIENT = register("entity.enchanter.ambient");
	public static final Supplier<SoundEvent> ENCHANTER_CELEBRATE = register("entity.enchanter.celebrate");
	public static final Supplier<SoundEvent> ENCHANTER_DEATH = register("entity.enchanter.death");
	public static final Supplier<SoundEvent> ENCHANTER_HURT = register("entity.enchanter.hurt");
	public static final Supplier<SoundEvent> FROSTBITTEN_AMBIENT = register("entity.frostbitten.ambient");
	public static final Supplier<SoundEvent> FROSTBITTEN_DEATH = register("entity.frostbitten.death");
	public static final Supplier<SoundEvent> FROSTBITTEN_HURT = register("entity.frostbitten.hurt");
	public static final Supplier<SoundEvent> FROSTBITTEN_SHOOT = register("entity.frostbitten.shoot");
	public static final Supplier<SoundEvent> FROSTBITTEN_STEP = register("entity.frostbitten.step");
	public static final Supplier<SoundEvent> GEOMANCER_AMBIENT = register("entity.geomancer.ambient");
	public static final Supplier<SoundEvent> GEOMANCER_CELEBRATE = register("entity.geomancer.celebrate");
	public static final Supplier<SoundEvent> GEOMANCER_DEATH = register("entity.geomancer.death");
	public static final Supplier<SoundEvent> GEOMANCER_HURT = register("entity.geomancer.hurt");
	public static final Supplier<SoundEvent> ICEOLOGER_AMBIENT = register("entity.iceologer.ambient");
	public static final Supplier<SoundEvent> ICEOLOGER_CELEBRATE = register("entity.iceologer.celebrate");
	public static final Supplier<SoundEvent> ICEOLOGER_DEATH = register("entity.iceologer.death");
	public static final Supplier<SoundEvent> ICEOLOGER_HURT = register("entity.iceologer.hurt");
	public static final Supplier<SoundEvent> LOST_AMBIENT = register("entity.lost.ambient");
	public static final Supplier<SoundEvent> LOST_DEATH = register("entity.lost.death");
	public static final Supplier<SoundEvent> LOST_HURT = register("entity.lost.hurt");
	public static final Supplier<SoundEvent> LOST_STEP = register("entity.lost.step");
	public static final Supplier<SoundEvent> MOUNTAINEER_AMBIENT = register("entity.mountaineer.ambient");
	public static final Supplier<SoundEvent> MOUNTAINEER_CELEBRATE = register("entity.mountaineer.celebrate");
	public static final Supplier<SoundEvent> MOUNTAINEER_DEATH = register("entity.mountaineer.death");
	public static final Supplier<SoundEvent> MOUNTAINEER_HURT = register("entity.mountaineer.hurt");
	public static final Supplier<SoundEvent> NECROMANCER_AMBIENT = register("entity.necromancer.ambient");
	public static final Supplier<SoundEvent> NECROMANCER_BALL_HIT = register("entity.necromancer_ball.hit");
	public static final Supplier<SoundEvent> NECROMANCER_DEATH = register("entity.necromancer.death");
	public static final Supplier<SoundEvent> NECROMANCER_HURT = register("entity.necromancer.hurt");
	public static final Supplier<SoundEvent> NECROMANCER_LAUGH = register("entity.necromancer.laugh");
	public static final Supplier<SoundEvent> NECROMANCER_PREPARE_SUMMON = register("entity.necromancer.prepare_summon");
	public static final Supplier<SoundEvent> NECROMANCER_PREPARE_SUMMON_ALT = register("entity.necromancer.prepare_summon_alt");
	public static final Supplier<SoundEvent> NECROMANCER_SPELL = register("entity.necromancer.spell");
	public static final Supplier<SoundEvent> NECROMANCER_STEP = register("entity.necromancer.step");
	public static final Supplier<SoundEvent> NECROMANCER_SUMMON = register("entity.necromancer.summon");
	public static final Supplier<SoundEvent> PARROT_IMITATE_ENCHANTER = register("entity.parrot.imitate.enchanter");
	public static final Supplier<SoundEvent> PARROT_IMITATE_FROSTBITTEN = register("entity.parrot.imitate.frostbitten");
	public static final Supplier<SoundEvent> PARROT_IMITATE_LOST = register("entity.parrot.imitate.lost");
	public static final Supplier<SoundEvent> PARROT_IMITATE_NECROMANCER = register("entity.parrot.imitate.necromancer");
	public static final Supplier<SoundEvent> PARROT_IMITATE_RECLAIMED = register("entity.parrot.imitate.reclaimed");
	public static final Supplier<SoundEvent> PARROT_IMITATE_SUNKEN = register("entity.parrot.imitate.sunken");
	public static final Supplier<SoundEvent> PARROT_IMITATE_VILER_WITCH = register("entity.parrot.imitate.viler_witch");
	public static final Supplier<SoundEvent> RECLAIMED_AMBIENT = register("entity.reclaimed.ambient");
	public static final Supplier<SoundEvent> RECLAIMED_BONEMEAL = register("entity.reclaimed.bone_meal");
	public static final Supplier<SoundEvent> RECLAIMED_DEATH = register("entity.reclaimed.death");
	public static final Supplier<SoundEvent> RECLAIMED_HURT = register("entity.reclaimed.hurt");
	public static final Supplier<SoundEvent> RECLAIMED_SHEAR = register("entity.reclaimed.shear");
	public static final Supplier<SoundEvent> RECLAIMED_STEP = register("entity.reclaimed.step");
	public static final Supplier<SoundEvent> RECLAIMED_VOMIT = register("entity.reclaimed.projectile_vomit");
	public static final Supplier<SoundEvent> REDSTONE_GOLEM_AMBIENT = register("entity.redstone_golem.ambient");
	public static final Supplier<SoundEvent> REDSTONE_GOLEM_DEATH = register("entity.redstone_golem.death");
	public static final Supplier<SoundEvent> REDSTONE_GOLEM_HURT = register("entity.redstone_golem.hurt");
	public static final Supplier<SoundEvent> REDSTONE_GOLEM_STEP_HEAVY = register("entity.redstone_golem.step_heavy");
	public static final Supplier<SoundEvent> REDSTONE_GOLEM_STEP_LIGHT = register("entity.redstone_golem.step_light");
	public static final Supplier<SoundEvent> SKELETON_CONVERTED_TO_SUNKEN = register("entity.skeleton.converted_to_sunken");
	public static final Supplier<SoundEvent> SUNKEN_AMBIENT = register("entity.sunken.ambient");
	public static final Supplier<SoundEvent> SUNKEN_AMBIENT_WATER = register("entity.sunken.ambient_water");
	public static final Supplier<SoundEvent> SUNKEN_DEATH = register("entity.sunken.death");
	public static final Supplier<SoundEvent> SUNKEN_DEATH_WATER = register("entity.sunken.death_water");
	public static final Supplier<SoundEvent> SUNKEN_HURT = register("entity.sunken.hurt");
	public static final Supplier<SoundEvent> SUNKEN_HURT_WATER = register("entity.sunken.hurt_water");
	public static final Supplier<SoundEvent> SUNKEN_SHEAR = register("entity.sunken.shear");
	public static final Supplier<SoundEvent> SUNKEN_STEP = register("entity.sunken.step");
	public static final Supplier<SoundEvent> SUNKEN_STEP_WATER = register("entity.sunken.step_water");
	public static final Supplier<SoundEvent> VILER_WITCH_AMBIENT = register("entity.viler_witch.ambient");
	public static final Supplier<SoundEvent> VILER_WITCH_CELEBRATE = register("entity.viler_witch.celebrate");
	public static final Supplier<SoundEvent> VILER_WITCH_DEATH = register("entity.viler_witch.death");
	public static final Supplier<SoundEvent> VILER_WITCH_DRINK = register("entity.viler_witch.drink");
	public static final Supplier<SoundEvent> VILER_WITCH_HURT = register("entity.viler_witch.hurt");
	public static final Supplier<SoundEvent> VILER_WITCH_THROW = register("entity.viler_witch.throw");
	public static final Supplier<SoundEvent> WINDCALLER_AMBIENT = register("entity.windcaller.ambient");
	public static final Supplier<SoundEvent> WINDCALLER_CELEBRATE = register("entity.windcaller.celebrate");
	public static final Supplier<SoundEvent> WINDCALLER_DEATH = register("entity.windcaller.death");
	public static final Supplier<SoundEvent> WINDCALLER_HURT = register("entity.windcaller.hurt");
	public static final Supplier<SoundEvent> ZOMBIE_CONVERTED_TO_FROSTBITTEN = register("entity.zombie.converted_to_frostbitten");
	public static final Supplier<SoundEvent> ZOMBIFIED_PIGLIN_BRUTE_AMBIENT = register("entity.zombified_piglin_brute.ambient");
	public static final Supplier<SoundEvent> ZOMBIFIED_PIGLIN_BRUTE_ANGRY = register("entity.zombified_piglin_brute.angry");
	public static final Supplier<SoundEvent> ZOMBIFIED_PIGLIN_BRUTE_DEATH = register("entity.zombified_piglin_brute.death");
	public static final Supplier<SoundEvent> ZOMBIFIED_PIGLIN_BRUTE_HURT = register("entity.zombified_piglin_brute.hurt");

    public static void load() {}

    public static Supplier<SoundEvent> register(String name) {
        Identifier id = Identifier.fromNamespaceAndPath(SMCM.MOD_ID, name);
		//? neoforge {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
		//?} fabric {
		/*SoundEvent sound = Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
		return () -> sound;
		*///?}
    }
}
