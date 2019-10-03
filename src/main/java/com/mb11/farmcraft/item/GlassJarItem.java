package com.mb11.farmcraft.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GlassJarItem extends Item {
    public GlassJarItem(Settings settings) {
        super(settings);
    }

    public ItemStack finishUsing(ItemStack itemStack_1, World world_1, LivingEntity livingEntity_1) {
        super.finishUsing(itemStack_1, world_1, livingEntity_1);
        return new ItemStack(Items.glass_jar);
    }
}
