package lv.javaguru.java3.rest.authentication;
/**
 * Created by Anna on 16.11.2015.
 */

import lv.javaguru.java3.core.dto.user.UserDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

public interface AuthenticationResource {

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/authentication/login")
    String login(UserDTO userDTO);

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/authentication/logout")
    String logout();

}
