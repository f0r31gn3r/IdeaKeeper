package lv.javaguru.java3.core.rest.attempts;

import lv.javaguru.java3.core.commands.users.UserConverter;
import lv.javaguru.java3.core.database.AttemptDAO;
import lv.javaguru.java3.core.database.DatabaseCleaner;
import lv.javaguru.java3.core.database.UserDAO;
import lv.javaguru.java3.core.domain.user.AccessLevel;
import lv.javaguru.java3.core.dto.attempt.AttemptDTO;
import lv.javaguru.java3.core.dto.user.UserDTO;
import lv.javaguru.java3.core.rest.RESTResourceTest;
import lv.javaguru.java3.core.services.attempts.AttemptFactory;
import lv.javaguru.java3.core.services.attempts.AttemptFactoryImpl;
import lv.javaguru.java3.core.services.users.UserFactory;
import lv.javaguru.java3.core.services.users.UserFactoryImpl;
import lv.javaguru.java3.core.services.users.UserValidator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;

import static lv.javaguru.java3.core.dto.attempt.AttemptDTOBuilder.createAttemptDTO;
import static lv.javaguru.java3.core.dto.user.UserDTOBuilder.createUserDTO;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class AttemptResourceImplTest extends RESTResourceTest {

    @Mock private AttemptDAO attemptDAO;
    @Mock   private UserValidator userValidator;
    @Mock   private UserDAO userDAO;
    @InjectMocks    private UserFactory userFactory = new UserFactoryImpl();
    @InjectMocks    private AttemptFactory attemptFactory = new AttemptFactoryImpl();
    @Autowired UserConverter userConverter;

    Calendar date = Calendar.getInstance();
    long t = date.getTimeInMillis();
    final long ONE_MINUTE_IN_MILLIS = 60000;


    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String EMAIL = "email@email.lv";
    private static final String ACCESSLEVEL = AccessLevel.USER.name();

    private static final int ATTEMPTS = 1;
    private static final Date LASTMOD = new Date();

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    @Before
    public void setUp() throws Exception {
        databaseCleaner.cleanDatabase();
    }

    @Test
    public void createAttemptTest() {

        UserDTO user = userResource.create(
                createUserDTO()
                        .withLogin(LOGIN)
                        .withPassword(PASSWORD)
                        .withName(NAME)
                        .withSurname(SURNAME)
                        .withEmail(EMAIL)
                        .withAccessLevel(ACCESSLEVEL)
                        .build()
        );
        assertThat(user, is(notNullValue()));
        assertThat(user.getUserId(), is(notNullValue()));

        AttemptDTO attemptDTO = attemptResource.create(
                createAttemptDTO()
                        .withLogin(user.getLogin())
                        .withAttempts(2)
                        .withDate(new Date())
                        .withUserDTO(user)
                        .build()
        );
        assertThat(attemptDTO, is(notNullValue()));
    }

    @Test
    public void getAttemptTest() {

        UserDTO user = userResource.create(
                createUserDTO()
                        .withLogin(LOGIN)
                        .withPassword(PASSWORD)
                        .withName(NAME)
                        .withSurname(SURNAME)
                        .withEmail(EMAIL)
                        .withAccessLevel(ACCESSLEVEL)
                        .build()
        );
        assertThat(user, is(notNullValue()));
        assertThat(user.getUserId(), is(notNullValue()));

        AttemptDTO newAttempt = attemptResource.create(
                createAttemptDTO()
                        .withLogin(user.getLogin())
                        .withAttempts(2)
                        .withDate(new Date())
                        .withUserDTO(user)
                        .build()
        );
        assertThat(newAttempt, is(notNullValue()));
        AttemptDTO oldAttempt = attemptResource.get(newAttempt.getAttemptId());
        assertThat(newAttempt.getAttemptId(), is(oldAttempt.getAttemptId()));
        assertThat(newAttempt.getLogin(), is(oldAttempt.getLogin()));
    }

    @Test
    public void updateFailedAttemptsTest() {
        UserDTO user = userResource.create(
                createUserDTO()
                        .withLogin(LOGIN)
                        .withPassword(PASSWORD)
                        .withName(NAME)
                        .withSurname(SURNAME)
                        .withEmail(EMAIL)
                        .withAccessLevel(ACCESSLEVEL)
                        .build()
        );
        assertThat(user, is(notNullValue()));
        assertThat(user.getUserId(), is(notNullValue()));

        AttemptDTO newAttempt = attemptResource.create(
                createAttemptDTO()
                        .withLogin(user.getLogin())
                        .withAttempts(1)
                        .withDate(new Date(t-10*ONE_MINUTE_IN_MILLIS))
                        .withUserDTO(user)
                        .build()
        );
        assertThat(newAttempt, is(notNullValue()));
        AttemptDTO updatedAttempt = attemptResource.failed(user.getLogin());

        assertThat(updatedAttempt.getAttempts(), is(2));
        assertTrue(Math.abs(updatedAttempt.getLastModified().getTime() - new Date().getTime()) < 1500);

    }

    @Test
    public void resetAttemptsByTimeTest() {

        UserDTO user = userResource.create(
                createUserDTO()
                        .withLogin(LOGIN)
                        .withPassword(PASSWORD)
                        .withName(NAME)
                        .withSurname(SURNAME)
                        .withEmail(EMAIL)
                        .withAccessLevel(ACCESSLEVEL)
                        .build()
        );
        assertThat(user, is(notNullValue()));
        assertThat(user.getUserId(), is(notNullValue()));

        AttemptDTO newAttempt = attemptResource.create(
                createAttemptDTO()
                        .withLogin(user.getLogin())
                        .withAttempts(2)
                        .withDate(new Date(t-50*ONE_MINUTE_IN_MILLIS))
                        .withUserDTO(user)
                        .build()
        );
        assertThat(newAttempt, is(notNullValue()));
        AttemptDTO updatedAttempt = attemptResource.resetByTime(user.getLogin());

        assertThat(updatedAttempt.getAttempts(), is(0));

    }

    @Test
    public void resetAttemptsByLoginTest() {

        UserDTO user = userResource.create(
                createUserDTO()
                        .withLogin(LOGIN)
                        .withPassword(PASSWORD)
                        .withName(NAME)
                        .withSurname(SURNAME)
                        .withEmail(EMAIL)
                        .withAccessLevel(ACCESSLEVEL)
                        .build()
        );
        assertThat(user, is(notNullValue()));
        assertThat(user.getUserId(), is(notNullValue()));

        AttemptDTO newAttempt = attemptResource.create(
                createAttemptDTO()
                        .withLogin(user.getLogin())
                        .withAttempts(2)
                        .withDate(new Date())
                        .withUserDTO(user)
                        .build()
        );
        assertThat(newAttempt, is(notNullValue()));
        AttemptDTO updatedAttempt = attemptResource.resetByLogin(user.getLogin());

        assertThat(updatedAttempt.getAttempts(), is(0));

    }

}