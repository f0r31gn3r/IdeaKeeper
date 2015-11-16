package lv.javaguru.java3.core.commands.attempts;


/**
 * Created by Anna on 02.11.2015.
 */
import lv.javaguru.java3.core.domain.attempt.Attempt;
import lv.javaguru.java3.core.dto.attempt.AttemptDTO;
import lv.javaguru.java3.core.services.DomainCommandHandler;
import lv.javaguru.java3.core.services.attempts.AttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class GetAttemptCommandHandler
        implements DomainCommandHandler<GetAttemptCommand, GetAttemptResult> {

    @Autowired  private AttemptService attemptService;
    @Autowired  private AttemptConverter attemptConverter;

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
