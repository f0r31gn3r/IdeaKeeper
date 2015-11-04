package janis.core.commands.users;

import lv.javaguru.java3.core.commands.DomainCommand;

/**
 * Created by Anna on 27.10.2015.
 */
public class GetUserCommand implements DomainCommand<GetUserResult> {

    private Long userId;

    public GetUserCommand(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

}
