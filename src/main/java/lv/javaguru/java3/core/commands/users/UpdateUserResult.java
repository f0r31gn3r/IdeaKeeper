package lv.javaguru.java3.core.commands.users;

import lv.javaguru.java3.core.commands.DomainCommandResult;
import lv.javaguru.java3.core.dto.user.UserDTO;

/**
 * Created by Anna on 27.10.2015.
 */
public class UpdateUserResult implements DomainCommandResult {

    private UserDTO user;

    public UpdateUserResult(UserDTO user) {
        this.user = user;
    }

    public UserDTO getUser() {
        return user;
    }
}
