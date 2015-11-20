package lv.javaguru.java3.core.services.attempts;

/**
 * Created by Anna on 02.11.2015.
 */

import lv.javaguru.java3.core.database.AttemptDAO;
import lv.javaguru.java3.core.database.UserDAO;
import lv.javaguru.java3.core.domain.attempt.Attempt;
import lv.javaguru.java3.core.domain.user.AccessLevel;
import lv.javaguru.java3.core.domain.user.User;
import lv.javaguru.java3.core.services.users.UserFactory;
import lv.javaguru.java3.core.services.users.UserFactoryImpl;
import lv.javaguru.java3.core.services.users.UserValidator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class AttemptFactoryImplTest {

    @Mock private AttemptDAO attemptDAO;
    @Mock   private UserValidator userValidator;
    @Mock   private UserDAO userDAO;
    @InjectMocks    private UserFactory userFactory = new UserFactoryImpl();
    @InjectMocks    private AttemptFactory attemptFactory = new AttemptFactoryImpl();

    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String EMAIL = "email@email.lv";
    private static final String ACCESSLEVEL = AccessLevel.USER.name();

    private static final int ATTEMPTS = 2;
    private static final Date LASTMOD = new Date();

    @Test
    public void createShouldReturnNewClient() {
        User user = userFactory.create(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL);
        doReturn(user)
                .when(userDAO).getById(user.getUserId());

        Attempt attempt = attemptFactory.create(LOGIN, ATTEMPTS, LASTMOD, user.getUserId());

        assertThat(attempt.getLogin(), is(LOGIN));
        assertThat(attempt.getAttempts(), is(ATTEMPTS));
        assertThat(attempt.getLastModified(), is(LASTMOD));
    }
}
