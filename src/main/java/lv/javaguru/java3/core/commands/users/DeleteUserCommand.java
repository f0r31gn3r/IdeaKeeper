package lv.javaguru.java3.core.commands.users;

import lv.javaguru.java3.core.commands.DomainCommand;

/**
 * Created by Anna on 20.11.2015.
 */
public class DeleteUserCommand implements DomainCommand<DeleteUserResult> {

    private Long userId;

    public DeleteUserCommand(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}
