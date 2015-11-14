package lv.javaguru.java3.rest.attempts;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lv.javaguru.java3.core.commands.attempts.CreateAttemptCommand;
import lv.javaguru.java3.core.commands.attempts.CreateAttemptResult;
import lv.javaguru.java3.core.commands.attempts.GetAttemptCommand;
import lv.javaguru.java3.core.commands.attempts.GetAttemptResult;
import lv.javaguru.java3.core.dto.attempt.AttemptDTO;
import lv.javaguru.java3.core.services.CommandExecutor;

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
        		attemptDTO.getUserId(), attemptDTO.getLogin(),
        		attemptDTO.getAttempts(), attemptDTO.getLastModified()
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
