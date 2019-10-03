package com.mb11.farmcraft;

import com.mb11.farmcraft.Plants;
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
            }
        });
    }
}
