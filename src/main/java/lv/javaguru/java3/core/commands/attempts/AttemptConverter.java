package lv.javaguru.java3.core.commands.attempts;

/**
 * Created by Anna on 13.11.2015.
 */
import lv.javaguru.java3.core.domain.attempt.Attempt;
import lv.javaguru.java3.core.dto.attempt.AttemptDTO;
import org.springframework.stereotype.Component;

import static lv.javaguru.java3.core.dto.attempt.AttemptDTOBuilder.createAttemptDTO;

@Component
public class AttemptConverter {

    public AttemptDTO convert(Attempt attempt) {
        return createAttemptDTO()
                .withId(attempt.getAttemptId())
                .withAll(attempt.getLogin(), attempt.getAttempts(), attempt.getLastModified())
                .build();
    }

}

