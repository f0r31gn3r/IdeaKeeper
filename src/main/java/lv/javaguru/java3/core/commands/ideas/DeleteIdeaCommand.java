package lv.javaguru.java3.core.commands.ideas;

import lv.javaguru.java3.core.commands.DomainCommand;

/**
 * Created by Anna on 27.11.2015.
 */
public class DeleteIdeaCommand implements DomainCommand<DeleteIdeaResult> {

    private Long ideaId;

    public DeleteIdeaCommand(Long ideaId) {
        this.ideaId = ideaId;
    }

    public Long getIdeaId() {
        return ideaId;
    }
}

