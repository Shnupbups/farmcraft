package com.mb11.farmcraft.api.crop;

import com.mb11.farmcraft.api.dispenser.SeedDispenserBehavior;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.Material;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.shape.VoxelShape;

public class Crop {

    public Identifier id;

    private Item item;
    private CropItem seed;
    private CropBlock block;
    private FoodComponent food;

    private ItemGroup seedgroup;
    private ItemGroup itemgroup;

    private boolean seperateseed;
    private boolean twotall;
    private boolean partialharvest;

    private VoxelShape[] growthBoundingBoxes;

    public Crop(Identifier id) {
        this.id = id;
        this.seperateseed = false;
        this.twotall = false;
        this.partialharvest = false;
        this.seedgroup = ItemGroup.MISC;
        this.itemgroup = ItemGroup.FOOD;
        this.food = null;
        this.growthBoundingBoxes = null;
    }

    public Crop setSeperateSeed(boolean seperateseed) {
        this.seperateseed = seperateseed;
        return this;
    }

    public Identifier getSeedId() {
        Identifier seedid = this.id;
        if (this.seperateseed) {
            seedid = new Identifier(this.id.getNamespace(), this.id.getPath() + "_seeds");
        }
        return seedid;
    }

    public Item getItem() {
        return this.item;
    }

    public Item getSeed() {
        return block.getSeedsItem().asItem();
    }

    public Block getBlock() {
        return this.block;
    }

    public Crop setFood(FoodComponent food) {
        this.food = food;
        return this;
    }

    public FoodComponent getFood() {
        return this.food;
    }

    public Crop setSeedGroup(ItemGroup group) {
        this.seedgroup = group;
        return this;
    }

    public Crop setItemGroup(ItemGroup group) {
        this.itemgroup = group;
        return this;
    }

    public Crop setPartialHarvest(boolean partialharvest) {
        this.partialharvest = partialharvest;
        return this;
    }

    public Crop setCropGrowthBoundingBoxes(VoxelShape[] growthBoundingBoxes) {
        this.growthBoundingBoxes = growthBoundingBoxes;
        return this;
    }

    public Crop setTwoTall(boolean twotall) {
        this.twotall = twotall;
        return this;
    }

    public Crop build() {
        this.block = new CropBlock(FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP).build(), this.id, this.getSeedId())
                .setGrowthBoundingBoxes(this.growthBoundingBoxes)
                .setTwoTall(this.twotall)
                .setPartialHarvest(this.partialharvest);
        if (this.seperateseed) {
            this.seed = new CropItem(this.block, new Item.Settings().group(this.seedgroup));
            this.item = new Item(new Item.Settings().group(this.itemgroup).food(this.food));
        } else {
            this.item = new CropItem(this.block, new Item.Settings().group(this.itemgroup).food(this.food));
        }
        return this;
    }

    public void register() {
        Registry.register(Registry.ITEM, this.id, this.item);
        this.block.register(this.id);
        if (seperateseed) {
            Registry.register(Registry.ITEM, this.getSeedId(), this.seed);
        }
        DispenserBlock.registerBehavior(this.getSeed(), new SeedDispenserBehavior());
    }


}
