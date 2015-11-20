package lv.javaguru.java3.core.domain.attempt;

import lv.javaguru.java3.core.domain.user.User;

import java.util.Date;

/**
 * Created by Anna on 02.11.2015.
 */


public class AttemptBuilder {

    private Long attemptId;

    private String login;

    private int attempts;

    private Date lastModified;

    private User user;

    public static AttemptBuilder createAttempt() {
        return new AttemptBuilder();
    }

    public Attempt build(){
        Attempt attempt = new Attempt();

        attempt.setAttemptId(attemptId);
        attempt.setLogin(login);
        attempt.setAttempts(attempts);
        attempt.setLastModified(lastModified);
        attempt.setUser(user);

        return attempt;
    }

    public AttemptBuilder withId(Long attemptId) {
        this.attemptId = attemptId;
        return this;
    }

    public AttemptBuilder withLogin(String login) {
        this.login = login;
        return this;
    }

    public AttemptBuilder withAttempts(int attempts) {
        this.attempts = attempts;
        return this;
    }

    public AttemptBuilder withDate(Date lastModified) {
        this.lastModified = lastModified;
        return this;
    }

    public AttemptBuilder withUser(User user) {
        this.user = user;
        return this;
    }
}

