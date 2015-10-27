package lv.javaguru.java3.core.services.users;

/**
 * Created by Anna on 27.10.2015.
 */
import lv.javaguru.java3.core.database.UserDAO;
import lv.javaguru.java3.core.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class UserServiceImpl implements UserService {

    @Autowired private UserDAO userDAO;
    @Autowired private UserValidator userValidator;


    @Override
    public User update(Long userId,
                       String newLogin,
                       String newPassword) {
        userValidator.validate(newLogin, newPassword);
        User user = get(userId);
        user.setLogin(newLogin);
        user.setPassword(newPassword);
        return user;
    }

    @Override
    public User get(Long userId) {
        return userDAO.getRequired(userId);
    }

}

