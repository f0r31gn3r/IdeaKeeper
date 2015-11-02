package lv.javaguru.java3.core.commands.attempts;

import lv.javaguru.java3.core.commands.DomainCommand;

/**
 * Created by Anna on 02.11.2015.
 */
public class GetAttemptCommand implements DomainCommand<GetAttemptResult> {

    private Long attemptId;

    public GetAttemptCommand(Long attemptId) {
        this.attemptId = attemptId;
    }

    public Long getClientId() {
        return attemptId;
    }

}
