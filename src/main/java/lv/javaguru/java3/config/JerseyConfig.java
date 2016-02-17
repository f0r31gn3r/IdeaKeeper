package lv.javaguru.java3.config;

import lv.javaguru.java3.rest.attempts.AttemptResourceImpl;
import lv.javaguru.java3.rest.authentication.AuthenticationResourceImpl;
import lv.javaguru.java3.rest.ideas.IdeaResourceImpl;
import lv.javaguru.java3.rest.users.UserResourceImpl;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;

@Configuration
@ApplicationPath("/rest")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {

        //register(ClientResourceImpl.class);
        register(UserResourceImpl.class);
        register(AttemptResourceImpl.class);
        register(AuthenticationResourceImpl.class);
        register(IdeaResourceImpl.class);
        register(RestAuthenticationFilter.class); //comment this for passing REST tests
        register(RabbitConfiguration.class);

        register(CORSResponseFilter.class);
    }

}
