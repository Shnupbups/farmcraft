package com.mb11.farmcraft;

import com.mb11.farmcraft.event.Events;
import com.mb11.farmcraft.item.Items;
import net.fabricmc.api.ModInitializer;

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

}
