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

    public static String state = new String("NOT");

    @Override
    public boolean authenticate(String username, String password) {
        boolean authenticationStatus = false;

//        if (null == authCredentials)
//            return false;
//        final String encodedUserPassword = authCredentials.replaceFirst("Basic"
//                + " ", "");
//        String usernameAndPassword = null;
//        try {
//            byte[] decodedBytes = Base64.getDecoder().decode(
//                    encodedUserPassword);
//            usernameAndPassword = new String(decodedBytes, "UTF-8");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        final StringTokenizer tokenizer = new StringTokenizer(
//                usernameAndPassword, ":");
//
//        //--------USERNAME AND PASSWORD GOT---------------
//        final String username = tokenizer.nextToken();
//        final String password = tokenizer.nextToken();

        //--------IF USER WITH SUCH USERNAME EXISTS---------
        if(userService.get(username) != null){

            User userFromDB = userService.get(username);
            Attempt userAttempt = userFromDB.getAttempt();

            //IF USER IS ALREADY BLOCKED
            if(userFromDB.getAccessLevel().equals(AccessLevel.BLOCKED.name())){
                return false;
            }

            //IF THIS USER HAS FAILED LOGIN MORE, THAN 3 TIMES, BLOCK HIM
            if(userAttempt != null && userAttempt.getAttempts() >= MAX_ATTEMPTS){
                userService.blockUser(userFromDB);
                return false;
            }

            //IF PASSWORD CORRECT
            if(userFromDB.getPassword().equals(password)){

                //IF USER HAS TRIED TO LOGIN EARLIER
                if(userAttempt != null){
                    attemptService.resetBySuccessfulLogin(userAttempt);
                }
                state = "OK";
                return true;
            } else {
                //IF USER HASN'T TRIED TO LOGIN EARLIER, CREATE ATTEMPTS RECORD FOR HIM
                if(userAttempt == null){
                    userAttempt = attemptFactory.create(userFromDB.getLogin(), 0, new Date(), userFromDB.getUserId());
                }

                //UPDATE FAILED ATTEMPTS
                attemptService.updateFailAttempts(userAttempt);
                return false;
            }
        }
//        if(username.equals("user") && password.equals("user")){
//			authenticationStatus=true;
//		}
//
        return authenticationStatus;
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
