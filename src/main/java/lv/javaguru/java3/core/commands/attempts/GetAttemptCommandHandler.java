package lv.javaguru.java3.core.commands.attempts;


/**
 * Created by Anna on 02.11.2015.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lv.javaguru.java3.core.commands.attempts.GetAttemptCommand;
import lv.javaguru.java3.core.commands.attempts.GetAttemptResult;
import lv.javaguru.java3.core.domain.attempt.Attempt;
import lv.javaguru.java3.core.dto.attempt.AttemptDTO;
import lv.javaguru.java3.core.services.DomainCommandHandler;
import lv.javaguru.java3.core.services.attempts.AttemptService;

@Component
class GetAttemptCommandHandler
        implements DomainCommandHandler<GetAttemptCommand, GetAttemptResult> {

    @Autowired
    private AttemptService attemptService;
    @Autowired private AttemptConverter attemptConverter;


    @Override
    public GetAttemptResult execute(GetAttemptCommand command) {
        Attempt attempt = attemptService.get(command.getAttemptId());
        AttemptDTO attemptDTO = attemptConverter.convert(attempt);
        return new GetAttemptResult(attemptDTO);    }

    @Override
    public Class getCommandType() {
        return GetAttemptCommand.class;
    }

}
