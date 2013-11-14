package be.pxl.minecraft.storage;

import java.util.ArrayList;
import java.util.List;

import be.pxl.minecraft.model.Item;
import com.sun.jersey.spi.resource.Singleton;

@Singleton
public class ItemStorage {
    private List<Item> recipesList;

    public ItemStorage() {
        recipesList = new ArrayList<Item>();
        
        //Ingots & ores
        recipesList.add(new Item(106, 11, "furnace", "Furnace",
                        "1,1,1,1,0,1,1,1,1", "air,cobblestone"));
        recipesList.add(new Item(1, 11, "diamond_ingot", "Diamond ingot",
                        "0,1,0,0,2,0,0,3,0", "air,diamond_ore,coal_item,furnace"));
        recipesList.add(new Item(2, 11, "iron_ingot", "Iron ingot",
                        "0,1,0,0,2,0,0,3,0", "air,iron_ore,coal_item,furnace"));
        recipesList.add(new Item(3, 11, "gold_ingot", "Gold ingot",
                        "0,1,0,0,2,0,0,3,0", "air,gold_ore,coal_item,furnace"));
        recipesList.add(new Item(64, 11, "coal_item", "Coal",
                        "0,0,0,0,0,0,0,0,1", "air,coal_ore"));
        recipesList.add(new Item(66, 11, "redstone_dust", "Redstone dust",
                        "0,0,0,0,0,0,0,0,1", "air,redstone_ore"));
        recipesList.add(new Item(4, 11, "diamond_ore", "Diamond ore",
                        "0,0,0,0,5,4,3,2,1", "air,diamond_pickaxe,iron_pickaxe,gold_pickaxe,stone_pickaxe,wooden_pickaxe"));
        recipesList.add(new Item(5, 11, "iron_ore", "Iron ore",
                        "0,0,0,0,5,4,3,2,1", "air,diamond_pickaxe,iron_pickaxe,gold_pickaxe,stone_pickaxe,wooden_pickaxe"));
        recipesList.add(new Item(6, 11, "gold_ore", "Gold ore",
                        "0,0,0,0,5,4,3,2,1", "air,diamond_pickaxe,iron_pickaxe,gold_pickaxe,stone_pickaxe,wooden_pickaxe"));
        recipesList.add(new Item(63, 11, "coal_ore", "Coal ore",
                        "0,0,0,0,5,4,3,2,1", "air,diamond_pickaxe,iron_pickaxe,gold_pickaxe,stone_pickaxe,wooden_pickaxe"));
        recipesList.add(new Item(65, 11, "redstone_ore", "Redstone ore",
                        "0,0,0,0,5,4,3,2,1", "air,diamond_pickaxe,iron_pickaxe,gold_pickaxe,stone_pickaxe,wooden_pickaxe"));
        recipesList.add(new Item(19, 5, "diamond_block", "Diamond block",
                        "1,1,1,1,1,1,1,1,1", "air,diamond_ingot"));
        recipesList.add(new Item(20, 5, "iron_block", "Iron block",
                        "1,1,1,1,1,1,1,1,1", "air,iron_ingot"));
        recipesList.add(new Item(21, 5, "gold_block", "Gold block",
                        "1,1,1,1,1,1,1,1,1", "air,gold_ingot"));
        
        //Armor
        recipesList.add(new Item(7, 2, "diamond_boots", "Boots (Diamond)",
                        "0,0,0,1,0,1,1,0,1", "air,diamond_ingot"));
        recipesList.add(new Item(8, 2, "gold_boots", "Boots (Gold)",
                        "0,0,0,1,0,1,1,0,1", "air,gold_ingot"));
        recipesList.add(new Item(9, 2, "iron_boots", "Boots (Iron)",
                        "0,0,0,1,0,1,1,0,1", "air,iron_ingot"));
        recipesList.add(new Item(10, 2, "diamond_leggings", "Leggings (Diamond)",
                        "1,1,1,1,0,1,1,0,1", "air,diamond_ingot"));
        recipesList.add(new Item(11, 2, "gold_leggings", "Leggings (Gold)",
                        "1,1,1,1,0,1,1,0,1", "air,gold_ingot"));
        recipesList.add(new Item(12, 2, "iron_leggings", "Leggings (Iron)",
                        "1,1,1,1,0,1,1,0,1", "air,iron_ingot"));
        recipesList.add(new Item(13, 2, "diamond_chestplate", "Chestplate (Diamond)",
                        "1,0,1,1,1,1,1,1,1", "air,diamond_ingot"));
        recipesList.add(new Item(14, 2, "gold_chestplate", "Chestplate (Gold)",
                        "1,0,1,1,1,1,1,1,1", "air,gold_ingot"));
        recipesList.add(new Item(15, 2, "iron_chestplate", "Chestplate (Iron)",
                        "1,0,1,1,1,1,1,1,1", "air,iron_ingot"));
        recipesList.add(new Item(16, 2, "diamond_helmet", "Helmet (Diamond)",
                        "1,1,1,1,0,1,0,0,0", "air,diamond_ingot"));
        recipesList.add(new Item(17, 2, "gold_helmet", "Helmet (Gold)",
                        "1,1,1,1,0,1,0,0,0", "air,gold_ingot"));
        recipesList.add(new Item(18, 2, "iron_helmet", "Helmet (Iron)",
                        "1,1,1,1,0,1,0,0,0", "air,iron_ingot"));
        
        //Weapons
        recipesList.add(new Item(22, 3, "diamond_sword", "Sword (Diamond)",
                        "0,2,0,0,2,0,0,1,0", "air,stick,diamond_ingot"));
        recipesList.add(new Item(23, 3, "iron_sword", "Sword (Iron)",
                        "0,2,0,0,2,0,0,1,0", "air,stick,iron_ingot"));
        recipesList.add(new Item(24, 3, "gold_sword", "Sword (Gold)",
                        "0,2,0,0,2,0,0,1,0", "air,stick,gold_ingot"));
        recipesList.add(new Item(25, 3, "stone_sword", "Sword (Stone)",
                        "0,2,0,0,2,0,0,1,0", "air,stick,cobblestone"));
        recipesList.add(new Item(26, 3, "wooden_sword", "Sword (Wood)",
                        "0,2,0,0,2,0,0,1,0", "air,stick,wooden_plank"));
        recipesList.add(new Item(27, 3, "bow", "Bow",
                        "0,1,2,1,0,2,0,1,2", "air,stick,string"));
        recipesList.add(new Item(28, 3, "arrow", "Arrow",
                        "0,1,0,0,2,0,0,3,0", "air,flint,stick,feather"));
        
        //Other
        recipesList.add(new Item(29, 11, "wooden_plank", "Wooden planks",
                        "0,0,0,0,0,0,0,0,1", "air,wood"));
        recipesList.add(new Item(30, 11, "stick", "Stick",
                        "0,0,0,0,1,0,0,1,0", "air,wooden_plank"));
        recipesList.add(new Item(31, 11, "flint", "Flint",
                        "0,0,0,0,0,0,0,0,1", "air,gravel"));
        recipesList.add(new Item(32, 11, "feather", "Feather",
                        "0,0,0,0,0,0,0,0,1", "air,chicken"));
        
        //Tools
        recipesList.add(new Item(33, 4, "crafting_table", "Crafting table",
                        "0,0,0,1,1,0,1,1,0", "air,wooden_plank"));
        //Axes
        recipesList.add(new Item(34, 4, "diamond_axe", "Axe (Diamond)",
                        "1,1,0,1,2,0,0,2,0", "air,diamond_ingot,stick"));
        recipesList.add(new Item(35, 4, "iron_axe", "Axe (Iron)",
                        "1,1,0,1,2,0,0,2,0", "air,iron_ingot,stick"));
        recipesList.add(new Item(36, 4, "gold_axe", "Axe (Golden)",
                        "1,1,0,1,2,0,0,2,0", "air,gold_ingot,stick"));
        recipesList.add(new Item(37, 4, "stone_axe", "Axe (Stone)",
                        "1,1,0,1,2,0,0,2,0", "air,cobblestone,stick"));
        recipesList.add(new Item(38, 4, "wooden_axe", "Axe (Wooden)",
                        "1,1,0,1,2,0,0,2,0", "air,wooden_plank,stick"));
        //Pickaxes
        recipesList.add(new Item(39, 4, "diamond_pickaxe", "Pickaxe (Diamond)",
                        "1,1,1,0,2,0,0,2,0", "air,diamond_ingot,stick"));
        recipesList.add(new Item(40, 4, "iron_pickaxe", "Pickaxe (Iron)",
                        "1,1,1,0,2,0,0,2,0", "air,iron_ingot,stick"));
        recipesList.add(new Item(41, 4, "gold_pickaxe", "Pickaxe (Golden)",
                        "1,1,1,0,2,0,0,2,0", "air,gold_ingot,stick"));
        recipesList.add(new Item(42, 4, "stone_pickaxe", "Pickaxe (Stone)",
                        "1,1,1,0,2,0,0,2,0", "air,cobblestone,stick"));
        recipesList.add(new Item(43, 4, "wooden_pickaxe", "Pickaxe (Wooden)",
                        "1,1,1,0,2,0,0,2,0", "air,wooden_plank,stick"));
        //Shovels
        recipesList.add(new Item(44, 4, "diamond_shovel", "Shovel (Diamond)",
                        "0,1,0,0,2,0,0,2,0", "air,diamond_ingot,stick"));
        recipesList.add(new Item(45, 4, "iron_shovel", "Shovel (Iron)",
                        "0,1,0,0,2,0,0,2,0", "air,iron_ingot,stick"));
        recipesList.add(new Item(46, 4, "gold_shovel", "Shovel (Golden)",
                        "0,1,0,0,2,0,0,2,0", "air,gold_ingot,stick"));
        recipesList.add(new Item(47, 4, "stone_shovel", "Shovel (Stone)",
                        "0,1,0,0,2,0,0,2,0", "air,cobblestone,stick"));
        recipesList.add(new Item(48, 4, "wooden_shovel", "Shovel (Wooden)",
                        "0,1,0,0,2,0,0,2,0", "air,wooden_plank,stick"));
        //Hoe's
        recipesList.add(new Item(44, 4, "diamond_hoe", "Hoe (Diamond)",
                        "1,1,0,0,2,0,0,2,0", "air,diamond_ingot,stick"));
        recipesList.add(new Item(45, 4, "iron_hoe", "Hoe (Iron)",
                        "1,1,0,0,2,0,0,2,0", "air,iron_ingot,stick"));
        recipesList.add(new Item(46, 4, "gold_hoe", "Hoe (Golden)",
                        "1,1,0,0,2,0,0,2,0", "air,gold_ingot,stick"));
        recipesList.add(new Item(47, 4, "stone_hoe", "Hoe (Stone)",
                        "1,1,0,0,2,0,0,2,0", "air,cobblestone,stick"));
        recipesList.add(new Item(48, 4, "wooden_hoe", "Hoe (Wooden)",
                        "1,1,0,0,2,0,0,2,0", "air,wooden_plank,stick"));
        //Other tools
        recipesList.add(new Item(49, 4, "flint_and_steel", "Flint and steel",
                        "0,0,0,1,0,0,0,2,0", "air,iron_ingot,flint"));
        recipesList.add(new Item(80, 4, "bucket", "Bucket",
                        "0,0,0,1,0,1,0,1,0", "air,iron_ingot"));
        
        //Decorations
        recipesList.add(new Item(50, 6, "torch", "Torch",
                        "0,0,0,0,1,0,0,2,0", "air,coal_item,stick"));
        recipesList.add(new Item(51, 6, "chest", "Chest",
                        "1,1,1,1,0,1,1,1,1", "air,wooden_plank"));
        recipesList.add(new Item(52, 6, "wooden_pressure_plate", "Pressure plate (Wooden)",
                        "0,0,0,0,0,0,1,1,0", "air,wooden_plank"));
        recipesList.add(new Item(53, 6, "stone_pressure_plate", "Pressure plate (Stone)",
                        "0,0,0,0,0,0,1,1,0", "air,cobblestone"));
        recipesList.add(new Item(54, 6, "sign", "Sign",
                        "1,1,1,1,1,1,0,2,0", "air,wooden_plank,stick"));
        recipesList.add(new Item(55, 6, "wooden_door", "Door (Wooden)",
                        "1,1,0,1,1,0,1,1,0", "air,wooden_plank"));
        recipesList.add(new Item(56, 6, "iron_door", "Door (Iron)",
                        "1,1,0,1,1,0,1,1,0", "air,iron_ingot"));
        recipesList.add(new Item(57, 6, "wooden_stairs", "Stairs (Wooden)",
                        "1,0,0,1,1,0,1,1,1", "air,wooden_plank"));
        recipesList.add(new Item(58, 6, "stone_stairs", "Stairs (Stone)",
                        "1,0,0,1,1,0,1,1,1", "air,wooden_plank"));
        recipesList.add(new Item(59, 6, "stone_slab", "Slab (Stone)",
                        "0,0,0,0,0,0,1,1,1", "air,stone"));
        recipesList.add(new Item(60, 6, "wooden_slab", "Slab (Wooden)",
                        "0,0,0,0,0,0,1,1,1", "air,wooden_plank"));
        recipesList.add(new Item(61, 6, "bookshelf", "Bookshelf",
                        "1,1,1,2,2,2,1,1,1", "air,wooden_plank,book"));
        recipesList.add(new Item(62, 6, "jukebox", "Jukebox",
                        "1,1,1,1,2,1,1,0,1", "air,wooden_plank,diamond_ingot"));
        
        //Redstone related
        recipesList.add(new Item(67, 9, "redstone_torch_active", "Redstone torch",
                        "0,0,0,0,1,0,0,2,0", "air,redstone_dust,stick"));
        recipesList.add(new Item(68, 9, "redstone_repeater_active", "Redstone repeater",
                        "0,0,0,1,2,1,3,3,3", "air,redstone_torch_active,redstone_dust,stone"));
        recipesList.add(new Item(69, 9, "redstone_wire", "Redstone wire",
                        "0,0,0,0,0,0,0,0,1", "air,redstone_dust"));
        recipesList.add(new Item(70, 9, "compass", "Compass",
                        "0,1,0,1,2,1,0,1,0", "air,iron_ingot,redstone_dust"));
        recipesList.add(new Item(71, 9, "clock", "Clock",
                        "0,1,0,1,2,1,0,1,0", "air,gold_ingot,redstone_dust"));
        recipesList.add(new Item(72, 9, "dispenser", "Dispenser",
                        "1,1,1,1,2,1,1,3,1", "air,cobblestone,bow,redstone_dust"));
        recipesList.add(new Item(73, 9, "detector_rail", "Detector rail",
                        "1,0,1,1,2,1,1,3,1", "air,iron_ingot,stone_pressure_plate,redstone_dust"));
        recipesList.add(new Item(74, 9, "piston", "Piston",
                        "1,1,1,2,3,2,2,4,2", "air,wooden_plank,cobblestone,iron_ingot,redstone_dust"));
        
        //Food
        recipesList.add(new Item(75, 8, "cookie", "Cookie",
                        "0,0,0,1,2,1,0,0,0", "air,wheat,cocoa_beans,wheat"));
        recipesList.add(new Item(76, 8, "bread", "Bread",
                        "0,0,0,0,0,0,1,1,1", "air,wheat"));
        recipesList.add(new Item(77, 8, "cake", "Cake",
                        "1,1,1,2,3,2,4,4,4", "air,milk_bucket,sugar,egg,wheat"));
        recipesList.add(new Item(78, 8, "apple", "Apple",
                        "0,0,0,0,0,0,0,0,1", "air,leaves"));
        recipesList.add(new Item(79, 8, "gold_apple", "Golden apple",
                        "1,1,1,1,2,1,1,1,1", "air,gold_nugget,apple"));
        
        //Blocks
        recipesList.add(new Item(81, 5, "wood", "Wood",
                        "0,0,0,0,5,4,3,2,1", "air,diamond_axe,iron_axe,gold_axe,stone_axe,wood_axe"));
        recipesList.add(new Item(82, 5, "tnt", "TNT",
                        "1,2,1,2,1,2,1,2,1", "air,gunpowder,sand"));
        recipesList.add(new Item(83, 5, "sand", "Sand",
                        "0,0,0,0,5,4,3,2,1", "air,diamond_shovel,iron_shovel,gold_shovel,stone_shovel,wooden_shovel"));
        recipesList.add(new Item(84, 5, "gravel", "Gravel",
                        "0,0,0,0,5,4,3,2,1", "air,diamond_shovel,iron_shovel,gold_shovel,stone_shovel,wooden_shovel"));
        recipesList.add(new Item(85, 5, "grass", "Grass",
                        "0,0,0,0,5,4,3,2,1", "air,diamond_shovel,iron_shovel,gold_shovel,stone_shovel,wooden_shovel"));
        recipesList.add(new Item(86, 5, "dirt", "Dirt",
                        "0,0,0,0,5,4,3,2,1", "air,diamond_shovel,iron_shovel,gold_shovel,stone_shovel,wooden_shovel"));
        recipesList.add(new Item(87, 5, "glass", "Glass",
                        "0,0,0,0,0,0,0,0,1", "air,sand"));
        recipesList.add(new Item(88, 5, "glass_pane", "Glass pane",
                        "0,0,0,1,1,1,1,1,1", "air,glass"));
        
        //Brewing
        recipesList.add(new Item(89, 7, "brewing_stand", "Brewing stand",
                        "0,0,0,0,1,0,2,2,2", "air,blaze_rod,cobblestone"));
        recipesList.add(new Item(90, 7, "glass_bottle", "Glass bottle",
                        "0,0,0,1,0,1,0,1,0", "air,glass"));
        recipesList.add(new Item(91, 7, "awkward_potion", "Awkward potion",
                        "0,1,0,2,0,2,0,2,0", "air,brewing_stand,water_bottle"));
        recipesList.add(new Item(92, 7, "potion_of_healing", "Healing (Potion)",
                        "0,1,0,0,2,0,0,3,0", "air,glistering_melon,brewing_stand,awkward_potion"));
        recipesList.add(new Item(93, 7, "potion_of_fire_resistance", "Fire resistance (Potion)",
                        "0,1,0,0,2,0,0,3,0", "air,magma_cream,brewing_stand,awkward_potion"));
        recipesList.add(new Item(94, 7, "potion_of_regeneration", "Regeneration (Potion)",
                        "0,1,0,0,2,0,0,3,0", "air,ghast_tear,brewing_stand,awkward_potion"));
        recipesList.add(new Item(95, 7, "potion_of_strength", "Strength (Potion)",
                        "0,1,0,0,2,0,0,3,0", "air,blaze_powder,brewing_stand,awkward_potion"));
        recipesList.add(new Item(96, 7, "potion_of_swiftness", "Swiftness (Potion)",
                        "0,1,0,0,2,0,0,3,0", "air,sugar,brewing_stand,awkward_potion"));
        recipesList.add(new Item(97, 7, "potion_of_poison", "Poison (Potion)",
                        "0,1,0,0,2,0,0,3,0", "air,spider_eye,brewing_stand,awkward_potion"));
        recipesList.add(new Item(98, 7, "potion_of_healing", "Healing II (Potion)",
                        "0,1,0,0,2,0,0,3,0", "air,glowstone_dust,brewing_stand,potion_of_healing"));
        recipesList.add(new Item(99, 7, "potion_of_fire_resistance", "Extended resistance (Potion)",
                        "0,1,0,0,2,0,0,3,0", "air,redstone_dust,brewing_stand,potion_of_healing"));
        recipesList.add(new Item(100, 7, "potion_of_regeneration", "Regeneration II (Potion)",
                        "0,1,0,0,2,0,0,3,0", "air,glowstone_dust,brewing_stand,awkward_potion"));
        recipesList.add(new Item(101, 7, "potion_of_strength", "Extended strength (Potion)",
                        "0,1,0,0,2,0,0,3,0", "air,redstone_dust,brewing_stand,awkward_potion"));
        recipesList.add(new Item(102, 7, "potion_of_swiftness", "Swiftness II (Potion)",
                        "0,1,0,0,2,0,0,3,0", "air,glowstone_dust,brewing_stand,awkward_potion"));
        
        //Transportation
        recipesList.add(new Item(103, 10, "rails", "Rails",
                        "1,0,1,0,2,0,1,0,1", "air,iron_ingot,stick"));
        recipesList.add(new Item(104, 10, "minecart", "Minecart",
                        "0,0,0,1,0,1,1,1,1", "air,iron_ingot"));
        recipesList.add(new Item(105, 10, "storage_minecart", "Minecart with chest",
                        "0,0,0,0,1,0,0,2,0", "air,chest,minecart"));
        
    }

    public void setItems(List<Item> list) {
        recipesList = list;
    }

    public List<Item> getItems() {
        return recipesList;
    }
}
