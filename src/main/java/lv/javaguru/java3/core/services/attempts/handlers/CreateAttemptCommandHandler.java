package lv.javaguru.java3.core.services.attempts.handlers;

/**
 * Created by Anna on 02.11.2015.
 */
import lv.javaguru.java3.core.commands.attempts.CreateAttemptCommand;
import lv.javaguru.java3.core.commands.attempts.CreateAttemptResult;
import lv.javaguru.java3.core.domain.attempt.Attempt;
import lv.javaguru.java3.core.services.DomainCommandHandler;
import lv.javaguru.java3.core.services.attempts.AttemptFactory;
import lv.javaguru.java3.core.services.clients.ClientFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Deprecated
@Component
class CreateAttemptCommandHandler
        implements DomainCommandHandler<CreateAttemptCommand, CreateAttemptResult> {

    @Autowired
    private AttemptFactory attemptFactory;


    @Override
    public CreateAttemptResult execute(CreateAttemptCommand command) {
        Attempt attempt = attemptFactory.create(
                command.getUserId(),
                command.getLogin(),
                command.getAttempts(),
                command.getLastModified()
        );
        return new CreateAttemptResult(attempt);
    }

    @Override
    public Class getCommandType() {
        return CreateAttemptCommand.class;
    }

}