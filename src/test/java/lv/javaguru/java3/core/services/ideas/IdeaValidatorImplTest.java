package lv.javaguru.java3.core.services.ideas;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by Anna on 29.10.2015.
 */
public class IdeaValidatorImplTest {

    private IdeaValidator validator = new IdeaValidatorImpl();

    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";

    @Test
    public void validateShouldFailIfTitleIsNull() {
        validateShouldFail(null, DESCRIPTION, "Idea title must not be null");
    }

    @Test
    public void validateShouldFailIfDescriptionIsNull() {
        validateShouldFail(TITLE, null, "Idea description must not be null");
    }

    @Test
    public void validateShouldFailIfTitleIsEmpty() {
        validateShouldFail("", DESCRIPTION, "Idea title must not be empty");
    }

    @Test
    public void validateShouldFailIfDescriptionIsEmpty() {
        validateShouldFail(TITLE, "", "Idea description must not be empty");
    }

    private void validateShouldFail(String title,
                                    String description,
                                    String errorMessage) {
        try {
            validator.validate(title, description);
            fail();
        } catch (Exception e) {
            assertThat(e.getMessage(), is(errorMessage));
        }
    }

}
