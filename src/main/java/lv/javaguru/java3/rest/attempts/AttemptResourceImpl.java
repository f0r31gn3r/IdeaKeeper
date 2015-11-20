package lv.javaguru.java3.rest.attempts;

import lv.javaguru.java3.core.commands.attempts.CreateAttemptCommand;
import lv.javaguru.java3.core.commands.attempts.CreateAttemptResult;
import lv.javaguru.java3.core.commands.attempts.GetAttemptCommand;
import lv.javaguru.java3.core.commands.attempts.GetAttemptResult;
import lv.javaguru.java3.core.dto.attempt.AttemptDTO;
import lv.javaguru.java3.core.services.CommandExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Component
@Path("/attempts")
public class AttemptResourceImpl {
	private CommandExecutor commandExecutor;

    @Autowired
    public AttemptResourceImpl(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public AttemptDTO create(AttemptDTO attemptDTO) {
        CreateAttemptCommand command = new CreateAttemptCommand(
                attemptDTO.getLogin(),
                attemptDTO.getAttempts(),
                attemptDTO.getLastModified(),
                attemptDTO.getUserDTO().getUserId()
        );
        CreateAttemptResult result = commandExecutor.execute(command);
        return result.getAttempt();
    }

    @GET
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/{attemptId}")
    public AttemptDTO get(@PathParam("attemptId") Long attemptId) {
        GetAttemptCommand command = new GetAttemptCommand(attemptId);
        GetAttemptResult result = commandExecutor.execute(command);
        return result.getAttempt();
    }

}
