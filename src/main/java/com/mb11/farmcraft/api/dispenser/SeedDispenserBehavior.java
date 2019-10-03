package com.mb11.farmcraft.api.dispenser;

import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.BlockPlacementDispenserBehavior;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class SeedDispenserBehavior extends BlockPlacementDispenserBehavior {

    public SeedDispenserBehavior() {

    }

    protected ItemStack dispenseSilently(BlockPointer blockPointer_1, ItemStack itemStack_1) {
        this.success = false;
        Item item_1 = itemStack_1.getItem();
        if (item_1 instanceof BlockItem) {
            Direction direction_1 = (Direction)blockPointer_1.getBlockState().get(DispenserBlock.FACING);
            BlockPos blockPos_1 = blockPointer_1.getBlockPos().offset(direction_1);
            Direction direction_2 = blockPointer_1.getWorld().isAir(blockPos_1.down()) ? direction_1 : Direction.UP;
            if (!this.success) {
                this.success = ((BlockItem) item_1).place(new AutomaticItemPlacementContext(blockPointer_1.getWorld(), blockPos_1, direction_1, itemStack_1, direction_2)) == ActionResult.SUCCESS;
            }
        }

        return itemStack_1;
    }
}
