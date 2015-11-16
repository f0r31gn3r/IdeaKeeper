package lv.javaguru.java3.core.domain.user;

/**
 * Created by Anna on 26.10.2015.
 */

import lv.javaguru.java3.core.domain.attempt.Attempt;
import lv.javaguru.java3.core.domain.idea.Idea;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="users")
public class User {

//    @Id
//    @GeneratedValue(generator = "users_seq", strategy = GenerationType.SEQUENCE)
//    @SequenceGenerator(name = "users_seq", sequenceName = "users_seq", allocationSize = 1)
//    @Column(name="user_id", nullable = false)

    @Id
    //@Column(name="user_id",columnDefinition = "int(11)")
    @Column(name="user_id", columnDefinition = "int(11)")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(name="login", columnDefinition = "CHAR(50)")
    //@Column(name="login")
    private String login;

    @Column(name="password", columnDefinition = "CHAR(150)")
    //@Column(name="password")
    private String password;

    @Column(name="name", columnDefinition = "CHAR(50)")
    //@Column(name="name")
    private String name;

    @Column(name="surname", columnDefinition = "CHAR(50)")
    //@Column(name="surname")
    private String surname;

    @Column(name="email", columnDefinition = "CHAR(50)")
    //@Column(name="email")
    private String email;

    @Column(name="access_level", columnDefinition = "CHAR(50)")
    //@Column(name="access_level")
    private String accessLevel;

//    @OneToMany(mappedBy = "ideaId", fetch = FetchType.EAGER,
//            cascade = CascadeType.REMOVE, orphanRemoval = true)

   // @OneToMany(mappedBy = "ideaId", fetch = FetchType.EAGER,
   //        cascade = CascadeType.REMOVE, orphanRemoval = true)

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Idea> ideas;

//    @OneToMany(mappedBy = "activityId", fetch = FetchType.LAZY,
//            cascade = CascadeType.REMOVE, orphanRemoval = true)
//    private List<Activity> activities;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Attempt> attempts;

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

    public Set<Idea> getIdeas() {
        return ideas;
    }

    public void setIdeas(Set<Idea> ideas) {
        this.ideas = ideas;
    }

//    public List<Activity> getActivities() {
//        return activities;
//    }
//
//    public void setActivities(List<Activity> activities) {
//        this.activities = activities;
//    }
//
    public Set<Attempt> getAttempts() {
        return attempts;
    }

    public void setAttempts(Set<Attempt> attempts) {
        this.attempts = attempts;
    }
}
