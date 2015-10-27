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
class UserFactoryImpl implements UserFactory {

    @Autowired private UserValidator userValidator;
    @Autowired private UserDAO userDAO;


    @Override
    public User create(String login, String password) {
        userValidator.validate(login, password);
        User user = createUser()
                .withLogPas(login, password)
                .build();
        userDAO.create(user);
        return user;
    }

    @Override
    public User create(String login, String password, String name, String surname) {
        userValidator.validate(login, password);
        User user = createUser()
                .withLogPasNamSur(login, password, name, surname)
                .build();
        userDAO.create(user);
        return user;
    }

}

