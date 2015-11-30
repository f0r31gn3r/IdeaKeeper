package lv.javaguru.java3.rest.authentication;
/**
 * Created by Anna on 16.11.2015.
 */

import lv.javaguru.java3.core.dto.user.UserDTO;
import lv.javaguru.java3.core.services.authentication.AuthenticationService;
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
		if(authenticationService.authenticate(userDTO.getLogin(), userDTO.getPassword())){
			return "Login successful!";
		} else {
			return "Login failed!";
		}
	}

	@GET
	@Path("/logout")
	@Produces(MediaType.TEXT_PLAIN)
	public String logout() {
		return "Logout!";
	}
}
