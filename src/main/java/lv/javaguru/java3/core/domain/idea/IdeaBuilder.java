package lv.javaguru.java3.core.domain.idea;

/**
 * Created by Anna on 28.10.2015.
 */
public class IdeaBuilder {

    private Long ideaId;

    private String title;

    private String description;

    private Long userId;

    public static IdeaBuilder createIdea() {
        return new IdeaBuilder();
    }

    public Idea build(){
        Idea idea = new Idea();

        idea.setIdeaId(ideaId);
        idea.setTitle(title);
        idea.setDescription(description);
        idea.setUserId(userId);

        return idea;
    }

    public IdeaBuilder withTitle(String title, Long userId) {
        this.title = title;
        this.userId = userId;
        return this;
    }

    public IdeaBuilder withAll(String title, String description, Long userId) {
        this.title = title;
        this.description = description;
        this.userId = userId;
        return this;
    }
}
