package com.github.pigsteel.smcm.mixson.advancements;

import com.github.pigsteel.smcm.SMCM;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.resources.Identifier;
import net.ramixin.mixson.Mixson;
import net.ramixin.mixson.enums.ErrorPolicy;
import net.ramixin.mixson.enums.Lifetime;
import net.ramixin.mixson.util.Index;

public class AdvancementEvents {
	public static void init() {
		Mixson.registerEvent(
				0,
				Lifetime.PERSISTENT,
				ErrorPolicy.LOG,
				"add_smcm_mobs_to_kill_a_mob",
				index -> index.idEquals(new Index("advancement/adventure/kill_a_mob")),
				context -> {
					JsonObject root = context.getFile().getAsJsonObject();
					JsonObject criteria = root.getAsJsonObject("criteria");
					JsonArray requirements = root.getAsJsonArray("requirements");
					makeKillCriteria(criteria, requirements, "smcm:frostbitten", true);
					makeKillCriteria(criteria, requirements, "smcm:viler_witch", true);
					makeKillCriteria(criteria, requirements, "smcm:reclaimed", true);
					makeKillCriteria(criteria, requirements, "smcm:necromancer", true);
					makeKillCriteria(criteria, requirements, "smcm:redstone_golem", true);
					makeKillCriteria(criteria, requirements, "smcm:lost", true);
					makeKillCriteria(criteria, requirements, "smcm:mountaineer", true);
					makeKillCriteria(criteria, requirements, "smcm:geomancer", true);
					makeKillCriteria(criteria, requirements, "smcm:iceologer", true);
					makeKillCriteria(criteria, requirements, "smcm:windcaller", true);
					makeKillCriteria(criteria, requirements, "smcm:sunken", true);
					makeKillCriteria(criteria, requirements, "smcm:enchanter", true);
					makeKillCriteria(criteria, requirements, "smcm:bruiser", true);
					makeKillCriteria(criteria, requirements, "smcm:zombified_piglin_brute", true);
				}
		);

		Mixson.registerEvent(
				0,
				Lifetime.PERSISTENT,
				ErrorPolicy.LOG,
				"add_smcm_mobs_to_kill_all_mobs",
				index -> index.idEquals(new Index("advancement/adventure/kill_all_mobs")),
				context -> {
					JsonObject root = context.getFile().getAsJsonObject();
					JsonObject criteria = root.getAsJsonObject("criteria");
					JsonArray requirements = root.getAsJsonArray("requirements");
					makeKillCriteria(criteria, requirements, "smcm:frostbitten", false);
					makeKillCriteria(criteria, requirements, "smcm:viler_witch", false);
					makeKillCriteria(criteria, requirements, "smcm:reclaimed", false);
				}
		);

	}

	public static void makeKillCriteria(JsonObject criteria, JsonArray requirements, String id, boolean inner) {
		JsonObject predicate = new JsonObject();
		//? <26.2 {
		/*predicate.addProperty("type", id);
		*///?} >= 26.2 {
		predicate.addProperty("minecraft:entity_type", id);
		//?}

		// creating the inner condition item object
		JsonObject conditionItem = new JsonObject();
		conditionItem.addProperty("condition", "minecraft:entity_properties");
		conditionItem.addProperty("entity", "this");
		conditionItem.add("predicate", predicate);

		// creating the entities array and add the condition item: [...]
		JsonArray entityArray = new JsonArray();
		entityArray.add(conditionItem);

		// 4. Create the conditions object: {"entity": [...]}
		JsonObject conditions = new JsonObject();
		conditions.add("entity", entityArray);

		// 5. Create the inner body: {"conditions": {...}, "trigger": "..."}
		JsonObject innerBody = new JsonObject();
		innerBody.add("conditions", conditions);
		innerBody.addProperty("trigger", "minecraft:player_killed_entity");

		criteria.add(id, innerBody);

		if(inner) {
			requirements.get(0).getAsJsonArray().add(id);
		} else {
			JsonArray requirement = new JsonArray();
			requirement.add(id);
			requirements.add(requirement);
		}
	}
}
