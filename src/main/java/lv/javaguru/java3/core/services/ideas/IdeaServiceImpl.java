package lv.javaguru.java3.core.services.ideas;

import lv.javaguru.java3.core.database.IdeaDAO;
import lv.javaguru.java3.core.domain.idea.Idea;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Anna on 28.10.2015.
 */
public class IdeaServiceImpl implements IdeaService {

    @Autowired private IdeaDAO ideaDAO;
    @Autowired private IdeaValidator ideaValidator;


    @Override
    public Idea update(Long ideaId,
                       String newTitle,
                       String newDescription) {
        ideaValidator.validate(newTitle, newDescription);
        Idea idea = get(ideaId);

        idea.setTitle(newTitle);
        idea.setDescription(newDescription);
        return idea;
    }

    @Override
    public Idea get(Long ideaId) {
        return ideaDAO.getRequired(ideaId);
    }
}
