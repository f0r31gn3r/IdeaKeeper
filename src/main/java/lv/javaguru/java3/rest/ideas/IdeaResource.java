package lv.javaguru.java3.rest.ideas;

import lv.javaguru.java3.core.dto.idea.IdeaDTO;

import javax.ws.rs.*;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by Anna on 22.11.2015.
 */
public interface IdeaResource {

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/ideas")
    IdeaDTO create(IdeaDTO ideaDTO);

    @GET
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/ideas/{ideaId}")
    IdeaDTO get(@PathParam("ideaId") Long ideaId);


}
