package lv.javaguru.java3.rest.attempts;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import lv.javaguru.java3.core.dto.attempt.AttemptDTO;

public interface AttemptResource {
	
	@POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/attempts")
    AttemptDTO create(AttemptDTO attemptDTO);

    @GET
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/attempts/{attemptId}")
    AttemptDTO get(@PathParam("attemptId") Long attemptId);

}
