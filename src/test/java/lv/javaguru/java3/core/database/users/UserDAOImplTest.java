package lv.javaguru.java3.core.database.users;

/**
 * Created by Anna on 27.10.2015.
 */

import lv.javaguru.java3.core.database.DatabaseHibernateTest;
import lv.javaguru.java3.core.domain.user.AccessLevel;
import lv.javaguru.java3.core.domain.user.User;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.security.core.Authentication;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import static lv.javaguru.java3.core.domain.user.UserBuilder.createUser;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserDAOImplTest extends DatabaseHibernateTest {

    @Mock
    Authentication request;

    private static final String LOGIN = "login";
    private static final String INCORRECTLOGIN = "login1";
    private static final String PASSWORD = "password";
    private static final String INCORRECTPASSWORD = "password1";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String EMAIL = "email";
    private static final String ACCESSLEVEL = AccessLevel.USER.name();
    private static final int ALLOWEDATTEMPTS = 2;
    private static final int DISALLOWEDATTEMPTS = 5;

    @Test
    @Transactional
    public void testCreateUserWithAll() {
        User user = createUser()
                .withLogPas(LOGIN, PASSWORD)
                .withLogPasNamSur(LOGIN, PASSWORD, NAME, SURNAME)
                .withAll(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL)
                .build();
        assertThat(user.getUserId(), is(nullValue()));
        userDAO.create(user);
        assertThat(user.getUserId(), is(notNullValue()));
    }

    @Test
    @Transactional
    public void testGetUserById() {
        User user = createUser()
                .withLogPas(LOGIN, PASSWORD)
                .withLogPasNamSur(LOGIN, PASSWORD, NAME, SURNAME)
                .withAll(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL)
                .build();
        userDAO.create(user);
        User userFromDb = userDAO.getById(user.getUserId());
        assertThat(userFromDb, is(notNullValue()));
    }

    @Test
    @Transactional
    public void testGetUserByLogin() {
        User user = createUser()
                .withLogPas(LOGIN, PASSWORD)
                .withLogPasNamSur(LOGIN, PASSWORD, NAME, SURNAME)
                .withAll(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL)
                .build();
        userDAO.create(user);
        assertThat(userDAO.getUserByLogin(LOGIN), is(notNullValue()));
    }

    @Test
    @Transactional
    public void testGetAll() {
        User user = createUser()
                .withLogPas(LOGIN, PASSWORD)
                .withLogPasNamSur(LOGIN, PASSWORD, NAME, SURNAME)
                .withAll(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL)
                .build();
        userDAO.create(user);
        assertThat(userDAO.getAll().size(), is(1));
    }

//    @Test
//    @Transactional
//    public void testAuthenticateShouldBeOK() {
//        //request = new UsernamePasswordAuthenticationToken("login", new BCryptPasswordEncoder().encode("password"));
//        request = new UsernamePasswordAuthenticationToken(LOGIN, PASSWORD);
//        assertThat(request.getName(), is(LOGIN));
//        //assertThat(request.getCredentials(), is(new BCryptPasswordEncoder().encode("password")));
//        assertThat(request.getCredentials(), is(PASSWORD));
//
//        User user = createUser()
//                .withAll(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL)
//                .build();
//        userDAO.create(user);
//        User userFromDb = userDAO.getById(user.getUserId());
//        assertThat(userFromDb.getLogin(), is(LOGIN));
//        assertThat(userFromDb, is(notNullValue()));
//
//        Attempt attempt = createAttempt()
//                .withAll(user.getUserId(), user.getLogin(), 1, new Date())
//                .build();
//        attemptDAO.create(attempt);
//        assertThat(attempt.getAttemptId(), is(notNullValue()));
//        assertThat(attempt.getLogin(), is(LOGIN));
//
//        Authentication result = userDAO.authenticate(request);
//        assertThat(result.getName(), is(LOGIN));
//
//    }
//
//    @Test (expected=BadCredentialsException.class)
//    @Transactional
//    public void testAuthenticateShouldFail() {
//        request = new UsernamePasswordAuthenticationToken(LOGIN, INCORRECTPASSWORD);
//        assertThat(request.getName(), is(LOGIN));
//        assertThat(request.getCredentials(), is(INCORRECTPASSWORD));
//
//        User user = createUser()
//                .withAll(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL)
//                .build();
//        userDAO.create(user);
//        User userFromDb = userDAO.getById(user.getUserId());
//        assertThat(userFromDb.getLogin(), is(LOGIN));
//        assertThat(userFromDb, is(notNullValue()));
//
//        Attempt attempt = createAttempt()
//                .withAll(user.getUserId(), user.getLogin(), 1, new Date())
//                .build();
//        attemptDAO.create(attempt);
//        assertThat(attempt.getAttemptId(), is(notNullValue()));
//        assertThat(attempt.getLogin(), is(LOGIN));
//
//        Authentication result = userDAO.authenticate(request);
//    }
//
//    @Test (expected=DisabledException.class)
//    @Transactional
//    public void testAuthenticateShouldBlock() {
//        request = new UsernamePasswordAuthenticationToken(LOGIN, PASSWORD);
//        assertThat(request.getName(), is(LOGIN));
//        assertThat(request.getCredentials(), is(PASSWORD));
//
//        User user = createUser()
//                .withAll(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL)
//                .build();
//        userDAO.create(user);
//        User userFromDb = userDAO.getById(user.getUserId());
//        assertThat(userFromDb.getLogin(), is(LOGIN));
//        assertThat(userFromDb, is(notNullValue()));
//
//        Attempt attempt = createAttempt()
//                .withAll(user.getUserId(), user.getLogin(), 5, new Date())
//                .build();
//        attemptDAO.create(attempt);
//        assertThat(attempt.getAttemptId(), is(notNullValue()));
//        assertThat(attempt.getLogin(), is(LOGIN));
//
//        Authentication result = userDAO.authenticate(request);
//        assertThat(user.getAccessLevel(), is(AccessLevel.BLOCKED.name()));
//    }

//    @Test
//    @Transactional
//    public void loginShouldSuccessWithPreviousAttempts() {
//
//        User user = createUser()
//                .withAll(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL)
//                .build();
//        userDAO.create(user);
//
//        Attempt attempt = createAttempt()
//                .withAll(user.getUserId(), user.getLogin(), ALLOWEDATTEMPTS, new Date())
//                .build();
//        attemptDAO.create(attempt);
//
//        assertThat(attemptDAO.getAttemptByUserLogin(LOGIN).getAttempts(), is(ALLOWEDATTEMPTS));
//        String msg = userDAO.login(LOGIN, PASSWORD);
//        assertThat(msg, is("Login attempt successful"));
//        assertThat(attemptDAO.getAttemptByUserLogin(LOGIN).getAttempts(), is(0));
//    }
//
//    @Test
//    @Transactional
//    public void loginShouldSuccessWithoutPreviousAttempts() {
//
//        User user = createUser()
//                .withAll(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL)
//                .build();
//        userDAO.create(user);
//        assertThat(attemptDAO.getAttemptByUserLogin(LOGIN), is(nullValue()));
//
//        String msg = userDAO.login(LOGIN, PASSWORD);
//        assertThat(msg, is("Login attempt successful"));
//        assertThat(attemptDAO.getAttemptByUserLogin(LOGIN).getAttempts(), is(0));
//    }
//
//    @Test
//    @Transactional
//    public void loginShouldFailWithPreviousAttempts() {
//
//        User user = createUser()
//                .withAll(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL)
//                .build();
//        userDAO.create(user);
//
//        Attempt attempt = createAttempt()
//                .withAll(user.getUserId(), user.getLogin(), ALLOWEDATTEMPTS, new Date())
//                .build();
//        attemptDAO.create(attempt);
//
//        String msg = userDAO.login(LOGIN, INCORRECTPASSWORD);
//        assertThat(msg, is("Login and pass don't match"));
//        assertThat(attemptDAO.getAttemptByUserLogin(LOGIN).getAttempts(), is(ALLOWEDATTEMPTS+1));
//    }
//
//    @Test
//    @Transactional
//    public void loginShouldFailWithoutPreviousAttempts() {
//
//        User user = createUser()
//                .withAll(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL)
//                .build();
//        userDAO.create(user);
//
//        String msg = userDAO.login(LOGIN, INCORRECTPASSWORD);
//        assertThat(msg, is("Login and pass don't match"));
//        assertThat(attemptDAO.getAttemptByUserLogin(LOGIN).getAttempts(), is(1));
//    }
//
//    @Test
//    @Transactional
//    public void loginShouldBlockUser() {
//
//        User user = createUser()
//                .withAll(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL)
//                .build();
//        userDAO.create(user);
//
//        Attempt attempt = createAttempt()
//                .withAll(user.getUserId(), user.getLogin(), DISALLOWEDATTEMPTS, new Date())
//                .build();
//        attemptDAO.create(attempt);
//
//        String msg = userDAO.login(LOGIN, INCORRECTPASSWORD);
//        assertThat(msg, is("User is blocked"));
//        //assertThat(attemptDAO.getAttemptByUserLogin(LOGIN).getAttempts(), is(1));
//    }
//
//    @Test
//    @Transactional
//    public void loginShouldFailWithIncorrectLoginName() {
//
//        User user = createUser()
//                .withAll(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL)
//                .build();
//        userDAO.create(user);
//
//        String msg = userDAO.login(INCORRECTLOGIN, PASSWORD);
//        assertThat(msg, is("User with such login doesn't exist"));
//        //assertThat(attemptDAO.getAttemptByUserLogin(LOGIN).getAttempts(), is(1));
//    }

}
