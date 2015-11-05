package lv.javaguru.java3.core.commands.tasklists;

import lv.javaguru.java3.core.commands.DomainCommand;
import java.util.Date;

/**
 * Created by Fatum on 05-Nov-15.
 */
public class UpdateTaskListCommand implements DomainCommand<UpdateTaskListResult> {

    private Long taskListId;
    private Long userId;
    private Long taskId;
    private Date createdDate;
    private Date endDate;

    public UpdateTaskListCommand(Long taskListId,
                                 Long userId,
                                 Long taskId,
                                 Date createdDate,
                                 Date endDate){
        this.taskListId=taskListId;
        this.userId=userId;
        this.taskId=taskId;
        this.createdDate=createdDate;
        this.endDate=endDate;
    }

    public Long getTaskListId() {
        return taskListId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
