package lv.javaguru.java3.core.database.clients;

import lv.javaguru.java3.core.database.DatabaseCleaner;
import lv.javaguru.java3.core.domain.Client;
import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import static lv.javaguru.java3.core.domain.ClientBuilder.createClient;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class ClientDAOImplTest extends DatabaseHibernateTest {

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    @Before
    public void setUp() throws Exception {
        databaseCleaner.cleanDatabase();
    }

    @Test
    @Transactional
    public void testCreateClient() {
        Client client = createClient()
                .withLogin("login")
                .withPassword("password").build();
        assertThat(client.getId(), is(nullValue()));
        clientDAO.create(client);
        assertThat(client.getId(), is(notNullValue()));
    }

    @Test
    @Transactional
    public void testGetClientById() {
        Client client = createClient()
                .withLogin("login")
                .withPassword("password").build();
        clientDAO.create(client);
        Client clientFromDb = clientDAO.getById(client.getId());
        assertThat(clientFromDb, is(notNullValue()));
    }

    @Test
    @Transactional
    public void testGetAllClients() {
        Client client = createClient()
                .withLogin("login")
                .withPassword("password").build();
        clientDAO.create(client);
        assertThat(clientDAO.getAll().size(), is(1));
    }

    @Test
    @Transactional
    public void testDeleteClient() {
        Client client = createClient()
                .withLogin("login")
                .withPassword("password").build();
        clientDAO.create(client);
        Client clientFromDb = clientDAO.getById(client.getId());
        assertThat(clientFromDb, is(notNullValue()));
        clientDAO.delete(clientFromDb);
        clientFromDb = clientDAO.getById(client.getId());
        assertThat(clientFromDb, is(nullValue()));

    }

}