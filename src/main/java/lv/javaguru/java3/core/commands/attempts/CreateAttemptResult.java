package lv.javaguru.java3.core.commands.attempts;

/**
 * Created by Anna on 02.11.2015.
 */

import lv.javaguru.java3.core.commands.DomainCommandResult;
import lv.javaguru.java3.core.dto.attempt.AttemptDTO;

public class CreateAttemptResult implements DomainCommandResult {

    private AttemptDTO attempt;

    public CreateAttemptResult(AttemptDTO attempt) {
        this.attempt = attempt;
    }

    public AttemptDTO getAttempt() {
        return attempt;
    }

}