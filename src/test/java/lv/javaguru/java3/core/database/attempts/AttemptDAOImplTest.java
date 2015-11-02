package lv.javaguru.java3.core.database.attempts;

/**
 * Created by Anna on 02.11.2015.
 */
import lv.javaguru.java3.core.database.DatabaseHibernateTest;
import lv.javaguru.java3.core.domain.attempt.Attempt;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

import static lv.javaguru.java3.core.domain.attempt.AttemptBuilder.createAttempt;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class AttemptDAOImplTest extends DatabaseHibernateTest {

    private  SimpleDateFormat sdf =  new SimpleDateFormat ("dd.MM.yyyy hh:mm:ss");


    @Test
    @Transactional
    public void testCreateAttempt() {
        Attempt attempt = createAttempt()
                .withAll(10L, "login", 3, new Date())
                .build();
        assertThat(attempt.getAttemptId(), is(nullValue()));
        attemptDAO.create(attempt);
        assertThat(attempt.getAttemptId(), is(notNullValue()));
    }

    @Test
    @Transactional
    public void testGetAttemptById() {
        Attempt attempt = createAttempt()
                .withAll(10L, "login", 2, new Date())
                .build();
        attemptDAO.create(attempt);
        Attempt attemptFromDb = attemptDAO.getById(attempt.getAttemptId());
        assertThat(attemptFromDb, is(notNullValue()));
    }


}
