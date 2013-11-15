/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.pxl.minecraft.rest;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
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
    private String chatSession = "";
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ChatResourceResource
     */
    public ChatResourceResource() {
    }

    /**
     * Retrieves representation of an instance of be.pxl.minecraft.rest.ChatResourceResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/string")
    public String getChat() {
        return chatSession;
    }

    /**
     * PUT method for updating or creating an instance of ChatResourceResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/string")
    public void putChat(String chatInsert) {
        chatSession = chatSession.concat("\r\n"+chatInsert);
    }
}
