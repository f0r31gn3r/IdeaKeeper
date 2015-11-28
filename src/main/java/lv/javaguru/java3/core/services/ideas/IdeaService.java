package lv.javaguru.java3.core.services.ideas;

import lv.javaguru.java3.core.domain.idea.Idea;

/**
 * Created by Anna on 28.10.2015.
 */
public interface IdeaService {

    Idea update(Long ideaId,
                String title,
                String description);

    Idea get(Long ideaId);

    int delete(Long ideaId);
}
