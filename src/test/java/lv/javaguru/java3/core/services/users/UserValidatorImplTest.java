package lv.javaguru.java3.core.services.users;

/**
 * Created by Anna on 27.10.2015.
 */
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class UserValidatorImplTest {

    private UserValidator validator = new UserValidatorImpl();

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String EMAIL = "email";
    private static final String ACCESSLEVEL = "accesslevel";

    @Test
    public void validateShouldFailIfLoginIsNull() {
        validateShouldFail(null, PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL, "User login must not be null");
    }

    @Test
    public void validateShouldFailIfPasswordIsNull() {
        validateShouldFail(LOGIN, null, NAME, SURNAME, EMAIL, ACCESSLEVEL, "User password must not be null");
    }

    @Test
    public void validateShouldFailIfLoginIsEmpty() {
        validateShouldFail("", PASSWORD, NAME, SURNAME, EMAIL, ACCESSLEVEL, "User login must not be empty");
    }

    @Test
    public void validateShouldFailIfPasswordIsEmpty() {
        validateShouldFail(LOGIN, "", NAME, SURNAME, EMAIL, ACCESSLEVEL, "User password must not be empty");
    }

    @Test
    public void validateShouldFailIfNameContainsSymbols() {
        validateShouldFail(LOGIN, PASSWORD, "name&^%", SURNAME, EMAIL, ACCESSLEVEL, "User name must not contain symbols");
    }

    @Test
    public void validateShouldFailIfNameContainsNumbers() {
        validateShouldFail(LOGIN, PASSWORD, "name123", SURNAME, EMAIL, ACCESSLEVEL, "User name must not contain numbers");
    }

    @Test
    public void validateShouldFailIfSurnameContainsSymbols() {
        validateShouldFail(LOGIN, PASSWORD, NAME, "surname$$$", EMAIL, ACCESSLEVEL, "User surname must not contain symbols");
    }

    @Test
    public void validateShouldFailIfSurameContainsNumbers() {
        validateShouldFail(LOGIN, PASSWORD, NAME, "surname123", EMAIL, ACCESSLEVEL, "User surname must not contain numbers");
    }

    @Test
    public void validateShouldFailIfEmailTemplate() {
        validateShouldFail(LOGIN, PASSWORD, NAME, SURNAME, "aaaaa", ACCESSLEVEL, "User email must match template email@email.com");
    }



    private void validateShouldFail(String login,
                                    String password,
                                    String name,
                                    String surname,
                                    String email,
                                    String accesLevel,
                                    String errorMessage) {
        try {
            validator.validate(login, password, name, surname, email, accesLevel);
            fail();
        } catch (Exception e) {
            assertThat(e.getMessage(), is(errorMessage));
        }
    }

}

