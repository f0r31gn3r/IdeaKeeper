package lv.javaguru.java3.core.services.login;

//import lv.javaguru.java3.core.database.UserDAO;
//import lv.javaguru.java3.core.domain.user.AccessLevel;
//import lv.javaguru.java3.core.domain.user.User;
//import lv.javaguru.java3.core.services.users.*;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InOrder;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.runners.MockitoJUnitRunner;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.DisabledException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.util.MatcherAssertionErrors.assertThat;

/**
 * Created by Anna on 07.11.2015.
 */

//@RunWith(MockitoJUnitRunner.class)
//public class LoginServiceImplTest {
//
//    @Mock
//    private UserDAO userDAO;
//    @Mock private UserValidator userValidator;
//
//    @InjectMocks
//    private UserFactory userFactory = new UserFactoryImpl();
//    @InjectMocks	private UserService userService = new UserServiceImpl();
//    @InjectMocks	private LoginService loginService = new LoginServiceImpl();
//
//    private static final String LOGIN = "login";
//    private static final String PASSWORD = "password";
//    private static final String INCORRECTPASSWORD = "incorrectPassword";
//    private static final String NAME = "name";
//    private static final String SURNAME = "surname";
//    private static final String EMAIL = "email@email.lv";
//    private static final String ACCESSLEVEL = AccessLevel.USER.name();
//    private static final String BLOCKEDACCESS = AccessLevel.BLOCKED.name();
//
//    @Test
//    public void testAuthenticateShouldBeSuccessful() {
//
//        Authentication auth = Mockito.mock(Authentication.class);
//
//        //creating user
//        User user = userFactory.create(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL);
//
//        //creating correct authentication for user
//        auth = new UsernamePasswordAuthenticationToken(LOGIN,PASSWORD);
//
//        //checking if login and pass of created user are
//        // the same as in correct authentication object
//        assertThat(auth.getName(), is(LOGIN));
//        assertThat(auth.getCredentials(), is(PASSWORD));
//
//        //mocking DAO to return created user from database
//        when(userDAO.getUserByLogin(LOGIN)).thenReturn(user);
//
//        //authenticating...
//        Authentication a = loginService.authenticate(auth);
//
//        //asserting that DAO method is called during authentication
//        InOrder inOrder = inOrder(userDAO);
//        inOrder.verify(userDAO).getUserByLogin(LOGIN);
//
//        //asserting correct authentication
//        assertThat(loginService.authenticate(auth), is(new UsernamePasswordAuthenticationToken(
//                        auth.getPrincipal(),
//                        auth.getCredentials(),
//                        loginService.getAuthorities(AccessLevel.USER.name())))
//        );
//        assertThat(a.getName(), is(user.getLogin()));
//        assertThat(a.getCredentials(), is(user.getPassword()));
//        assertThat(a.getAuthorities(), is(loginService.getAuthorities(user.getAccessLevel())));
//    }
//
//
//    //If user with such login doesn't exist
//    @Test(expected = NullPointerException.class)
//    public void testAuthenticateShouldFailLoginNotExist() {
//
//        Authentication auth = Mockito.mock(Authentication.class);
//
//        //creating authentication for user login, that doesn't exist
//        auth = new UsernamePasswordAuthenticationToken(LOGIN, PASSWORD);
//
//        //mocking DAO to return, that user with such login doesn't exist
//        when(userDAO.getUserByLogin(LOGIN)).thenReturn(null);
//
//        //trying to authenticate and waiting for NullPointerException
//        loginService.authenticate(auth);
//    }
//
//    //If password doesn't match
//    @Test(expected = BadCredentialsException.class)
//    public void testAuthenticateShouldFailLoginPassIncorrect() {
//        Authentication auth = Mockito.mock(Authentication.class);
//
//        User user = userFactory.create(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL);
//
//        auth = new UsernamePasswordAuthenticationToken(LOGIN, INCORRECTPASSWORD);
//        assertThat(auth.getName(), is(LOGIN));
//        assertThat(auth.getCredentials(), is(INCORRECTPASSWORD));
//
//        when(userDAO.getUserByLogin(LOGIN)).thenReturn(user);
//
//        //trying to authenticate and waiting for BadCredentialsException
//        loginService.authenticate(auth);
//
//        InOrder inOrder = inOrder(userDAO);
//        inOrder.verify(userDAO).getUserByLogin(LOGIN);
//    }
//
//    //if user is blocked
//    @Test(expected = DisabledException.class)
//    public void testAuthenticateShouldFailUserBlocked() {
//        Authentication auth = Mockito.mock(Authentication.class);
//
//        User user = userFactory.create(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, BLOCKEDACCESS);
//
//        auth = new UsernamePasswordAuthenticationToken(LOGIN, PASSWORD);
//        assertThat(auth.getName(), is(LOGIN));
//        assertThat(auth.getCredentials(), is(PASSWORD));
//
//        when(userDAO.getUserByLogin(LOGIN)).thenReturn(user);
//
//        //trying to login to blocked user
//        loginService.authenticate(auth);
//
//        InOrder inOrder = inOrder(userDAO);
//        inOrder.verify(userDAO).getUserByLogin(LOGIN);
//    }
//
//
//    @Test
//    //tests sessions attributes set by successful authentication
//    public void testOnAuthenticationSuccess() throws IOException, ServletException {
//
//        HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
//        HttpSession session = Mockito.mock(HttpSession.class);
//        when(req.getSession()).thenReturn(session);
//        HttpServletResponse resp = Mockito.mock(HttpServletResponse.class);
//        Authentication auth = Mockito.mock(Authentication.class);
//
//        User user = userFactory.create(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL);
//        Authentication successfulAuthentication = new UsernamePasswordAuthenticationToken(
//                user.getLogin(),
//                user.getPassword(),
//                loginService.getAuthorities(user.getAccessLevel()));
//        when(userDAO.getUserByLogin(LOGIN)).thenReturn(user);
//
//        loginService.onAuthenticationSuccess(req, resp, successfulAuthentication);
//
//        verify(session, times(1)).setAttribute("login", user.getLogin());
//        verify(session, times(1)).setAttribute("name", user.getName());
//        verify(session, times(1)).setAttribute("surname", user.getSurname());
//        verify(session, times(1)).setAttribute("email", user.getEmail());
//        verify(session, times(1)).setAttribute("access_level", loginService.getAuthorities(user.getAccessLevel()));
//        verify(session, times(0)).setAttribute("password", user.getPassword());
//    }
//
//    @Test
//    //tests sessions attributes set by authentication failed by incorrect login
//    public void testOnAuthenticationFailByPassword() throws IOException, ServletException {
//
//        HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
//        HttpSession session = Mockito.mock(HttpSession.class);
//        when(req.getSession()).thenReturn(session);
//        HttpServletResponse resp = Mockito.mock(HttpServletResponse.class);
//        Authentication auth = Mockito.mock(Authentication.class);
//
//        User user = userFactory.create(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL);
//        Authentication unsuccessfulAuthentication = new UsernamePasswordAuthenticationToken(
//                user.getLogin(),
//                INCORRECTPASSWORD,
//                loginService.getAuthorities(user.getAccessLevel()));
//
//        when(userDAO.getUserByLogin(LOGIN)).thenReturn(user);
//        when(session.getAttribute("login_attempts")).thenReturn("1");
//
//        loginService.onAuthenticationFailByPassword(req, resp, LOGIN);
//
//        verify(session, times(1)).setAttribute("login", user.getLogin());
//        verify(session, times(0)).setAttribute("name", user.getName());
//        verify(session, times(0)).setAttribute("surname", user.getSurname());
//        verify(session, times(0)).setAttribute("email", user.getEmail());
//        verify(session, times(0)).setAttribute("access_level", loginService.getAuthorities(user.getAccessLevel()));
//        verify(session, times(0)).setAttribute("password", user.getPassword());
//        verify(session, times(1)).setAttribute("login_attempts", "2");
//
//    }
//
//    @Test
//    public void testLoginShouldSuccess() throws IOException, ServletException {
//        HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
//        HttpSession session = Mockito.mock(HttpSession.class);
//        when(req.getSession()).thenReturn(session);
//        HttpServletResponse resp = Mockito.mock(HttpServletResponse.class);
//        Authentication auth = Mockito.mock(Authentication.class);
//
//        User user = userFactory.create(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL);
//        when(userDAO.getUserByLogin(LOGIN)).thenReturn(user);
//
//        auth = new UsernamePasswordAuthenticationToken(LOGIN, PASSWORD);
//
//        String loginMsg = loginService.login(req, resp, auth);
//        assertThat(loginMsg, is("Login attempt successful"));
//    }
//
//    @Test
//    public void testLoginShouldFailLoginPassIncorrect() throws IOException, ServletException {
//        HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
//        HttpSession session = Mockito.mock(HttpSession.class);
//        when(req.getSession()).thenReturn(session);
//        HttpServletResponse resp = Mockito.mock(HttpServletResponse.class);
//        Authentication auth = Mockito.mock(Authentication.class);
//
//        User user = userFactory.create(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL);
//        when(userDAO.getUserByLogin(LOGIN)).thenReturn(user);
//        when(session.getAttribute("login_attempts")).thenReturn("1");
//
//        auth = new UsernamePasswordAuthenticationToken(LOGIN, INCORRECTPASSWORD);
//
//        String loginMsg = loginService.login(req, resp, auth);
//        assertThat(loginMsg, is("Login or/and pass don't match"));
//    }
//
//    @Test
//    public void testLoginShouldFailUserBlocked() throws IOException, ServletException {
//        HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
//        HttpSession session = Mockito.mock(HttpSession.class);
//        when(req.getSession()).thenReturn(session);
//        HttpServletResponse resp = Mockito.mock(HttpServletResponse.class);
//        Authentication auth = Mockito.mock(Authentication.class);
//
//        User user = userFactory.create(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, BLOCKEDACCESS);
//        when(userDAO.getUserByLogin(LOGIN)).thenReturn(user);
//
//        auth = new UsernamePasswordAuthenticationToken(LOGIN, PASSWORD);
//
//        String loginMsg = loginService.login(req, resp, auth);
//        assertThat(loginMsg, is("User is blocked"));
//    }
//
//    @Test
//    public void testLoginShouldFailLoginNotExist() throws IOException, ServletException {
//        HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
//        HttpSession session = Mockito.mock(HttpSession.class);
//        when(req.getSession()).thenReturn(session);
//        HttpServletResponse resp = Mockito.mock(HttpServletResponse.class);
//        Authentication auth = Mockito.mock(Authentication.class);
//
//        auth = new UsernamePasswordAuthenticationToken(LOGIN, PASSWORD);
//
//        String loginMsg = loginService.login(req, resp, auth);
//        assertThat(loginMsg, is("User login doesn't exist"));
//    }
//
//
//}
