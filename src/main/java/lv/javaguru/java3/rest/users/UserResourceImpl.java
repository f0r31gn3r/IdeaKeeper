package lv.javaguru.java3.rest.users;

import lv.javaguru.java3.core.commands.ideas.IdeaConverter;
import lv.javaguru.java3.core.commands.users.*;
import lv.javaguru.java3.core.domain.idea.Idea;
import lv.javaguru.java3.core.domain.user.User;
import lv.javaguru.java3.core.dto.idea.IdeaDTO;
import lv.javaguru.java3.core.dto.user.UserDTO;
import lv.javaguru.java3.core.services.CommandExecutor;
import lv.javaguru.java3.core.services.authentication.AuthenticationService;
import lv.javaguru.java3.core.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Path("/users")
public class UserResourceImpl {

    private CommandExecutor commandExecutor;

    @Autowired
    public UserResourceImpl(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }

    @Autowired  UserService userService;
    @Autowired  AuthenticationService authenticationService;

    @POST
//    @Consumes(APPLICATION_JSON)
//    @Produces(APPLICATION_JSON)
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("/create")
    public UserDTO create(UserDTO userDTO) {
        //if(authenticationService.getAccessLevel().equals(AccessLevel.VIP.name())){
        CreateUserCommand command = new CreateUserCommand(
                userDTO.getLogin(), userDTO.getPassword(),
                userDTO.getName(), userDTO.getSurname(),
                userDTO.getEmail(), userDTO.getAccessLevel()
        );
        CreateUserResult result = commandExecutor.execute(command);
        return result.getUser();
        //} else return null;
    }

    @GET
//    @Consumes(APPLICATION_JSON)
//    @Produces(APPLICATION_JSON)
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("/get/{userId}")
    public UserDTO get(@PathParam("userId") Long userId) {
        GetUserCommand command = new GetUserCommand(userId);
        GetUserResult result = commandExecutor.execute(command);
        return result.getUser();
    }

    @DELETE
//    @Consumes(APPLICATION_JSON)
//    @Produces(APPLICATION_JSON)
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    //@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("/delete/{userId}")
    public int delete(@PathParam("userId") Long userId) {
        DeleteUserCommand command = new DeleteUserCommand(userId);
        DeleteUserResult result = commandExecutor.execute(command);
        return result.getResult();
    }

    @PUT
//    @Consumes(APPLICATION_JSON)
//    @Produces(APPLICATION_JSON)
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("/update/{userId}")
    public UserDTO update(@PathParam("userId") Long userId, UserDTO userDTO) {
        UpdateUserCommand command = new UpdateUserCommand(
                userId,
                userDTO.getLogin(), userDTO.getPassword(),
                userDTO.getName(), userDTO.getSurname(),
                userDTO.getEmail(), userDTO.getAccessLevel()
        );
        UpdateUserResult result = commandExecutor.execute(command);
        return result.getUser();
    }

    @PUT
//    @Consumes(APPLICATION_JSON)
//    @Produces(APPLICATION_JSON)
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
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
//    @Consumes(APPLICATION_JSON)
//    @Produces(APPLICATION_JSON)
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
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
//    @Consumes(APPLICATION_JSON)
//    @Produces(APPLICATION_JSON)
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
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

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("/get_ideas/{userId}")
    public Set<IdeaDTO> getUserIdeas(@PathParam("userId") Long userId) {
        Set<IdeaDTO> resultSetDTO = new HashSet<IdeaDTO>();
        Set<Idea> resultSet = new HashSet<Idea>();
        User user = userService.get(userId);

        if(user.getIdeas() != null){
            resultSet = user.getIdeas();
        } else{
            return resultSetDTO;
        }

        for(Idea i:resultSet){
            resultSetDTO.add(new IdeaConverter().convert(i));
        }
        return resultSetDTO;
    }

    @GET
//    @Consumes(APPLICATION_JSON)
//    @Produces(APPLICATION_JSON)
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })

    public List<UserDTO> getFirstFive(){
        List<User> users = userService.getFirstFive();
        List<UserDTO> usersDTO = new ArrayList<UserDTO>();

        if(users.size() > 0){
            for(User u : users){
                usersDTO.add(new UserConverter().convert(u));
            }
        }

        return usersDTO;
    }
}
