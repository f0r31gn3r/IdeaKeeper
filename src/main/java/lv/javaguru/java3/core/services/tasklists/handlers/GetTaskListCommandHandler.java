package lv.javaguru.java3.core.services.tasklists.handlers;

import lv.javaguru.java3.core.commands.tasklists.GetTaskListCommand;
import lv.javaguru.java3.core.commands.tasklists.GetTaskListResult;
import lv.javaguru.java3.core.domain.tasklist.TaskList;
import lv.javaguru.java3.core.services.DomainCommandHandler;
import lv.javaguru.java3.core.services.tasklists.TaskListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Fatum on 05-Nov-15.
 */
@Deprecated
@Component
class GetTaskListCommandHandler
        implements DomainCommandHandler<GetTaskListCommand, GetTaskListResult> {

    @Autowired
    private TaskListService taskListService;


    @Override
    public GetTaskListResult execute(GetTaskListCommand command) {
        TaskList taskList = taskListService.get(command.getTaskListId());
        return new GetTaskListResult(taskList);
    }

    @Override
    public Class getCommandType() {
        return GetTaskListCommand.class;
    }

}
