package lv.javaguru.java3.core.services.ideas;

/**
 * Created by Anna on 28.10.2015.
 */

import lv.javaguru.java3.core.database.IdeaDAO;
import lv.javaguru.java3.core.database.UserDAO;
import lv.javaguru.java3.core.domain.idea.Idea;
import lv.javaguru.java3.core.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static lv.javaguru.java3.core.domain.idea.IdeaBuilder.createIdea;

@Component
class IdeaFactoryImpl implements IdeaFactory {

    @Autowired private IdeaValidator ideaValidator;
    @Autowired private IdeaDAO ideaDAO;
    @Autowired private UserDAO userDAO;


    @Override
    public Idea create(String title, String description, Long userId) {
        User userFromDB = userDAO.getById(userId);

        if(userFromDB != null){
            ideaValidator.validate(title, description);
            Idea idea = createIdea()
                    .withTitle(title)
                    .withDescription(description)
                    .withUser(userFromDB)
                    .build();
            ideaDAO.create(idea);
            return idea;
        } else {
            return null;
        }
    }

}
