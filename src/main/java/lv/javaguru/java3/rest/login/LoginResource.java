package lv.javaguru.java3.rest.login;
/**
 * Created by Anna on 16.11.2015.
 */
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface LoginResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
    @Path("/login")
    String login();
	
}
