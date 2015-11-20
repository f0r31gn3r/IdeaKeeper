package lv.javaguru.java3.core.commands.users;

/**
 * Created by Anna on 20.11.2015.
 */

import lv.javaguru.java3.core.services.DomainCommandHandler;
import lv.javaguru.java3.core.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteUserCommandHandler implements DomainCommandHandler<DeleteUserCommand, DeleteUserResult> {

    @Autowired private UserService userService;

    @Override
    public DeleteUserResult execute(DeleteUserCommand command) {
        //User user = userService.get(command.getUserId());

        return new DeleteUserResult(userService.delete(command.getUserId()));
    }

    @Override
    public Class getCommandType() {
        return DeleteUserCommand.class;
    }

}
