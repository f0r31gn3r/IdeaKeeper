package lv.javaguru.java3.core.services.users.handlers;

/**
 * Created by Anna on 27.10.2015.
 */

import lv.javaguru.java3.core.commands.users.UpdateUserCommand;
import lv.javaguru.java3.core.commands.users.UpdateUserResult;
import lv.javaguru.java3.core.domain.user.User;
import lv.javaguru.java3.core.services.DomainCommandHandler;
import lv.javaguru.java3.core.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
class UpdateUserCommandHandler
        implements DomainCommandHandler<UpdateUserCommand, UpdateUserResult> {

    @Autowired
    private UserService userService;


    @Override
    public UpdateUserResult execute(UpdateUserCommand command) {
        User user = userService.update(
                command.getUserId(),
                command.getLogin(),
                command.getPassword(),
                command.getName(),
                command.getSurname(),
                command.getEmail(),
                command.getAccessLevel()
        );
        return new UpdateUserResult(user);
    }

    @Override
    public Class getCommandType() {
        return UpdateUserCommand.class;
    }

}

