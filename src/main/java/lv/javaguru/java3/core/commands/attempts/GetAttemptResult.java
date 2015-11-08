package lv.javaguru.java3.core.commands.attempts;

import lv.javaguru.java3.core.commands.DomainCommandResult;
import lv.javaguru.java3.core.domain.attempt.Attempt;

/**
 * Created by Anna on 02.11.2015.
 */

@Deprecated
public class GetAttemptResult implements DomainCommandResult {

    private Attempt attempt;

    public GetAttemptResult(Attempt attempt) {
        this.attempt = attempt;
    }

    public Attempt getAttempt() {
        return attempt;
    }

}
