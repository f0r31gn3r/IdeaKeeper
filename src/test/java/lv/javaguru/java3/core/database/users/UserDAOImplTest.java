package lv.javaguru.java3.core.database.users;

/**
 * Created by Anna on 27.10.2015.
 */

import lv.javaguru.java3.core.database.DatabaseCleaner;
import lv.javaguru.java3.core.domain.idea.Idea;
import lv.javaguru.java3.core.domain.user.AccessLevel;
import lv.javaguru.java3.core.domain.user.User;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import static lv.javaguru.java3.core.domain.idea.IdeaBuilder.createIdea;
import static lv.javaguru.java3.core.domain.user.UserBuilder.createUser;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;


public class UserDAOImplTest extends DatabaseHibernateTest {


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
    @Transactional
    public void testCreateUserWithAll() {
        User user = createUser()
                .withLogin(LOGIN)
                .withPassword(PASSWORD)
                .withName(NAME)
                .withSurname(SURNAME)
                .withEmail(EMAIL)
                .withAccessLevel(ACCESSLEVEL)
                .build();
        assertThat(user.getUserId(), is(nullValue()));
        userDAO.create(user);
        assertThat(user.getUserId(), is(notNullValue()));
    }

    @Test
    @Transactional
    public void testGetUserById() {
        User user = createUser()
                .withLogin(LOGIN)
                .withPassword(PASSWORD)
                .withName(NAME)
                .withSurname(SURNAME)
                .withEmail(EMAIL)
                .withAccessLevel(ACCESSLEVEL)
                .build();
        userDAO.create(user);
        User userFromDb = userDAO.getById(user.getUserId());
        assertThat(userFromDb, is(notNullValue()));
    }

    @Test
    @Transactional
    public void testGetUserByLogin() {
        User user = createUser()
                .withLogin(LOGIN)
                .withPassword(PASSWORD)
                .withName(NAME)
                .withSurname(SURNAME)
                .withEmail(EMAIL)
                .withAccessLevel(ACCESSLEVEL)
                .build();
        userDAO.create(user);
        assertThat(userDAO.getUserByLogin(LOGIN), is(notNullValue()));
    }

    @Test
    @Transactional
    public void testGetAll() {
        User user = createUser()
                .withLogin(LOGIN)
                .withPassword(PASSWORD)
                .withName(NAME)
                .withSurname(SURNAME)
                .withEmail(EMAIL)
                .withAccessLevel(ACCESSLEVEL)
                .build();
        userDAO.create(user);
        assertThat(userDAO.getAll().size(), is(1));
    }

    @Ignore
    @Test
    @Transactional
    public void testDeleteUser() {

        User user = createUser()
                .withLogin(LOGIN)
                .withPassword(PASSWORD)
                .withName(NAME)
                .withSurname(SURNAME)
                .withEmail(EMAIL)
                .withAccessLevel(ACCESSLEVEL)
                .build();
        userDAO.create(user);

        User userFromDb = userDAO.getById(user.getUserId());
        assertThat(userFromDb, is(notNullValue()));

        Idea idea = createIdea()
                .withTitle(TITLE)
                .withDescription(DESCRIPTION)
                .withUser(user)
                .build();
        ideaDAO.create(idea);

        Idea ideaFromDb = ideaDAO.getById(idea.getIdeaId());
        assertThat(ideaFromDb, is(notNullValue()));

        userDAO.delete(userFromDb);
        userFromDb = userDAO.getById(user.getUserId());
        ideaFromDb = ideaDAO.getById(idea.getIdeaId());

        assertThat(userFromDb, is(nullValue()));
        assertThat(ideaFromDb, is(nullValue()));
    }


}
