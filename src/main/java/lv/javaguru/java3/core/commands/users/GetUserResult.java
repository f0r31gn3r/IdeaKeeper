package lv.javaguru.java3.core.commands.users;

import lv.javaguru.java3.core.commands.DomainCommandResult;
import lv.javaguru.java3.core.domain.user.User;

/**
 * Created by Anna on 27.10.2015.
 */
public class GetUserResult implements DomainCommandResult {

    private User user;

    public GetUserResult(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

}
