package lv.javaguru.java3.rest.users;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lv.javaguru.java3.core.commands.users.CreateUserCommand;
import lv.javaguru.java3.core.commands.users.CreateUserResult;
import lv.javaguru.java3.core.commands.users.GetUserCommand;
import lv.javaguru.java3.core.commands.users.GetUserResult;
import lv.javaguru.java3.core.dto.user.UserDTO;
import lv.javaguru.java3.core.services.CommandExecutor;

@Component
@Path("/users")
public class UserResourceImpl {
	
	private CommandExecutor commandExecutor;

    @Autowired
    public UserResourceImpl(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public UserDTO create(UserDTO userDTO) {
        CreateUserCommand command = new CreateUserCommand(
                userDTO.getLogin(), userDTO.getPassword(),
                userDTO.getName(), userDTO.getSurname(),
                userDTO.getEmail(), userDTO.getAccessLevel()
        );
        CreateUserResult result = commandExecutor.execute(command);
        return result.getUser();
    }

    @GET
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/{userId}")
    public UserDTO get(@PathParam("userId") Long userId) {
        GetUserCommand command = new GetUserCommand(userId);
        GetUserResult result = commandExecutor.execute(command);
        return result.getUser();
    }

}
