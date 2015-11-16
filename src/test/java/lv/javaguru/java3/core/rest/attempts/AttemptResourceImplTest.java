package lv.javaguru.java3.core.rest.attempts;

import lv.javaguru.java3.core.dto.attempt.AttemptDTO;
import lv.javaguru.java3.core.rest.RESTResourceTest;
import org.junit.Test;

import java.util.Date;

import static lv.javaguru.java3.core.dto.attempt.AttemptDTOBuilder.createAttemptDTO;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class AttemptResourceImplTest extends RESTResourceTest {

    private static final String LOGIN = "login";
    private static final int ATTEMPTS = 1;
    private static final Date LASTMOD = new Date();

    @Test
    public void createAttemptTest() {

        AttemptDTO attemptDTO = attemptResource.create(
                createAttemptDTO()
                        .withAll(LOGIN, ATTEMPTS, LASTMOD).build()
        );
        assertThat(attemptDTO, is(notNullValue()));
    }

    @Test
    public void getAttemptTest() {

        AttemptDTO newAttempt = attemptResource.create(
                createAttemptDTO()
                        .withAll(LOGIN, ATTEMPTS, LASTMOD).build()
        );
        AttemptDTO oldAttempt = attemptResource.get(newAttempt.getAttemptId());
        assertThat(newAttempt.getAttemptId(), is(oldAttempt.getAttemptId()));
        assertThat(newAttempt.getLogin(), is(oldAttempt.getLogin()));
    }

}