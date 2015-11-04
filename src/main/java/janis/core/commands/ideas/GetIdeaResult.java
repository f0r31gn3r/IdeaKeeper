package janis.core.commands.ideas;

import lv.javaguru.java3.core.commands.DomainCommandResult;
import lv.javaguru.java3.core.domain.idea.Idea;

/**
 * Created by Anna on 28.10.2015.
 */
public class GetIdeaResult implements DomainCommandResult {

    private Idea idea;

    public GetIdeaResult(Idea idea) {
        this.idea = idea;
    }

    public Idea getIdea() {
        return idea;
    }

}

