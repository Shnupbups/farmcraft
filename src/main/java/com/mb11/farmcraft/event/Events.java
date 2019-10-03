package com.mb11.farmcraft.event;

import com.mb11.farmcraft.item.Items;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.Box;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Events {

    public static void register() {
        PlayerTickCallback.EVENT.register((player) -> {

            boolean helmet = false;
            boolean chestplate = false;
            boolean leggings = false;
            boolean boots = false;

            for (int i = 0; i < player.inventory.armor.size(); i++) {
                ItemStack item = player.inventory.armor.get(i);
                if (item.getItem() == Items.bee_mask) {
                    helmet = true;
                }
                if (item.getItem() == Items.bee_jacket) {
                    chestplate = true;
                }
                if (item.getItem() == Items.bee_pants) {
                    leggings = true;
                }
                if (item.getItem() == Items.bee_boots) {
                    boots = true;
                }
            }

            if (helmet && chestplate && leggings && boots) {
                List<Entity> entities = player.getEntityWorld().getEntities(player, new Box(player.getBlockPos().add(16, 16, 16), player.getBlockPos().add(-16, -16, -16)));
                for (int i = 0; i < entities.size(); i++) {
                    Entity entity = entities.get(i);
                    if (entity instanceof BeeEntity) {
                        BeeEntity bee = ((BeeEntity) entity);
                        if (bee.getAttacker() == player) {
                            bee.setAnger(0);
                        }
                    }
                }

            }
            return ActionResult.PASS;
        });

    }
}
