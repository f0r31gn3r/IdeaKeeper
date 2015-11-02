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
//class AttemptFactoryImpl extends DaoAuthenticationProvider implements AttemptFactory {
class AttemptFactoryImpl implements AttemptFactory {


    //@Autowired private AttemptValidator attemptValidator;
    @Autowired private AttemptDAO attemptDAO;

    @Override
    public Attempt create(Long userId, String login, int attempts,	Date lastModified) {
        //attemptValidator
        Attempt attempt = createAttempt()
                .withAll(userId, login, attempts, lastModified)
                .build();
        attemptDAO.create(attempt);
        return attempt;
    }

//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//
//        try {
//            Authentication auth = super.authenticate(authentication);
//
//            //if corrent password, reset the user_attempts
//           attemptDAO.resetFailAttempts(authentication.getName());
//
//            return auth;
//
//        } catch (BadCredentialsException e) {
//
//            //invalid login, update user_attempts, set attempts+1
//            attemptDAO.updateFailAttempts(authentication.getName());
//
//            throw e;
//
//        }
//
//    }
}

