package lv.javaguru.java3.core.commands.attempts;

/**
 * Created by Anna on 02.11.2015.
 */
import lv.javaguru.java3.core.commands.DomainCommandResult;
import lv.javaguru.java3.core.domain.attempt.Attempt;


public class UpdateAttemptResult implements DomainCommandResult {

    private Attempt attempt;

    public UpdateAttemptResult(Attempt attempt) {
        this.attempt = attempt;
    }

    public Attempt getAttempt() {
        return attempt;
    }

    public void setAttempt(Attempt attempt) {
        this.attempt = attempt;
    }



}

