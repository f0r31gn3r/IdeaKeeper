package lv.javaguru.java3.core.rest;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.jaxrs.JAXRSContract;
import lv.javaguru.java3.config.Application;
import lv.javaguru.java3.rest.attempts.AttemptResource;
import lv.javaguru.java3.rest.clients.ClientResource;
import lv.javaguru.java3.rest.ideas.IdeaResource;
import lv.javaguru.java3.rest.authentication.AuthenticationResource;
import lv.javaguru.java3.rest.users.UserResource;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@WebIntegrationTest(randomPort = true)
@SpringApplicationConfiguration(classes = {Application.class})
public class RESTResourceTest {

    @Value("${local.server.port}")
    private int port;

    protected ClientResource clientResource;
    protected UserResource userResource;
    protected AttemptResource attemptResource;
    protected AuthenticationResource authenticationResource;
    protected IdeaResource ideaResource;

    @Before
    public void init() throws IOException {
        String url = "http://localhost:" + port;

        clientResource = Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .contract(new JAXRSContract())
                .target(ClientResource.class, url);
        userResource = Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .contract(new JAXRSContract())
                .target(UserResource.class, url);

        attemptResource = Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .contract(new JAXRSContract())
                .target(AttemptResource.class, url);

        authenticationResource = Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .contract(new JAXRSContract())
                .target(AuthenticationResource.class, url);

        ideaResource = Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .contract(new JAXRSContract())
                .target(IdeaResource.class, url);
    }

}
