package lv.javaguru.java3.core.commands.ideas;

import lv.javaguru.java3.core.commands.DomainCommand;
import lv.javaguru.java3.core.domain.user.User;

/**
 * Created by Anna on 28.10.2015.
 */
public class CreateIdeaCommand implements DomainCommand<CreateIdeaResult> {

    private String title;
    private String description;

    public CreateIdeaCommand(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public CreateIdeaCommand(String title, User user) {

        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

}
