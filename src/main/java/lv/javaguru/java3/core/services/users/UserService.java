package lv.javaguru.java3.core.services.users;

import lv.javaguru.java3.core.domain.user.User;

/**
 * Created by Anna on 27.10.2015.
 */
public interface UserService {

    User update(Long userId,
                String newLogin,
                String newPassword,
                String newName,
                String newSurname,
                String newEmail,
                String newAccessLevel);

    User get(Long userId);

    User get(String login);
}
