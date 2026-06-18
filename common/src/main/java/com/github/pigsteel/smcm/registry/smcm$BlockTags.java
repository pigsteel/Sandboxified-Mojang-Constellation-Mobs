package com.github.pigsteel.smcm.registry;

import com.github.pigsteel.smcm.SMCM;
import com.github.pigsteel.smcm.services.util.RegistryHandle;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class smcm$BlockTags {
    public static final TagKey<Block> FROSTBITTEN_IMMUNE_TO;

    static {
        FROSTBITTEN_IMMUNE_TO = create("frostbitten_immune_to");
    }

    private static TagKey<Block> create(String name) {
        return TagKey.create(Registries.BLOCK, SMCM.id(name));
    }
}
