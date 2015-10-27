package lv.javaguru.java3.core.services.users.handlers;

/**
 * Created by Anna on 27.10.2015.
 */

import lv.javaguru.java3.core.commands.users.GetUserCommand;
import lv.javaguru.java3.core.commands.users.GetUserResult;
import lv.javaguru.java3.core.domain.user.User;
import lv.javaguru.java3.core.services.DomainCommandHandler;
import lv.javaguru.java3.core.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class GetUserCommandHandler
        implements DomainCommandHandler<GetUserCommand, GetUserResult> {

    @Autowired
    private UserService userService;


    @Override
    public GetUserResult execute(GetUserCommand command) {
        User user = userService.get(command.getUserId());
        return new GetUserResult(user);
    }

    @Override
    public Class getCommandType() {
        return GetUserCommand.class;
    }

}
