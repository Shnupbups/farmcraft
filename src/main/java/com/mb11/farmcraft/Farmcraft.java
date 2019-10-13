package com.mb11.farmcraft;

import com.mb11.farmcraft.event.Events;
import com.mb11.farmcraft.item.Items;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class Farmcraft implements ModInitializer {

    //Pillager Outpost, Shipwreck Supply,

    public static final String MODID = "farmcraft";
    public static final ItemGroup GROUP = FabricItemGroupBuilder.build(id("group"), () -> new ItemStack(Plants.bell_pepper.getItem()));

    public static Identifier id(String string) {
        return new Identifier(MODID, string);
    }


    @Override
    public void onInitialize() {
        Plants.register();
        Items.register();
        Blocks.init();
        LootTables.register();
        Events.register();

        System.out.println("Hello Fabric world!");
    }

}
