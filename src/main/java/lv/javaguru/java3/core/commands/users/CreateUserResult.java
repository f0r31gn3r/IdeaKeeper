package lv.javaguru.java3.core.commands.users;

import lv.javaguru.java3.core.commands.DomainCommandResult;
import lv.javaguru.java3.core.dto.user.UserDTO;

/**
 * Created by Anna on 27.10.2015.
 */
public class CreateUserResult implements DomainCommandResult {

    private UserDTO user;

    public CreateUserResult(UserDTO user) {
        this.user = user;
    }

    public UserDTO getUser() {
        return user;
    }


}
