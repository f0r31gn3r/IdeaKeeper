package lv.javaguru.java3.core.services.users;

/**
 * Created by Anna on 27.10.2015.
 */

import lv.javaguru.java3.core.domain.user.AccessLevel;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang.StringUtils.containsAny;
import static org.apache.commons.lang.StringUtils.isBlank;

@Component
class UserValidatorImpl implements UserValidator {

    @Override
    public void validate(String login, String password, String name, String surname, String email, String accessLevel) {
        validateLogin(login);
        validatePassword(password);
        validateName(name);
        validateSurname(surname);
        validateEmail(email);
        validateAccessLevel(accessLevel);
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
        checkArgument(!(name.length() > 0 && name.trim().length()==0), "User name must not contain all spaces");
    }

    private void validateSurname(String surname) {
        checkArgument(!containsAny(surname, "!?$%^<>*()#~/?&|"), "User surname must not contain symbols");
        checkArgument(!containsAny(surname, "1234567890"), "User surname must not contain numbers");
        checkArgument(!(surname.length() > 0 && surname.trim().length()==0), "User surname must not contain all spaces");
    }

    private void validateEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);

        checkArgument(matcher.matches(), "User email must match template email@email.com");
    }

    private void validateAccessLevel(String accessLevel) {
        checkArgument(accessLevel.toUpperCase().equals(AccessLevel.USER.name())
                        || accessLevel.toUpperCase().equals(AccessLevel.VIP.name())
                        || accessLevel.toUpperCase().equals(AccessLevel.BLOCKED.name()),
                "User must have defined acccess level");
    }
}
