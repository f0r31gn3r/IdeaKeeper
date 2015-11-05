package lv.javaguru.java3.core.services.tasklists.handlers;

import lv.javaguru.java3.core.commands.tasklists.CreateTaskListCommand;
import lv.javaguru.java3.core.commands.tasklists.CreateTaskListResult;
import lv.javaguru.java3.core.domain.tasklist.TaskList;
import lv.javaguru.java3.core.services.DomainCommandHandler;
import lv.javaguru.java3.core.services.tasklists.TaskListFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 * Created by Fatum on 05-Nov-15.
 */
@Component
class CreateTaskListCommandHandler
        implements DomainCommandHandler<CreateTaskListCommand, CreateTaskListResult> {

    @Autowired
    private TaskListFactory taskListFactory;


    @Override
    public CreateTaskListResult execute(CreateTaskListCommand command) {
        TaskList taskList = taskListFactory.create(
                command.getUserId(),
                command.getTaskId(),
                command.getCreatedDate(),
                command.getEndDate()
        );
        return new CreateTaskListResult(taskList);
    }

    @Override
    public Class getCommandType() {
        return CreateTaskListCommand.class;
    }

}