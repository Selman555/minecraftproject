/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.pxl.minecraft.storage;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Robbie
 */
public class ImageStorage {
    HashMap<String,Image> categories;
    HashMap<String,Image> items;
    Toolkit toolkit;
    
    public ImageStorage() {
        toolkit = Toolkit.getDefaultToolkit();
        categories = new HashMap();
        try {
            categories.put("all", ImageIO.read(new URL("/src/images/all.png")));
            categories.put("armor", toolkit.getImage("/src/armor.png"));
            categories.put("blocks", toolkit.getImage("blocks.png"));
            categories.put("enchantment", toolkit.getImage("enchantment_and_brewing.png"));
            categories.put("food", toolkit.getImage("food.png"));
            categories.put("other", toolkit.getImage("other.png"));
            categories.put("redstone", toolkit.getImage("redstone.png"));
            categories.put("tools", toolkit.getImage("tools.png"));
            categories.put("transport", toolkit.getImage("transport.png"));
            categories.put("weapons", toolkit.getImage("weapons.png"));
        } catch (IOException ex) {
            Logger.getLogger(ImageStorage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        items = new HashMap();
        items.put("air", toolkit.getImage("/src/air.png"));
        items.put("diamond_ingot", toolkit.getImage("diamond_ingot.png"));
        items.put("iron_ingot", toolkit.getImage("iron_ingot.png"));
        items.put("gold_ingot", toolkit.getImage("gold_ingot.png"));
        items.put("diamond_helmet", toolkit.getImage("diamond_helmet.png"));
        items.put("iron_helmet", toolkit.getImage("iron_helmet.png"));
        items.put("gold_helmet", toolkit.getImage("gold_helmet.png"));
        items.put("diamond_chestplate", toolkit.getImage("diamond_chestplate.png"));
        items.put("iron_chestplate", toolkit.getImage("iron_chestplate.png"));
        items.put("gold_chestplate", toolkit.getImage("gold_chestplate.png"));
        items.put("diamond_leggings", toolkit.getImage("diamond_leggings.png"));
        items.put("iron_leggings", toolkit.getImage("iron_leggings.png"));
        items.put("gold_leggings", toolkit.getImage("gold_leggings.png"));
        items.put("diamond_boots", toolkit.getImage("diamond_boots.png"));
        items.put("iron_boots", toolkit.getImage("iron_boots.png"));
        items.put("gold_boots", toolkit.getImage("gold_boots.png"));
    }
    
    public HashMap<String, Image> getCategories() {
        return categories;
    }
    
    public HashMap<String, Image> getItems() {
        return items;
    }
}
