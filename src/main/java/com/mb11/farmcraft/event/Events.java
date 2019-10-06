package com.mb11.farmcraft.event;

import com.mb11.farmcraft.armor.CustomArmorMaterials;
import com.mb11.farmcraft.item.Items;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.item.ArmorItem;
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
            player.inventory.armor.forEach(item -> {
                if (!((ArmorItem) item.getItem()).getMaterial().equals(CustomArmorMaterials.BEESUIT)) {
                    return;
                } else {
                    player.getEntityWorld().getEntities(player, new Box(player.getBlockPos().add(16, 16, 16), player.getBlockPos().add(-16, -16, -16))).forEach(entity -> {
                        if (entity instanceof BeeEntity) {
                            if (((BeeEntity) entity).getAttacker() == player) {
                                ((BeeEntity) entity).setAnger(0);
                            }
                        }
                    });
                }
            });
            return ActionResult.PASS;
        });

    }
}
