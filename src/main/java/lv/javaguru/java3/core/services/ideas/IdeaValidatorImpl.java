package lv.javaguru.java3.core.services.ideas;

/**
 * Created by Anna on 28.10.2015.
 */
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang.StringUtils.*;

@Component
class IdeaValidatorImpl implements IdeaValidator {

    @Override
    public void validate(String title, String description, Long userId) {
        validateTitle(title);
        validateDescription(description);
    }

    private void validateTitle(String title) {
        checkNotNull(title, "Idea title must not be null");
        checkArgument(!isBlank(title), "Idea title must not be empty");
    }

    private void validateDescription(String description) {
        checkNotNull(description, "Idea description must not be null");
        checkArgument(!isBlank(description), "Idea description must not be empty");
    }

    //TODO: validation for userId
}
