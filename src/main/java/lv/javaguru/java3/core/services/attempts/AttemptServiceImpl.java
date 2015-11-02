package lv.javaguru.java3.core.services.attempts;

/**
 * Created by Anna on 02.11.2015.
 */
import lv.javaguru.java3.core.database.AttemptDAO;
import lv.javaguru.java3.core.domain.attempt.Attempt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
class AttemptServiceImpl implements AttemptService {

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

}

