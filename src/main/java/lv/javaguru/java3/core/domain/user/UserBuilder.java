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

    public UserBuilder withId(Long userId) {
        this.userId = userId;
        return this;
    }

    public UserBuilder withLogin(String login) {
        this.login = login;
        return this;
    }

    public UserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder withSurname (String surname) {
        this.surname = surname;
        return this;
    }

    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder withAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
        return this;
    }
}
