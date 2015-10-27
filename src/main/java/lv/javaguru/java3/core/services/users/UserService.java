package lv.javaguru.java3.core.services.users;

import lv.javaguru.java3.core.domain.user.User;

/**
 * Created by Anna on 27.10.2015.
 */
public interface UserService {

    User update(Long userId,
                String newLogin,
                String newPassword);

    User get(Long userId);

}
