package com.mb11.farmcraft;

import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.util.Identifier;
import net.minecraft.world.loot.ConstantLootTableRange;
import net.minecraft.world.loot.condition.RandomChanceLootCondition;
import net.minecraft.world.loot.entry.ItemEntry;

public class LootTables {
    public static void register() {
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
            if (new Identifier("minecraft", "blocks/grass").equals(id)) {
                supplier.withPool(FabricLootPoolBuilder.builder()
                        .withRolls(ConstantLootTableRange.create(1))
                        .withEntry(ItemEntry.builder(Plants.barley.getSeed()))
                        .withEntry(ItemEntry.builder(Plants.rye.getSeed()))
                        .withEntry(ItemEntry.builder(Plants.oat.getSeed()))
                        .withCondition(RandomChanceLootCondition.builder(0.125f))
                );
            } else if (new Identifier("minecraft", "blocks/tall_grass").equals(id)) {
                supplier.withPool(FabricLootPoolBuilder.builder()
                        .withRolls(ConstantLootTableRange.create(1))
                        .withEntry(ItemEntry.builder(Plants.barley.getSeed()))
                        .withEntry(ItemEntry.builder(Plants.rye.getSeed()))
                        .withEntry(ItemEntry.builder(Plants.oat.getSeed()))
                        .withCondition(RandomChanceLootCondition.builder(0.125f))
                );
            } else if (new Identifier("minecraft", "chests/simple_dungeon").equals(id)) {
                supplier.withPool(FabricLootPoolBuilder.builder()
                        .withRolls(ConstantLootTableRange.create(3))
                        .withEntry(ItemEntry.builder(Plants.bell_pepper.getSeed()))
                        .withEntry(ItemEntry.builder(Plants.tomato.getSeed()))
                        .withCondition(RandomChanceLootCondition.builder(0.25f))
                );
            } else if (new Identifier("minecraft", "chests/abandoned_mineshaft").equals(id)) {
                supplier.withPool(FabricLootPoolBuilder.builder()
                        .withRolls(ConstantLootTableRange.create(3))
                        .withEntry(ItemEntry.builder(Plants.bell_pepper.getSeed()))
                        .withEntry(ItemEntry.builder(Plants.tomato.getSeed()))
                        .withCondition(RandomChanceLootCondition.builder(0.25f))
                );
            } else if (new Identifier("minecraft", "chests/pillager_outpost").equals(id)) {
                supplier.withPool(FabricLootPoolBuilder.builder()
                        .withRolls(ConstantLootTableRange.create(3))
                        .withEntry(ItemEntry.builder(Plants.bell_pepper.getItem()))
                        .withEntry(ItemEntry.builder(Plants.tomato.getItem()))
                        .withEntry(ItemEntry.builder(Plants.peanut.getItem()))
                        .withEntry(ItemEntry.builder(Plants.onion.getItem()))
                        .withEntry(ItemEntry.builder(Plants.rice.getItem()))
                        .withEntry(ItemEntry.builder(Plants.barley.getItem()))
                        .withEntry(ItemEntry.builder(Plants.oat.getItem()))
                        .withEntry(ItemEntry.builder(Plants.rye.getItem()))
                        .withCondition(RandomChanceLootCondition.builder(0.25f))
                );
            } else if (new Identifier("minecraft", "chests/shipwreck_supply").equals(id)) {
                supplier.withPool(FabricLootPoolBuilder.builder()
                        .withRolls(ConstantLootTableRange.create(3))
                        .withEntry(ItemEntry.builder(Plants.bell_pepper.getItem()))
                        .withEntry(ItemEntry.builder(Plants.tomato.getItem()))
                        .withEntry(ItemEntry.builder(Plants.peanut.getItem()))
                        .withEntry(ItemEntry.builder(Plants.onion.getItem()))
                        .withEntry(ItemEntry.builder(Plants.rice.getItem()))
                        .withEntry(ItemEntry.builder(Plants.barley.getItem()))
                        .withEntry(ItemEntry.builder(Plants.oat.getItem()))
                        .withEntry(ItemEntry.builder(Plants.rye.getItem()))
                        .withCondition(RandomChanceLootCondition.builder(0.25f))
                );
            }
        });
    }
}
