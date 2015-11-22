package lv.javaguru.java3.core.services.login;

//import lv.javaguru.java3.core.database.UserDAO;
//import lv.javaguru.java3.core.domain.attempt.Attempt;
//import lv.javaguru.java3.core.domain.user.User;
//import lv.javaguru.java3.core.services.attempts.AttemptFactory;
//import lv.javaguru.java3.core.services.attempts.AttemptService;
//import lv.javaguru.java3.core.services.users.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.util.Base64;
//import java.util.Date;
//import java.util.StringTokenizer;
//
///**
// * Created by Anna on 22.11.2015.
// */
//
//@Component
//public class AuthenticationServiceImpl implements AuthenticationService{
//    private static final int MAX_ATTEMPTS = 3;
//
//    @Autowired   private UserDAO userDAO;
//    @Autowired   private AttemptService attemptService;
//    @Autowired   private AttemptFactory attemptFactory;
//    @Autowired   private UserService userService;
//
//    @Override
//    public boolean authenticate(String authCredentials) {
//
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
//        final String username = tokenizer.nextToken();
//        final String password = tokenizer.nextToken();
//
//        boolean authenticationStatus = false;
//
//        if(userService.get(username) != null){
//
//            User userFromDB = userService.get(username);
//            Attempt userAttempt = attemptService.get(username);
//
//            if(userAttempt != null && userAttempt.getAttempts() >= MAX_ATTEMPTS){
//                userService.blockUser(userFromDB);
//                return false;
//            }
//
//            if(userFromDB.getPassword().equals(password)){
//                if(userAttempt != null){
//                    attemptService.resetFailAttempts(userAttempt);
//                }
//                authenticationStatus = true;
//            } else {
//                if(userAttempt == null){
//                    userAttempt = attemptFactory.create(userFromDB.getLogin(), 0, new Date(), userFromDB.getUserId());
//                }
//                attemptService.updateFailAttempts(userAttempt);
//                authenticationStatus = false;
//            }
//        }
////        if(username.equals("user") && password.equals("user")){
////			authenticationStatus=true;
////		}
////
//        return authenticationStatus;
//    }
//
//
//}
