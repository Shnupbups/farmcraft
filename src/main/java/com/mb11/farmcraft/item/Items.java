package com.mb11.farmcraft.item;

import com.mb11.farmcraft.armor.CustomArmorMaterials;
import com.mb11.farmcraft.item.GlassJarItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.mb11.farmcraft.Farmcraft.MODID;

public class Items {

    public static final Item wheat_flour = new Item((new Item.Settings()).group(ItemGroup.FOOD));
    public static final Item barley_flour = new Item((new Item.Settings()).group(ItemGroup.FOOD));
    public static final Item rye_flour = new Item((new Item.Settings()).group(ItemGroup.FOOD));
    public static final Item oat_flour = new Item((new Item.Settings()).group(ItemGroup.FOOD));

    public static final Item glass_jar = new Item((new Item.Settings()).group(ItemGroup.MISC).maxCount(16));
    public static final Item peanut_butter = new GlassJarItem((new Item.Settings()).group(ItemGroup.FOOD).maxCount(1).food( (new FoodComponent.Builder()).hunger(4).saturationModifier(1.0f).build()) );


    public static final ArmorItem bee_mask = new ArmorItem(CustomArmorMaterials.BEESUIT, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.MISC));
    public static final ArmorItem bee_jacket = new ArmorItem(CustomArmorMaterials.BEESUIT, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.MISC));
    public static final ArmorItem bee_pants = new ArmorItem(CustomArmorMaterials.BEESUIT, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.MISC));
    public static final ArmorItem bee_boots = new ArmorItem(CustomArmorMaterials.BEESUIT, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.MISC));

    public static void register() {
        Registry.register(Registry.ITEM, new Identifier(MODID, "wheat_flour"), wheat_flour);
        Registry.register(Registry.ITEM, new Identifier(MODID, "barley_flour"), barley_flour);
        Registry.register(Registry.ITEM, new Identifier(MODID, "rye_flour"), rye_flour);
        Registry.register(Registry.ITEM, new Identifier(MODID, "oat_flour"), oat_flour);

        Registry.register(Registry.ITEM, new Identifier(MODID, "glass_jar"), glass_jar);
        Registry.register(Registry.ITEM, new Identifier(MODID, "peanut_butter"), peanut_butter);

        Registry.register(Registry.ITEM, new Identifier(MODID, "bee_mask"), bee_mask);
        Registry.register(Registry.ITEM, new Identifier(MODID, "bee_jacket"), bee_jacket);
        Registry.register(Registry.ITEM, new Identifier(MODID, "bee_pants"), bee_pants);
        Registry.register(Registry.ITEM, new Identifier(MODID, "bee_boots"), bee_boots);
    }
}
