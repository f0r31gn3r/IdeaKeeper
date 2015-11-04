package lv.javaguru.java3.core.database;

import lv.javaguru.java3.core.domain.user.User;
import org.springframework.security.authentication.AuthenticationManager;

/**
 * Created by Anna on 26.10.2015.
 */
public interface UserDAO extends CRUDOperationDAO<User, Long>, AuthenticationManager {

    User getByLogin(String login);

}
