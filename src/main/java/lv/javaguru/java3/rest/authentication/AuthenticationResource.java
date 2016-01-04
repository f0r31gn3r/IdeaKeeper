package lv.javaguru.java3.rest.authentication;
/**
 * Created by Anna on 16.11.2015.
 */

import lv.javaguru.java3.core.dto.message.MessageDTO;
import lv.javaguru.java3.core.dto.user.UserDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
public interface AuthenticationResource {

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("/authentication/login")
    MessageDTO login(UserDTO userDTO);

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/authentication/logout")
    String logout();

}
