package lv.javaguru.java3.core.commands.users;

import lv.javaguru.java3.core.commands.DomainCommand;

/**
 * Created by Anna on 27.10.2015.
 */
public class CreateUserCommand implements DomainCommand<CreateUserResult> {

    private String login;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String accessLevel;


    public CreateUserCommand(String login,
                             String password) {
        this.login = login;
        this.password = password;
    }

    public CreateUserCommand(String login,
                             String password,
                             String name,
                             String surname) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public CreateUserCommand(String login,
                             String password,
                             String name,
                             String surname,
                             String email,
                             String accessLevel) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.accessLevel = accessLevel;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() { return email; }

    public String getAccessLevel() { return accessLevel; }
}
