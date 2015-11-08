package lv.javaguru.java3.core.services.users;

/**
 * Created by Anna on 27.10.2015.
 */

import lv.javaguru.java3.core.database.AttemptDAO;
import lv.javaguru.java3.core.database.UserDAO;
import lv.javaguru.java3.core.domain.user.User;
import lv.javaguru.java3.core.services.attempts.AttemptFactory;
import lv.javaguru.java3.core.services.attempts.AttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired private UserDAO userDAO;
    @Autowired private UserValidator userValidator;
    @Autowired private AttemptDAO attemptDAO;
    @Autowired private AttemptFactory attemptFactory;
    @Autowired private AttemptService attemptService;

    @Override
    public User update(Long userId,
                       String newLogin,
                       String newPassword,
                       String newName,
                       String newSurname,
                       String newEmail,
                       String newAccessLevel) {
        userValidator.validate(newLogin, newPassword, newName, newSurname, newEmail, newAccessLevel);
        User user = get(userId);
        user.setLogin(newLogin);
        user.setPassword(newPassword);
        user.setName(newName);
        user.setSurname(newSurname);
        user.setEmail(newEmail);
        user.setAccessLevel(newAccessLevel);
        userDAO.update(user);
        return user;
    }

    @Override
    public User get(Long userId) {
        return userDAO.getRequired(userId);
    }

    @Override
    public User get(String login) {
        return userDAO.getUserByLogin(login);
    }

//	@Override
//	public String login(String login, String password) {
//
//		if(userDAO.getByLogin(login) == null){ //if user with such login doesn't exist
//			return new String("User with such login doesn't exist");
//		}
//
//		User user = userDAO.getByLogin(login);
//		Authentication request = new UsernamePasswordAuthenticationToken(login, password);
//
//		//if user hasn't tried to login yet, create him a record with attempts
//		if(attemptDAO.getAttemptByUserLogin(login) == null){
//			attemptFactory.create(user.getUserId(), login, 0, null);
//		}
//
//		Attempt attempt = attemptDAO.getAttemptByUserLogin(login);
//
//		try{
//			userDAO.authenticate(request);
//			attemptService.resetFailAttempts(attempt);
//			return new String("Login attempt successful");
//		}catch(BadCredentialsException be){ //if login and pass don't match
//			attemptService.updateFailAttempts(attempt);
//			return new String("Login and pass don't match");
//		}catch(DisabledException de){ //if user is blocked
//			return new String("User is blocked");
//		}
//	}
}

