package lv.javaguru.java3.core.database.users;

import lv.javaguru.java3.core.database.UserDAO;
import lv.javaguru.java3.core.domain.user.User;
import org.springframework.stereotype.Component;

/**
 * Created by Anna on 26.10.2015.
 */

@Component
public class UserDAOImpl extends CRUDOperationDAOImpl<User, Long> implements UserDAO {

}
