package com.mb11.farmcraft.api.crop;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.data.server.BlockTagsProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;

import java.util.Random;

import com.mb11.farmcraft.api.crop.CropBlock;


public class CropBlockEntity extends BlockEntity implements Tickable {

    private Identifier id;

    private int yield = 1;
    private int growth = 1;

    public CropBlockEntity(Identifier id) {
        super(Registry.BLOCK_ENTITY.get(id));
        this.id = id;
    }

    public int getYield() {
        return this.yield;
    }

    public int getGrowth() {
        return this.growth;
    }

    public void setYield(int yield) {
        if (yield > 5) {
            this.yield = 5;
        } else {
            this.yield = yield;
        }
    }

    public void setGrowth(int growth) {
        if (growth > 5) {
            this.growth = 5;
        } else {
            this.growth = growth;
        }
    }

    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        CompoundTag genes = new CompoundTag();
        genes.putInt("yield", this.yield);
        genes.putInt("growth", this.growth);
        tag.put("Genes", genes);
        return tag;
    }

    public void fromTag(CompoundTag tag) {
        super.fromTag(tag);
        CompoundTag genes = tag.getCompound("Genes");
        this.yield = genes.getInt("yield");
        this.growth = genes.getInt("growth");
    }

    public CompoundTag getMutation(Random random) {
        CompoundTag tag = this.toTag(new CompoundTag());
        CompoundTag genes = tag.getCompound("Genes");
        if (random.nextInt(32) == 0) {
            genes.putInt("yield", genes.getInt("yield") + 1);
        }
        if (random.nextInt(32) == 0) {
            genes.putInt("growth", genes.getInt("growth") + 1);
        }
        tag.put("Genes", genes);
        return tag;
    }

    public void tick() {
        world = this.getWorld();

        if (this.growth > 5) {
            this.setGrowth(5);
        }

        if (this.growth < 1) {
            this.setGrowth(1);
        }

        if (this.yield > 5) {
            this.setYield(5);
        }

        if (this.yield < 1) {
            this.setYield(1);
        }

        if (world.getBlockState(this.getPos()).getBlock() instanceof CropBlock) {
            CropBlock block = (CropBlock) world.getBlockState(this.getPos()).getBlock();
            if (block.isTwoTall()) {
                BlockState state = world.getBlockState(this.getPos());
                BlockPos other = state.get(block.getBlockHalfProperty()) == DoubleBlockHalf.LOWER ? this.getPos().up() : this.getPos().down(1);
                if (world.getBlockState(other).getBlock() instanceof CropBlock) {
                    if (world.getBlockState(other).get(block.getAgeProperty()) > state.get(block.getAgeProperty())) {
                        world.setBlockState(this.getPos(), world.getBlockState(other).with(block.getBlockHalfProperty(), state.get(block.getBlockHalfProperty())));
                    } else {
                        world.setBlockState(other, world.getBlockState(this.getPos()).with(block.getBlockHalfProperty(), world.getBlockState(other).get(block.getBlockHalfProperty())));
                    }
                }
            }
        }

    }



}
