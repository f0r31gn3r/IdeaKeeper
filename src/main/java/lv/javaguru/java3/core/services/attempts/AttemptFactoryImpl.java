package lv.javaguru.java3.core.services.attempts;

/**
 * Created by Anna on 02.11.2015.
 */

import lv.javaguru.java3.core.database.AttemptDAO;
import lv.javaguru.java3.core.domain.attempt.Attempt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

import static lv.javaguru.java3.core.domain.attempt.AttemptBuilder.createAttempt;


@Component
public
class AttemptFactoryImpl implements AttemptFactory {

    @Autowired private AttemptDAO attemptDAO;

    @Override
    public Attempt create(String login, int attempts,	Date lastModified) {
        Attempt attempt = createAttempt()
                .withAll(login, attempts, lastModified)
                .build();
        attemptDAO.create(attempt);
        return attempt;
    }
}

