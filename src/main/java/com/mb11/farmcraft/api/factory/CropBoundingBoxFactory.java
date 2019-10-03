package com.mb11.farmcraft.api.factory;

import net.minecraft.block.Block;
import net.minecraft.util.shape.VoxelShape;

public class CropBoundingBoxFactory {

    public CropBoundingBoxFactory() {

    }

    public VoxelShape[] buildBoundingBoxes(float[] heights) {
        VoxelShape[] boxes = new VoxelShape[heights.length + 1];
        for (int i = 0; i < heights.length; i++) {
            boxes[i] = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, heights[i], 16.0D);
        }
        return boxes;
    }
}
