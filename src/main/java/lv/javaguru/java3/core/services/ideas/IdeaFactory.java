package lv.javaguru.java3.core.services.ideas;

import lv.javaguru.java3.core.domain.idea.Idea;

/**
 * Created by Anna on 28.10.2015.
 */
public interface IdeaFactory {

    Idea create(String title, String description);

}
