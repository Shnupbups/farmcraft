package com.mb11.farmcraft;

import com.mb11.farmcraft.event.Events;
import com.mb11.farmcraft.item.Items;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.StructureFeature;

public class Farmcraft implements ModInitializer {

    public static final String MODID = "farmcraft";




    @Override
    public void onInitialize() {
        Plants.register();
        Items.register();
        LootTables.register();
        Events.register();

        System.out.println("Hello Fabric world!");
    }

    // TODO: World Generation & Cool Structures
    // TODO: Biomes that feature new plants
    // TODO: Crafting Recipes
    // TODO: Other Plants
    // TODO: New ores along with the custom tool crafting system (Copper, Bronze, Tin, Silver, Lead)
    // TODO: Sulfur in the nether
    // TODO: Nether vines
    // TODO: Curse of Rusting (Opposite of unbreaking)
    // TODO: Curse of Misfortune (There's a chance it won't work)
    // TODO: Curse of Haunting (There's a chance a Zombie (Husk), Skeleton (Stray), Phantom, or Vex will spawn nearby)
    // TODO: Curse of Harming (There's a chance using an item will damage the player)
    // TODO: Vindicators, Evokers, Illusioners, Vex spawn in Dark Forests in packs
    // TODO: Villagers will follow players holding emeralds and emerald blocks
    // TODO: Sponges will reduce fall damage
    // TODO: Totem Of Returning (Binds to a block and on right click allows fast travel back)
    // TODO: Feather Falling protects farmland
    // TODO: Allow Villagers to trade for custom plants
    // TODO: Villages contain new plants
    // TODO: Custom Tool Crafting System (TC Esque)
    // TODO: Colored Slime blocks
    // TODO: Allow right click harvest for default plants
    // TODO: Dispensers play records
    // TODO: Watcher (Outputs a signal when a player is looking at it)
    // TODO: End Sand (Falls Upwards)
    // TODO: Animals will pick up food items to eat and then breed
    // TODO: Chains (can make chainmail) (Can connect minecarts)
    // TODO: Obsidian Plates (Only trigger for players)
    // TODO: Pistons Move Tile Entities
    // TODO: Item that places a random block from your hotbar
    // TODO: Turf (Grass texture on all sides)
    // TODO: Colored Flower Pots
    // TODO: Colored Item Frames
    // TODO: Iron Ladders (don't need a backing block just one above or below)
    // TODO: Ground Leaves
    // TODO: Candles
    // TODO: Placeable blaze rods
    // TODO: Tie fences together
    // TODO: Chest Buttons (Sort Chest, Sort Inventory, All to Chest, All to Inventory, All to chest if one already, All to inventory if one already)
    // TODO: Chest Boats (Right clicking)
    // TODO: Clicking a minecart with an item will turn it into a furnace or chest or hopper minecart etc
    // TODO: Inventory trashcan
    // TODO: Right click on top of a shulker box to add the item when in inventory
    // TODO: Pressing F in inventory puts that item into your offhand
    // TODO: Armor Stands by default have arms
    // TODO: Wrench to change direction of blocks
    // TODO: Shulker Boxes are indestructible
    // TODO: Stone tools can be made from all types of stone
    // TODO: Baby zombies burn in daylight
    // TODO: Torches as furnace fuel
    // TODO: Chicken Shed
    // TODO: Chicken Shear
    // TODO: Compasses in the nether lead to the nearest portal
    // TODO: Compasses in the end lead to the nearest portal
    // TODO: Dirt can be made into path blocks now
    // TODO: Double doors open together
    // TODO: Dye any wool color into any other
    // TODO: Dye multiple wool colors at a time
    // TODO: Hoes harvest grass, crops, and flowers in a larger radius
    // TODO: Jump boost gives you step assist like horses
    // TODO: Door Knock
    // TODO: Right clicking a ladder will place ladders on the top and or bottom of it
    // TODO: Shift clicking dogs pets them and makes them happy
    // TODO: Shift right clicking an armor stand will swap your armor and offhand item
    // TODO: Right clicking snow layers with a shovel makes them smaller
    // TODO: Squid Ink
    // TODO: Banners on Boats
    // TODO: Firework Duplication
    // TODO: Stair Chairs
    // TODO: Witch Hats Drop
    // TODO: Natural Blazes in Nether
    // TODO: Nether Structures
    // TODO: Overworld and nether broken portals
    // TODO: New Dungeons
    // TODO: Flower Circles
    // TODO: Fallen Trees
    // TODO: Obsidian Spikes in Nether
    // TODO: Marble, Limestone, Slate
    // TODO: 1.15 3D biomes
    // TODO: Underground Clay
    // TODO: Shulker Box Tooltip
    // TODO: Food Tooltips
    // TODO: Maps have inventory tooltips
    // TODO: Items despawning flash
    // TODO: When on horses you can still see your hunger and xp
    // TODO: Stops potion effects from shifting your inventory
    // TODO: Shields are lower
    // TODO: Fire effect is lower
    // TODO: Endermites bury into Purpur making shulkers
    // TODO: Explosive Arrow
    // TODO: Horse Whistle
    // TODO: Note blocks with mob heads on them emit the mob's noise
    // TODO: More mob heads
    // TODO: placeable gunpowder for a fuse
    // TODO: Totem of Holding (A grave)
    // TODO: Bookshelves can now hold books and enchanted books
    // TODO: Composter makes dirt now
    // TODO: Ender Anchor (Eating a Chorus Fruit teleports you to the nearest one within 64 blocks)
    // TODO: Redstone Sand (Falls like sand, emits redstone)
    // TODO: Barrels of multiple wood types
    // TODO: Chests of multiple wood types
    // TODO: Animal Types/Textures (Colored Wolves, Male and Female Animals)
    // TODO: Right Clicking a Cactus Fill a water bottle or bucket
    // TODO: Beacons apply status effects to tamed animals
    // TODO: Shift right clicking a compass on any block will link it
    // TODO: All records can be found in chests in the world
    // TODO: Dispensers can play records
    // TODO: Potions no longer have the glint
    // TODO: Potions with multiple uses
    // TODO: Totems can be anywhere in your inventory
    // TODO: Water Loggable redstone
    // TODO: Villages in more biomes (Jungle, Savanna, Swamps)
    // TODO: Gold ore spawns in nether
    // TODO: Totem of Shielding (Shields all incoming damage until durability is gone)
    // TODO: Mass storage for one item (Can be picked up)
    // TODO: Bell Ringing Triggers Redstone
    // TODO: Dropper into Composter
    // TODO: Dropper into Lecturn
    // TODO: Stone cutter deals damage when stood on
    // TODO: Villager that plants saplings and cuts down trees
    // TODO: Villager that opens nearby chests and removes items if they can be put in other chests with item frames on them
    // TODO: Warrior Villager
    // TODO: Abandonned Campfires
    // TODO: Random villager houses in the middle of nowhere
    // TODO: Redstone block slab that only powers in one direction
    // TODO: Colored Redstone
    // TODO: Chorus tipped arrow
    // TODO: Snow Accumulates
    // TODO: Bamboo Everything
    // TODO: Sneak through berry bushes
    // TODO: Can turn dirt into path blocks
    // TODO: Layers
    // TODO: Block Rotator
    // TODO: Trough that animals will feed from
    // TODO: Exploding item entities of stone -> cobble -> gravel -> sand


}
