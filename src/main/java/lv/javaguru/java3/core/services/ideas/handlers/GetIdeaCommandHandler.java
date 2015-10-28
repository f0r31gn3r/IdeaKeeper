package lv.javaguru.java3.core.services.ideas.handlers;

import lv.javaguru.java3.core.commands.ideas.GetIdeaCommand;
import lv.javaguru.java3.core.commands.ideas.GetIdeaResult;
import lv.javaguru.java3.core.commands.users.GetUserCommand;
import lv.javaguru.java3.core.commands.users.GetUserResult;
import lv.javaguru.java3.core.domain.idea.Idea;
import lv.javaguru.java3.core.domain.user.User;
import lv.javaguru.java3.core.services.DomainCommandHandler;
import lv.javaguru.java3.core.services.ideas.IdeaService;
import lv.javaguru.java3.core.services.users.UserService;
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


    @Override
    public GetIdeaResult execute(GetIdeaCommand command) {
        Idea idea = ideaService.get(command.getIdeaId());
        return new GetIdeaResult(idea);
    }

    @Override
    public Class getCommandType() {
        return GetIdeaCommand.class;
    }

}
