package lv.javaguru.java3.rest.users;

import lv.javaguru.java3.core.commands.users.*;
import lv.javaguru.java3.core.domain.user.User;
import lv.javaguru.java3.core.dto.user.UserDTO;
import lv.javaguru.java3.core.services.CommandExecutor;
import lv.javaguru.java3.core.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Component
@Path("/users")
public class UserResourceImpl {
	
	private CommandExecutor commandExecutor;

    @Autowired
    public UserResourceImpl(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }

    @Autowired  UserService userService;

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

    @DELETE
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/{userId}")
    public int delete(@PathParam("userId") Long userId) {
        DeleteUserCommand command = new DeleteUserCommand(userId);
        DeleteUserResult result = commandExecutor.execute(command);
        return result.getResult();
    }

    @PUT
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public UserDTO update(UserDTO userDTO) {
        UpdateUserCommand command = new UpdateUserCommand(
                userDTO.getUserId(),
                userDTO.getLogin(), userDTO.getPassword(),
                userDTO.getName(), userDTO.getSurname(),
                userDTO.getEmail(), userDTO.getAccessLevel()
        );
        UpdateUserResult result = commandExecutor.execute(command);
        return result.getUser();
    }

    @PUT
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/block/{userId}")
    public UserDTO block(@PathParam("userId") Long userId) {
        User user = userService.get(userId);
        user = userService.blockUser(user);
        UserDTO userDTO = new UserConverter().convert(user);

        UpdateUserCommand command = new UpdateUserCommand(
                userDTO.getUserId(),
                userDTO.getLogin(), userDTO.getPassword(),
                userDTO.getName(), userDTO.getSurname(),
                userDTO.getEmail(), userDTO.getAccessLevel()
        );
        UpdateUserResult result = commandExecutor.execute(command);
        return result.getUser();
    }

    @PUT
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/unblock/{userId}")
    public UserDTO unblock(@PathParam("userId") Long userId) {
        User user = userService.get(userId);
        user = userService.unblockUser(user);
        UserDTO userDTO = new UserConverter().convert(user);

        UpdateUserCommand command = new UpdateUserCommand(
                userDTO.getUserId(),
                userDTO.getLogin(), userDTO.getPassword(),
                userDTO.getName(), userDTO.getSurname(),
                userDTO.getEmail(), userDTO.getAccessLevel()
        );
        UpdateUserResult result = commandExecutor.execute(command);
        return result.getUser();
    }

    @PUT
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/setvip/{userId}")
    public UserDTO setVip(@PathParam("userId") Long userId) {
        User user = userService.get(userId);
        user = userService.setStatusVIP(user);
        UserDTO userDTO = new UserConverter().convert(user);

        UpdateUserCommand command = new UpdateUserCommand(
                userDTO.getUserId(),
                userDTO.getLogin(), userDTO.getPassword(),
                userDTO.getName(), userDTO.getSurname(),
                userDTO.getEmail(), userDTO.getAccessLevel()
        );
        UpdateUserResult result = commandExecutor.execute(command);
        return result.getUser();
    }


}
