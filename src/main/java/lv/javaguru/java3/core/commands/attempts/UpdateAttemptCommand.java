package lv.javaguru.java3.core.commands.attempts;

/**
 * Created by Anna on 02.11.2015.
 */
import lv.javaguru.java3.core.commands.DomainCommand;

import java.util.Date;


public class UpdateAttemptCommand implements DomainCommand<UpdateAttemptResult> {

    private Long attemptId;

    private String login;

    private int attempts;

    private Date lastModified;

    public UpdateAttemptCommand(Long attemptId, String login, int attempts, Date lastModified) {
        this.attemptId = attemptId;
        this.login = login;
        this.attempts = attempts;
        this.lastModified = lastModified;
    }

    public Long getAttemptId() {
        return attemptId;
    }

    public void setAttemptId(Long attemptId) {
        this.attemptId = attemptId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }


}
