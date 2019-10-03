package com.mb11.farmcraft.api.crop;

import net.minecraft.advancement.criterion.Criterions;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.Property;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.List;

public class CropItem extends BlockItem {
    public CropItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public ActionResult place(ItemPlacementContext itemPlacementContext_1) {
        if (!itemPlacementContext_1.canPlace()) {
            return ActionResult.FAIL;
        } else {
            ItemPlacementContext itemPlacementContext_2 = this.getPlacementContext(itemPlacementContext_1);
            if (itemPlacementContext_2 == null) {
                return ActionResult.FAIL;
            } else {
                BlockState blockState_1 = this.getPlacementState(itemPlacementContext_2);
                if (blockState_1 == null) {
                    return ActionResult.FAIL;
                } else if (!this.place(itemPlacementContext_2, blockState_1)) {
                    return ActionResult.FAIL;
                } else {
                    BlockPos blockPos_1 = itemPlacementContext_2.getBlockPos();
                    World world_1 = itemPlacementContext_2.getWorld();
                    PlayerEntity playerEntity_1 = itemPlacementContext_2.getPlayer();
                    ItemStack itemStack_1 = itemPlacementContext_2.getStack();
                    BlockState blockState_2 = world_1.getBlockState(blockPos_1);
                    Block block_1 = blockState_2.getBlock();
                    if (block_1 == blockState_1.getBlock()) {
                        blockState_2 = this.placeFromTag(blockPos_1, world_1, itemStack_1, blockState_2);
                        this.postPlacement(blockPos_1, world_1, playerEntity_1, itemStack_1, blockState_2);
                        block_1.onPlaced(world_1, blockPos_1, blockState_2, playerEntity_1, itemStack_1);
                        if (playerEntity_1 instanceof ServerPlayerEntity) {
                            Criterions.PLACED_BLOCK.handle((ServerPlayerEntity)playerEntity_1, blockPos_1, itemStack_1);
                        }
                    }

                    BlockSoundGroup blockSoundGroup_1 = blockState_2.getSoundGroup();
                    world_1.playSound(playerEntity_1, blockPos_1, this.getPlaceSound(blockState_2), SoundCategory.BLOCKS, (blockSoundGroup_1.getVolume() + 1.0F) / 2.0F, blockSoundGroup_1.getPitch() * 0.8F);
                    itemStack_1.decrement(1);
                    return ActionResult.SUCCESS;
                }
            }
        }
    }

    @Override
    protected boolean postPlacement(BlockPos blockPos_1, World world_1, PlayerEntity playerEntity_1, ItemStack itemStack_1, BlockState blockState_1) {
        return writeTagToBlockEntity(world_1, playerEntity_1, blockPos_1, itemStack_1);
    }

    public static boolean writeTagToBlockEntity(World world_1, PlayerEntity playerEntity_1, BlockPos blockPos_1, ItemStack itemStack_1) {
        MinecraftServer minecraftServer_1 = world_1.getServer();
        if (minecraftServer_1 == null) {
            return false;
        } else {
            CompoundTag compoundTag_1 = itemStack_1.getSubTag("BlockEntityTag");

            BlockEntity blockEntity_1 = world_1.getBlockEntity(blockPos_1);
            if (blockEntity_1 != null) {
                if (!world_1.isClient && blockEntity_1.shouldNotCopyTagFromItem() && (playerEntity_1 == null || !playerEntity_1.isCreativeLevelTwoOp())) {
                    return false;
                }

                CompoundTag compoundTag_2 = blockEntity_1.toTag(new CompoundTag());
                CompoundTag compoundTag_3 = compoundTag_2.method_10553();
                if (compoundTag_1 != null) {
                    compoundTag_2.copyFrom(compoundTag_1);
                }

                CompoundTag genes = itemStack_1.getSubTag("Genes");
                compoundTag_2.put("Genes", genes);

                compoundTag_2.putInt("x", blockPos_1.getX());
                compoundTag_2.putInt("y", blockPos_1.getY());
                compoundTag_2.putInt("z", blockPos_1.getZ());
                if (!compoundTag_2.equals(compoundTag_3)) {
                    blockEntity_1.fromTag(compoundTag_2);
                    blockEntity_1.markDirty();
                    return true;
                }
            }
        }
        return false;
    }

    private static <T extends Comparable<T>> BlockState with(BlockState blockState_1, Property<T> property_1, String string_1) {
        return (BlockState)property_1.getValue(string_1).map((comparable_1) -> {
            return (BlockState)blockState_1.with(property_1, comparable_1);
        }).orElse(blockState_1);
    }

    private BlockState placeFromTag(BlockPos blockPos_1, World world_1, ItemStack itemStack_1, BlockState blockState_1) {
        BlockState blockState_2 = blockState_1;
        CompoundTag compoundTag_1 = itemStack_1.getTag();
        if (compoundTag_1 != null) {
            CompoundTag compoundTag_2 = compoundTag_1.getCompound("BlockStateTag");
            StateFactory<Block, BlockState> stateFactory_1 = blockState_1.getBlock().getStateFactory();
            Iterator var9 = compoundTag_2.getKeys().iterator();

            while(var9.hasNext()) {
                String string_1 = (String)var9.next();
                Property<?> property_1 = stateFactory_1.getProperty(string_1);
                if (property_1 != null) {
                    String string_2 = compoundTag_2.getTag(string_1).asString();
                    blockState_2 = with(blockState_2, property_1, string_2);
                }
            }

        }

        if (blockState_2 != blockState_1) {
            world_1.setBlockState(blockPos_1, blockState_2, 2);
        }

        return blockState_2;
    }


    /*// TODO: Change implementation so it does getDefaultState
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int num, boolean bool) {
        CompoundTag genes;
        if (stack.getSubTag("Genes") == null) {
            genes = new CompoundTag();
            genes.putInt("yield", 1);
            genes.putInt("growth", 1);
        } else {
            genes = stack.getSubTag("Genes");
            if (genes.getInt("yield") < 1) {
                genes.putInt("yield", 1);
            }
            if (genes.getInt("growth") < 1) {
                genes.putInt("growth", 1);
            }
        }
        stack.putSubTag("Genes", genes);
    }*/


    // TODO: Change this to work with a property registry
    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        if (Screen.hasShiftDown()) {
            if (stack.getSubTag("Genes") == null) {
                return;
            }
            CompoundTag genes = stack.getSubTag("Genes");
            if (genes != null) {
                tooltip.add(new LiteralText("Yield: " + genes.getInt("yield")));
                tooltip.add(new LiteralText("Growth: " + genes.getInt("growth")));
            }
        }
    }



}
