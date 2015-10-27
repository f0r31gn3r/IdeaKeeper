package lv.javaguru.java3.core.commands.users;

import lv.javaguru.java3.core.commands.DomainCommandResult;
import lv.javaguru.java3.core.domain.user.User;

/**
 * Created by Anna on 27.10.2015.
 */
public class CreateUserResult implements DomainCommandResult {

    private User user;

    public CreateUserResult(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

}
