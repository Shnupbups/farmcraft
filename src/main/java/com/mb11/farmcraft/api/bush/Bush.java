package com.mb11.farmcraft.api.bush;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.mb11.farmcraft.Farmcraft.MODID;

public class Bush {

    public Identifier id;
    private Identifier seedid;
    private BushItem item;
    private BushBlock block;
    private FoodComponent food;

    private ItemGroup group;

    public Bush(Identifier id) {
        this.id = id;
        this.food = null;
    }

    public Bush setFood(FoodComponent food) {
        this.food = food;
        return this;
    }

    public Bush setItemGroup(ItemGroup group) {
        this.group = group;
        return this;
    }

    public FoodComponent getFood() {
        return this.food;
    }

    public Item getItem() {
        return this.item;
    }

    public Block getBlock() {
        return this.block;
    }

    public void register() {
        Registry.register(Registry.ITEM, this.id, this.item);
        Registry.register(Registry.BLOCK, this.id, this.block);
    }

    public Identifier getSeedId() {
        Identifier seedid = this.id;
        seedid = new Identifier(this.id.getNamespace(), this.id.getPath() + "_seeds");
        return seedid;
    }

    public Bush build() {
        this.block = new BushBlock(FabricBlockSettings.of(Material.PLANT).ticksRandomly().noCollision().sounds(BlockSoundGroup.SWEET_BERRY_BUSH).build(), this.getSeedId());
        this.item = new BushItem(this.block, new Item.Settings().group(this.group).food(this.food));
        return this;
    }
}
