package lv.javaguru.java3.rest.ideas;

import lv.javaguru.java3.core.commands.ideas.CreateIdeaCommand;
import lv.javaguru.java3.core.commands.ideas.CreateIdeaResult;
import lv.javaguru.java3.core.commands.ideas.GetIdeaCommand;
import lv.javaguru.java3.core.commands.ideas.GetIdeaResult;
import lv.javaguru.java3.core.dto.idea.IdeaDTO;
import lv.javaguru.java3.core.services.CommandExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by Anna on 22.11.2015.
 */

@Component
@Path("/ideas")
public class IdeaResourceImpl {

    private CommandExecutor commandExecutor;

    @Autowired
    public IdeaResourceImpl(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }


    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public IdeaDTO create(IdeaDTO ideaDTO) {
        CreateIdeaCommand command = new CreateIdeaCommand(
                ideaDTO.getTitle(),
                ideaDTO.getDescription(),
                ideaDTO.getUserDTO().getUserId()
        );
        CreateIdeaResult result = commandExecutor.execute(command);
        return result.getIdea();
    }

    @GET
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/{ideaId}")
    public IdeaDTO get(@PathParam("ideaId") Long ideaId) {
        GetIdeaCommand command = new GetIdeaCommand(ideaId);
        GetIdeaResult result = commandExecutor.execute(command);
        return result.getIdea();
    }


}
