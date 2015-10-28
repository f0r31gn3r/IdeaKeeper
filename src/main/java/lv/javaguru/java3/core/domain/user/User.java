package lv.javaguru.java3.core.domain.user;

/**
 * Created by Anna on 26.10.2015.
 */

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(generator = "users_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "users_seq", sequenceName = "users_seq", allocationSize = 1)

    @Column(name="user_id", nullable = false)
    private Long userId;

    @Column(name="login")
    private String login;

    @Column(name="password")
    private String password;

    @Column(name="name")
    private String name;

    @Column(name="surname")
    private String surname;

    @Column(name="email")
    private String email;

    @Column(name="access_level")
    private String accessLevel;

    @OneToMany(mappedBy = "ideaId", fetch = FetchType.EAGER,
            cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Idea0> ideas;

    @OneToMany(mappedBy = "activityId", fetch = FetchType.EAGER,
            cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Activity0> activities;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    public List<Idea0> getIdeas() {
        return ideas;
    }

    public void setIdeas(List<Idea0> ideas) {
        this.ideas = ideas;
    }

    public List<Activity0> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity0> activities) {
        this.activities = activities;
    }
}