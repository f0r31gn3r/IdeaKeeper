package lv.javaguru.java3.core.commands.ideas;

import lv.javaguru.java3.core.services.DomainCommandHandler;
import lv.javaguru.java3.core.services.ideas.IdeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Anna on 27.11.2015.
 */

@Component
public class DeleteIdeaCommandHandler implements DomainCommandHandler<DeleteIdeaCommand, DeleteIdeaResult> {

@Autowired
private IdeaService ideaService;

@Override
public DeleteIdeaResult execute(DeleteIdeaCommand command) {
        return new DeleteIdeaResult(ideaService.delete(command.getIdeaId()));
        }

@Override
public Class getCommandType() {
        return DeleteIdeaCommand.class;
}

        }