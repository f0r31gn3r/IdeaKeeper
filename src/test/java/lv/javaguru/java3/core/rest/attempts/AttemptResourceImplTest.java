package lv.javaguru.java3.core.rest.attempts;

import lv.javaguru.java3.core.domain.user.AccessLevel;
import lv.javaguru.java3.core.dto.attempt.AttemptDTO;
import lv.javaguru.java3.core.dto.user.UserDTO;
import lv.javaguru.java3.core.rest.RESTResourceTest;
import org.junit.Test;

import java.util.Date;

import static lv.javaguru.java3.core.dto.attempt.AttemptDTOBuilder.createAttemptDTO;
import static lv.javaguru.java3.core.dto.user.UserDTOBuilder.createUserDTO;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class AttemptResourceImplTest extends RESTResourceTest {
	
 	private static final String LOGIN = "login";
 	private static final String LOGIN2 = "login2";
 	private static final String LOGIN3 = "login3";

    private static final int ATTEMPTS = 1;
    private static final Date LASTMOD = new Date();
    
    private static final String PASSWORD = "password";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String EMAIL = "email@email.lv";
    private static final String ACCESSLEVEL = AccessLevel.USER.name();
	
    @Test
    public void createAttemptTest() {
    	
    	UserDTO user = userResource.create(
                createUserDTO()
                        .withLogPas(LOGIN2, PASSWORD)
                        .withLogPasNamSur(LOGIN2, PASSWORD, NAME, SURNAME)
                        .withAll(LOGIN2, PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL)
                        .build()
        );
    	
    	AttemptDTO attempt = attemptResource.create(
                createAttemptDTO()
                        .withAll(user.getUserId(), LOGIN2, ATTEMPTS, LASTMOD).build()
        );
        assertThat(attempt, is(notNullValue()));
        assertThat(attempt.getAttemptId(), is(notNullValue()));
    }

    @Test
    public void getAttemptTest() {
    	
    	UserDTO user = userResource.create(
                createUserDTO()
                        .withLogPas(LOGIN3, PASSWORD)
                        .withLogPasNamSur(LOGIN3, PASSWORD, NAME, SURNAME)
                        .withAll(LOGIN3, PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL)
                        .build()
        );
    	
    	AttemptDTO newAttempt = attemptResource.create(
                createAttemptDTO()
                        .withAll(user.getUserId(), LOGIN3, ATTEMPTS, LASTMOD).build()
        );
    	AttemptDTO oldAttempt = attemptResource.get(newAttempt.getAttemptId());
        assertThat(newAttempt.getAttemptId(), is(oldAttempt.getAttemptId()));
        assertThat(newAttempt.getLogin(), is(oldAttempt.getLogin()));
    }

}
