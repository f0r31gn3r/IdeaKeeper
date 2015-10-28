package lv.javaguru.java3.core.services.users.handlers;

/**
 * Created by Anna on 27.10.2015.
 */

import lv.javaguru.java3.core.commands.users.CreateUserCommand;
import lv.javaguru.java3.core.commands.users.CreateUserResult;
import lv.javaguru.java3.core.domain.user.User;
import lv.javaguru.java3.core.services.DomainCommandHandler;
import lv.javaguru.java3.core.services.users.UserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class CreateUserCommandHandler
        implements DomainCommandHandler<CreateUserCommand, CreateUserResult> {

    @Autowired
    private UserFactory userFactory;


    @Override
    public CreateUserResult execute(CreateUserCommand command) {
        User user = userFactory.create(
                command.getLogin(),
                command.getPassword(),
                command.getName(),
                command.getSurname(),
                command.getEmail(),
                command.getAccessLevel()
        );
        return new CreateUserResult(user);
    }

    @Override
    public Class getCommandType() {
        return CreateUserCommand.class;
    }

}
