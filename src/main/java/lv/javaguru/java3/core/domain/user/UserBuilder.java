package lv.javaguru.java3.core.domain.user;

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

    private List<String> ideas;

    private List<String> activities;

    public UserBuilder() {
    }

    public static UserBuilder createUser() {
        return new UserBuilder();
    }

    public User build(){
        User user = new User();

        user.setUserId(userId);
        user.setLogin(login);
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
}
