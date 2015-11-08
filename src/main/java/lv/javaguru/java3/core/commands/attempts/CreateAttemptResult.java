package lv.javaguru.java3.core.commands.attempts;

/**
 * Created by Anna on 02.11.2015.
 */
import lv.javaguru.java3.core.commands.DomainCommandResult;
import lv.javaguru.java3.core.domain.attempt.Attempt;

@Deprecated
public class CreateAttemptResult implements DomainCommandResult {

    private Attempt attempt;

    public CreateAttemptResult(Attempt attempt) {
        this.attempt = attempt;
    }

    public Attempt getAttempt() {
        return attempt;
    }

}