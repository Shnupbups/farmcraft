package com.mb11.farmcraft;

import com.mb11.farmcraft.block.LayerBlock;
import com.mb11.farmcraft.block.LeafLayerBlock;
import com.mb11.farmcraft.block.RenderProvider;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.mb11.farmcraft.Farmcraft.MODID;

public class Blocks {

    // TODO: Other leaf layers
    // TODO: Block Loot Tables for all leaf layers
    // TODO: Models for other leaf layers
    public static final Block oak_leaf_layer = new LeafLayerBlock(FabricBlockSettings.of(Material.PLANT).sounds(BlockSoundGroup.GRASS).build(), net.minecraft.block.Blocks.OAK_LEAVES);

    public static void init() {
        register(oak_leaf_layer, new Identifier(MODID, "oak_leaf_layer"));
    }

    public static void register(Block block, Identifier id) {
        Registry.register(Registry.BLOCK, id, block);
        Registry.register(Registry.ITEM, id, new BlockItem(block, new Item.Settings()));
        if (block instanceof RenderProvider) {
            ((RenderProvider) block).registerRenderer();
        }
    }
}
