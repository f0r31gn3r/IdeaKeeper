package lv.javaguru.java3.rest.login;
/**
 * Created by Anna on 16.11.2015.
 */
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Component
@Path("/login")
public class LoginResourceImpl {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String login() {
		return "Hello, user!";
	}
}
