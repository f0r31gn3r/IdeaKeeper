package lv.javaguru.java3.core.services.attempts;

/**
 * Created by Anna on 04.11.2015.
 */

import lv.javaguru.java3.core.database.AttemptDAO;
import lv.javaguru.java3.core.domain.attempt.Attempt;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.inOrder;

@RunWith(MockitoJUnitRunner.class)
public class AttemptServiceImplTest {

    //@Mock private AttemptValidator attemptValidator;
    @Mock private AttemptDAO attemptDAO;

    @InjectMocks
    private AttemptFactory attemptFactory = new AttemptFactoryImpl();
    @InjectMocks
    private AttemptService attemptService = new AttemptServiceImpl();

    private static final Long USERID = 5L;
    private static final String LOGIN = "login";
    private static final int ATTEMPTS = 1;
    private static final Date LASTMOD = new Date();



    @Test
    public void resetShouldResetFailedAttemptsCount() {
        Attempt attempt = attemptFactory.create(USERID, LOGIN, ATTEMPTS, LASTMOD);

        Attempt modAttempt = attemptService.resetFailAttempts(attempt);
        assertThat(modAttempt.getUserId(), is(USERID));
        assertThat(modAttempt.getLogin(), is(LOGIN));
        assertThat(modAttempt.getAttempts(), is(0));
        assertThat(modAttempt.getLastModified(), is(new Date()));
    }

    @Test
    public void updateShouldIncreaseFailedAttemptsCount() {
        Date refreshDate = new Date();
        Attempt attempt = attemptFactory.create(USERID, LOGIN, ATTEMPTS, LASTMOD);

        Attempt modAttempt = attemptService.updateFailAttempts(attempt);
        assertThat(modAttempt.getUserId(), is(USERID));
        assertThat(modAttempt.getLogin(), is(LOGIN));
        assertThat(modAttempt.getAttempts(), is(ATTEMPTS + 1));
        assertThat(modAttempt.getLastModified(), is(refreshDate));
    }

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
