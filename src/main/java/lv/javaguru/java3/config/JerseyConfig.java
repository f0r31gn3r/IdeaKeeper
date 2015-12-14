package lv.javaguru.java3.config;

import lv.javaguru.java3.rest.attempts.AttemptResourceImpl;
import lv.javaguru.java3.rest.clients.ClientResourceImpl;
import lv.javaguru.java3.rest.ideas.IdeaResourceImpl;
import lv.javaguru.java3.rest.authentication.AuthenticationResourceImpl;
import lv.javaguru.java3.rest.users.UserResourceImpl;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("rest")

public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {

        register(ClientResourceImpl.class);
        register(UserResourceImpl.class);
        register(AttemptResourceImpl.class);
        register(AuthenticationResourceImpl.class);
        register(IdeaResourceImpl.class);

        register(CORSResponseFilter.class);
    }

}
