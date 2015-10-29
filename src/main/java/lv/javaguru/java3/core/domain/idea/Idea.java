package lv.javaguru.java3.core.domain.idea;

/**
 * Created by Anna on 28.10.2015.
 */

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="ideas")
public class Idea {

    @Id
    @GeneratedValue(generator = "ideas_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "ideas_seq", sequenceName = "ideas_seq", allocationSize = 1)

    @Column(name="idea_id", nullable = false)
    private Long ideaId;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="user_id", nullable = false)
    private Long userId;

    public Long getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(Long ideaId) {
        this.ideaId = ideaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
