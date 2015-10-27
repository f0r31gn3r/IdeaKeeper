package lv.javaguru.java3.core.services.users;

/**
 * Created by Anna on 27.10.2015.
 */
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang.StringUtils.isBlank;

@Component
class UserValidatorImpl implements UserValidator {

    @Override
    public void validate(String login, String password) {
        validateLogin(login);
        validatePassword(password);
    }

    private void validateLogin(String login) {
        checkNotNull(login, "User login must not be null");
        checkArgument(!isBlank(login), "User login must not be empty");
    }

    private void validatePassword(String password) {
        checkNotNull(password, "User password must not be null");
        checkArgument(!isBlank(password), "User password must not be empty");
    }

}
