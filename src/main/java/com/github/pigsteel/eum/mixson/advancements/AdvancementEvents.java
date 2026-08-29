package com.github.pigsteel.eum.mixson.advancements;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.ramixin.mixson.Mixson;
import net.ramixin.mixson.enums.ErrorPolicy;
import net.ramixin.mixson.enums.Lifetime;
import net.ramixin.mixson.util.Index;

public class AdvancementEvents {
	public static void load() {
		Mixson.registerEvent(
				0,
				Lifetime.PERSISTENT,
				ErrorPolicy.LOG,
				"add_eum_mobs_to_kill_a_mob",
				index -> index.idEquals(new Index("advancement/adventure/kill_a_mob")),
				context -> {
					JsonObject root = context.getFile().getAsJsonObject();
					JsonObject criteria = root.getAsJsonObject("criteria");
					JsonArray requirements = root.getAsJsonArray("requirements");
					makeKillCriteria(criteria, requirements, "eum:frostbitten", true);
					makeKillCriteria(criteria, requirements, "eum:viler_witch", true);
					makeKillCriteria(criteria, requirements, "eum:reclaimed", true);
					makeKillCriteria(criteria, requirements, "eum:necromancer", true);
					makeKillCriteria(criteria, requirements, "eum:redstone_golem", true);
					makeKillCriteria(criteria, requirements, "eum:lost", true);
					makeKillCriteria(criteria, requirements, "eum:mountaineer", true);
					makeKillCriteria(criteria, requirements, "eum:geomancer", true);
					makeKillCriteria(criteria, requirements, "eum:iceologer", true);
					makeKillCriteria(criteria, requirements, "eum:windcaller", true);
					makeKillCriteria(criteria, requirements, "eum:sunken", true);
					makeKillCriteria(criteria, requirements, "eum:enchanter", true);
					makeKillCriteria(criteria, requirements, "eum:bruiser", true);
					makeKillCriteria(criteria, requirements, "eum:zombified_piglin_brute", true);
				}
		);

		Mixson.registerEvent(
				0,
				Lifetime.PERSISTENT,
				ErrorPolicy.LOG,
				"add_eum_mobs_to_kill_all_mobs",
				index -> index.idEquals(new Index("advancement/adventure/kill_all_mobs")),
				context -> {
					JsonObject root = context.getFile().getAsJsonObject();
					JsonObject criteria = root.getAsJsonObject("criteria");
					JsonArray requirements = root.getAsJsonArray("requirements");
					makeKillCriteria(criteria, requirements, "eum:frostbitten", false);
					makeKillCriteria(criteria, requirements, "eum:viler_witch", false);
					makeKillCriteria(criteria, requirements, "eum:reclaimed", false);
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
