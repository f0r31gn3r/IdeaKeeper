package lv.javaguru.java3.core.commands.ideas;

import lv.javaguru.java3.core.domain.idea.Idea;
import lv.javaguru.java3.core.dto.idea.IdeaDTO;
import lv.javaguru.java3.core.services.DomainCommandHandler;
import lv.javaguru.java3.core.services.ideas.IdeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Anna on 28.10.2015.
 */
@Component
class UpdateIdeaCommandHandler
        implements DomainCommandHandler<UpdateIdeaCommand, UpdateIdeaResult> {

    @Autowired
    private IdeaService ideaService;

    @Autowired private IdeaConverter ideaConverter;


    @Override
    public UpdateIdeaResult execute(UpdateIdeaCommand command) {
        Idea idea = ideaService.update(
                command.getIdeaId(),
                command.getTitle(),
                command.getDescription()
        );
        IdeaDTO ideaDTO = ideaConverter.convert(idea);
        return new UpdateIdeaResult(ideaDTO);
    }

    @Override
    public Class getCommandType() {
        return UpdateIdeaCommand.class;
    }

}
