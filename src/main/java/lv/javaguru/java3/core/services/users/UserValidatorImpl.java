package lv.javaguru.java3.core.services.users;

/**
 * Created by Anna on 27.10.2015.
 */
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang.StringUtils.*;

@Component
class UserValidatorImpl implements UserValidator {

    @Override
    public void validate(String login, String password, String name, String surname, String email, String accessLevel) {
        validateLogin(login);
        validatePassword(password);
        validateName(name);
        validateSurname(surname);
        validateEmail(email);
    }

    private void validateLogin(String login) {
        checkNotNull(login, "User login must not be null");
        checkArgument(!isBlank(login), "User login must not be empty");
    }

    private void validatePassword(String password) {
        checkNotNull(password, "User password must not be null");
        checkArgument(!isBlank(password), "User password must not be empty");
    }

    private void validateName(String name) {
        checkArgument(!containsAny(name, "!?$%^<>*()#~/?&|"), "User name must not contain symbols");
        checkArgument(!containsAny(name, "1234567890"), "User name must not contain numbers");
    }

    private void validateSurname(String surname) {
        checkArgument(!containsAny(surname, "!?$%^<>*()#~/?&|"), "User surname must not contain symbols");
        checkArgument(!containsAny(surname, "1234567890"), "User surname must not contain numbers");
    }

    private void validateEmail(String email) {
        String EMAIL_REGEX = "\\S+@\\S+\\.\\S+/";
        checkArgument(email.matches(EMAIL_REGEX), "User email must match template email@email.com");
    }

}
