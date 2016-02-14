package lv.javaguru.java3.core.rest.ideas;

import lv.javaguru.java3.core.commands.users.UserConverter;
import lv.javaguru.java3.core.database.DatabaseCleaner;
import lv.javaguru.java3.core.database.IdeaDAO;
import lv.javaguru.java3.core.database.UserDAO;
import lv.javaguru.java3.core.domain.user.AccessLevel;
import lv.javaguru.java3.core.dto.idea.IdeaDTO;
import lv.javaguru.java3.core.dto.user.UserDTO;
import lv.javaguru.java3.core.rest.RESTResourceTest;
import lv.javaguru.java3.core.services.ideas.IdeaFactory;
import lv.javaguru.java3.core.services.ideas.IdeaFactoryImpl;
import lv.javaguru.java3.core.services.users.UserFactory;
import lv.javaguru.java3.core.services.users.UserFactoryImpl;
import lv.javaguru.java3.core.services.users.UserValidator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import static lv.javaguru.java3.core.dto.idea.IdeaDTOBuilder.createIdeaDTO;
import static lv.javaguru.java3.core.dto.user.UserDTOBuilder.createUserDTO;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by Anna on 22.11.2015.
 */
public class IdeaResourceImplTest extends RESTResourceTest {

    @Mock   private IdeaDAO ideaDAO;
    @Mock   private UserValidator userValidator;
    @Mock   private UserDAO userDAO;

    @InjectMocks    private UserFactory userFactory = new UserFactoryImpl();
    @InjectMocks    private IdeaFactory ideaFactory = new IdeaFactoryImpl();
    @Autowired      UserConverter userConverter;

    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String EMAIL = "email@email.lv";
    private static final String ACCESSLEVEL = AccessLevel.USER.name();

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    @Before
    public void setUp() throws Exception {
        databaseCleaner.cleanDatabase();
    }

    @Test
    public void createIdeaTest() {

        UserDTO user = userResource.create(
                createUserDTO()
                        .withLogin(LOGIN)
                        .withPassword(PASSWORD)
                        .withName(NAME)
                        .withSurname(SURNAME)
                        .withEmail(EMAIL)
                        .withAccessLevel(ACCESSLEVEL)
                        .build()
        );
        assertThat(user, is(notNullValue()));
        assertThat(user.getUserId(), is(notNullValue()));

        IdeaDTO ideaDTO = ideaResource.create(user.getUserId(),
                createIdeaDTO()
                        .withTitle(TITLE)
                        .withDescription(DESCRIPTION)
                        .withUserDTO(user)
                        .build()
        );
        assertThat(ideaDTO, is(notNullValue()));
    }

    @Test
    public void getIdeaTest() {

        UserDTO user = userResource.create(
                createUserDTO()
                        .withLogin(LOGIN)
                        .withPassword(PASSWORD)
                        .withName(NAME)
                        .withSurname(SURNAME)
                        .withEmail(EMAIL)
                        .withAccessLevel(ACCESSLEVEL)
                        .build()
        );
        assertThat(user, is(notNullValue()));
        assertThat(user.getUserId(), is(notNullValue()));

        IdeaDTO newIdea = ideaResource.create(user.getUserId(),
                createIdeaDTO()
                        .withTitle(TITLE)
                        .withDescription(DESCRIPTION)
                        .withUserDTO(user)
                        .build()
        );
        assertThat(newIdea, is(notNullValue()));
        IdeaDTO oldIdea = ideaResource.get(newIdea.getIdeaId());
        assertThat(newIdea.getIdeaId(), is(oldIdea.getIdeaId()));
    }

}
