package lv.javaguru.java3.core.database.attempts;

/**
 * Created by Anna on 02.11.2015.
 */

import lv.javaguru.java3.core.database.DatabaseCleaner;
import lv.javaguru.java3.core.domain.attempt.Attempt;
import lv.javaguru.java3.core.domain.user.AccessLevel;
import lv.javaguru.java3.core.domain.user.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

import static lv.javaguru.java3.core.domain.attempt.AttemptBuilder.createAttempt;
import static lv.javaguru.java3.core.domain.user.UserBuilder.createUser;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class AttemptDAOImplTest extends DatabaseHibernateTest {

    private  SimpleDateFormat sdf =  new SimpleDateFormat ("dd.MM.yyyy hh:mm:ss");
    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String EMAIL = "email@email.lv";
    private static final String ACCESSLEVEL = AccessLevel.USER.name();

    @Before
    public void setUp() throws Exception {
        databaseCleaner.cleanDatabase();
    }

    @Test
    @Transactional
    public void testCreateAttempt() {

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

        attempt.setUser(user);
        user.setAttempt(attempt);
        attemptDAO.create(attempt);
        userDAO.create(user);

        assertThat(attempt.getAttemptId(), is(notNullValue()));
    }

    @Test
    @Transactional
    public void testGetAttemptById() {
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

        attempt.setUser(user);
        user.setAttempt(attempt);
        attemptDAO.create(attempt);
        userDAO.create(user);
        Attempt attemptFromDb = attemptDAO.getById(attempt.getAttemptId());
        assertThat(attemptFromDb, is(notNullValue()));
    }

    @Test
    @Transactional
    public void testDeleteAttempt() {
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

        attempt.setUser(user);
        user.setAttempt(attempt);
        attemptDAO.create(attempt);
        userDAO.create(user);
        Attempt attemptFromDb = attemptDAO.getById(attempt.getAttemptId());
        assertThat(attemptFromDb, is(notNullValue()));
        attemptDAO.delete(attemptFromDb);
        attemptFromDb = attemptDAO.getById(attempt.getAttemptId());
        assertThat(attemptFromDb, is(nullValue()));
    }
}
