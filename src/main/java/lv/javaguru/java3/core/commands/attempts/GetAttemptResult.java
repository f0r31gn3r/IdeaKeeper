package lv.javaguru.java3.core.commands.attempts;

import lv.javaguru.java3.core.commands.DomainCommandResult;
import lv.javaguru.java3.core.dto.attempt.AttemptDTO;

/**
 * Created by Anna on 02.11.2015.
 */

public class GetAttemptResult implements DomainCommandResult {
    private AttemptDTO attempt;

    public GetAttemptResult(AttemptDTO attempt) {
        this.attempt = attempt;
    }

    public AttemptDTO getAttempt() {
        return attempt;
    }

}
