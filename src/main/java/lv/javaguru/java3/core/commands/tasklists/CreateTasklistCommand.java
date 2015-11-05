package lv.javaguru.java3.core.commands.tasklists;

import lv.javaguru.java3.core.commands.DomainCommand;

import java.util.Date;

/**
 * Created by Fatum on 05-Nov-15.
 */
public class CreateTaskListCommand implements DomainCommand<CreateTaskListResult> {

    private Long userId;
    private Long taskId;
    private Date createdDate;
    private Date endDate;

    public CreateTaskListCommand(Long userId,
                                 Long taskId,
                                 Date createdDate) {
        this.userId = userId;
        this.taskId = taskId;
        this.createdDate = createdDate;
    }

    public CreateTaskListCommand(Long userId,
                                 Long taskId,
                                 Date createdDate,
                                 Date endDate) {
        this.userId = userId;
        this.taskId = taskId;
        this.createdDate = createdDate;
        this.endDate = endDate;
    }

    public Long getUserId() {
        return this.userId;
    }
    public Long getTaskId() {
        return this.taskId;
    }
    public Date getCreatedDate() {
        return this.createdDate;
    }
    public Date getEndDate() {
        return this.endDate;
    }
}
