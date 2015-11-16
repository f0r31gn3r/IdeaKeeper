package lv.javaguru.java3.core.commands.attempts;


/**
 * Created by Anna on 02.11.2015.
 */
import lv.javaguru.java3.core.commands.attempts.UpdateAttemptCommand;
import lv.javaguru.java3.core.commands.attempts.UpdateAttemptResult;
import lv.javaguru.java3.core.domain.attempt.Attempt;
import lv.javaguru.java3.core.dto.attempt.AttemptDTO;
import lv.javaguru.java3.core.services.DomainCommandHandler;
import lv.javaguru.java3.core.services.attempts.AttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
class UpdateAttemptCommandHandler
        implements DomainCommandHandler<UpdateAttemptCommand, UpdateAttemptResult> {

    @Autowired
    private AttemptService attemptService;
    @Autowired private AttemptConverter attemptConverter;



    @Override
    public UpdateAttemptResult execute(UpdateAttemptCommand command) {
        Attempt attempt = attemptService.update(
                command.getAttemptId(),
                command.getLogin(),
                command.getAttempts(),
                command.getLastModified()
        );
        AttemptDTO attemptDTO = attemptConverter.convert(attempt);
        return new UpdateAttemptResult(attemptDTO);    }

    @Override
    public Class getCommandType() {
        return UpdateAttemptCommand.class;
    }

}
