package lv.javaguru.java3.core.domain.attempt;

import java.util.Date;

/**
 * Created by Anna on 02.11.2015.
 */


public class AttemptBuilder {

    private Long attemptId;

    private String login;

    private int attempts;

    private Date lastModified;

    public static AttemptBuilder createAttempt() {
        return new AttemptBuilder();
    }

    public Attempt build(){
        Attempt attempt = new Attempt();

        attempt.setAttemptId(attemptId);
        attempt.setLogin(login);
        attempt.setAttempts(attempts);
        attempt.setLastModified(lastModified);
        return attempt;
    }

    public AttemptBuilder withAll(String login, int attempts, Date lastModified) {
        this.login = login;
        this.attempts = attempts;
        this.lastModified = lastModified;
        return this;
    }
}

