package lv.javaguru.java3.core.rest.clients;

import lv.javaguru.java3.core.dto.ClientDTO;
import lv.javaguru.java3.core.rest.RESTResourceTest;
import org.junit.Test;

import static lv.javaguru.java3.core.dto.ClientDTOBuilder.createClientDTO;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class ClientResourceImplTest extends RESTResourceTest {

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    @Test
    public void createClientTest() {
        ClientDTO client = clientResource.create(
                createClientDTO()
                        .withLogin(LOGIN)
                        .withPassword(PASSWORD).build()
        );
        assertThat(client, is(notNullValue()));
        assertThat(client.getId(), is(notNullValue()));
    }

    @Test
    public void getClientTest() {
        ClientDTO newClient = clientResource.create(
                createClientDTO()
                        .withLogin(LOGIN)
                        .withPassword(PASSWORD).build()
        );
        ClientDTO oldClient = clientResource.get(newClient.getId());
        assertThat(newClient.getId(), is(oldClient.getId()));
        assertThat(newClient.getLogin(), is(oldClient.getLogin()));
        assertThat(newClient.getPassword(), is(oldClient.getPassword()));
    }

}
