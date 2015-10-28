package lv.javaguru.java3.core.domain.idea;

/**
 * Created by Anna on 28.10.2015.
 */
public class IdeaBuilder {

    private Long ideaId;

    private String title;

    private String description;

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

    public IdeaBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public IdeaBuilder withAll(String title, String description) {
        this.title = title;
        this.description = description;
        return this;
    }
}
