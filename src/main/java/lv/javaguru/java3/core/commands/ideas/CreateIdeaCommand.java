package lv.javaguru.java3.core.commands.ideas;

import lv.javaguru.java3.core.commands.DomainCommand;
import lv.javaguru.java3.core.domain.user.User;

/**
 * Created by Anna on 28.10.2015.
 */
public class CreateIdeaCommand implements DomainCommand<CreateIdeaResult> {

    private String title;
    private String description;
    private User user;

    public CreateIdeaCommand(String title, String description, User user) {
        this.title = title;
        this.description = description;
        this.user = user;
    }

    public CreateIdeaCommand(String title, User user) {

        this.title = title;
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public User getUser() {
        return user;
    }
}
