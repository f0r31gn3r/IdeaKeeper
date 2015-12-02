package lv.javaguru.java3.rest.attempts;

import lv.javaguru.java3.core.commands.attempts.*;
import lv.javaguru.java3.core.domain.attempt.Attempt;
import lv.javaguru.java3.core.dto.attempt.AttemptDTO;
import lv.javaguru.java3.core.services.CommandExecutor;
import lv.javaguru.java3.core.services.attempts.AttemptService;
import lv.javaguru.java3.core.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Component
@Path("/attempts")
public class AttemptResourceImpl {
	private CommandExecutor commandExecutor;


    @Autowired   UserService userService;
    @Autowired   AttemptService attemptService;

    @Autowired
    public AttemptResourceImpl(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/create")
    public AttemptDTO create(AttemptDTO attemptDTO) {

        Long id = attemptDTO.getUserId();
        if(id==null){
            id = attemptDTO.getUserDTO().getUserId();
        }
        CreateAttemptCommand command = new CreateAttemptCommand(
                attemptDTO.getLogin(),
                attemptDTO.getAttempts(),
                attemptDTO.getLastModified(),
                id
        );
        CreateAttemptResult result = commandExecutor.execute(command);
        return result.getAttempt();
    }

    @GET
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/get/{attemptId}")
    public AttemptDTO get(@PathParam("attemptId") Long attemptId) {
        GetAttemptCommand command = new GetAttemptCommand(attemptId);
        GetAttemptResult result = commandExecutor.execute(command);
        return result.getAttempt();
    }

    @PUT
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/modify/failed/{userLogin}")
    public AttemptDTO failed(@PathParam("userLogin") String userLogin) {
        Attempt userAttempt = attemptService.get(userLogin);
        userAttempt = attemptService.updateFailAttempts(userAttempt);

        AttemptDTO attemptDTO = new AttemptConverter().convert(userAttempt);

        UpdateAttemptCommand command = new UpdateAttemptCommand(
                attemptDTO.getAttemptId(),
                attemptDTO.getLogin(),
                attemptDTO.getAttempts(),
                attemptDTO.getLastModified()
        );

        UpdateAttemptResult result = commandExecutor.execute(command);
        return result.getAttempt();
    }

    @PUT
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/modify/reset_by_time/{userLogin}")
    public AttemptDTO resetByTime(@PathParam("userLogin") String userLogin) {
        Attempt userAttempt = attemptService.get(userLogin);
        userAttempt = attemptService.resetByTime(userAttempt);

        AttemptDTO attemptDTO = new AttemptConverter().convert(userAttempt);

        UpdateAttemptCommand command = new UpdateAttemptCommand(
                attemptDTO.getAttemptId(),
                attemptDTO.getLogin(),
                attemptDTO.getAttempts(),
                attemptDTO.getLastModified()
        );

        UpdateAttemptResult result = commandExecutor.execute(command);
        return result.getAttempt();
    }

    @PUT
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/modify/reset_by_login/{userLogin}")
    public AttemptDTO resetByLogin(@PathParam("userLogin") String userLogin) {
        Attempt userAttempt = attemptService.get(userLogin);
        userAttempt = attemptService.resetBySuccessfulLogin(userAttempt);

        AttemptDTO attemptDTO = new AttemptConverter().convert(userAttempt);

        UpdateAttemptCommand command = new UpdateAttemptCommand(
                attemptDTO.getAttemptId(),
                attemptDTO.getLogin(),
                attemptDTO.getAttempts(),
                attemptDTO.getLastModified()
        );

        UpdateAttemptResult result = commandExecutor.execute(command);
        return result.getAttempt();
    }

}
