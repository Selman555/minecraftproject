package be.pxl.minecraft.storage;

import java.util.ArrayList;
import java.util.List;

import be.pxl.minecraft.model.Item;
import com.sun.jersey.spi.resource.Singleton;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

@Singleton
public class ItemStorage {
    private ImageStorage test;
    private HashMap<String, BufferedImage> categories;
    private HashMap<String, BufferedImage> images;
    private List<Item> recipesList;

    public ItemStorage() {
        categories = new HashMap<String, BufferedImage>();
        images = new HashMap<String, BufferedImage>();
        recipesList = new ArrayList<Item>();
        
        File directory = new File("C:\\minecraftimages\\items");
        if (directory.isDirectory()) {
            BufferedImage img = null;
            for (File f : directory.listFiles()) {
                try {
                    img = ImageIO.read(f);
                    int length = f.getName().indexOf(".");
                    String name = f.getName().substring(0, length-1);
                    images.put(name, img);
                } catch (IOException ex) {
                    Logger.getLogger(ItemStorage.class.getName()).log(Level.SEVERE, "Error loading image", ex);
                }
            }
        }

        recipesList = new ArrayList<Item>();
        BufferedImage air = images.get("air");
        recipesList.add(new Item(1, 11, "diamond_ingot", "Diamond ingot",
                        "0,0,0,0,0,0,0,0,1", "air,diamond_ore"));
        recipesList.add(new Item(2, 11, "iron_ingot", "Iron ingot",
                        "0,0,0,0,0,0,0,0,1", "air,iron_ore"));
        recipesList.add(new Item(3, 11, "gold_ingot", "Gold ingot",
                        "0,0,0,0,0,0,0,0,1", "air,gold_ore"));
        recipesList.add(new Item(4, 11, "diamond_ore", "Diamond ore",
                        "0,0,0,0,0,0,0,0,1", "air,wooden_pickaxe"));
        recipesList.add(new Item(5, 11, "iron_ore", "Iron ore",
                        "0,0,0,0,0,0,0,0,1", "air,wooden_pickaxe"));
        recipesList.add(new Item(6, 11, "gold_ore", "Gold ore",
                        "0,0,0,0,0,0,0,0,1", "air,wooden_pickaxe"));

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
    }

    public void setItems(List<Item> list) {
        recipesList = list;
    }

    public List<Item> getItems() {
        return recipesList;
    }
}
