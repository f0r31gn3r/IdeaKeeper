package lv.javaguru.java3.core.services.users;

/**
 * Created by Anna on 27.10.2015.
 */
import lv.javaguru.java3.core.database.UserDAO;
import lv.javaguru.java3.core.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static lv.javaguru.java3.core.domain.user.UserBuilder.createUser;

@Component
public class UserFactoryImpl implements UserFactory {

    @Autowired private UserValidator userValidator;
    @Autowired private UserDAO userDAO;


    @Override
    public User create(String login, String password, String name, String surname, String email, String accessLevel) {
        userValidator.validate(login, password, name, surname, email, accessLevel);
        if(userDAO.getUserByLogin(login) == null){
            User user = createUser()
                .withLogPas(login, password)
                .withLogPasNamSur(login, password, name, surname)
                .withAll(login, password, name, surname, email, accessLevel)
                .build();

            userDAO.create(user);
            return user;
        } else {
            return null;
        }

    }

}

