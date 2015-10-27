package lv.javaguru.java3.core.domain.user;

/**
 * Created by Anna on 27.10.2015.
 */
import javax.persistence.*;


@Entity
@Table(name="ideas0")
public class Idea0 {

    @Id
    @GeneratedValue(generator = "ideas_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "ideas_seq", sequenceName = "ideas_seq", allocationSize = 1)

    @Column(name="idea_id", nullable = false)
    private Long ideaId;

    @Column(name="user_id", nullable = false)
    private Long userId;

    public Long getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(Long ideaId) {
        this.ideaId = ideaId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
