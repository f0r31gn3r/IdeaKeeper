package lv.javaguru.java3.core.services.attempts;

/**
 * Created by Anna on 02.11.2015.
 */

import org.junit.Ignore;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

@Deprecated @Ignore
public class AttemptValidatorImplTest {

    private AttemptValidator validator = new AttemptValidatorImpl();

    private static final Long USERID = 5L;
    private static final String LOGIN = "login";
    private static final int ATTEMPTS = 3;
    private static final Date LASTMOD = new Date();

//    @Test
//    public void validateShouldFailIfLoginIsNull() {
//        validateShouldFail(USERID, LOGIN, ATTEMPTS, LASTMOD, "Client login must not be null");
//    }
//
//    @Test
//    public void validateShouldFailIfLoginIsEmpty() {
//        validateShouldFail(USERID, LOGIN, ATTEMPTS, LASTMOD, "Client login must not be empty");
//    }

    private void validateShouldFail(Long userId,
                                    String login,
                                    int attempts,
                                    Date lastModified,
                                    String errorMessage) {
        try {
            //validator.validate(userId, login, attempts, lastModified);
            fail();
        } catch (Exception e) {
            assertThat(e.getMessage(), is(errorMessage));
        }
    }

}

