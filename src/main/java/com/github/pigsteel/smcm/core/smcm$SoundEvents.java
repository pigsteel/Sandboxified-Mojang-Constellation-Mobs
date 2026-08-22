package com.github.pigsteel.smcm.core;

import com.github.pigsteel.smcm.SMCM;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

import java.util.function.Supplier;

//? neoforge {
/*import static com.github.pigsteel.smcm.platform.neoforge.NeoforgeVariables.SOUND_EVENTS;
*///?}

public class smcm$SoundEvents {
    public static final Supplier<SoundEvent> FROSTBITTEN_AMBIENT = registerSoundEvent("entity.frostbitten.ambient");
    public static final Supplier<SoundEvent> FROSTBITTEN_HURT = registerSoundEvent("entity.frostbitten.hurt");
    public static final Supplier<SoundEvent> FROSTBITTEN_DEATH = registerSoundEvent("entity.frostbitten.death");
    public static final Supplier<SoundEvent> FROSTBITTEN_SHOOT = registerSoundEvent("entity.frostbitten.shoot");
	public static final Supplier<SoundEvent> FROSTBITTEN_STEP = registerSoundEvent("entity.frostbitten.step");
    public static final Supplier<SoundEvent> ZOMBIE_CONVERTED_TO_FROSTBITTEN = registerSoundEvent("entity.zombie.converted_to_frostbitten");
    public static final Supplier<SoundEvent> RECLAIMED_AMBIENT = registerSoundEvent("entity.reclaimed.ambient");
    public static final Supplier<SoundEvent> RECLAIMED_DEATH = registerSoundEvent("entity.reclaimed.death");
    public static final Supplier<SoundEvent> RECLAIMED_HURT = registerSoundEvent("entity.reclaimed.hurt");
    public static final Supplier<SoundEvent> RECLAIMED_STEP = registerSoundEvent("entity.reclaimed.step");
    public static final Supplier<SoundEvent> RECLAIMED_SHEAR = registerSoundEvent("entity.reclaimed.shear");
    public static final Supplier<SoundEvent> RECLAIMED_BONEMEAL = registerSoundEvent("entity.reclaimed.bone_meal");
    public static final Supplier<SoundEvent> RECLAIMED_VOMIT = registerSoundEvent("entity.reclaimed.projectile_vomit");
    public static final Supplier<SoundEvent> ENCHANTER_AMBIENT = registerSoundEvent("entity.enchanter.ambient");
    public static final Supplier<SoundEvent> ENCHANTER_DEATH = registerSoundEvent("entity.enchanter.death");
    public static final Supplier<SoundEvent> ENCHANTER_HURT = registerSoundEvent("entity.enchanter.hurt");
    public static final Supplier<SoundEvent> NECROMANCER_AMBIENT =
            registerSoundEvent("entity.necromancer.ambient");
    public static final Supplier<SoundEvent> NECROMANCER_DEATH =
            registerSoundEvent("entity.necromancer.death");
    public static final Supplier<SoundEvent> NECROMANCER_HURT =
            registerSoundEvent("entity.necromancer.hurt");
    public static final Supplier<SoundEvent> NECROMANCER_STEP =
            registerSoundEvent("entity.necromancer.step");
    public static final Supplier<SoundEvent> NECROMANCER_LAUGH =
            registerSoundEvent("entity.necromancer.laugh");
    public static final Supplier<SoundEvent> NECROMANCER_SUMMON =
            registerSoundEvent("entity.necromancer.summon");
    public static final Supplier<SoundEvent> NECROMANCER_PREPARE_SUMMON =
            registerSoundEvent("entity.necromancer.prepare_summon");
	public static final Supplier<SoundEvent> NECROMANCER_PREPARE_SUMMON_ALT =
			registerSoundEvent("entity.necromancer.prepare_summon_alt");
    public static final Supplier<SoundEvent> NECROMANCER_SPELL =
            registerSoundEvent("entity.necromancer.spell");
	public static final Supplier<SoundEvent> NECROMANCER_BALL_HIT =
			registerSoundEvent("entity.necromancer_ball.hit");
    public static final Supplier<SoundEvent> PARROT_IMITATE_FROSTBITTEN = registerSoundEvent("entity.parrot.imitate.frostbitten");
    public static final Supplier<SoundEvent> PARROT_IMITATE_RECLAIMED = registerSoundEvent("entity.parrot.imitate.reclaimed");
    public static final Supplier<SoundEvent> PARROT_IMITATE_ENCHANTER = registerSoundEvent("entity.parrot.imitate.enchanter");
    public static final Supplier<SoundEvent> PARROT_IMITATE_NECROMANCER = registerSoundEvent("entity.parrot.imitate.necromancer");
    public static final Supplier<SoundEvent> PARROT_IMITATE_LOST = registerSoundEvent("entity.parrot.imitate.lost");
    public static final Supplier<SoundEvent> PARROT_IMITATE_SUNKEN = registerSoundEvent("entity.parrot.imitate.sunken");
	public static final Supplier<SoundEvent> PARROT_IMITATE_VILER_WITCH = registerSoundEvent("entity.parrot.imitate.viler_witch");

    public static final Supplier<SoundEvent> LOST_AMBIENT =
            registerSoundEvent("entity.lost.ambient");
    public static final Supplier<SoundEvent> LOST_DEATH =
            registerSoundEvent("entity.lost.death");
    public static final Supplier<SoundEvent> LOST_HURT =
            registerSoundEvent("entity.lost.hurt");
    public static final Supplier<SoundEvent> LOST_STEP =
            registerSoundEvent("entity.lost.step");

    public static final Supplier<SoundEvent> SUNKEN_AMBIENT =
            registerSoundEvent("entity.sunken.ambient");
    public static final Supplier<SoundEvent> SUNKEN_DEATH =
            registerSoundEvent("entity.sunken.death");
    public static final Supplier<SoundEvent> SUNKEN_HURT =
            registerSoundEvent("entity.sunken.hurt");
    public static final Supplier<SoundEvent> SUNKEN_STEP =
            registerSoundEvent("entity.sunken.step");
	public static final Supplier<SoundEvent> SUNKEN_SHEAR =
			registerSoundEvent("entity.sunken.shear");

    public static final Supplier<SoundEvent> REDSTONE_GOLEM_STEP_LIGHT =
            registerSoundEvent("entity.redstone_golem.step_light");
    public static final Supplier<SoundEvent> REDSTONE_GOLEM_HURT =
            registerSoundEvent("entity.redstone_golem.hurt");
    public static final Supplier<SoundEvent> REDSTONE_GOLEM_DEATH =
            registerSoundEvent("entity.redstone_golem.death");
    public static final Supplier<SoundEvent> REDSTONE_GOLEM_STEP_HEAVY =
            registerSoundEvent("entity.redstone_golem.step_heavy");
    public static final Supplier<SoundEvent> REDSTONE_GOLEM_AMBIENT =
            registerSoundEvent("entity.redstone_golem.ambient");

    public static final Supplier<SoundEvent> GEOMANCER_AMBIENT =
            registerSoundEvent("entity.geomancer.ambient");
    public static final Supplier<SoundEvent> GEOMANCER_HURT =
            registerSoundEvent("entity.geomancer.hurt");
    public static final Supplier<SoundEvent> GEOMANCER_DEATH =
            registerSoundEvent("entity.geomancer.death");

	public static final Supplier<SoundEvent> VILER_WITCH_AMBIENT =
			registerSoundEvent("entity.viler_witch.ambient");
	public static final Supplier<SoundEvent> VILER_WITCH_DEATH =
			registerSoundEvent("entity.viler_witch.death");
	public static final Supplier<SoundEvent> VILER_WITCH_DRINK =
			registerSoundEvent("entity.viler_witch.drink");
	public static final Supplier<SoundEvent> VILER_WITCH_HURT =
			registerSoundEvent("entity.viler_witch.hurt");
	public static final Supplier<SoundEvent> VILER_WITCH_THROW =
			registerSoundEvent("entity.viler_witch.throw");
	public static final Supplier<SoundEvent> VILER_WITCH_CELEBRATE =
			registerSoundEvent("entity.viler_witch.celebrate");



	public static final Supplier<SoundEvent> SKELETON_CONVERTED_TO_SUNKEN =
            registerSoundEvent("entity.skeleton.converted_to_sunken");

    public static Supplier<SoundEvent> registerSoundEvent(String name) {
        Identifier id = Identifier.fromNamespaceAndPath(SMCM.MOD_ID, name);
		//? neoforge {
        /*return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
		*///?} fabric {
		SoundEvent sound = Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
		return () -> sound;
		//?}
    }

    public static void load() {}
}
