package lv.javaguru.java3.core.commands.tasklists;

import lv.javaguru.java3.core.commands.DomainCommand;

/**
 * Created by Fatum on 05-Nov-15.
 */
@Deprecated
public class GetTaskListCommand implements DomainCommand<GetTaskListResult> {

    private Long taskListId;

    public GetTaskListCommand(Long taskListId) {
        this.taskListId = taskListId;
    }

    public Long getTaskListId() {
        return taskListId;
    }

}