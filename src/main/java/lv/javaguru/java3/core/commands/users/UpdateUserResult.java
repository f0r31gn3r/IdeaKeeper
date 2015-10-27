package lv.javaguru.java3.core.commands.users;

import lv.javaguru.java3.core.commands.DomainCommandResult;
import lv.javaguru.java3.core.domain.user.User;

/**
 * Created by Anna on 27.10.2015.
 */
public class UpdateUserResult implements DomainCommandResult {

    private User user;

    public UpdateUserResult(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

}
