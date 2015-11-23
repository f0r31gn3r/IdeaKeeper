package lv.javaguru.java3.core.database.users;

/**
 * Created by Anna on 27.10.2015.
 */

import lv.javaguru.java3.core.database.DatabaseCleaner;
import lv.javaguru.java3.core.domain.attempt.Attempt;
import lv.javaguru.java3.core.domain.idea.Idea;
import lv.javaguru.java3.core.domain.user.AccessLevel;
import lv.javaguru.java3.core.domain.user.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static lv.javaguru.java3.core.domain.attempt.AttemptBuilder.createAttempt;
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

        Idea idea = createIdea()
                .withTitle(TITLE)
                .withDescription(DESCRIPTION)
                .withUser(user)
                .build();

        Idea idea2 = createIdea()
                .withTitle(TITLE)
                .withDescription(DESCRIPTION)
                .withUser(user)
                .build();

        Attempt attempt = createAttempt()
                .withLogin(user.getLogin())
                .withAttempts(2)
                .withDate(new Date())
                .withUser(user)
                .build();

        Set<Idea> ideas = new HashSet<Idea>();

        ideas.add(idea);
        ideas.add(idea2);
        user.setIdeas(ideas);
        user.setAttempt(attempt);

        attemptDAO.create(attempt);
        ideaDAO.create(idea);
        ideaDAO.create(idea2);
        userDAO.create(user);

        User userFromDb = userDAO.getById(user.getUserId());
        userDAO.delete(userFromDb);

        userFromDb = userDAO.getById(user.getUserId());
        assertThat(userFromDb, is(nullValue()));

        Idea ideaFromDb = ideaDAO.getById(idea.getIdeaId());
        assertThat(ideaFromDb, is(nullValue()));
        Idea idea2FromDb = ideaDAO.getById(idea2.getIdeaId());
        assertThat(idea2FromDb, is(nullValue()));
        Attempt attemptFromDb = attemptDAO.getById(attempt.getAttemptId());
        assertThat(attemptFromDb, is(nullValue()));
    }

    @Test
    @Transactional
    public void testGetUserIdeas() {

        User user = createUser()
                .withLogin(LOGIN)
                .withPassword(PASSWORD)
                .withName(NAME)
                .withSurname(SURNAME)
                .withEmail(EMAIL)
                .withAccessLevel(ACCESSLEVEL)
                .build();

        Idea idea = createIdea()
                .withTitle(TITLE)
                .withDescription(DESCRIPTION)
                .withUser(user)
                .build();

        Idea idea2 = createIdea()
                .withTitle(TITLE)
                .withDescription(DESCRIPTION)
                .withUser(user)
                .build();

        Set<Idea> ideas = new HashSet<Idea>();

        ideas.add(idea);
        ideas.add(idea2);
        user.setIdeas(ideas);

        ideaDAO.create(idea);
        ideaDAO.create(idea2);
        userDAO.create(user);

        User userFromDb = userDAO.getById(user.getUserId());
        assertThat(userFromDb, is(notNullValue()));
        Idea ideaFromDb = ideaDAO.getById(idea.getIdeaId());
        assertThat(ideaFromDb, is(notNullValue()));
        Idea idea2FromDb = ideaDAO.getById(idea2.getIdeaId());
        assertThat(idea2FromDb, is(notNullValue()));

        Set<Idea> userIdeas = userFromDb.getIdeas();
        assertThat(userIdeas.size(), is(2));
    }

    @Test
    @Transactional
    public void testGetUserAttempts() {

        User user = createUser()
                .withLogin(LOGIN)
                .withPassword(PASSWORD)
                .withName(NAME)
                .withSurname(SURNAME)
                .withEmail(EMAIL)
                .withAccessLevel(ACCESSLEVEL)
                .build();

        Attempt attempt = createAttempt()
                .withLogin(user.getLogin())
                .withAttempts(2)
                .withDate(new Date())
                .withUser(user)
                .build();

        user.setAttempt(attempt);
        attemptDAO.create(attempt);
        userDAO.create(user);


        User userFromDb = userDAO.getById(user.getUserId());
        assertThat(userFromDb, is(notNullValue()));
        Attempt attemptFromDb = userFromDb.getAttempt();
        assertThat(attemptFromDb, is(notNullValue()));
    }


}
