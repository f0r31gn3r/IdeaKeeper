package lv.javaguru.java3.rest.authentication;
/**
 * Created by Anna on 16.11.2015.
 */
import lv.javaguru.java3.core.dto.user.UserDTO;
import lv.javaguru.java3.core.services.authentication.AuthenticationService;
import lv.javaguru.java3.core.services.authentication.AuthenticationStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Component
@Path("/authentication")
public class AuthenticationResourceImpl {

	@Autowired AuthenticationService authenticationService;

	@POST
	@Consumes(APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/login")
	public String login(UserDTO userDTO) {
		authenticationService.authenticate(userDTO.getLogin(), userDTO.getPassword());

		if(authenticationService.getState().equals(AuthenticationStatus.SUCCESSFUL_LOGIN.getValue())){
			return "Welcome!";
		} else if(authenticationService.getState().equals(AuthenticationStatus.BLOCKED.getValue())){
			return "This user is blocked!";
		} else if(authenticationService.getState().equals(AuthenticationStatus.PASS_FAILED.getValue())){
			return "Incorrect password!";
		}else if(authenticationService.getState().equals(AuthenticationStatus.USERNAME_FAILED.getValue())){
			return "User not found!";
		} else return "Go home";
	}

	@GET
	@Path("/logout")
	@Produces(MediaType.TEXT_PLAIN)
	public String logout() {
		return "Logout!";
	}
}
