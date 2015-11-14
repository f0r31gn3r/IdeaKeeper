package lv.javaguru.java3.core.services.attempts;

/**
 * Created by Anna on 02.11.2015.
 */
import lv.javaguru.java3.core.database.AttemptDAO;
import lv.javaguru.java3.core.domain.attempt.Attempt;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class AttemptFactoryImplTest {

    //@Mock private AttemptValidator attemptValidator;
    @Mock private AttemptDAO attemptDAO;

//    @Mock
//    HttpServletRequest request;
//
//    @Autowired
//    @Mock
//    private AuthenticationManager authenticationManager;


    @InjectMocks
    private AttemptFactory attemptFactory = new AttemptFactoryImpl();

    private static final Long USERID = 5L;
    private static final String LOGIN = "login";
    private static final int ATTEMPTS = 2;
    private static final Date LASTMOD = new Date();

    @Test
    public void createShouldReturnNewClient() {
        Attempt attempt = attemptFactory.create(USERID, LOGIN, ATTEMPTS, LASTMOD);

        assertThat(attempt.getUserId(), is(USERID));
        assertThat(attempt.getLogin(), is(LOGIN));
        assertThat(attempt.getAttempts(), is(ATTEMPTS));
        assertThat(attempt.getLastModified(), is(LASTMOD));
    }

//    @Test
//    public void authentificationShouldBeOK() {
//        Attempt attempt = attemptFactory.create(USERID, LOGIN, ATTEMPTS, LASTMOD);
//        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(LOGIN, "stubpass");
//
//        Authentication authentication = authenticationManager.authenticate(authRequest);
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        securityContext.setAuthentication(authentication);
//
//        attemptFactory.authenticate(authentication);
//
//        assertThat(attempt.getAttempts(), is(0));
//    }
}
