package lv.javaguru.java3.rest.users;

import lv.javaguru.java3.core.dto.user.UserDTO;

import javax.ws.rs.*;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

public interface UserResource {
	

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/users")
    UserDTO create(UserDTO userDTO);

    @GET
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/users/{userId}")
    UserDTO get(@PathParam("userId") Long userId);

    @GET
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/users/{login}")
    UserDTO getByLogin(@PathParam("login") Long userId);
    
}
