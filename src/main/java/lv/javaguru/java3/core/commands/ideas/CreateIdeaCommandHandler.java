package lv.javaguru.java3.core.commands.ideas;

/**
 * Created by Anna on 28.10.2015.
 */

import lv.javaguru.java3.core.domain.idea.Idea;
import lv.javaguru.java3.core.dto.idea.IdeaDTO;
import lv.javaguru.java3.core.services.DomainCommandHandler;
import lv.javaguru.java3.core.services.ideas.IdeaFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class CreateIdeaCommandHandler
        implements DomainCommandHandler<CreateIdeaCommand, CreateIdeaResult> {

    @Autowired  private IdeaFactory ideaFactory;
    @Autowired  private IdeaConverter ideaConverter;


    @Override
    public CreateIdeaResult execute(CreateIdeaCommand command) {
        Idea idea = ideaFactory.create(
                command.getTitle(),
                command.getDescription(),
                command.getUserId()
        );
        IdeaDTO ideaDTO = ideaConverter.convert(idea);
        return new CreateIdeaResult(ideaDTO);
    }

    @Override
    public Class getCommandType() {
        return CreateIdeaCommand.class;
    }

}
