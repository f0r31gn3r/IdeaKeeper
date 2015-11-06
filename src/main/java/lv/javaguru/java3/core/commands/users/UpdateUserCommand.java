package lv.javaguru.java3.core.commands.users;

import lv.javaguru.java3.core.commands.DomainCommand;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by Anna on 27.10.2015.
 */
public class UpdateUserCommand implements DomainCommand<UpdateUserResult> {

    private Long userId;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String accessLevel;

    public UpdateUserCommand(Long userId,
                             String login,
                             String password,
                             String name,
                             String surname,
                             String email,
                             String accessLevel) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.accessLevel = accessLevel;
    }

    public Long getUserId() {
        return userId;
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

    public String getEmail() {
        return email;
    }

    public String getAccessLevel() {
        return accessLevel;
    }
}
