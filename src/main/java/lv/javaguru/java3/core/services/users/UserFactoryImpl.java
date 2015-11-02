package lv.javaguru.java3.core.services.users;

/**
 * Created by Anna on 27.10.2015.
 */

import lv.javaguru.java3.core.database.UserDAO;
import lv.javaguru.java3.core.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static lv.javaguru.java3.core.domain.user.UserBuilder.createUser;

@Component
class UserFactoryImpl implements UserFactory {

    @Autowired private UserValidator userValidator;
    @Autowired private UserDAO userDAO;


    @Override
    public User create(String login, String password, String name, String surname, String email, String accessLevel) {
        userValidator.validate(login, password, name, surname, email, accessLevel);
        User user = createUser()
                .withLogPas(login, password)
                .withLogPasNamSur(login, password, name, surname)
                .withAll(login, password, name, surname, email, accessLevel)
                .build();
        //if there are no users with such login
        List<User> usersFromDB = userDAO.getAll();
        for(User u : usersFromDB){
            if (u.getLogin().equals(user.getLogin())){
                return null;
            }
        }
        userDAO.create(user);
        return user;
    }
}

