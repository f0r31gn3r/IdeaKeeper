package lv.javaguru.java3.core.services.attempts;

/**
 * Created by Anna on 02.11.2015.
 */

import lv.javaguru.java3.core.database.AttemptDAO;
import lv.javaguru.java3.core.domain.attempt.Attempt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;


@Component
public class AttemptServiceImpl implements AttemptService {

    final long ONE_MINUTE_IN_MILLIS = 60000;

    @Autowired private AttemptDAO attemptDAO;

    @Override
    public Attempt update(Long attemptId, String newLogin, int newAttempts,	Date newLastModified) {
        Attempt attempt = get(attemptId);
        attempt.setLogin(newLogin);
        attempt.setAttempts(newAttempts);
        attempt.setLastModified(newLastModified);
        attemptDAO.update(attempt);
        return attempt;
    }

    @Override
    public Attempt get(Long attemptId) {
        return attemptDAO.getRequired(attemptId);
    }

    @Override
    public Attempt get(String userLogin) {
        return attemptDAO.getAttemptByUserLogin(userLogin);
    }

    @Override
    public Attempt updateFailAttempts(Attempt userAttempt) {
        return update(userAttempt.getAttemptId(), userAttempt.getLogin(), userAttempt.getAttempts() +1, new Date());    }

    @Override
    public Attempt resetBySuccessfulLogin(Attempt userAttempt) {
        return update(userAttempt.getAttemptId(), userAttempt.getLogin(), 0, userAttempt.getLastModified());
    }

    @Override
    public Attempt resetByTime(Attempt userAttempt) {
        Calendar date = Calendar.getInstance();
        long t = date.getTimeInMillis();

        if(userAttempt.getLastModified().getTime() < (t-30*ONE_MINUTE_IN_MILLIS) ){
            userAttempt = update(userAttempt.getAttemptId(), userAttempt.getLogin(), 0, userAttempt.getLastModified());
        }
        return userAttempt;
    }

}

