package com.mb11.farmcraft.block;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.EntityContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;
import net.minecraft.world.WorldView;

public class LayerBlock extends Block implements RenderProvider {
    protected static final VoxelShape SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);

    public LayerBlock(Settings settings) {
        super(settings.noCollision().nonOpaque());
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, EntityContext context) {
        return SHAPE;
    }

    protected boolean canPlantOnTop(BlockState state, BlockView view, BlockPos pos) {
        return state.isSideSolidFullSquare(view, pos, Direction.UP);
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState state2, IWorld world, BlockPos pos1, BlockPos pos2) {
        return !state.canPlaceAt(world, pos1) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, state2, world, pos1, pos2);
    }

    public boolean canPlaceAt(BlockState state, WorldView view, BlockPos pos) {
        return this.canPlantOnTop(view.getBlockState(pos.down(1)), view, pos);
    }

    public boolean isTranslucent(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1) {
        return true;
    }

    public void registerRenderer() {
        BlockRenderLayerMap.INSTANCE.putBlock(this, RenderLayer.method_23579());
    }
}
