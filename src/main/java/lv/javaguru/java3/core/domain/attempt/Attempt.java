package lv.javaguru.java3.core.domain.attempt;

/**
 * Created by Anna on 02.11.2015.
 */
import javax.persistence.*;
import java.util.Date;

@Deprecated
@Entity
@Table(name="attempts")
public class Attempt {

//    @Id
//    @GeneratedValue(generator = "attempts_seq", strategy = GenerationType.SEQUENCE)
//    @SequenceGenerator(name = "attempts_seq", sequenceName = "attempts_seq", allocationSize = 1)
//    @Column(name="attempt_id", nullable = false)

    @Id
    @Column(name="attempt_id", columnDefinition = "int(11)")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long attemptId;

    @Column(name="user_id", columnDefinition = "int(11)", nullable = false)
    private Long userId;

    @Column(name="login", columnDefinition = "CHAR(50)")
    private String login;

    @Column(name="attempts_count", columnDefinition = "int(1)")
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
