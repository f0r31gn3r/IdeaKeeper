package lv.javaguru.java3.core.services.users;

/**
 * Created by Anna on 27.10.2015.
 */

import lv.javaguru.java3.core.database.UserDAO;
import lv.javaguru.java3.core.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired private UserDAO userDAO;
    @Autowired private UserValidator userValidator;

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

    @Override
    public int delete(Long userId) {
        User user = get(userId);
        if(user != null){
            userDAO.delete(user);
            return 0;
        } else {
            return -1;
        }
    }

}

