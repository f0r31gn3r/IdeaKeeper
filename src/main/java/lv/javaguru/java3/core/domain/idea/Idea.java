package lv.javaguru.java3.core.domain.idea;

/**
 * Created by Anna on 28.10.2015.
 */

import lv.javaguru.java3.core.domain.user.User;

import javax.persistence.*;

@Entity
@Table(name="ideas")
public class Idea {

//    @Id
//    @GeneratedValue(generator = "ideas_seq", strategy = GenerationType.SEQUENCE)
//    @SequenceGenerator(name = "ideas_seq", sequenceName = "ideas_seq", allocationSize = 1)
//    @Column(name="idea_id", nullable = false)

    @Id
    @Column(name="idea_id",columnDefinition = "int(11)")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ideaId;

    @Column(name="title", columnDefinition = "CHAR(50)")
    private String title;

    @Column(name="description", columnDefinition = "CHAR(50)")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
