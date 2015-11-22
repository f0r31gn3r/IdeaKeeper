package lv.javaguru.java3.core.commands.ideas;

import lv.javaguru.java3.core.domain.idea.Idea;
import lv.javaguru.java3.core.dto.idea.IdeaDTO;
import org.springframework.stereotype.Component;

import static lv.javaguru.java3.core.dto.idea.IdeaDTOBuilder.createIdeaDTO;

/**
 * Created by Anna on 22.11.2015.
 */
@Component

public class IdeaConverter {

    public IdeaDTO convert(Idea idea) {
        return createIdeaDTO()
                .withId(idea.getIdeaId())
                .withTitle(idea.getTitle())
                .withDescription(idea.getDescription())
                .withUser(idea.getUser())
                .build();
    }
}
