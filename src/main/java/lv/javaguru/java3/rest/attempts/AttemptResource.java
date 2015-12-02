package lv.javaguru.java3.rest.attempts;

import lv.javaguru.java3.core.dto.attempt.AttemptDTO;

import javax.ws.rs.*;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

public interface AttemptResource {

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/attempts/create")
    AttemptDTO create(AttemptDTO attemptDTO);

    @GET
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/attempts/get/{attemptId}")
    AttemptDTO get(@PathParam("attemptId") Long attemptId);

    @PUT
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/attempts/modify/failed/{userLogin}")
    AttemptDTO failed(@PathParam("userLogin") String userLogin);

    @PUT
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/attempts/modify/reset_by_time/{userLogin}")
    AttemptDTO resetByTime(@PathParam("userLogin") String userLogin);

    @PUT
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/attempts/modify/reset_by_login/{userLogin}")
    AttemptDTO resetByLogin(@PathParam("userLogin") String userLogin);

}
