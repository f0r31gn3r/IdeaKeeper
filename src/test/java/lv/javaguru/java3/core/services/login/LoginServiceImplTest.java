package lv.javaguru.java3.core.services.login;

import lv.javaguru.java3.core.database.UserDAO;
import lv.javaguru.java3.core.domain.user.AccessLevel;
import lv.javaguru.java3.core.domain.user.User;
import lv.javaguru.java3.core.services.users.*;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.MatcherAssertionErrors.assertThat;

/**
 * Created by Anna on 07.11.2015.
 */

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceImplTest {

    @Mock    private UserDAO userDAO;
    @Mock    private UserValidator userValidator;
    @Mock    private Authentication auth;
    //@Mock    private HttpServletRequest request;
    @Mock    private HttpServletResponse response;

    @InjectMocks    private UserFactory userFactory = new UserFactoryImpl();
    @InjectMocks    private UserService userService = new UserServiceImpl();
    @InjectMocks    private LoginService loginService = new LoginServiceImpl();

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String INCORRECTPASSWORD = "incorrectPassword";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String EMAIL = "email@email.lv";
    private static final String ACCESSLEVEL = AccessLevel.USER.name();
    private static final String BLOCKEDACCESS = AccessLevel.BLOCKED.name();


    @Test
    public void testAuthenticateShouldBeSuccessful() {

        //creating user
        User user = userFactory.create(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL);

        //creating correct authentication for user
        auth = new UsernamePasswordAuthenticationToken(LOGIN,PASSWORD);

        //checking if login and pass of created user are
        // the same as in correct authentication object
        assertThat(auth.getName(), is(LOGIN));
        assertThat(auth.getCredentials(), is(PASSWORD));

        //mocking DAO to return created user from database
        when(userDAO.getUserByLogin(LOGIN)).thenReturn(user);

        //authenticating...
        Authentication a = loginService.authenticate(auth);

        //asserting that DAO method is called during authentication
        InOrder inOrder = inOrder(userDAO);
        inOrder.verify(userDAO).getUserByLogin(LOGIN);

        //asserting correct authentication
        assertThat(loginService.authenticate(auth), is(new UsernamePasswordAuthenticationToken(
                auth.getPrincipal(),
                auth.getCredentials(),
                loginService.getAuthorities(AccessLevel.USER.name())))
                );
        assertThat(a.getName(), is(user.getLogin()));
        assertThat(a.getCredentials(), is(user.getPassword()));
        assertThat(a.getAuthorities(), is(loginService.getAuthorities(user.getAccessLevel())));

    }


    @Test(expected = NullPointerException.class)
    public void testAuthenticateShouldFailLoginNotExist() {

        //creating authentication for user login, that doesn't exist
        auth = new UsernamePasswordAuthenticationToken(LOGIN, PASSWORD);

        //mocking DAO to return, that user with such login doesn't exist
        when(userDAO.getUserByLogin(LOGIN)).thenReturn(null);

        //trying to authenticate and waiting for NullPointerException
        loginService.authenticate(auth);
    }

    //if password doesn't match
    @Test(expected = BadCredentialsException.class)
    public void testAuthenticateShouldFailLoginPassIncorrect() {

        User user = userFactory.create(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL);

        auth = new UsernamePasswordAuthenticationToken(LOGIN, INCORRECTPASSWORD);
        assertThat(auth.getName(), is(LOGIN));
        assertThat(auth.getCredentials(), is(INCORRECTPASSWORD));

        when(userDAO.getUserByLogin(LOGIN)).thenReturn(user);

        //trying to authenticate and waiting for BadCredentialsException
        loginService.authenticate(auth);

        InOrder inOrder = inOrder(userDAO);
        inOrder.verify(userDAO).getUserByLogin(LOGIN);
    }

    //if user is blocked
    @Test(expected = DisabledException.class)
    public void testAuthenticateShouldFailUserBlocked() {

        User user = userFactory.create(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, BLOCKEDACCESS);

        auth = new UsernamePasswordAuthenticationToken(LOGIN, PASSWORD);
        assertThat(auth.getName(), is(LOGIN));
        assertThat(auth.getCredentials(), is(PASSWORD));

        when(userDAO.getUserByLogin(LOGIN)).thenReturn(user);

        //trying to login to blocked user
        loginService.authenticate(auth);

        InOrder inOrder = inOrder(userDAO);
        inOrder.verify(userDAO).getUserByLogin(LOGIN);
    }

    @Ignore
    @Test
    //tests sessions attributes set by successful authentication
    public void testOnAuthenticationSuccess() throws IOException, ServletException {

        HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(req.getSession()).thenReturn(session);

        User user = userFactory.create(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, BLOCKEDACCESS);
        Authentication successfulAuthentication = new UsernamePasswordAuthenticationToken(
                                                                user.getLogin(),
                                                                user.getPassword(),
                                                                loginService.getAuthorities(user.getAccessLevel()));
        when(userDAO.getUserByLogin(LOGIN)).thenReturn(user);

        loginService.onAuthenticationSuccess(req, response, successfulAuthentication);

        assertThat(req.getSession().getAttribute("login"), is(user.getLogin()));
    }

    @Test
    public void testLoginShouldSuccess() throws IOException, ServletException {

        HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(req.getSession()).thenReturn(session);

        User user = userFactory.create(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL);
        when(userDAO.getUserByLogin(LOGIN)).thenReturn(user);

        auth = new UsernamePasswordAuthenticationToken(LOGIN, PASSWORD);

        String loginMsg = loginService.login(req, response, auth);
        assertThat(loginMsg, is("Login attempt successful"));

        //TODO: test session attributes
    }

    @Ignore
    @Test
    public void testLoginShouldFailLoginPassIncorrect() throws IOException, ServletException {

        HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(req.getSession()).thenReturn(session);

        User user = userFactory.create(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL);
        when(userDAO.getUserByLogin(LOGIN)).thenReturn(user);

        auth = new UsernamePasswordAuthenticationToken(LOGIN, INCORRECTPASSWORD);

        Mockito.when(req.getSession().getAttribute("login_attempts")).thenReturn(1);
        String loginMsg = loginService.login(req, response, auth);
        assertThat(loginMsg, is("Login or/and pass don't match"));

        //TODO: test session attributes
    }

    @Test
    public void testLoginShouldFailUserBlocked() throws IOException, ServletException {

        HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(req.getSession()).thenReturn(session);

        User user = userFactory.create(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, BLOCKEDACCESS);
        when(userDAO.getUserByLogin(LOGIN)).thenReturn(user);

        auth = new UsernamePasswordAuthenticationToken(LOGIN, PASSWORD);

        String loginMsg = loginService.login(req, response, auth);
        assertThat(loginMsg, is("User is blocked"));

        //TODO: test session attributes
    }

    @Test
    public void testLoginShouldFailLoginNotExist() throws IOException, ServletException {

        HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(req.getSession()).thenReturn(session);

        auth = new UsernamePasswordAuthenticationToken(LOGIN, PASSWORD);

        String loginMsg = loginService.login(req, response, auth);
        assertThat(loginMsg, is("User login doesn't exist"));
    }



}
