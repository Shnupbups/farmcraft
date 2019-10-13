package com.mb11.farmcraft.block;

import net.fabricmc.fabric.api.client.render.ColorProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.color.block.BlockColorProvider;

public class LeafLayerBlock extends LayerBlock {

    private Block superblock;

    public LeafLayerBlock(Settings settings, Block superblock) {
        super(settings);
        this.superblock = superblock;
    }

    public void registerRenderer() {
        super.registerRenderer();
        ColorProviderRegistry.BLOCK.register((block, pos, world, layer) -> {
            BlockColorProvider provider = ColorProviderRegistry.BLOCK.get(this.superblock);
            return provider == null ? -1 : provider.getColor(block, pos, world, layer);
        }, this);
    }
}
