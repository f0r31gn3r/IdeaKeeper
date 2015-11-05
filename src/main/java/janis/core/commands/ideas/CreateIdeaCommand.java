package janis.core.commands.ideas;

import lv.javaguru.java3.core.commands.DomainCommand;

/**
 * Created by Anna on 28.10.2015.
 */
public class CreateIdeaCommand implements DomainCommand<CreateIdeaResult> {

    private String title;
    private String description;
    private Long userId;

    public CreateIdeaCommand(String title,
                             String description,
                             Long userId) {
        this.title = title;
        this.description = description;
        this.userId = userId;
    }

    public CreateIdeaCommand(String title,
                             Long userId) {
        this.title = title;
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Long getUserId() {
        return userId;
    }
}
