package lv.javaguru.java3.core.domain.attempt;

/**
 * Created by Anna on 02.11.2015.
 */
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="attempts")
public class Attempt {

    @Id
    @GeneratedValue(generator = "attempts_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "attempts_seq", sequenceName = "attempts_seq", allocationSize = 1)

    @Column(name="attempt_id", nullable = false)
    private Long attemptId;

    @Column(name="user_id", nullable = false)
    private Long userId;

    @Column(name="login")
    private String login;

    @Column(name="attempts_count")
    private int attempts;

    @Column(name = "last_modified", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;

    public Long getAttemptId() {
        return attemptId;
    }

    public void setAttemptId(Long attemptId) {
        this.attemptId = attemptId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
