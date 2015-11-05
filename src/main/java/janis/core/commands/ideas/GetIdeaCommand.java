package janis.core.commands.ideas;

import lv.javaguru.java3.core.commands.DomainCommand;

/**
 * Created by Anna on 28.10.2015.
 */
public class GetIdeaCommand implements DomainCommand<GetIdeaResult> {

    private Long ideaId;

    public GetIdeaCommand(Long ideaId) {
        this.ideaId = ideaId;
    }

    public Long getIdeaId() {
        return ideaId;
    }

}
