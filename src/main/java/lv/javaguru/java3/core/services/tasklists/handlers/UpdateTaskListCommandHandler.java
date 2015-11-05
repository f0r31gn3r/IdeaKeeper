package lv.javaguru.java3.core.services.tasklists.handlers;

import lv.javaguru.java3.core.commands.tasklists.UpdateTaskListCommand;
import lv.javaguru.java3.core.commands.tasklists.UpdateTaskListResult;
import lv.javaguru.java3.core.domain.tasklist.TaskList;
import lv.javaguru.java3.core.services.DomainCommandHandler;
import lv.javaguru.java3.core.services.tasklists.TaskListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Fatum on 05-Nov-15.
 */
@Component
class UpdateTaskListCommandHandler
        implements DomainCommandHandler<UpdateTaskListCommand, UpdateTaskListResult> {

    @Autowired
    private TaskListService taskListService;


    @Override
    public UpdateTaskListResult execute(UpdateTaskListCommand command) {
        TaskList taskList = taskListService.update(
                command.getTaskListId(),
                command.getUserId(),
                command.getTaskId(),
                command.getCreatedDate(),
                command.getEndDate()
        );
        return new UpdateTaskListResult(taskList);
    }

    @Override
    public Class getCommandType() {
        return UpdateTaskListCommand.class;
    }

}

