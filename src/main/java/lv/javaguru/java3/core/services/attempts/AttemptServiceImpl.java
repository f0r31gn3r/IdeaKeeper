package lv.javaguru.java3.core.services.attempts;

/**
 * Created by Anna on 02.11.2015.
 */

import lv.javaguru.java3.core.database.AttemptDAO;
import lv.javaguru.java3.core.domain.attempt.Attempt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
class AttemptServiceImpl implements AttemptService {

    private static final int MAX_ATTEMPTS = 5;

    public static int getMaxAttempts() {
        return MAX_ATTEMPTS;
    }

    @Autowired private AttemptDAO attemptDAO;
    //@Autowired private AttemptValidator attemptValidator;


    @Override
    public Attempt update(Long attemptId, Long newUserId, String newLogin, int newAttempts,	Date newLastModified) {
        //attemptValidator.validate();
        Attempt attempt = get(attemptId);
        attempt.setLogin(newLogin);
        attempt.setUserId(newUserId);
        attempt.setAttempts(newAttempts);
        attempt.setLastModified(newLastModified);
        return attempt;
    }

    @Override
    public Attempt get(Long attemptId) {
        return attemptDAO.getRequired(attemptId);
    }

    @Override
    public Attempt updateFailAttempts(Attempt userAttempt) {
        Date refreshDate = new Date();
        if ( userAttempt == null ){
            userAttempt = new Attempt();
            userAttempt.setLastModified(refreshDate);
            userAttempt.setAttempts(1);
            attemptDAO.create(userAttempt);
        } else if (userAttempt.getAttempts() + 1 >= MAX_ATTEMPTS){
            throw new LockedException("User Account is locked!");
        } else {
            userAttempt.setAttempts(userAttempt.getAttempts() + 1);
            userAttempt.setLastModified(refreshDate);
            attemptDAO.update(userAttempt);
        }
        return userAttempt;
    }

    @Override
    public Attempt resetFailAttempts(Attempt attempt) {
        attempt.setAttempts(0);
        attemptDAO.update(attempt);
        return attempt;
    }

}

