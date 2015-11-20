package lv.javaguru.java3.core.services.attempts;

/**
 * Created by Anna on 04.11.2015.
 */

import lv.javaguru.java3.core.database.AttemptDAO;
import lv.javaguru.java3.core.database.UserDAO;
import lv.javaguru.java3.core.domain.attempt.Attempt;
import lv.javaguru.java3.core.domain.user.AccessLevel;
import lv.javaguru.java3.core.services.users.UserFactory;
import lv.javaguru.java3.core.services.users.UserFactoryImpl;
import lv.javaguru.java3.core.services.users.UserValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.inOrder;

@RunWith(MockitoJUnitRunner.class)
public class AttemptServiceImplTest {

    @Mock
    private AttemptDAO attemptDAO;
    @Mock
    private UserValidator userValidator;
    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private AttemptFactory attemptFactory = new AttemptFactoryImpl();
    @InjectMocks
    private AttemptService attemptService = new AttemptServiceImpl();
    @InjectMocks
    private UserFactory userFactory = new UserFactoryImpl();

    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String EMAIL = "email@email.lv";
    private static final String ACCESSLEVEL = AccessLevel.USER.name();


//    @Test
//    public void resetShouldResetFailedAttemptsCount() {
//        Attempt attempt = attemptFactory.create(LOGIN, ATTEMPTS, LASTMOD);
//
//        Attempt modAttempt = attemptService.resetFailAttempts(attempt);
//        assertThat(modAttempt.getLogin(), is(LOGIN));
//        assertThat(modAttempt.getAttempts(), is(0));
//        assertThat(modAttempt.getLastModified(), is(new Date()));
//    }
//
//    @Test
//    public void updateShouldIncreaseFailedAttemptsCount() {
//        Date refreshDate = new Date();
//        Attempt attempt = attemptFactory.create(LOGIN, ATTEMPTS, LASTMOD);
//
//        Attempt modAttempt = attemptService.updateFailAttempts(attempt);
//        assertThat(modAttempt.getLogin(), is(LOGIN));
//        assertThat(modAttempt.getAttempts(), is(ATTEMPTS + 1));
//        assertThat(modAttempt.getLastModified(), is(refreshDate));
//    }

//    @Test
//    public void updateShouldCreateNewAttempt() {
//        Date refreshDate = new Date();
//
//        Attempt modAttempt = attemptService.updateFailAttempts(null);
//        assertThat(modAttempt.getAttempts(), is(1));
//        assertThat(modAttempt.getLastModified(), is(refreshDate));
//    }

//    @Test(expected=LockedException.class)
//    public void updateShouldBlockUser() {
//        Attempt attempt = attemptFactory.create(USERID, LOGIN, 5, LASTMOD);
//        Attempt modAttempt = attemptService.updateFailAttempts(attempt);
//    }

    @Test
    public void getShouldReturnCreatedAttempt() {
        Long attemptId = 123L;
        Attempt attempt = attemptService.get(attemptId);

        InOrder inOrder = inOrder(attemptDAO);
        inOrder.verify(attemptDAO).getRequired(attemptId);
    }
}
