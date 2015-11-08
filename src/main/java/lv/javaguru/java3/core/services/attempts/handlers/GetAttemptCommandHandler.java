package lv.javaguru.java3.core.services.attempts.handlers;

/**
 * Created by Anna on 02.11.2015.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lv.javaguru.java3.core.commands.attempts.GetAttemptCommand;
import lv.javaguru.java3.core.commands.attempts.GetAttemptResult;
import lv.javaguru.java3.core.domain.attempt.Attempt;
import lv.javaguru.java3.core.services.DomainCommandHandler;
import lv.javaguru.java3.core.services.attempts.AttemptService;
@Deprecated
@Component
class GetAttemptCommandHandler
        implements DomainCommandHandler<GetAttemptCommand, GetAttemptResult> {

    @Autowired
    private AttemptService attemptService;


    @Override
    public GetAttemptResult execute(GetAttemptCommand command) {
        Attempt attempt = attemptService.get(command.getClientId());
        return new GetAttemptResult(attempt);
    }

    @Override
    public Class getCommandType() {
        return GetAttemptCommand.class;
    }

}
