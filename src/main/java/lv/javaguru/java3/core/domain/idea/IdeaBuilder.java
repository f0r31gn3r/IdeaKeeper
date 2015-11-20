package lv.javaguru.java3.core.domain.idea;

import lv.javaguru.java3.core.domain.user.User;

/**
 * Created by Anna on 28.10.2015.
 */
public class IdeaBuilder {

    private Long ideaId;

    private String title;

    private String description;

    private User user;

    public static IdeaBuilder createIdea() {
        return new IdeaBuilder();
    }

    public Idea build(){
        Idea idea = new Idea();

        idea.setIdeaId(ideaId);
        idea.setTitle(title);
        idea.setDescription(description);
        return idea;
    }

    public IdeaBuilder withId(Long id) {
        this.ideaId = id;
        return this;
    }

    public IdeaBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public IdeaBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public IdeaBuilder withUser(User user) {
        this.user = user;
        return this;
    }
}
