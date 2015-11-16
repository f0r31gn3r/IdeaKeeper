package lv.javaguru.java3.core.commands.ideas;

import lv.javaguru.java3.core.commands.DomainCommand;

/**
 * Created by Anna on 28.10.2015.
 */
public class UpdateIdeaCommand implements DomainCommand<UpdateIdeaResult> {

    private Long ideaId;
    private String title;
    private String description;

    public UpdateIdeaCommand(Long ideaId,
                             String title,
                             String description) {
        this.ideaId = ideaId;
        this.title = title;
        this.description = description;
    }

    public Long getIdeaId() {
        return ideaId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }


}
