package com.github.pigsteel.eum.core;

import com.github.pigsteel.eum.EUM;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class EUMBlockTags {
    public static final TagKey<Block> FROSTBITTEN_IMMUNE_TO;

    static {
        FROSTBITTEN_IMMUNE_TO = create("frostbitten_immune_to");
    }

    private static TagKey<Block> create(String name) {
        return TagKey.create(Registries.BLOCK, EUM.id(name));
    }

}
