package lv.javaguru.java3.core.commands.ideas;

import lv.javaguru.java3.core.commands.attempts.GetAttemptResult;
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
class GetIdeaCommandHandler
        implements DomainCommandHandler<GetIdeaCommand, GetIdeaResult> {

    @Autowired
    private IdeaService ideaService;
    @Autowired  private IdeaConverter ideaConverter;



    @Override
    public GetIdeaResult execute(GetIdeaCommand command) {
        Idea idea = ideaService.get(command.getIdeaId());
        IdeaDTO ideaDTO = ideaConverter.convert(idea);
        return new GetIdeaResult(ideaDTO);
    }

    @Override
    public Class getCommandType() {
        return GetIdeaCommand.class;
    }

}
