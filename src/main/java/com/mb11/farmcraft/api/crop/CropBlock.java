package com.mb11.farmcraft.api.crop;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.class_4538;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityContext;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.GameRules;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.loot.LootSupplier;
import net.minecraft.world.loot.LootTables;
import net.minecraft.world.loot.context.LootContext;
import net.minecraft.world.loot.context.LootContextParameters;
import net.minecraft.world.loot.context.LootContextTypes;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CropBlock extends net.minecraft.block.CropBlock implements BlockEntityProvider {
    private Identifier itemid;
    private Identifier id;
    private BlockEntityType<CropBlockEntity> entity;

    private VoxelShape[] growthBoundingBoxes;
    private boolean twotall;
    private boolean partialharvest;

    public static final EnumProperty<DoubleBlockHalf> HALF;

    public CropBlock(Settings settings, Identifier id, Identifier itemid) {
        super(settings);
        this.itemid = itemid;
        this.id = id;
        this.twotall = false;
        this.partialharvest = false;
        this.growthBoundingBoxes = new VoxelShape[]{Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D), Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D), Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D), Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D), Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D), Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D), Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)};
    }

    public boolean isTwoTall() {
        return this.twotall;
    }

    public CropBlock setPartialHarvest(boolean partialHarvest) {
        this.partialharvest = partialHarvest;
        return this;
    }

    public BlockState getStateForNeighborUpdate(BlockState blockState_1, Direction direction_1, BlockState blockState_2, IWorld iWorld_1, BlockPos blockPos_1, BlockPos blockPos_2) {
        if (this.twotall) {
            DoubleBlockHalf doubleBlockHalf_1 = (DoubleBlockHalf) blockState_1.get(HALF);
            if (direction_1.getAxis() == Direction.Axis.Y && doubleBlockHalf_1 == DoubleBlockHalf.LOWER == (direction_1 == Direction.UP) && (blockState_2.getBlock() != this || blockState_2.get(HALF) == doubleBlockHalf_1)) {
                return Blocks.AIR.getDefaultState();
            } else {
                return doubleBlockHalf_1 == DoubleBlockHalf.LOWER && direction_1 == Direction.DOWN && !blockState_1.canPlaceAt(iWorld_1, blockPos_1) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(blockState_1, direction_1, blockState_2, iWorld_1, blockPos_1, blockPos_2);
            }
        } else {
            return super.getStateForNeighborUpdate(blockState_1, direction_1, blockState_2, iWorld_1, blockPos_1, blockPos_2);
        }
    }

    public BlockState getPlacementState(ItemPlacementContext itemPlacementContext_1) {
        if (this.twotall) {
            BlockPos blockPos_1 = itemPlacementContext_1.getBlockPos();
            return blockPos_1.getY() < 255 && itemPlacementContext_1.getWorld().getBlockState(blockPos_1.up()).canReplace(itemPlacementContext_1) ? super.getPlacementState(itemPlacementContext_1) : null;
        } else {
            return super.getPlacementState(itemPlacementContext_1);
        }
    }

    public CropBlock setTwoTall(boolean twotall) {
        this.twotall = twotall;
        if (twotall) {
            this.setDefaultState((BlockState)((BlockState)this.stateFactory.getDefaultState()).with(HALF, DoubleBlockHalf.LOWER));
        }
        return this;
    }

    public void onPlaced(World world_1, BlockPos blockPos_1, BlockState blockState_1, LivingEntity livingEntity_1, ItemStack itemStack_1) {
        if (this.twotall) {
            world_1.setBlockState(blockPos_1.up(), (BlockState) this.getDefaultState().with(HALF, DoubleBlockHalf.UPPER), 3);
            world_1.setBlockEntity(blockPos_1.up(1), world_1.getBlockEntity(blockPos_1));
        } else {
            super.onPlaced(world_1, blockPos_1, blockState_1, livingEntity_1, itemStack_1);
        }
    }

    public boolean canPlaceAt(BlockState blockState_1, class_4538 class_4538_1, BlockPos blockPos_1) {
        if (this.twotall) {
            if (blockState_1.get(HALF) != DoubleBlockHalf.UPPER) {
                return super.canPlaceAt(blockState_1, class_4538_1, blockPos_1);
            } else {
                BlockState blockState_2 = class_4538_1.getBlockState(blockPos_1.down());
                return blockState_2.getBlock() == this && blockState_2.get(HALF) == DoubleBlockHalf.LOWER;
            }
        } else {
            return super.canPlaceAt(blockState_1, class_4538_1, blockPos_1);
        }
    }

    public void placeAt(IWorld iWorld_1, BlockPos blockPos_1, int int_1) {
        if (this.twotall) {
            iWorld_1.setBlockState(blockPos_1, (BlockState) this.getDefaultState().with(HALF, DoubleBlockHalf.LOWER), int_1);
            iWorld_1.setBlockState(blockPos_1.up(), (BlockState) this.getDefaultState().with(HALF, DoubleBlockHalf.UPPER), int_1);
        }
    }

    public void onBreak(World world_1, BlockPos blockPos_1, BlockState blockState_1, PlayerEntity playerEntity_1) {
        if (this.twotall) {
            DoubleBlockHalf doubleBlockHalf_1 = (DoubleBlockHalf) blockState_1.get(HALF);
            BlockPos blockPos_2 = doubleBlockHalf_1 == DoubleBlockHalf.LOWER ? blockPos_1.up() : blockPos_1.down();
            BlockState blockState_2 = world_1.getBlockState(blockPos_2);
            if (blockState_2.getBlock() == this && blockState_2.get(HALF) != doubleBlockHalf_1) {
                world_1.setBlockState(blockPos_2, Blocks.AIR.getDefaultState(), 35);
                world_1.playLevelEvent(playerEntity_1, 2001, blockPos_2, Block.getRawIdFromState(blockState_2));
                if (!world_1.isClient && !playerEntity_1.isCreative()) {
                    dropStacks(blockState_1, world_1, blockPos_1, (BlockEntity) null, playerEntity_1, playerEntity_1.getMainHandStack());
                }
            }
        }

        super.onBreak(world_1, blockPos_1, blockState_1, playerEntity_1);
    }

    protected void appendProperties(StateFactory.Builder<Block, BlockState> stateFactory$Builder_1) {
        stateFactory$Builder_1.add(new Property[]{HALF});
        super.appendProperties(stateFactory$Builder_1);
    }

    @Environment(EnvType.CLIENT)
    public long getRenderingSeed(BlockState blockState_1, BlockPos blockPos_1) {
        if (this.twotall) {
            return MathHelper.hashCode(blockPos_1.getX(), blockPos_1.down(blockState_1.get(HALF) == DoubleBlockHalf.LOWER ? 0 : 1).getY(), blockPos_1.getZ());
        } else {
            return super.getRenderingSeed(blockState_1, blockPos_1);
        }
    }





    public CropBlock setGrowthBoundingBoxes(VoxelShape[] growthBoundingBoxes) {
        if (growthBoundingBoxes != null) {
            this.growthBoundingBoxes = growthBoundingBoxes;
        }
        return this;
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, EntityContext context) {
        VoxelShape shape = growthBoundingBoxes[ state.get(this.getAgeProperty()) ];
        if (this.twotall) {
            if (state.get(HALF) == DoubleBlockHalf.UPPER) {
                return shape.offset(0, -1.0, 0);
            } else {
                return shape;
            }
        }
        return shape;
    }

    @Environment(EnvType.CLIENT)
    protected ItemConvertible getSeedsItem() {
        return Registry.ITEM.get(this.itemid);
    }

    @Override
    public boolean activate(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult blockHitResult) {
        if (state.get(AGE) == 7) {
            player.addExhaustion(0.005F);
            if (world instanceof ServerWorld) {
                getDroppedStacks(state, (ServerWorld) world, pos, (BlockEntity) null).forEach((stack) -> {
                    if (stack.getItem() == this.getSeedsItem()) {
                        if (this.partialharvest) {
                            stack.setCount(0);
                        } else {
                            stack.decrement(1);
                        }
                    }
                    dropStackCustom(world, pos, stack, world.getBlockEntity(pos), state);
                });
            }
            if (this.partialharvest) {
                world.setBlockState(pos, state.with(AGE, 6));
            } else {
                world.setBlockState(pos, state.with(AGE, 0));
            }
            if (this.twotall && state.get(HALF) == DoubleBlockHalf.UPPER) {
                world.setBlockState(pos.down(1), world.getBlockState(pos).with(HALF, DoubleBlockHalf.LOWER));
            }
            if (this.twotall && state.get(HALF) == DoubleBlockHalf.LOWER) {
                world.setBlockState(pos.up(1), world.getBlockState(pos).with(HALF, DoubleBlockHalf.UPPER));
            }
            return true;
        } else {
            return false;
        }
    }

    public BlockEntity createBlockEntity(BlockView blockView) {
        return new CropBlockEntity(this.id);
    }

    public void register(Identifier id) {
        Registry.register(Registry.BLOCK, id, this);
        this.entity = Registry.register(Registry.BLOCK_ENTITY, id, BlockEntityType.Builder.create( () -> new CropBlockEntity(id), this).build(null));
    }

    public static void dropStacks(BlockState blockState_1, World world_1, BlockPos blockPos_1, BlockEntity blockEntity_1, Entity entity_1, ItemStack itemStack_1) {
        if (world_1 instanceof ServerWorld) {
            getDroppedStacks(blockState_1, (ServerWorld)world_1, blockPos_1, blockEntity_1, entity_1, itemStack_1).forEach((itemStack_1x) -> {
                dropStackCustom(world_1, blockPos_1, itemStack_1x, blockEntity_1, blockState_1);
            });
        }

        blockState_1.onStacksDropped(world_1, blockPos_1, itemStack_1);
    }

    @Override
    public void afterBreak(World world_1, PlayerEntity playerEntity_1, BlockPos blockPos_1, BlockState blockState_1, BlockEntity blockEntity_1, ItemStack itemStack_1) {
        if (this.twotall) {
            blockState_1 = Blocks.AIR.getDefaultState();
        }
        playerEntity_1.incrementStat(Stats.MINED.getOrCreateStat(this));
        playerEntity_1.addExhaustion(0.005F);
        dropStacks(blockState_1, world_1, blockPos_1, blockEntity_1, playerEntity_1, itemStack_1);
    }


    public static void dropStackCustom(World world_1, BlockPos blockPos_1, ItemStack itemStack_1, BlockEntity entity, BlockState state) {
        if (!world_1.isClient && !itemStack_1.isEmpty() && world_1.getGameRules().getBoolean(GameRules.DO_TILE_DROPS)) {
            if (itemStack_1.getItem() == ((CropBlock) state.getBlock()).getSeedsItem()) {
                if (entity != null) {
                    CompoundTag genes = entity.toTag(new CompoundTag()).getCompound("Genes");
                    itemStack_1.putSubTag("Genes", genes);
                }
            }

            float float_1 = 0.5F;
            double double_1 = (double)(world_1.random.nextFloat() * 0.5F) + 0.25D;
            double double_2 = (double)(world_1.random.nextFloat() * 0.5F) + 0.25D;
            double double_3 = (double)(world_1.random.nextFloat() * 0.5F) + 0.25D;
            ItemEntity itemEntity_1 = new ItemEntity(world_1, (double)blockPos_1.getX() + double_1, (double)blockPos_1.getY() + double_2, (double)blockPos_1.getZ() + double_3, itemStack_1);
            itemEntity_1.setToDefaultPickupDelay();
            world_1.spawnEntity(itemEntity_1);
        }
    }


    @Override
    public void onScheduledTick(BlockState blockState_1, ServerWorld serverWorld_1, BlockPos blockPos_1, Random random_1) {
        if (blockState_1.get(HALF) == DoubleBlockHalf.UPPER && this.twotall) {
            return;
        }
        super.onScheduledTick(blockState_1, serverWorld_1, blockPos_1, random_1);
        if (serverWorld_1.getBaseLightLevel(blockPos_1, 0) >= 9) {
            int int_1 = this.getAge(blockState_1);
            if (int_1 < this.getMaxAge()) {
                float float_1 = getAvailableMoisture(this, serverWorld_1, blockPos_1);
                if (random_1.nextInt((int)(25.0F / (float_1)) + 1) == 0) {
                    serverWorld_1.setBlockState(blockPos_1, this.withAge(int_1 + 1), 2);
                    if (blockState_1.get(HALF) == DoubleBlockHalf.LOWER && this.twotall) {
                        serverWorld_1.setBlockState(blockPos_1.up(1), serverWorld_1.getBlockState(blockPos_1).with(HALF, DoubleBlockHalf.UPPER).with(AGE, this.getAge(serverWorld_1.getBlockState(blockPos_1))), 2);
                    }
                }
            } else {
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        BlockPos pos = new BlockPos(blockPos_1.getX() + i, blockPos_1.getY(), blockPos_1.getZ() + j);
                        if (serverWorld_1.isAir(pos)) {
                            if (this.canPlantOnTop(serverWorld_1.getBlockState(pos.down(1)), null, pos.down(1))) {
                                if (random_1.nextInt(32) == 0) {
                                    CompoundTag tag = ((CropBlockEntity) serverWorld_1.getBlockEntity(blockPos_1)).getMutation(random_1);

                                    if (this.twotall) {
                                        this.placeAt(serverWorld_1, pos, 2);
                                        serverWorld_1.getBlockEntity(pos).fromTag(tag);
                                        serverWorld_1.getBlockEntity(pos.up(1)).fromTag(tag);
                                    } else {
                                        serverWorld_1.setBlockState(pos, this.getDefaultState(), 2);
                                        serverWorld_1.getBlockEntity(pos).fromTag(tag);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    static {
        HALF = Properties.DOUBLE_BLOCK_HALF;
    }

}
