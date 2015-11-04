package lv.javaguru.java3.core.database.users;

/**
 * Created by Anna on 27.10.2015.
 */

import lv.javaguru.java3.core.database.DatabaseHibernateTest;
import lv.javaguru.java3.core.domain.attempt.Attempt;
import lv.javaguru.java3.core.domain.user.AccessLevel;
import lv.javaguru.java3.core.domain.user.User;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static lv.javaguru.java3.core.domain.attempt.AttemptBuilder.createAttempt;
import static lv.javaguru.java3.core.domain.user.UserBuilder.createUser;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserDAOImplTest extends DatabaseHibernateTest {

    @Mock
    Authentication request;

    @Test
    @Transactional
    public void testCreateUserWithAll() {
        User user = createUser()
                .withLogPas("login", "password")
                .withLogPasNamSur("login", "password", "name", "surname")
                .withAll("login", "password", "name", "surname", "email", "access")
                .build();
        assertThat(user.getUserId(), is(nullValue()));
        userDAO.create(user);
        assertThat(user.getUserId(), is(notNullValue()));
    }

    @Test
    @Transactional
    public void testGetUserById() {
        User user = createUser()
                .withLogPas("login", "password")
                .withLogPasNamSur("login", "password", "name", "surname")
                .withAll("login", "password", "name", "surname", "email", "access")
                .build();
        userDAO.create(user);
        User userFromDb = userDAO.getById(user.getUserId());
        assertThat(userFromDb, is(notNullValue()));
    }

    @Test
    @Transactional
    public void testGetByLogin() {
        User user = createUser()
                .withLogPas("login", "password")
                .withLogPasNamSur("login", "password", "name", "surname")
                .withAll("login", "password", "name", "surname", "email", "access")
                .build();
        userDAO.create(user);
        assertThat(userDAO.getByLogin("login"), is(notNullValue()));
    }

    @Test
    @Transactional
    public void testGetAll() {
        User user = createUser()
                .withLogPas("login", "password")
                .withLogPasNamSur("login", "password", "name", "surname")
                .withAll("login", "password", "name", "surname", "email", "access")
                .build();
        userDAO.create(user);
        assertThat(userDAO.getAll().size(), is(1));
    }

    @Test
    @Transactional
    public void testAuthenticateShouldBeOK() {
        //request = new UsernamePasswordAuthenticationToken("login", new BCryptPasswordEncoder().encode("password"));
        request = new UsernamePasswordAuthenticationToken("login", "password");
        assertThat(request.getName(), is("login"));
        //assertThat(request.getCredentials(), is(new BCryptPasswordEncoder().encode("password")));
        assertThat(request.getCredentials(), is("password"));

        User user = createUser()
                .withAll("login", "password", "name", "surname", "email", AccessLevel.USER.name())
                .build();
        userDAO.create(user);
        User userFromDb = userDAO.getById(user.getUserId());
        assertThat(userFromDb.getLogin(), is("login"));
        assertThat(userFromDb, is(notNullValue()));

        Attempt attempt = createAttempt()
                .withAll(user.getUserId(), user.getLogin(), 1, new Date())
                .build();
        attemptDAO.create(attempt);
        assertThat(attempt.getAttemptId(), is(notNullValue()));
        assertThat(attempt.getLogin(), is("login"));

        Authentication result = userDAO.authenticate(request);
        assertThat(result.getName(), is("login"));

    }

    @Test (expected=BadCredentialsException.class)
    @Transactional
    public void testAuthenticateShouldFail() {
        request = new UsernamePasswordAuthenticationToken("login", "password123");
        assertThat(request.getName(), is("login"));
        assertThat(request.getCredentials(), is("password123"));

        User user = createUser()
                .withAll("login", "password", "name", "surname", "email", AccessLevel.USER.name())
                .build();
        userDAO.create(user);
        User userFromDb = userDAO.getById(user.getUserId());
        assertThat(userFromDb.getLogin(), is("login"));
        assertThat(userFromDb, is(notNullValue()));

        Attempt attempt = createAttempt()
                .withAll(user.getUserId(), user.getLogin(), 1, new Date())
                .build();
        attemptDAO.create(attempt);
        assertThat(attempt.getAttemptId(), is(notNullValue()));
        assertThat(attempt.getLogin(), is("login"));

        Authentication result = userDAO.authenticate(request);
    }

    @Test (expected=DisabledException.class)
    @Transactional
    public void testAuthenticateShouldBlock() {
        request = new UsernamePasswordAuthenticationToken("login", "password");
        assertThat(request.getName(), is("login"));
        assertThat(request.getCredentials(), is("password"));

        User user = createUser()
                .withAll("login", "password", "name", "surname", "email", AccessLevel.USER.name())
                .build();
        userDAO.create(user);
        User userFromDb = userDAO.getById(user.getUserId());
        assertThat(userFromDb.getLogin(), is("login"));
        assertThat(userFromDb, is(notNullValue()));

        Attempt attempt = createAttempt()
                .withAll(user.getUserId(), user.getLogin(), 5, new Date())
                .build();
        attemptDAO.create(attempt);
        assertThat(attempt.getAttemptId(), is(notNullValue()));
        assertThat(attempt.getLogin(), is("login"));

        Authentication result = userDAO.authenticate(request);
    }

}
