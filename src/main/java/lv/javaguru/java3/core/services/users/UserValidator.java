package lv.javaguru.java3.core.services.users;

/**
 * Created by Anna on 27.10.2015.
 */
public interface UserValidator {

    void validate(String login, String password, String name, String surname, String email, String accessLevel);

    void validateName(String name);

    void validateEmail(String email);

}
