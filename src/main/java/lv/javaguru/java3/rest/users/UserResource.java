package lv.javaguru.java3.rest.users;

import lv.javaguru.java3.core.dto.idea.IdeaDTO;
import lv.javaguru.java3.core.dto.user.UserDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Set;
public interface UserResource {

    @POST
//    @Consumes(APPLICATION_JSON)
//    @Produces(APPLICATION_JSON)
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("/users/create")
    UserDTO create(UserDTO userDTO);

    @GET
//    @Consumes(APPLICATION_JSON)
//    @Produces(APPLICATION_JSON)
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("/users/get/{userId}")
    UserDTO get(@PathParam("userId") Long userId);

    @DELETE
//    @Consumes(APPLICATION_JSON)
//    @Produces(APPLICATION_JSON)
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("/users/delete/{userId}")
    int delete(@PathParam("userId") Long userId);

    @PUT
//    @Consumes(APPLICATION_JSON)
//    @Produces(APPLICATION_JSON)
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("/users/update")
    UserDTO update(UserDTO userDTO);

    @PUT
//    @Consumes(APPLICATION_JSON)
//    @Produces(APPLICATION_JSON)
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("/users/block/{userId}")
    UserDTO block(@PathParam("userId") Long userId);

    @PUT
//    @Consumes(APPLICATION_JSON)
//    @Produces(APPLICATION_JSON)
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("/users/unblock/{userId}")
    UserDTO unblock(@PathParam("userId") Long userId);

    @PUT
//    @Consumes(APPLICATION_JSON)
//    @Produces(APPLICATION_JSON)
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("/users/setvip/{userId}")
    UserDTO setVip(@PathParam("userId") Long userId);

    @GET
//    @Consumes(APPLICATION_JSON)
//    @Produces(APPLICATION_JSON)
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("/users/get_ideas/{userId}")
    Set<IdeaDTO> getUserIdeas(@PathParam("userId") Long userId);

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("/users")
    List<UserDTO> getAllUsers();
}
