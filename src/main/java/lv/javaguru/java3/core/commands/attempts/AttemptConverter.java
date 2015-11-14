package lv.javaguru.java3.core.commands.attempts;

/**
 * Created by Anna on 13.11.2015.
 */
import static lv.javaguru.java3.core.dto.attempt.AttemptDTOBuilder.createAttemptDTO;

import org.springframework.stereotype.Component;

import lv.javaguru.java3.core.domain.attempt.Attempt;
import lv.javaguru.java3.core.domain.user.User;
import lv.javaguru.java3.core.dto.attempt.AttemptDTO;
import lv.javaguru.java3.core.dto.user.UserDTO;

@Component
public class AttemptConverter {
	
	public AttemptDTO convert(Attempt attempt) {
        return createAttemptDTO()
        		.withId(attempt.getAttemptId())
                .withAll(attempt.getUserId(), attempt.getLogin(), attempt.getAttempts(), attempt.getLastModified())
                .build();
    }

}
