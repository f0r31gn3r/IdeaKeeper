package lv.javaguru.java3.core.services.users;

import lv.javaguru.java3.core.domain.user.User;

/**
 * Created by Anna on 27.10.2015.
 */
public interface UserFactory {

    User create(String login, String password);

    User create(String login, String password, String name, String surname);

}
