package lv.javaguru.java3.rest.clients;

import lv.javaguru.java3.core.dto.ClientDTO;

import javax.ws.rs.*;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

public interface ClientResource {

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/clients")
    ClientDTO create(ClientDTO clientDTO);

    @GET
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/clients/{clientId}")
    ClientDTO get(@PathParam("clientId") Long clientId);

}
