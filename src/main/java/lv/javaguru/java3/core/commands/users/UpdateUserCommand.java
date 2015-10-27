package lv.javaguru.java3.core.commands.users;

import lv.javaguru.java3.core.commands.DomainCommand;

/**
 * Created by Anna on 27.10.2015.
 */
public class UpdateUserCommand implements DomainCommand<UpdateUserResult> {

    private Long userId;

    private String login;
    private String password;


    public UpdateUserCommand(Long userId,
                             String login,
                             String password) {
        this.userId = userId;
        this.login = login;
        this.password = password;
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

}
