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

    private boolean seperateseed = false;

    public Bush(String name, FoodComponent food) {
        this.id = new Identifier(MODID, name);
        this.food = food;

        this.block = new BushBlock(FabricBlockSettings.of(Material.PLANT).ticksRandomly().noCollision().sounds(BlockSoundGroup.SWEET_BERRY_BUSH).build(), this.getSeedId());
        this.item = new BushItem(this.block, new Item.Settings().group(ItemGroup.FOOD).food(this.food));

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
        if (this.seperateseed) {
            seedid = new Identifier(this.id.getNamespace(), this.id.getPath() + "_seeds");
        }
        return seedid;
    }
}
