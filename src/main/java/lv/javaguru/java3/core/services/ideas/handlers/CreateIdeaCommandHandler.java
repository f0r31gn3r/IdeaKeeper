package lv.javaguru.java3.core.services.ideas.handlers;

/**
 * Created by Anna on 28.10.2015.
 */
import lv.javaguru.java3.core.commands.ideas.CreateIdeaCommand;
import lv.javaguru.java3.core.commands.ideas.CreateIdeaResult;
import lv.javaguru.java3.core.domain.idea.Idea;
import lv.javaguru.java3.core.services.DomainCommandHandler;
import lv.javaguru.java3.core.services.ideas.IdeaFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class CreateIdeaCommandHandler
        implements DomainCommandHandler<CreateIdeaCommand, CreateIdeaResult> {

    @Autowired
    private IdeaFactory ideaFactory;


    @Override
    public CreateIdeaResult execute(CreateIdeaCommand command) {
        Idea idea = ideaFactory.create(
                command.getTitle(),
                command.getDescription(),
                command.getUser()
        );
        return new CreateIdeaResult(idea);
    }

    @Override
    public Class getCommandType() {
        return CreateIdeaCommand.class;
    }

}
