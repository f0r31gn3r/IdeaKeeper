package lv.javaguru.java3.core.services.authentication;

/**
 * Created by Anna on 01.12.2015.
 */
import lv.javaguru.java3.core.database.AttemptDAO;
import lv.javaguru.java3.core.database.UserDAO;
import lv.javaguru.java3.core.domain.attempt.Attempt;
import lv.javaguru.java3.core.domain.user.AccessLevel;
import lv.javaguru.java3.core.domain.user.User;
import lv.javaguru.java3.core.services.attempts.AttemptFactory;
import lv.javaguru.java3.core.services.attempts.AttemptFactoryImpl;
import lv.javaguru.java3.core.services.attempts.AttemptService;
import lv.javaguru.java3.core.services.users.UserFactory;
import lv.javaguru.java3.core.services.users.UserFactoryImpl;
import lv.javaguru.java3.core.services.users.UserService;
import lv.javaguru.java3.core.services.users.UserValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationServiceImplTest {

    @Mock    private UserValidator userValidator;
    @Mock    private UserDAO userDAO;
    @Mock    private AttemptDAO attemptDAO;
    @Mock    private UserService userService;
    @Mock	 private AttemptService attemptService;

    @InjectMocks    private AuthenticationService authenticationService = new AuthenticationServiceImpl();
    @InjectMocks   private AttemptFactory attemptFactory = new AttemptFactoryImpl();
    @InjectMocks    private UserFactory userFactory = new UserFactoryImpl();

    private static final String LOGIN = "login";
    private static final String FALSE_LOGIN = "login1";
    private static final String PASSWORD = "password";
    private static final String FALSE_PASSWORD = "password1";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String EMAIL = "email@email.lv";
    private static final String ACCESSLEVEL = AccessLevel.USER.name();

    private static final int ATTEMPTS = 1;
    private static final Date LASTMOD = new Date();

    @Before
    public void init(){
        authenticationService.setState("");
    }

    @Test
    public void getShouldReturnOK() {
        User user = userFactory.create(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL);
        doReturn(user)
                .when(userDAO).getById(user.getUserId());
        doReturn(user)
                .when(userService).get(user.getLogin());

        Attempt attempt = attemptFactory.create(LOGIN, ATTEMPTS, LASTMOD, user.getUserId());
        user.setAttempt(attempt);

        boolean authResult = authenticationService.authenticate(LOGIN, PASSWORD);
        String authState = authenticationService.getState();

        assertTrue(authResult);
        assertThat(authState, is(AuthenticationStatus.SUCCESSFUL_LOGIN.getValue()));
    }

    @Test
    public void getShouldFailOnPassIncorrect() {
        User user = userFactory.create(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL);
        doReturn(user)
                .when(userDAO).getById(user.getUserId());
        doReturn(user)
                .when(userService).get(user.getLogin());

        Attempt attempt = attemptFactory.create(LOGIN, ATTEMPTS, LASTMOD, user.getUserId());
        user.setAttempt(attempt);

        boolean authResult = authenticationService.authenticate(LOGIN, FALSE_PASSWORD);
        String authState = authenticationService.getState();

        assertFalse(authResult);
        assertThat(authState, is(AuthenticationStatus.PASS_FAILED.getValue()));
    }

    @Test
    public void getShouldFailOnBlockedUser() {
        User user = userFactory.create(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, AccessLevel.BLOCKED.name());
        doReturn(user)
                .when(userService).get(user.getLogin());

        boolean authResult = authenticationService.authenticate(LOGIN, PASSWORD);
        String authState = authenticationService.getState();

        assertFalse(authResult);
        assertThat(authState, is(AuthenticationStatus.BLOCKED.getValue()));
    }

    @Test
    public void getShouldFailOnMaxAttempts() {
        User user = userFactory.create(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL);
        doReturn(user)
                .when(userDAO).getById(user.getUserId());
        doReturn(user)
                .when(userService).get(user.getLogin());

        Attempt attempt = attemptFactory.create(LOGIN, 3, LASTMOD, user.getUserId());
        user.setAttempt(attempt);

        boolean authResult = authenticationService.authenticate(LOGIN, PASSWORD);
        String authState = authenticationService.getState();

        assertFalse(authResult);
        assertThat(authState, is(AuthenticationStatus.BLOCKED.getValue()));
    }

    @Test
    public void getShouldFailOnUsernameIncorrect() {
        doReturn(null)
                .when(userService).get(LOGIN);

        boolean authResult = authenticationService.authenticate(FALSE_LOGIN, PASSWORD);
        String authState = authenticationService.getState();

        assertFalse(authResult);
        assertThat(authState, is(AuthenticationStatus.USERNAME_FAILED.getValue()));
    }

}