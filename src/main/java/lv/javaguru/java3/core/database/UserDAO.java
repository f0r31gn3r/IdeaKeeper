package lv.javaguru.java3.core.database;

import lv.javaguru.java3.core.domain.user.User;

import java.util.List;

/**
 * Created by Anna on 26.10.2015.
 */
public interface UserDAO extends CRUDOperationDAO<User, Long>{

    User getUserByLogin(String login);

    List<User> getFirstFive();
}
