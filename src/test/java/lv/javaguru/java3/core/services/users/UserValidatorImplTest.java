package lv.javaguru.java3.core.services.users;

/**
 * Created by Anna on 27.10.2015.
 */
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class UserValidatorImplTest {

    private UserValidator validator = new UserValidatorImpl();

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";


    @Test
    public void validateShouldFailIfLoginIsNull() {
        validateShouldFail(null, PASSWORD, "User login must not be null");
    }

    @Test
    public void validateShouldFailIfPasswordIsNull() {
        validateShouldFail(LOGIN, null, "User password must not be null");
    }

    @Test
    public void validateShouldFailIfLoginIsEmpty() {
        validateShouldFail("", PASSWORD, "User login must not be empty");
    }

    @Test
    public void validateShouldFailIfPasswordIsEmpty() {
        validateShouldFail(LOGIN, "", "User password must not be empty");
    }

    private void validateShouldFail(String login,
                                    String password,
                                    String errorMessage) {
        try {
            validator.validate(login, password);
            fail();
        } catch (Exception e) {
            assertThat(e.getMessage(), is(errorMessage));
        }
    }

}

