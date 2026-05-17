package com.github.pigsteel.smcm.world.entity.monster.zombie;

import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.zombie.ZombifiedPiglin;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class ZombifiedPiglinBrute extends ZombifiedPiglin {
    public ZombifiedPiglinBrute(EntityType<? extends ZombifiedPiglinBrute> type, Level level) {
        super(type, level);
    }

    @Override
    public boolean isBaby() {
        return false;
    }

    @Override
    public void populateDefaultEquipmentSlots(final RandomSource random, final DifficultyInstance difficulty) {
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.GOLDEN_AXE));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return ZombifiedPiglin.createAttributes()
                .add(Attributes.MAX_HEALTH, 60.0F)
                .add(Attributes.MOVEMENT_SPEED, 0.21F)
                .add(Attributes.ATTACK_DAMAGE, 7.0F)
                .add(Attributes.FOLLOW_RANGE, 12.0F);
    }
}
