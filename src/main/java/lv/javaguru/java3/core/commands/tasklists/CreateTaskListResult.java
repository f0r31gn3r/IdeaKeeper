package lv.javaguru.java3.core.commands.tasklists;

import lv.javaguru.java3.core.commands.DomainCommandResult;
import lv.javaguru.java3.core.domain.tasklist.TaskList;

/**
 * Created by Fatum on 05-Nov-15.
 */
@Deprecated
public class CreateTaskListResult implements DomainCommandResult {

    private TaskList taskList;

    public CreateTaskListResult(TaskList taskList) {
        this.taskList = taskList;
    }

    public TaskList getTaskList() {
        return taskList;
    }


}
