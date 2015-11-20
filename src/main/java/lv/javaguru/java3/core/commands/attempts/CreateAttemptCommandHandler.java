package lv.javaguru.java3.core.commands.attempts;


/**
 * Created by Anna on 02.11.2015.
 */
import lv.javaguru.java3.core.domain.attempt.Attempt;
import lv.javaguru.java3.core.dto.attempt.AttemptDTO;
import lv.javaguru.java3.core.services.DomainCommandHandler;
import lv.javaguru.java3.core.services.attempts.AttemptFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
class CreateAttemptCommandHandler
        implements DomainCommandHandler<CreateAttemptCommand, CreateAttemptResult> {

    @Autowired  private AttemptFactory attemptFactory;
    @Autowired  private AttemptConverter attemptConverter;

    @Override
    public CreateAttemptResult execute(CreateAttemptCommand command) {
        Attempt attempt = attemptFactory.create(
                command.getLogin(),
                command.getAttempts(),
                command.getLastModified(),
                command.getUserId()
        );
        AttemptDTO attemptDTO = attemptConverter.convert(attempt);
        return new CreateAttemptResult(attemptDTO);    }

    @Override
    public Class getCommandType() {
        return CreateAttemptCommand.class;
    }

}