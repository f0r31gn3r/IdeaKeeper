package lv.javaguru.java3.core.services.attempts.handlers;

/**
 * Created by Anna on 02.11.2015.
 */
import lv.javaguru.java3.core.commands.attempts.UpdateAttemptCommand;
import lv.javaguru.java3.core.commands.attempts.UpdateAttemptResult;
import lv.javaguru.java3.core.domain.attempt.Attempt;
import lv.javaguru.java3.core.services.DomainCommandHandler;
import lv.javaguru.java3.core.services.attempts.AttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class UpdateAttemptCommandHandler
        implements DomainCommandHandler<UpdateAttemptCommand, UpdateAttemptResult> {

    @Autowired
    private AttemptService attemptService;


    @Override
    public UpdateAttemptResult execute(UpdateAttemptCommand command) {
        Attempt attempt = attemptService.update(
                command.getAttemptId(),
                command.getUserId(),
                command.getLogin(),
                command.getAttempts(),
                command.getLastModified()
        );
        return new UpdateAttemptResult(attempt);
    }

    @Override
    public Class getCommandType() {
        return UpdateAttemptCommand.class;
    }

}
