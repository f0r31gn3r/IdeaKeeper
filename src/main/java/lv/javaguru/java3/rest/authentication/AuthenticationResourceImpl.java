package lv.javaguru.java3.rest.authentication;
/**
 * Created by Anna on 16.11.2015.
 */

import lv.javaguru.java3.core.dto.message.MessageDTO;
import lv.javaguru.java3.core.dto.user.UserDTO;
import lv.javaguru.java3.core.services.authentication.AuthenticationService;
import lv.javaguru.java3.core.services.authentication.AuthenticationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//import static javax.ws.rs.core.MediaType.TEXT_XML;

@Component
@Path("/authentication")
public class AuthenticationResourceImpl {

	@Autowired AuthenticationService authenticationService;

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/login")
	public MessageDTO login(UserDTO userDTO) {
		authenticationService.authenticate(userDTO.getLogin(), userDTO.getPassword());
		MessageDTO messageDTO = new MessageDTO();
		if(authenticationService.getState().equals(AuthenticationStatus.SUCCESSFUL_LOGIN.getValue())){
			messageDTO.setMessageBody("Welcome!");
			return messageDTO;
		} else if(authenticationService.getState().equals(AuthenticationStatus.BLOCKED.getValue())){
			messageDTO.setMessageBody("This user is blocked!");
			return messageDTO;
		} else if(authenticationService.getState().equals(AuthenticationStatus.PASS_FAILED.getValue())){
			messageDTO.setMessageBody("Incorrect password!");
			return messageDTO;
		}else if(authenticationService.getState().equals(AuthenticationStatus.USERNAME_FAILED.getValue())){
			messageDTO.setMessageBody("User not found!");
			return messageDTO;
		} else{
			messageDTO.setMessageBody("Go home");
			return messageDTO;
		}
	}

	@GET
	@Path("/logout")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public String logout() {
		return "Logout!";
	}
}
