package be.pxl.minecraft.rest;

import be.pxl.minecraft.model.Item;
import be.pxl.minecraft.storage.ItemStorage;
import com.sun.jersey.api.core.InjectParam;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

@Path("Items")
public class ItemsResource {

    @InjectParam
    private ItemStorage items;
    
    public ItemsResource() {
    }

    @GET
    @Produces("application/json")
    public List<Item> getItems() {
        return items.getItems();
    }

    @PUT
    @Consumes("application/json")
    public void putItemList(List<Item> content) {
        this.items.setItems(content);
    }
}
