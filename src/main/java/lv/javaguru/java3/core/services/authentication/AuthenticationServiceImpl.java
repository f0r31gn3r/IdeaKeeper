package lv.javaguru.java3.core.services.authentication;

import lv.javaguru.java3.core.database.UserDAO;
import lv.javaguru.java3.core.domain.attempt.Attempt;
import lv.javaguru.java3.core.domain.user.AccessLevel;
import lv.javaguru.java3.core.domain.user.User;
import lv.javaguru.java3.core.services.attempts.AttemptFactory;
import lv.javaguru.java3.core.services.attempts.AttemptService;
import lv.javaguru.java3.core.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Anna on 22.11.2015.
 */

@Component
public class AuthenticationServiceImpl implements AuthenticationService{
    private static final int MAX_ATTEMPTS = 3;

    @Autowired   private UserDAO userDAO;
    @Autowired   private AttemptService attemptService;
    @Autowired   private AttemptFactory attemptFactory;
    @Autowired   private UserService userService;

    public static String state = new String("");
    public static User user;

    @Override
    public boolean authenticate(String username, String password) {

        //--------IF USER WITH SUCH USERNAME EXISTS---------
        if(userService.get(username) != null){

            User userFromDB = userService.get(username);
            Attempt userAttempt = userFromDB.getAttempt();

            //IF USER IS ALREADY BLOCKED
            if(userFromDB.getAccessLevel().equals(AccessLevel.BLOCKED.name())){
                setState(AuthenticationStatus.BLOCKED.getValue());
                return false;
            }

            //IF THIS USER HAS FAILED LOGIN MORE, THAN 3 TIMES, BLOCK HIM
            if(userAttempt != null && userAttempt.getAttempts() >= MAX_ATTEMPTS){
                setState(AuthenticationStatus.BLOCKED.getValue());
                userService.blockUser(userFromDB);
                return false;
            }

            //IF PASSWORD CORRECT
            if(userFromDB.getPassword().equals(password)){

                //IF USER HAS TRIED TO LOGIN EARLIER
                if(userAttempt != null){
                    attemptService.resetBySuccessfulLogin(userAttempt);
                }
                setState(AuthenticationStatus.SUCCESSFUL_LOGIN.getValue());
                setUser(userFromDB);
                return true;
            } else {
                //IF USER HASN'T TRIED TO LOGIN EARLIER, CREATE ATTEMPTS RECORD FOR HIM
                if(userAttempt == null){
                    userAttempt = attemptFactory.create(userFromDB.getLogin(), 0, new Date(), userFromDB.getUserId());
                }

                //UPDATE FAILED ATTEMPTS
                setState(AuthenticationStatus.PASS_FAILED.getValue());
                attemptService.updateFailAttempts(userAttempt);
                return false;
            }
        } else {
            setState(AuthenticationStatus.USERNAME_FAILED.getValue());
            return false;
        }
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void setUser(User inputUser) {
        user = inputUser;
    }

    @Override
    public String getState() {
        return state;
    }

    @Override
    public void setState(String inputState) {
        state = inputState;
    }
}
