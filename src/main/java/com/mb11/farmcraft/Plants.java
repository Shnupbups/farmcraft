package com.mb11.farmcraft;

import com.mb11.farmcraft.api.bush.Bush;
import com.mb11.farmcraft.api.crop.Crop;
import com.mb11.farmcraft.api.factory.CropBoundingBoxFactory;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

import static com.mb11.farmcraft.Farmcraft.MODID;

public class Plants {

    public static final Crop onion = new Crop(new Identifier(MODID, "onion"))
            .setFood((new FoodComponent.Builder())
                    .hunger(1)
                    .saturationModifier(0.3f)
                    .build())
            .setItemGroup(Farmcraft.GROUP)
            .setCropGrowthBoundingBoxes(CropBoundingBoxFactory.buildBoundingBoxes( new float[]{1, 2, 3, 4, 5, 6, 7, 8} ))
            .build();

    /*
    public static final Crop garlic = new Crop(new Identifier(MODID, "garlic"))
            .setFood((new FoodComponent.Builder())
                    .hunger(1)
                    .saturationModifier(0.3f)
                    .build())
            .setCropGrowthBoundingBoxes(new CropBoundingBoxFactory().buildBoundingBoxes( new float[]{1, 2, 3, 4, 5, 6, 7, 8} ))
            .build();

    public static final Crop ginger = new Crop(new Identifier(MODID, "ginger"))
            .setFood((new FoodComponent.Builder())
                    .hunger(1)
                    .saturationModifier(0.3f)
                    .build())
            .setCropGrowthBoundingBoxes(new CropBoundingBoxFactory().buildBoundingBoxes( new float[]{1, 2, 3, 4, 5, 6, 7, 8} ))
            .build();

    public static final Crop arrowroot = new Crop(new Identifier(MODID, "arrowroot"))
            .setFood((new FoodComponent.Builder())
                    .hunger(1)
                    .saturationModifier(0.3f)
                    .build())
            .setCropGrowthBoundingBoxes(new CropBoundingBoxFactory().buildBoundingBoxes( new float[]{1, 2, 3, 4, 5, 6, 7, 8} ))
            .build();
    */

    public static final Crop peanut = new Crop(new Identifier(MODID, "peanut"))
            .setFood((new FoodComponent.Builder())
                    .hunger(1)
                    .saturationModifier(0.3f)
                    .build())
            .setItemGroup(Farmcraft.GROUP)
            .setCropGrowthBoundingBoxes(CropBoundingBoxFactory.buildBoundingBoxes( new float[]{1, 2, 4, 5, 7, 8, 9, 10} ))
            .build();

    public static final Crop bell_pepper = new Crop(new Identifier(MODID, "bell_pepper"))
            .setFood((new FoodComponent.Builder())
                    .hunger(3)
                    .saturationModifier(0.6f)
                    .build())
            .setTwoTall(true)
            .setSeperateSeed(true)
            .setSeedGroup(Farmcraft.GROUP)
            .setPartialHarvest(true)
            .setItemGroup(Farmcraft.GROUP)
            .setCropGrowthBoundingBoxes(CropBoundingBoxFactory.buildBoundingBoxes( new float[]{1, 4, 6, 10, 12, 14, 18, 20} ))
            .build();

    /*
    public static final Crop corn = new Crop(new Identifier(MODID, "corn"))
            .setFood((new FoodComponent.Builder())
                    .hunger(3)
                    .saturationModifier(0.6f)
                    .build())
            .setTwoTall(true)
            .setSeperateSeed(true)
            .setPartialHarvest(true)
            .setCropGrowthBoundingBoxes(new CropBoundingBoxFactory().buildBoundingBoxes( new float[]{1, 4, 6, 10, 12, 14, 18, 20} ))
            .build();
    */

    public static final Crop tomato = new Crop(new Identifier(MODID, "tomato"))
            .setFood((new FoodComponent.Builder())
                    .hunger(3)
                    .saturationModifier(0.6f)
                    .build())
            .setTwoTall(true)
            .setSeperateSeed(true)
            .setSeedGroup(Farmcraft.GROUP)
            .setPartialHarvest(true)
            .setItemGroup(Farmcraft.GROUP)
            .setCropGrowthBoundingBoxes(CropBoundingBoxFactory.buildBoundingBoxes( new float[]{1, 4, 6, 10, 12, 14, 18, 20} ))
            .build();




    public static final Crop oat = new Crop(new Identifier(MODID, "oat"))
            .setItemGroup(Farmcraft.GROUP)
            .setCropGrowthBoundingBoxes(CropBoundingBoxFactory.buildBoundingBoxes( new float[]{2, 4, 6, 8, 10, 12, 14, 16} ))
            .setSeperateSeed(true)
            .setSeedGroup(Farmcraft.GROUP)
            .build();
    /*
    public static final Crop jute = new Crop(new Identifier(MODID, "jute"))
            .setCropGrowthBoundingBoxes(new CropBoundingBoxFactory().buildBoundingBoxes( new float[]{2, 4, 6, 8, 10, 12, 14, 16} ))
            .setSeperateSeed(true)
            .build();

    public static final Crop flax = new Crop(new Identifier(MODID, "flax"))
            .setCropGrowthBoundingBoxes(new CropBoundingBoxFactory().buildBoundingBoxes( new float[]{2, 4, 6, 8, 10, 12, 14, 16} ))
            .setSeperateSeed(true)
            .build();

    public static final Crop cotton = new Crop(new Identifier(MODID, "cotton"))
            .setCropGrowthBoundingBoxes(new CropBoundingBoxFactory().buildBoundingBoxes( new float[]{2, 4, 6, 8, 10, 12, 14, 16} ))
            .setSeperateSeed(true)
            .build();
    */

    public static final Crop rice = new Crop(new Identifier(MODID, "rice"))
            .setItemGroup(Farmcraft.GROUP)
            .setCropGrowthBoundingBoxes(CropBoundingBoxFactory.buildBoundingBoxes( new float[]{2, 4, 6, 8, 10, 12, 14, 16} ))
            .setSeperateSeed(true)
            .setSeedGroup(Farmcraft.GROUP)
            .build();

    public static final Crop rye = new Crop(new Identifier(MODID, "rye"))
            .setItemGroup(Farmcraft.GROUP)
            .setCropGrowthBoundingBoxes(CropBoundingBoxFactory.buildBoundingBoxes( new float[]{2, 4, 6, 8, 10, 12, 14, 16} ))
            .setSeperateSeed(true)
            .setSeedGroup(Farmcraft.GROUP)
            .build();

    public static final Crop barley = new Crop(new Identifier(MODID, "barley"))
            .setItemGroup(Farmcraft.GROUP)
            .setCropGrowthBoundingBoxes(CropBoundingBoxFactory.buildBoundingBoxes( new float[]{2, 4, 6, 8, 10, 12, 14, 16} ))
            .setSeperateSeed(true)
            .setSeedGroup(Farmcraft.GROUP)
            .build();

    public static final Bush blueberry = new Bush(new Identifier(MODID, "blueberry"))
            .setFood((new FoodComponent.Builder())
                    .hunger(3)
                    .saturationModifier(0.6f)
                    .build())
            .setItemGroup(Farmcraft.GROUP)
            .build();


    public static void register() {
        onion.register();
        peanut.register();


        bell_pepper.register();
        tomato.register();

        rye.register();
        barley.register();
        oat.register();
        rice.register();

        blueberry.register();
    }




    // Fruit Trees
    /*
    Kiwi
    Pineapple
    Avocado
    Tea Trees
    Apple
    Apricot
    Bananas
    Cherry
    Date
    Dragonfruit
    Durian
    Fig
    Grapefruit
    Guava
    Lemon
    Lime
    Lychee
    Mango
    Olive
    Orange
    Papaya
    Passion Fruit
    Peach
    Pear
    Plum
    Pomegranate
    Starfruit
    Almond
    Cashew
    Chestnut
    Coconut
    Hazelnut
    Pecan
    Pistachio
    Walnut
    Maple
     */

    // Stem Plant
    /*
    Cantaloupe
    Squash
     */

    // Other
    /*
    Peas
    Sunflower
    Cinnamon
    Nutmeg
    Peppercorn
    Tamarind
    Vanilla
    Grape
    Green Grape
    Mustard
     */

    /*
    Artichoke
    Asparagus
    Broccoli
    Brussel Sprout
    Cabbage
    Cauliflower
    Celery
    Chickpeas
    Chili Pepper
    Cucumber
    Eggplant
    Kale
    Kohlrabi
    Leek
    Lentil
    Lettuce
    Okra
    Parsnip
    Radish
    Rhubarb
    Rutabaga
    Scallion
    Soybean
    Spinach
    Sweet Potato
    Turnip
    Zucchini
    Agave
    Amaranth
    Quinoa
    Sesame
     */

    // Shrooms
    /*
    White Mushroom
    Shitake Mushrooms
    */

    //Berries

    /*
    public static final Bush blackberry = new Bush("blackberry", (new FoodComponent.Builder()).hunger(2).saturationModifier(0.1F).build());
    public static final Bush blueberry = new Bush("blueberry", (new FoodComponent.Builder()).hunger(2).saturationModifier(0.1F).build());
    public static final Bush cranberry = new Bush("cranberry", (new FoodComponent.Builder()).hunger(2).saturationModifier(0.1F).build());
    public static final Bush elderberry = new Bush("elderberry", (new FoodComponent.Builder()).hunger(2).saturationModifier(0.1F).build());
    public static final Bush huckleberry = new Bush("huckleberry", (new FoodComponent.Builder()).hunger(2).saturationModifier(0.1F).build());
    public static final Bush mulberry = new Bush("mulberry", (new FoodComponent.Builder()).hunger(2).saturationModifier(0.1F).build());
    public static final Bush raspberry = new Bush("raspberry", (new FoodComponent.Builder()).hunger(2).saturationModifier(0.1F).build());
    public static final Bush strawberry = new Bush("strawberry", (new FoodComponent.Builder()).hunger(2).saturationModifier(0.1F).build());
    public static final Bush candleberry = new Bush("candleberry", (new FoodComponent.Builder()).hunger(2).saturationModifier(0.1F).build());
    public static final Bush coffee = new Bush("coffee", null);
    */
}
