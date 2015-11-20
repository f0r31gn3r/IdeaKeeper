package lv.javaguru.java3.core.services.attempts;

/**
 * Created by Anna on 02.11.2015.
 */

import lv.javaguru.java3.core.database.AttemptDAO;
import lv.javaguru.java3.core.database.UserDAO;
import lv.javaguru.java3.core.domain.attempt.Attempt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

import static lv.javaguru.java3.core.domain.attempt.AttemptBuilder.createAttempt;


@Component
public
class AttemptFactoryImpl implements AttemptFactory {

    @Autowired private AttemptDAO attemptDAO;
    @Autowired private UserDAO userDAO;

    @Override
    public Attempt create(String login, int attempts, Date lastModified, Long userId) {
        if(userDAO.getById(userId) != null){
            Attempt attempt = createAttempt()
                    .withLogin(login)
                    .withAttempts(attempts)
                    .withDate(lastModified)
                    .withUser(userDAO.getById(userId))
                    .build();
            attemptDAO.create(attempt);
            return attempt;
        } else {
            return null;
        }

    }
}

