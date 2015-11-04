package lv.javaguru.java3.core.domain.user;

import lv.javaguru.java3.core.domain.attempt.Attempt;
import lv.javaguru.java3.core.domain.idea.Idea;

import java.util.List;

/**
 * Created by Anna on 26.10.2015.
 */
public class UserBuilder {

    private Long userId;

    private String login;

    private String password;

    private String name;

    private String surname;

    private String email;

    private String accessLevel;

    private List<Idea> ideas;

    private List<Activity> activities;

    private List<Attempt> attempts;

    public static UserBuilder createUser() {
        return new UserBuilder();
    }

    public User build(){
        User user = new User();

        user.setUserId(userId);
        user.setLogin(login);
        //user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setPassword(password);
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setAccessLevel(accessLevel);

        return user;
    }

    public UserBuilder withLogPas(String login, String password) {
        this.login = login;
        this.password = password;
        return this;
    }

    public UserBuilder withLogPasNamSur(String login, String password, String name, String surname) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        return this;
    }

    //Added!!!
    public UserBuilder withAll(String login, String password, String name, String surname, String email, String accessLevel) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.accessLevel = accessLevel;
        return this;
    }
}
