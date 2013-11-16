/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.pxl.minecraft.rest;

import be.pxl.minecraft.storage.ChatSessionStorage;
import com.sun.jersey.api.core.InjectParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

/**
 * REST Web Service
 *
 * @author Robbie
 */
@Path("ChatResource")
public class ChatResourceResource {
    
    @Context
    private UriInfo context;
    private static final ChatSessionStorage UNIQUE_CHATSESSION = new ChatSessionStorage();

    public ChatResourceResource() {
        
    }

    @GET
    @Produces("application/string")
    public String getChat() {
        return UNIQUE_CHATSESSION.getChatSessionText();
    }

    /**
     * PUT method for updating or creating an instance of ChatResourceResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/string")
    public void putChat(String chatInsert) {
        UNIQUE_CHATSESSION.setChatSessionText(chatInsert);
    }
}
